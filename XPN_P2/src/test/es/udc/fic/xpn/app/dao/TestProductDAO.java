package es.udc.fic.xpn.dao;

import es.udc.fic.xpn.model.Product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import java.util.Optional;

@SpringBootTest
public class TestProductDAO {
    @Autowired
    private ProductDAO productDAO;

    @Test
    public void testSave_And_Find() {
        Product product = new Product("sku-test", "SillaTest", "Silla", "IKEA");

        // Guardar el producto
        Long id = productDAO.save(product);
        product.setId(id);

        // Comprobar que el id creado no es nulo
        assertNotNull(id);

        // Buscar el producto
        Optional<Product> result = productDAO.find("sku-test");

        // Comprobar con el producto creado
        assertTrue(result.isPresent());
        Product p = result.get();
        assertEquals(product.getId(), p.getId());
        assertEquals(product.getSku(), p.getSku());
        assertEquals(product.getNombre(), p.getNombre());
        assertEquals(product.getTipo(), p.getTipo());
        assertEquals(product.getProveedor(), p.getProveedor());

        // Eliminar el producto
        productDAO.delete(id);
    }

    @Test
    public void testFind_NonExistentProduct() {
        // Buscar producto inexistente
        Optional<Product> result = productDAO.find("non-existent-sku");

        // Comprobar que no se encontró nada
        assertFalse(result.isPresent());
    }

    @Test
    public void testDelete() {
        Product product = new Product("sku-delete", "mesa", "mesa", "Leroy");

        // Crear el producto
        Long id = productDAO.save(product);

        // Eliminar el producto
        productDAO.delete(id);

        // Buscar el producto para comprobar que se eliminó
        Optional<Product> result = productDAO.find("sku-delete");
        assertFalse(result.isPresent());
    }
}
