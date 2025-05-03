package es.udc.fic.xpn.app.controller;

import es.udc.fic.xpn.app.dto.ProductDto;

import es.udc.fic.xpn.app.mapper.ProductMapper;
import es.udc.fic.xpn.app.model.Product;
import es.udc.fic.xpn.app.service.ProductService;

import es.udc.fic.xpn.app.model.Stock;
import es.udc.fic.xpn.app.service.StockService;

import es.udc.fic.xpn.app.model.Almacen;
import es.udc.fic.xpn.app.service.AlmacenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Comparator;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AlmacenService almacenService;

    @Autowired
    private StockService stockService;

    public ResponseEntity<String> obtenerNombre(Long idAlmacen) {
        Optional<Almacen> a = almacenService.findById(idAlmacen);
        if (!a.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imposible obtener el nombre de almacén");
        return ResponseEntity.ok(a.get().getNombre());
    }

    @GetMapping("/findDestinations")
    public ResponseEntity<String> findDestinations (@RequestParam("sku") String sku, @RequestParam("almacen") String almacen) {
        // Obtener el producto (para saber el productId)
        Optional<Product> p = productService.find(sku);
        if (!p.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        
        // Obtener la lista de stock del producto
        List<Stock> stocks = stockService.findStocks(p.get().getId());
        if (stocks == null || stocks.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado en ningún almacén");
    
        // Crear una lista de idAlmacen ordenada de menor a mayor stock
        List<Long> almacenes = stocks.stream()
                .sorted(Comparator.comparing(Stock::getStock))
                .map(Stock::getIdAlmacen)
                .collect(Collectors.toList());
        
        // Obtener el almacen (para saber el idAlmacen)
        Optional<Almacen> a = almacenService.find(almacen);
        if (!a.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Almacen no encontrado");
        
        // Devolver el almacen con menor stock
        if (almacenes.get(0).equals(a.get().getId())) { // Si el que tiene menor stock es el propio almacen
            if (almacenes.size() == 1) // Si solo hay 1 almacen con ese producto, error
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado en ningún otro almacén");
            return obtenerNombre(almacenes.get(1)); // Si no, devolver el siguiente
        }
        return obtenerNombre(almacenes.get(0)); // Devolver el primero
    }
    

    private ResponseEntity<Void> createStock (Long cantidad, Long idAlmacen, Long idProduct) {
        // Crear el stock
        stockService.save(new Stock(cantidad, idAlmacen, idProduct));

        // Devolver el código 201 (Created)
        return new ResponseEntity<>(HttpStatus.CREATED);
    } 


    private ResponseEntity<Void> createProduct(ProductDto productDto, Long idAlmacen) {
        // Crear el producto
        ProductDto p = ProductMapper.entityToDto(
                productService.create(ProductMapper.dtoToEntity(productDto)));

        // Crear el stock
        return createStock(productDto.getCantidad(), idAlmacen, p.getId());
    }

    private ResponseEntity<Void> update(Stock stock, Long cantidad) {
        // Actualizar la cantidad del stock actual
        stock.setStock(stock.getStock() + cantidad);
        stockService.update(stock);

        // Devuelve el código 200 (OK) con el DTO
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> updateStock (@RequestBody ProductDto productDto) {
        // Obtener el almacen.
        Optional<Almacen> a = almacenService.find(productDto.getAlmacen());
        
        // Si el no existe, no se crean nuevos almacenes.
        if (!a.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        // Si existe, obtener el almacen.
        Almacen almacen = a.get();
        
        // Obtener el producto a través del SKU.
        Optional<Product> p = productService.find(productDto.getSku());
        // Si no existe, se crea uno nuevo.
        if (!p.isPresent())
            return createProduct(productDto, almacen.getId());
        // Si existe, se obtiene el Producto.
        Product product = p.get();

        Long idAlmacen = almacen.getId();
        Long idProduct = product.getId();

        // Buscar el stock del producto en el almacen.
        Optional<Stock> s = stockService.find(idProduct, idAlmacen);
        // Si no existe, se crea el stock con la cantidad especificada.
        if (!s.isPresent())
            return createStock(productDto.getCantidad(), idAlmacen, idProduct);
        // Si existe, se actualiza.
        return update(s.get(), productDto.getCantidad());
    }

    @GetMapping("/overStock")
    public ResponseEntity<?> overStock (@RequestParam(value="sku") String sku, @RequestParam(value="almacen") String almacen) {
        return checkStock(sku, almacen, true);
    }

    @GetMapping("/underStock")
    public ResponseEntity<?> underStock (@RequestParam(value="sku") String sku, @RequestParam(value="almacen") String almacen) {
        return checkStock(sku, almacen, false);
    }

    private ResponseEntity<?> checkStock (String sku, String almacen, boolean max) {
        // Obtener el producto por SKU (para saber si id).
        Optional<Product> p = productService.find(sku);
        if (!p.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        
        // Si existe, obtener el almacen (para saber si id).
        Optional<Almacen> a = almacenService.find(almacen);
        if (!a.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Almacen no encontrado");
        
        // Obtener el stock para esos almacenes.
        Optional<Stock> s = stockService.find(p.get().getId(), a.get().getId());
        if (!s.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock no encontrado");
        
        // Si existe, obtener el stock.
        Stock stock = s.get();

        // Devolver la comparación del stock
        boolean result = max ? stock.isOverStock() : stock.isUnderStock();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}