package es.udc.fic.xpn.example.dto;

public class ProductDto {
    private Long id;
    private Long SKU;
    private String name;
    private String tipo;
    private String cantidad;
    private String almacen;
    private String proveedor;

    public ProductDto() {
    }

    public ProductDto(Long id, Long SKU, String name, String tipo, String cantidad, String almacen, String proveedor) {
        this.id = id;
        this.SKU = SKU;
        this.name = name;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.almacen = almacen;
        this.proveedor = proveedor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSKU() {
        return SKU;
    }

    public void setSKU(Long SKU) {
        this.SKU = SKU;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
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
                "SKU=" + SKU +
                ", name='" + name + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cantidad='" + cantidad + '\'' +
                ", almacen='" + almacen + '\'' +
                ", proveedor='" + proveedor + '\'' +
                '}';
    }
}
