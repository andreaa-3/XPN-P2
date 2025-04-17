package es.udc.fic.xpn.example.dto;

public class ProductDto {
    private Long id;
    private String sku;
    private String name;
    private String tipo;
    private String almacen;
    private String proveedor;
    private Long cantidad;
    private Long maxStock;
    private Long minStock;

    public ProductDto() {
    }

    public ProductDto(Long id, String sku, String name, String tipo, String almacen, String proveedor, Long cantidad, Long maxStock, Long minStock) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.tipo = tipo;
        this.almacen = almacen;
        this.proveedor = proveedor;
        this.cantidad = cantidad;
        this.maxStock = maxStock;
        this.minStock = minStock;
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

    public Long getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(Long maxStock) {
        this.maxStock = maxStock;
    }

    public Long getMinStock() {
        return minStock;
    }

    public void setMinStock(Long minStock) {
        this.minStock = minStock;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", sku=" + sku +
                ", name='" + name + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cantidad='" + cantidad + '\'' +
                ", almacen='" + almacen + '\'' +
                ", proveedor='" + proveedor + '\'' +
                ", maxStock=" + maxStock +
                ", minStock=" + minStock +
                '}';
    }
}
