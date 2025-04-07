package es.udc.fic.xpn.example.mapper;

import es.udc.fic.xpn.example.dto.ProductDto;
import es.udc.fic.xpn.example.model.Product;

public class ProductMapper {

    public static ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setSKU(product.getSKU());
        productDto.setName(product.getName());
        productDto.setTipo(product.getName());
        productDto.setCantidad(product.getStock());
        productDto.setAlmacen(product.getAlmacen().toString());
        productDto.setProveedor(product.getProveedor());

        return productDto;
    }

    // Para producto nuevo
    public static Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();

        product.setId(productDto.getId());
        product.setSKU(productDto.getSKU());
        product.setName(productDto.getName());
        product.setTipo(productDto.getTipo());
        product.setStock(productDto.getCantidad());
        product.setAlmacen(productDto.getAlmacen()); // poner a almacen
        product.setProveedor(productDto.getProveedor());

        return product;
    }
}
