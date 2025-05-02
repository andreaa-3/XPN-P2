package es.udc.fic.xpn.app.service;

import es.udc.fic.xpn.app.model.Almacen;

import java.util.Optional;

public interface AlmacenService {
    public Long save(Almacen almacen);
    public void delete(Long id);
    public Optional<Almacen> find(String nombre);
}
