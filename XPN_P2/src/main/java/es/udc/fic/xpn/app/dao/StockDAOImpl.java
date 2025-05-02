package es.udc.fic.xpn.app.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import es.udc.fic.xpn.app.dto.StockRowMapper;
import es.udc.fic.xpn.app.model.Stock;

public class StockDAOImpl implements StockDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Stock stock) {

        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(jdbcTemplate);

        String sql = "INSERT INTO stock (stock, maxStock, minStock) " +
                "VALUES (:stock, :maxStock, :minStock)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,
                new BeanPropertySqlParameterSource(stock),
                keyHolder, new String[]{"id"});

        return keyHolder.getKey().longValue();
    }

     @Override
    public Optional<Stock> find(Long idProducto, Long idAlmacen) {

        String sql = "SELECT idProducto, idAlmacen FROM stock WHERE idProducto = ? AND idAlmacen = ?";

        try {
            Stock stock = jdbcTemplate.queryForObject(sql, new StockRowMapper(), idProducto, idAlmacen);
            return Optional.ofNullable(stock);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM stock WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
