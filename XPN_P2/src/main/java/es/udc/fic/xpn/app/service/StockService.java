package es.udc.fic.xpn.app.service;

import java.util.Optional;

import es.udc.fic.xpn.app.model.Stock;

public interface StockService {
    public Stock save(Stock stock);
    public void delete(Long id); 
    public Optional<Stock> find(Long idProducto, Long idAlmacen);
}
