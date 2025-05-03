package es.udc.fic.xpn.app.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import es.udc.fic.xpn.app.dto.AlmacenRowMapper;
import es.udc.fic.xpn.app.model.Almacen;

@Component
public class AlmacenDAOImpl implements AlmacenDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Almacen almacen) {

        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(jdbcTemplate);

        String sql = "INSERT INTO almacen (nombre) " +
                "VALUES (:nombre)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,
                new BeanPropertySqlParameterSource(almacen),
                keyHolder, new String[]{"id"});

        return keyHolder.getKey().longValue();
    }

    @Override
    public Optional<Almacen> find(String nombre) {
        String sql = "SELECT id, nombre FROM almacen WHERE nombre = ?";

        try {
            Almacen almacen = jdbcTemplate.queryForObject(sql, new AlmacenRowMapper(), nombre);
            return Optional.ofNullable(almacen);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Almacen> findById(Long id) {
        String sql = "SELECT id, nombre FROM almacen WHERE nombre = ?";

        try {
            Almacen almacen = jdbcTemplate.queryForObject(sql, new AlmacenRowMapper(), id);
            return Optional.ofNullable(almacen);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM almacen WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
