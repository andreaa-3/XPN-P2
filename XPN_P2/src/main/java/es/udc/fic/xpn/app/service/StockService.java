package es.udc.fic.xpn.app.service;

import java.util.Optional;
import java.util.List;

import es.udc.fic.xpn.app.model.Stock;

public interface StockService {
    public Stock save(Stock stock);
    public void update(Stock stock);
    public Optional<Stock> find(Long idProducto, Long idAlmacen);
    public List<Stock> findStocks(Long idProducto);
    public void delete(Long id);
}
