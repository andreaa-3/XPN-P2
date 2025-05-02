package es.udc.fic.xpn.app.mapper;

import es.udc.fic.xpn.app.dto.StockDto;
import es.udc.fic.xpn.app.model.Stock;

public class StockMapper {

    public static StockDto entityToDto(Stock stock) {
        StockDto stockDto = new StockDto(
            stock.getId(),
            stock.getStock(),
            stock.getMaxStock(),
            stock.getMinStock(),
            stock.getIdAlmacen(),
            stock.getIdProducto()
        );

        return stockDto;
    }

    // Para producto nuevo
    public static Stock dtoToEntity(StockDto stockDto) {
        Stock stock = new Stock(
            stockDto.getId(),
            stockDto.getStock(),
            stockDto.getMaxStock(),
            stockDto.getMinStock(),
            stockDto.getIdAlmacen(),
            stockDto.getIdProducto()
        );

        return stock;
    }
}

