package es.udc.fic.xpn.app.controller;

import es.udc.fic.xpn.app.dto.ProductDto;
import es.udc.fic.xpn.app.mapper.ProductMapper;
import es.udc.fic.xpn.app.model.Product;
import es.udc.fic.xpn.app.model.Stock;
import es.udc.fic.xpn.app.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.Comparator;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /*
    @GetMapping
    private List<ProductDto> findAll() {
        List<Product> products = productService.findAll();

        return products.stream().map(ProductMapper::entityToDto).
                collect(Collectors.toList());
    }
    */

    @GetMapping("/findDestinations")
    public String findDestinations (@RequestParam(value="sku") String sku, @RequestParam(value="almacen") String almacen) {
        //getBySKU () ->  Lista<Product>
        List<Product> products = productService.findBySku(sku);
        // movidas de comprobar vacío
        if(products == null || products.isEmpty()){
            return null;
        }
        // todo OK -> recorres la lista y miras los stocks
        // return lista ordenada por menor a myor stock
        List<String> almacenes = products.stream().sorted(Comparator.comparing(Stock::getStock)).map(Product::getAlmacen).toList();

        if(almacenes.get(0) == almacen){
            return almacenes.get(1);
        }

        return almacenes.get(0);
    }


    private ResponseEntity<ProductDto> create(ProductDto productDto) {
        // Crear el producto
        ProductDto p = ProductMapper.entityToDto(
                productService.create(ProductMapper.dtoToEntity(productDto)));

        // Devolver el código 201 (Created)
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }
    

    private ResponseEntity<ProductDto> update(Product product) {
        productService.update(product);

        // Devuelve el código 200 (OK) con el DTO
        ProductDto p = ProductMapper.entityToDto(product);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDto> updateStock (@RequestBody ProductDto productDto) {
        // Buscar si el producto existe en el almacen a través del SKU
        Optional<Product> product = productService.findBySkuCity(productDto.getAlmacen(), productDto.getSku());

        // Si no existe, se crea
        if (!product.isPresent())
            return create(productDto);

        // Si existe, se actualiza el stock.
        Product p = product.get();
        p.setStock(p.getStock() + productDto.getCantidad());
        return update(p);
    }

    @GetMapping("/overStock")
    public boolean overStock (@RequestParam(value="sku") String sku, @RequestParam(value="almacen") String almacen) {
        return checkStock(sku, almacen, true);
    }

    @GetMapping("/underStock")
    public boolean underStock (@RequestParam(value="sku") String sku, @RequestParam(value="almacen") String almacen) {
        return checkStock(sku, almacen, false);
    }

    private boolean checkStock (String sku, String almacen, boolean max) {
        Optional<Product> product = productService.findBySkuCity(almacen, sku);

        // Si no existe, es error
        if (!product.isPresent())
            return false;

        // Devuelve la comparación del stock
        Product p = product.get();
        return max ? p.getStock() > p.getMaxStock() : p.getStock() < p.getMinStock();
    }
 }