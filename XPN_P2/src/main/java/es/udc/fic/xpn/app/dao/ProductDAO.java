package es.udc.fic.xpn.app.dao;

import es.udc.fic.xpn.app.model.Product;

import java.util.Optional;

public interface ProductDAO {
        public Long save(Product product);
        public Optional<Product> find(String sku);
        public void delete(Long id);
}
