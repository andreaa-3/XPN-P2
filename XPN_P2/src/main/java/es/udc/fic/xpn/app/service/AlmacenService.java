package es.udc.fic.xpn.app.service;

import es.udc.fic.xpn.app.model.Almacen;

import java.util.Optional;

public interface AlmacenService {
    public Almacen save(Almacen almacen);
    public Optional<Almacen> find(String nombre);
    public Optional<Almacen> findById(Long id);
    public void delete(Long id);
}
