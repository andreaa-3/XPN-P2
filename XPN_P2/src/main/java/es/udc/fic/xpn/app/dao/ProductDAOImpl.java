package es.udc.fic.xpn.app.dao;

import es.udc.fic.xpn.app.dto.ProductRowMapper;
import es.udc.fic.xpn.app.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Product product) {

        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(jdbcTemplate);

        String sql = "INSERT INTO product (sku, nombre, tipo, proveedor) " +
                "VALUES (:sku, :nombre, :tipo, :proveedor)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,
                new BeanPropertySqlParameterSource(product),
                keyHolder, new String[]{"id"});

        return keyHolder.getKey().longValue();
    }

    @Override
    public Optional<Product> find (String sku) {
        String sql = "SELECT id, sku, nombre, tipo, proveedor FROM product WHERE sku = ?";

        try {
            Product product = jdbcTemplate.queryForObject(sql, new ProductRowMapper(), sku);
            return Optional.ofNullable(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
