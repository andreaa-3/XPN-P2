package es.udc.fic.xpn.app.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import es.udc.fic.xpn.app.model.Almacen;

public class AlmacenRowMapper implements RowMapper<Almacen> {
     public Almacen mapRow(ResultSet rs, int rowNum) throws SQLException {
        Almacen a = new Almacen(
            rs.getLong("id"),
            rs.getString("nombre")
        );
        return a;
    }
}