package es.udc.fic.xpn.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import es.udc.fic.xpn.app.dao.AlmacenDAO;
import es.udc.fic.xpn.app.model.Almacen;

public class AlmacenServiceImpl implements AlmacenService {
    @Autowired
    private AlmacenDAO almacenDAO;
    
    @Override
    public Almacen save(Almacen almacen) {
        Long id = almacenDAO.save(almacen);
        almacen.setId(id);
        return id;
    }

    @Override
    public Optional<Almacen> find(String nombre) {
        return almacenDAO.find(nombre);
    }

   
    @Override
    public void delete(Long id) {
        almacenDAO.delete(id);
    }
}
