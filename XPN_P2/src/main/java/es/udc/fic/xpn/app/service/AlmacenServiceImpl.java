package es.udc.fic.xpn.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.fic.xpn.app.dao.AlmacenDAO;
import es.udc.fic.xpn.app.model.Almacen;

@Service
public class AlmacenServiceImpl implements AlmacenService {
    @Autowired
    private AlmacenDAO almacenDao;
    
    @Override
    public Almacen save(Almacen almacen) {
        Long id = almacenDao.save(almacen);
        almacen.setId(id);
        return almacen;
    }

    @Override
    public Optional<Almacen> find(String nombre) {
        return almacenDao.find(nombre);
    }

    @Override
    public Optional<Almacen> findById(Long id) {
        return almacenDao.findById(id);
    }
   
    @Override
    public void delete(Long id) {
        almacenDao.delete(id);
    }
}
