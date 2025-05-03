package es.udc.fic.xpn.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.fic.xpn.app.dao.StockDAO;
import es.udc.fic.xpn.app.model.Stock;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockDAO stockDao;
    
    @Override
    public Stock save(Stock stock) {
        Long id = stockDao.save(stock);
        stock.setId(id);
        return stock;
    }

    @Override
    public Optional<Stock> find(Long idProducto, Long idAlmacen) {
        return stockDao.find(idProducto, idAlmacen);
    }

    @Override
    public List<Stock> findStocks(Long idProducto) {
        return stockDao.findStocks(idProducto);
    }

    @Override
    public void update(Stock stock) {
        stockDao.update(stock);
    }
   
    @Override
    public void delete(Long id) {
        stockDao.delete(id);
    }
}
