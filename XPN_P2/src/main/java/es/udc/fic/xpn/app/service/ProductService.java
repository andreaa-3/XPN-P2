package es.udc.fic.xpn.app.service;

import es.udc.fic.xpn.app.model.Product;

import java.util.Optional;

public interface ProductService {
    public Product create(Product product);
    public Optional<Product> find(String sku);
    public void delete(Long id);
}
