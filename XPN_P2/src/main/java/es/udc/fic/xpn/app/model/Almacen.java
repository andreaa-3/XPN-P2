package es.udc.fic.xpn.app.model;

public class Almacen{
    private Long id;
    private String nombre;

    public Almacen(){
    }

    public Almacen(String nombre){
        this.nombre = nombre;
    }

    public Almacen(Long id, String nombre){
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