package es.udc.fic.xpn.app.dao;

import java.util.Optional;

import es.udc.fic.xpn.app.model.Stock;

public interface StockDAO {

    public Long save(Stock stock);
    public void delete(Long id); 
    public Optional<Stock> find(Long idProducto, Long idAlmacen);
}
