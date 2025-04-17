package es.udc.fic.xpn.example.mapper;

import es.udc.fic.xpn.example.dto.ProductDto;
import es.udc.fic.xpn.example.model.Product;

public class ProductMapper {

    public static ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto(
            product.getId(),
            product.getSku(),
            product.getName(),
            product.getTipo(),
            product.getAlmacen(),
            product.getProveedor(),
            product.getStock(),
            product.getMaxStock(),
            product.getMinStock()
        );

        return productDto;
    }

    // Para producto nuevo
    public static Product dtoToEntity(ProductDto productDto) {
        Product product = new Product(
                productDto.getId(),
                productDto.getSku(),
                productDto.getName(),
                productDto.getTipo(),
                productDto.getProveedor(),
                productDto.getAlmacen(),
                productDto.getCantidad()
        );

        return product;
    }
}
