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

    public Long save2(Product product) {
        String sql = "INSERT INTO product (name, description, price, stock) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
                    PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
                    ps.setString(1, product.getName());
                    ps.setString(2, product.getDescription());
                    ps.setDouble(3, product.getPrice());
                    ps.setInt(4, product.getStock());

                    return ps;
                },
                keyHolder);

        return keyHolder.getKey().longValue();
    }

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

    @Override
    public List<Product> findAll() {
        String sql = "SELECT id, name, description, price, stock FROM product";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    @Override
    public void update(Product product) {

        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(jdbcTemplate);

        String sql = "UPDATE product SET name = :name, description = :description, " +
                "price = :price, stock = :stock WHERE id = :id;";

        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(product));

    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }
}
