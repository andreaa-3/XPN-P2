package es.udc.fic.xpn.example.service;

import es.udc.fic.xpn.example.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public Product create(Product product);

    public Optional<Product> findById(Long id);

    public Optional<Product> findBySkuCity(String city, String sku);

    public List<Product> findBySku(String sku);
    
    public void update(Product product);
}
