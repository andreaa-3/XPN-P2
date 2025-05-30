package es.udc.fic.xpn.app.dto;

public class ProductDto {
    private Long id;
    private String sku;
    private String nombre;
    private String tipo;
    private String almacen;
    private String proveedor;
    private Long cantidad;

    public ProductDto() {
        // Necesario para que Jackson pueda crear instancias
    }
    
    public ProductDto(Long id, String sku, String nombre, String tipo, String almacen, String proveedor, Long cantidad) {
        this.id = id;
        this.sku = sku;
        this.nombre = nombre;
        this.tipo = tipo;
        this.almacen = almacen;
        this.proveedor = proveedor;
        this.cantidad = cantidad;
    }

    public ProductDto(String sku, String nombre, String tipo, String almacen, String proveedor, Long cantidad) {
        this.sku = sku;
        this.nombre = nombre;
        this.tipo = tipo;
        this.almacen = almacen;
        this.proveedor = proveedor;
        this.cantidad = cantidad;
    }

    public ProductDto(Long id, String sku, String nombre, String tipo, String proveedor) {
        this.id = id;
        this.sku = sku;
        this.nombre = nombre;
        this.tipo = tipo;
        this.proveedor = proveedor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", sku=" + sku +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cantidad='" + cantidad + '\'' +
                ", almacen='" + almacen + '\'' +
                ", proveedor='" + proveedor + '\'' +
                '}';
    }
}