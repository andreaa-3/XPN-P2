package es.udc.fic.xpn.app.service;

import es.udc.fic.xpn.app.dao.ProductDAO;
import es.udc.fic.xpn.app.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;
    
    @Override
    public Product create(Product product) {
        Long id = productDAO.save(product);
        product.setId(id);
        return product;
    }

    @Override
    public Optional<Product> find(String sku) {
        return productDAO.find(sku);
    }

    @Override
    public void delete(Long id) {
        productDAO.delete(id);
    }
}
