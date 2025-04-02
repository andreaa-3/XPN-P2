package es.udc.fic.xpn.example.service;

import es.udc.fic.xpn.example.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public Product create(Product product);

    public Optional<Product> find(Long id);

    public List<Product> findAll();

    public void update(Product product);

    public void delete(Long id);

}
