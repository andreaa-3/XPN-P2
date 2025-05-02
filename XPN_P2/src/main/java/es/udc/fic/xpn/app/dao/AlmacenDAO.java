package es.udc.fic.xpn.app.dao;

import java.util.Optional;

import es.udc.fic.xpn.app.model.Almacen;

public interface AlmacenDAO {
    public Long save(Almacen almacen);
    public void delete(Long id);
    public Optional<Almacen> find(String nombre);
}
