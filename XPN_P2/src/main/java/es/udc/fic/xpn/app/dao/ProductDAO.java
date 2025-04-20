package es.udc.fic.xpn.app.dao;

import es.udc.fic.xpn.app.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {
        public Long save(Product product);
        public Optional<Product> findById(Long id);
        public Optional<Product> findBySkuCity(String city, String sku);
        public List<Product> findBySku(String sku);
        public void update(Product product);
        public void delete(Long id);
}
