package es.udc.fic.xpn.example.service;

import es.udc.fic.xpn.example.dao.ProductDAO;
import es.udc.fic.xpn.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Optional<Product> findById(Long id) {
        return productDAO.findById(id);
    }

    @Override
    public Optional<Product> findBySkuCity(String city, String sku) {
        return productDAO.findBySkuCity(city, sku);
    }

    @Override
    public List<Product> findBySku(String sku) {
        return productDAO.findBySku(sku);
    }

    @Override
    public void update(Product product) {
        productDAO.update(product);
    }
}
