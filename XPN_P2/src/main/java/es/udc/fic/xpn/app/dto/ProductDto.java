package es.udc.fic.xpn.app.dto;

public class ProductDto {
    private Long id;
    private String sku;
    private String name;
    private String tipo;
    private String proveedor;
    private Long cantidad;
  

    public ProductDto() {
    }

    public ProductDto(Long id, String sku, String name, String tipo, String proveedor, Long cantidad) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.tipo = tipo;
        this.proveedor = proveedor;
        this.cantidad = cantidad;
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

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
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
                ", name='" + name + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cantidad='" + cantidad + '\'' +
                ", proveedor='" + proveedor + '\'' +
                '}';
    }
}
