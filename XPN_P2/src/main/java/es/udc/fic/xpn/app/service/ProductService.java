package es.udc.fic.xpn.app.service;

import es.udc.fic.xpn.app.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public Product create(Product product);

    public Optional<Product> findBySkuCity(String city, String sku);

    public List<Product> findBySku(String sku);
    
    public void update(Product product);
}
