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
    public Optional<Product> find(Long id) {
        return productDAO.find(id);
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public void update(Product product) {
        productDAO.update(product);
    }


    @Override
    public void delete(Long id) {
        if (! productDAO.find(id).isEmpty())
            productDAO.delete(id);
    }


}
