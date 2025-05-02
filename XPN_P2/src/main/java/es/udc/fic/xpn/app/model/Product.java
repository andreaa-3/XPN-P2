package es.udc.fic.xpn.app.model;

import java.util.Objects;



public class Product {
    private Long id;
    private String sku; // Código alfanumérico que identifica al producto.
    private String name;
    private String tipo;
    private String proveedor;
    private Long cantidad;

    public Product() {
    }

    public Product(Long id, String sku, String name, String tipo, String proveedor, Long cantidad) {
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

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Long getCantidad(){
        return this.cantidad;
    }

    public void setCantidad(Long cantidad){
        this.cantidad = cantidad;
    }
    

    @java.lang.Override
    public java.lang.String toString() {
        return "Product{" +
                "id=" + id +
                ", SKU='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", tipo='" + tipo + '\'' +
                ", proveedor='" + proveedor + '\'' +
                ", cantidad='" + cantidad + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(sku, product.sku) &&
                Objects.equals(name, product.name) &&
                Objects.equals(tipo, product.tipo) &&
                Objects.equals(proveedor, product.proveedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sku, name, tipo, proveedor);
    }
}
