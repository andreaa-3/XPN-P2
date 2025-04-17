package es.udc.fic.xpn.example.controller;

import es.udc.fic.xpn.example.dto.ProductDto;
import es.udc.fic.xpn.example.mapper.ProductMapper;
import es.udc.fic.xpn.example.model.Product;
import es.udc.fic.xpn.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        //getBySKU () ->  Lista<Product>
        // movidas de comprobar vacío
        // todo OK -> recorres la lista y miras los stocks
        // return lista ordenada por menor a myor stock
        return null;
    }


    //@PostMapping
    private ResponseEntity<Void> create(ProductDto productDto) {
        // Crear el producto
        productService.create(ProductMapper.dtoToEntity(productDto));

        // Devolver el código 201 (Created)
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    

    //@PutMapping
    private ResponseEntity<Void> update(/*@RequestBody*/ Product product) {
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