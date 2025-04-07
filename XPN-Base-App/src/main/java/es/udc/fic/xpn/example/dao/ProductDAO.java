package es.udc.fic.xpn.example.dao;

import es.udc.fic.xpn.example.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {
        public Long create(Product product);

        //public Optional<Product> find(Long id);
        public Optional<Product> find(Long SKU);
        public Optional<Long> findByCity(String city, Long SKU);

        //public List<Product> findAll();

        public void update(Product product);

        public void delete(Long id);
}
