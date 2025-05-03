package es.udc.fic.xpn.app.model;

import java.util.Objects;



public class Product {
    private Long id;
    private String sku; // Código alfanumérico que identifica al producto.
    private String nombre;
    private String tipo;
    private String proveedor;

    public Product () {
    }

    public Product(Long id, String sku, String nombre, String tipo, String proveedor) {
        this.id = id;
        this.sku = sku;
        this.nombre = nombre;
        this.tipo = tipo;
        this.proveedor = proveedor;
    }

    public Product(String sku, String nombre, String tipo, String proveedor) {
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

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Product{" +
                "id=" + id +
                ", SKU='" + sku + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", proveedor='" + proveedor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(sku, product.sku) &&
                Objects.equals(nombre, product.nombre) &&
                Objects.equals(tipo, product.tipo) &&
                Objects.equals(proveedor, product.proveedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sku, nombre, tipo, proveedor);
    }
}
