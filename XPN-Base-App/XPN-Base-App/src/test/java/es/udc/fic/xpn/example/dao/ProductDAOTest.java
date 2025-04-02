package es.udc.fic.xpn.example.dao;

import es.udc.fic.xpn.example.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductDAOTest {

    @Autowired
    private ProductDAO productDAO;

    @Test
    public void save() {

        Product p = new Product(1L, "Mouse", "A nice mouse", 50.0, 10);
        p.setId(productDAO.save(p));

        Optional<Product> res = productDAO.find(p.getId());
        assert(! res.isEmpty());

        productDAO.delete(p.getId());

    }
}
