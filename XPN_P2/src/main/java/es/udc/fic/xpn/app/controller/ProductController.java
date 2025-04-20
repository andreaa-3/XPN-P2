package es.udc.fic.xpn.app.controller;

import es.udc.fic.xpn.app.dto.ProductDto;
import es.udc.fic.xpn.app.mapper.ProductMapper;
import es.udc.fic.xpn.app.model.Product;
import es.udc.fic.xpn.app.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/{sku}")
    public List<String> findDestinations (@PathVariable String sku) {
        // Obtener la lista de productos con ese SKU
        List<Product> products = productService.findBySku(sku);

        // Comprobar si la lista no está vacía
        if (products == null || products.isEmpty()){
            return null;
        }

        // Devuelve la lista ordenada por menor a mayor stock
        return products.stream().sorted(Comparator.comparing(Product::getStock)).map(Product::getAlmacen).toList();
    }


    private ResponseEntity<Void> create(ProductDto productDto) {
        // Crear el producto
        productService.create(ProductMapper.dtoToEntity(productDto));

        // Devolver el código 201 (Created)
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    

    private ResponseEntity<Void> update(Product product) {
        if (productService.findById(product.getId()).isPresent()) {
            productService.update(product);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Void> updateStock (@RequestBody ProductDto productDto) {
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
 }