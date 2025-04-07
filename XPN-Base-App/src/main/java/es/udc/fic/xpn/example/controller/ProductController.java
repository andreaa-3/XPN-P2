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
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto) {
        return ProductMapper.entityToDto(
                productService.create(ProductMapper.dtoToEntity(productDto)));
    }


    @GetMapping
    public List<ProductDto> findAll() {
        List<Product> products = productService.findAll();

        return products.stream().map(ProductMapper::entityToDto).
                collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        Optional<Product> product = productService.find(id);

        if (product.isPresent())
            return new ResponseEntity<>(
                    ProductMapper.entityToDto(product.get()), HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ProductDto productDto) {
        if (productService.find(productDto.getId()).isPresent()) {

            productService.update(ProductMapper.dtoToEntity(productDto));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (productService.find(id).isPresent()) {
            productService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
