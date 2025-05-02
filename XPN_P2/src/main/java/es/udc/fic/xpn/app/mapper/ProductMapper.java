package es.udc.fic.xpn.app.mapper;

import es.udc.fic.xpn.app.dto.ProductDto;
import es.udc.fic.xpn.app.model.Product;

public class ProductMapper {

    public static ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto(
            product.getId(),
            product.getSku(),
            product.getName(),
            product.getTipo(),
            product.getProveedor(),
            product.getCantidad()
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
                productDto.getCantidad()
        );

        return product;
    }
}
