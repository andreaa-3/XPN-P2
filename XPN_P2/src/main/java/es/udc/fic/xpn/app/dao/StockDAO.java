package es.udc.fic.xpn.app.dao;

import java.util.List;
import java.util.Optional;

import es.udc.fic.xpn.app.model.Stock;

public interface StockDAO {
    public Long save(Stock stock);
    public void update(Stock stock);
    public Optional<Stock> find(Long idProducto, Long idAlmacen);
    public List<Stock> findStocks(Long idProducto);
    public void delete(Long id); 
}
