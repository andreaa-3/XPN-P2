package es.udc.fic.xpn.app.dao;

import es.udc.fic.xpn.app.model.Stock;
import es.udc.fic.xpn.app.model.Almacen;
import es.udc.fic.xpn.app.model.Product;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static es.udc.fic.xpn.app.model.ModelConstants.MAX_STOCK_BY_DEFECT;
import static es.udc.fic.xpn.app.model.ModelConstants.MIN_STOCK_BY_DEFECT;

@SpringBootTest
class TestStockDAO {
    @Autowired
    private StockDAO stockDAO;

    @Autowired
    private AlmacenDAO almacenDAO;

    @Autowired
    private ProductDAO productDAO;

    private Long crearProducto() {
        Product p = new Product("test-sku", "test-nombre", "test-tipo", "test-proveedor");
        return productDAO.save(p);
    }

    private Long crearAlmacen() {
        Almacen a = new Almacen("test-almacen");
        return almacenDAO.save(a);
    }

    @Test
    void testSaveAndFind() {
        Long idProducto = crearProducto();
        Long idAlmacen = crearAlmacen();

        Stock stock = new Stock(100L, idAlmacen, idProducto);

        // Crear stock
        Long id = stockDAO.save(stock);
        stock.setId(id);

        // Comprobar que el id creado no es nulo
        assertNotNull(id);

        // Buscar el stock
        Optional<Stock> result = stockDAO.find(idProducto, idAlmacen);

        // Comprobar con el stock creado
        assertTrue(result.isPresent());
        Stock s = result.get();
        assertEquals(stock.getId(), s.getId());
        assertEquals(stock.getStock(), s.getStock());
        assertEquals(MAX_STOCK_BY_DEFECT, s.getMaxStock());
        assertEquals(MIN_STOCK_BY_DEFECT, s.getMinStock());
        assertEquals(stock.getIdAlmacen(), s.getIdAlmacen());
        assertEquals(stock.getIdProducto(), s.getIdProducto());

        // Eliminar
        stockDAO.delete(id);
        almacenDAO.delete(idAlmacen);
        productDAO.delete(idProducto);
    }

    @Test
    void testUpdate() {
        Long idProducto = crearProducto();
        Long idAlmacen = crearAlmacen();
        Stock stock = new Stock(50L, idAlmacen, idProducto);

        // Crear stock
        Long id = stockDAO.save(stock);
        stock.setId(id);

        // Actualizar el stock
        Long cantidadActualizar = 80L;
        stock.setStock(cantidadActualizar);
        stockDAO.update(stock);

        // Buscar el stock
        Optional<Stock> result = stockDAO.find(idProducto, idAlmacen);

        // Comprobar con el stock actualizado
        assertTrue(result.isPresent());
        Stock s = result.get();
        assertEquals(stock.getId(), s.getId());
        assertEquals(cantidadActualizar, s.getStock());
        assertEquals(MAX_STOCK_BY_DEFECT, s.getMaxStock());
        assertEquals(MIN_STOCK_BY_DEFECT, s.getMinStock());
        assertEquals(stock.getIdAlmacen(), s.getIdAlmacen());
        assertEquals(stock.getIdProducto(), s.getIdProducto());

        // Eliminar
        stockDAO.delete(id);
        almacenDAO.delete(idAlmacen);
        productDAO.delete(idProducto);
    }

    @Test
    void testFind_NotExistentStock() {
        Long idAlmacen = crearAlmacen();

        // Buscar stock por id inexistente
        Optional<Stock> result = stockDAO.find(999L, idAlmacen);

        // Comprobar que no se encontró nada
        assertFalse(result.isPresent());

        // Eliminar
        almacenDAO.delete(idAlmacen);
    }

    @Test
    void testFindStocks_1() {
        Long idProducto = crearProducto();
        Long idAlmacen = crearAlmacen();

        Stock s = new Stock(10L, idAlmacen, idProducto);

        // Crear los stock
        Long id = stockDAO.save(s);
        s.setId(id);

        // Buscar stocks
        List<Stock> result = stockDAO.findStocks(idProducto);

        // Comprobar lista devuelta
        assertEquals(1, result.size());
        assertEquals(result.get(0), s);

        // Eliminar
        stockDAO.delete(id);
        almacenDAO.delete(idAlmacen);
        productDAO.delete(idProducto);
    }

    @Test
    void testFindStocks_2() {
        Long idProducto = crearProducto();
        Long idAlmacen = crearAlmacen();
        Long idAlmacen2 = crearAlmacen();

        Stock s1 = new Stock(20L, idAlmacen, idProducto);
        Stock s2 = new Stock(10L, idAlmacen2, idProducto);

        // Crear los stocks
        Long id1 = stockDAO.save(s1);
        s1.setId(id1);
        Long id2 = stockDAO.save(s2);
        s2.setId(id2);

        // Buscar stocks
        List<Stock> result = stockDAO.findStocks(idProducto);

        // Comprobar lista devuelta
        assertEquals(2, result.size());

        // Eliminar
        stockDAO.delete(id1);
        stockDAO.delete(id2);
        almacenDAO.delete(idAlmacen);
        almacenDAO.delete(idAlmacen2);
        productDAO.delete(idProducto);
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
        Long idProducto = crearProducto();
        Long idAlmacen = crearAlmacen();

        Stock stock = new Stock(30L, idAlmacen, idProducto);

        // Crear el stock
        Long id = stockDAO.save(stock);
        stock.setId(id);

        // Eliminar el stock
        stockDAO.delete(id);

        // Buscar el stock para comprobar que se eliminó
        Optional<Stock> result = stockDAO.find(5L, 5L);
        assertFalse(result.isPresent());

        // Eliminar
        almacenDAO.delete(idAlmacen);
        productDAO.delete(idProducto);
    }
} 
