package es.udc.fic.xpn.app.dao;

import java.util.Optional;

import es.udc.fic.xpn.app.model.Almacen;

public interface AlmacenDAO {
    public Long save(Almacen almacen);
    public Optional<Almacen> find(String nombre);
    public Optional<Almacen> findById(Long id);
    public void delete(Long id);
}
