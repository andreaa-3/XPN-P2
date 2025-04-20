package es.udc.fic.xpn.dao;

import es.udc.fic.xpn.model.Product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

@SpringBootTest
public class ProductDAOTest {

    @Autowired
    private ProductDAO productDAO;

    @Test
    public void save() {
        Product p = new Product(1L, "1234", "SillaToGuapa", "Silla", "Lugo", "Ikea", 50L);
        p.setId(productDAO.save(p));

        Optional<Product> res = productDAO.findBySkuCity("1234", "Lugo");
        assertEquals(p, res);
        
        productDAO.delete(p.getId());
    }
}
