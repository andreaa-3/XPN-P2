package es.udc.fic.xpn.example.dao;

import es.udc.fic.xpn.example.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {

        public Long save(Product product);

        public Optional<Product> find(Long id);

        public List<Product> findAll();

        public void update(Product product);

        public void delete(Long id);


}
