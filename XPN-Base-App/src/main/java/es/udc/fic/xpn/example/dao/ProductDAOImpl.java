package es.udc.fic.xpn.example.dao;

import es.udc.fic.xpn.example.dto.ProductRowMapper;
import es.udc.fic.xpn.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Product product) {

        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(jdbcTemplate);

        String sql = "INSERT INTO product (name, description, price, stock) " +
                "VALUES (:name, :description, :price, :stock)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,
                new BeanPropertySqlParameterSource(product),
                keyHolder, new String[]{"id"});

        return keyHolder.getKey().longValue();
    }

    /*
    @Override
    public Optional<Product> find(Long id) {

        String sql = "SELECT id, name, description, price, stock FROM product WHERE id = ?";

        try {
            Product product = jdbcTemplate.queryForObject(sql, new ProductRowMapper(), id);
            return Optional.ofNullable(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
     */
    @Override
    public Optional<Product> find(Long sku) {

        String sql = "SELECT id, name, description, price, stock FROM product WHERE SKU = ?";

        try {
            Product product = jdbcTemplate.queryForObject(sql, new ProductRowMapper(), sku);
            return Optional.ofNullable(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Long> findByCity (String city, Long SKU) {

        String sql = "SELECT stock FROM product WHERE almacen = ? AND SKU = ?";

        try {
            Long stock = jdbcTemplate.queryForObject(sql, new ProductRowMapper(), city, sku);
            return Optional.ofNullable(stock);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public List<Product> findAll() {
        String sql = "SELECT id, name, description, price, stock FROM product";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    @Override
    public void update(Product product) {

        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(jdbcTemplate);

        String sql = "UPDATE product SET stock = :stock WHERE id = :id;";

        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(product));

    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }
}
