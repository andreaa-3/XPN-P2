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
        List<String> almacenes = products.stream().sorted(Comparator.comparing(Product::getStock)).map(Product::getAlmacen).toList();

        if(almacenes.get(0) == almacen){
            return almacenes.get(1);
        }

        return almacenes.get(0);
    }


    private ProductDto create(ProductDto productDto) {
        // Crear el producto
        return ProductMapper.entityToDto(
                productService.create(ProductMapper.dtoToEntity(productDto)));
    }
    

    private void update(Product product) {
        // Se sobreentiende que si se encontró por el findBySku, existe.
        return productService.update(product);
    }

    @PostMapping
    public void updateStock (@RequestBody ProductDto productDto) {
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

    @GetMapping("/superaStock")
    boolean superaStock (@RequestParam(value="sku") String sku, @RequestParam(value="almacen") String almacen) {
        return comprobaciónStock(sku, almacen, true);
    }

    @GetMapping("/faltaStock")
    boolean faltaStock (@RequestParam(value="sku") String sku, @RequestParam(value="almacen") String almacen) {
        return comprobaciónStock(sku, almacen, false);
    }

    boolean comprobaciónStock (String sku, String almacen, bool max) {
        Optional<Product> product = productService.findBySkuCity(almacen, sku);

        // Si no existe, es error
        if (!product.isPresent())
            return false;

        // Si existe, obtiene el producto
        Product p = product.get();

        // Devuelve la comparación del stock
        if max {
            return p.getStock() > p.getMaxStock();
        }
        else {
            return p.getStock() < p.getMinStock();
        }
    }
 }