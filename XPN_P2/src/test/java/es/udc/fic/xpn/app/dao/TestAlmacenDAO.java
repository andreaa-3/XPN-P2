package es.udc.fic.xpn.app.dao;

import es.udc.fic.xpn.app.model.Almacen;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestAlmacenDAO {

    @Autowired
    private AlmacenDAO almacenDAO;

    @Test
    void testSave_And_FindById() {
        Almacen almacen = new Almacen("almacen-test");

        // Crear almacen
        Long id = almacenDAO.save(almacen);
        almacen.setId(id);

        // Comprobar que el id creado no es nulo
        assertNotNull(id);

        // Buscar el almacén
        Optional<Almacen> result = almacenDAO.findById(id);

        // Comprobar con el almacén creado
        assertTrue(result.isPresent());
        Almacen a = result.get();
        assertEquals(almacen.getId(), a.getId());
        assertEquals(almacen.getNombre(), a.getNombre());

        // Eliminar el almacen
        almacenDAO.delete(id);
    }

    @Test
    void testFind_NonExistentAlmacen() {
        // Buscar almacen inexistente
        Optional<Almacen> result = almacenDAO.find("non-existent-almacen");

        // Comprobar que no se encontró nada
        assertFalse(result.isPresent());
    }

    @Test
    void testFindById_NonExistentAlmacen() {
        // Buscar almacen por id inexistente
        Optional<Almacen> result = almacenDAO.findById(-1L);

        // Comprobar que no se encontró nada
        assertFalse(result.isPresent());
    }

    @Test
    void testDelete() {
        Almacen almacen = new Almacen("test-delete");

        // Crear el almacen
        Long id = almacenDAO.save(almacen);
        almacen.setId(id);

        // Eliminar el almacén
        almacenDAO.delete(id);

        // Buscar el producto para comprobar que se eliminó
        Optional<Almacen> result = almacenDAO.findById(id);
        assertFalse(result.isPresent());
    }
} 
