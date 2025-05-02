package es.udc.fic.xpn.app.dto;

public class AlmacenDto {
    private Long id;
    private String nombre;

    public AlmacenDto(Long id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre(){
        return this.nombre;
    }
    
    public Long getId(){
        return this.id;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setId(Long id){
        this.id = id;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Almacen{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
