package es.udc.fic.xpn.app.mapper;

import es.udc.fic.xpn.app.dto.AlmacenDto;
import es.udc.fic.xpn.app.model.Almacen;

public class AlmacenMapper {

    public static AlmacenDto entityToDto(Almacen almacen) {
        AlmacenDto almacenDto = new AlmacenDto(
            almacen.getId(),
            almacen.getNombre()
        );
        return almacenDto;
    }

    // Para producto nuevo
    public static Almacen dtoToEntity(AlmacenDto almacenDto) {
        Almacen almacen = new Almacen(
            almacenDto.getNombre()
        );

        return almacen;
    }
}
