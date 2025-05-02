package es.udc.fic.xpn.app.dto;

import es.udc.fic.xpn.app.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product p = new Product(
            rs.getLong("id"),
            rs.getString("sku"),
            rs.getString("name"),
            rs.getString("tipo"),
            rs.getString("proveedor"),
            rs.getLong("cantidad")
        );
        return p;
    }
}