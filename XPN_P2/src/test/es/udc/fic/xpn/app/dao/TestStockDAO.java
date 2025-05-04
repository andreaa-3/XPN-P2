package es.udc.fic.xpn.app.dao;

import es.udc.fic.xpn.app.model.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import static es.udc.fic.xpn.app.model.ModelConstants.MAX_STOCK_BY_DEFECT;
import static es.udc.fic.xpn.app.model.ModelConstants.MIN_STOCK_BY_DEFECT;

@SpringBootTest
class TestStockDAO {

    @Autowired
    private StockDAO stockDAO;

    @Test
    void testSaveAndFind() {
        Stock stock = new Stock(100L, 1L, 1L);

        // Crear stock
        Long id = stockDAO.save(stock);
        stock.setId(id);

        // Comprobar que el id creado no es nulo
        assertNotNull(id);

        // Buscar el stock
        Optional<Stock> result = stockDAO.find(1L, 1L);

        // Comprobar con el stock creado
        assertTrue(result.isPresent());
        Stock s = result.get();
        assertEquals(stock.getId(), s.getId());
        assertEquals(stock.getStock(), s.getStock());
        assertEquals(MAX_STOCK_BY_DEFECT, s.getMaxStock());
        assertEquals(MIN_STOCK_BY_DEFECT, s.getMinStock());
        assertEquals(stock.getIdAlmacen(), s.getIdAlmacen());
        assertEquals(stock.getIdProducto(), s.getIdProducto());

        // Eliminar el stock
        almacenDAO.delete(id);
    }

    @Test
    void testUpdate() {
        Stock stock = new Stock(50L, 2L, 2L);

        // Crear stock
        Long id = stockDAO.save(stock);
        stock.setId(id);

        // Actualizar el stock
        Long cantidadActualizar = 80L;
        stock.setStock(cantidadActualizar);
        stockDAO.update(stock);

        // Buscar el stock
        Optional<Stock> result = stockDAO.find(2L, 2L);

        // Comprobar con el stock actualizado
        assertTrue(result.isPresent());
        Stock s = result.get();
        assertEquals(stock.getId(), s.getId());
        assertEquals(cantidadActualizar, s.getStock());
        assertEquals(MAX_STOCK_BY_DEFECT, s.getMaxStock());
        assertEquals(MIN_STOCK_BY_DEFECT, s.getMinStock());
        assertEquals(stock.getIdAlmacen(), s.getIdAlmacen());
        assertEquals(stock.getIdProducto(), s.getIdProducto());

        // Eliminar el stock
        almacenDAO.delete(id);
    }

    @Test
    void testFind_NotExistentStock() {
        // Buscar stock por id inexistente
        Optional<Stock> result = stockDAO.find(999L, 999L);

        // Comprobar que no se encontró nada
        assertFalse(result.isPresent());
    }

    @Test
    void testFindStocks_1() {
        Stock s = new Stock(10L, idAlm1, 9L);

        // Crear los stock
        Long id = stockDAO.save(s);

        // Buscar stocks
        List<Stock> result = stockDAO.findStocks(9L);

        // Comprobar lista devuelta
        assertEquals(1, result.size());
        assertEquals(result.get(0), s);

        // Eliminar los stocks
        almacenDAO.delete(id);
    }

    @Test
    void testFindStocks_2() {
        Stock s1 = new Stock(10L, idAlm1, 9L);
        Stock s2 = new Stock(20L, idAlm2, 9L);

        // Crear los stocks
        Long id1 = stockDAO.save(s1);
        Long id2 = stockDAO.save(s2);

        // Buscar stocks
        List<Stock> result = stockDAO.findStocks(9L);

        // Comprobar lista devuelta
        assertEquals(2, result.size());
        assertEquals(result.get(0), s2);
        assertEquals(result.get(1), s1);

        // Eliminar los stocks
        almacenDAO.delete(id1);
        almacenDAO.delete(id2);
    }

    @Test
    void testFindStocks_NonExistentStocks() {
        // Buscar stocks
        List<Stock> result = stockDAO.findStocks(9L);

        // Comprobar lista devuelta
        assertEquals(0, result.size());
    }

    @Test
    void testDelete() {
        Stock stock = new Stock(30L, 5L, 5L);

        // Crear el stock
        Long id = stockDAO.save(stock);

        // Eliminar el stock
        stockDAO.delete(id);

        // Buscar el stock para comprobar que se eliminó
        Optional<Stock> result = stockDAO.find(5L, 5L);
        assertFalse(result.isPresent());
    }
} 
