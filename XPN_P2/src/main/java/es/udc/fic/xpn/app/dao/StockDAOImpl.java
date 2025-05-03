package es.udc.fic.xpn.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import es.udc.fic.xpn.app.dto.StockRowMapper;
import es.udc.fic.xpn.app.model.Stock;

@Component
public class StockDAOImpl implements StockDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Stock stock) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(jdbcTemplate);

        String sql = "INSERT INTO stock (idProducto, idAlmacen, stock, maxStock, minStock) " +
                "VALUES (:idProducto, :idAlmacen, :stock, :maxStock, :minStock)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,
                new BeanPropertySqlParameterSource(stock),
                keyHolder, new String[]{"id"});

        return keyHolder.getKey().longValue();
    }

    @Override
    public void update(Stock stock) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(jdbcTemplate);

        String sql = "UPDATE stock SET stock = :stock WHERE idProducto = :idProducto AND idAlmacen = :idAlmacen;";

        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(stock));
    }
    
    @Override
    public Optional<Stock> find(Long idProducto, Long idAlmacen) {
        String sql = "SELECT id, idProducto, idAlmacen, stock, maxStock, minStock FROM stock WHERE idProducto = ? AND idAlmacen = ?";
        
        try {
            Stock stock = jdbcTemplate.queryForObject(sql, new StockRowMapper(), idProducto, idAlmacen);
            return Optional.ofNullable(stock);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Stock> findStocks(Long idProducto) {
        String sql = "SELECT id, idProducto, idAlmacen, stock, maxStock, minStock FROM product WHERE idProducto = ?";
        
        return jdbcTemplate.query(sql, new StockRowMapper(), idProducto);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM stock WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
