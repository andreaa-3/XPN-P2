package es.udc.fic.xpn.app.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.udc.fic.xpn.app.model.Stock;

public class StockRowMapper implements RowMapper<Stock>{
    public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
        Stock s = new Stock(
            rs.getLong("id"),
            rs.getLong("stock"),
            rs.getLong("minStock"),
            rs.getLong("maxStock"),
            rs.getLong("idAlmacen"),
            rs.getLong("idProducto")
        );
        return s;
    }
    
}
