package es.udc.fic.xpn.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import es.udc.fic.xpn.app.dao.StockDAO;
import es.udc.fic.xpn.app.model.Stock;

public class StockServiceImpl implements StockService {
    @Autowired
    private StockDAO stockDAO;
    
    @Override
    public Stock save(Stock stock) {
        Long id = stockDAO.save(stock);
        stock.setId(id);
        return stock;
    }

    @Override
    public Optional<Stock> find(Long idProducto, Long idAlmacen) {
        return stockDAO.find(idProducto, idAlmacen);
    }

   
    @Override
    public void delete(Long id) {
        stockDAO.delete(id);
    }
}
