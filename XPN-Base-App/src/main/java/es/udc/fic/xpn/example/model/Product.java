package es.udc.fic.xpn.example.model;

import java.util.Objects;

public class Product {
    private enum Almacen {
        CORUÑA, LUGO, OURENSE, PONTEVEDRA
    }

    private Long id;
    private String SKU; // Código alfanumérico que identifica al producto.
    private String name;
    private String tipo;
    private Almacen almacen;
    private String proveedor;
    private Long stock;

    public Product() {
    }

    public Product (Long id, String SKU, String name, String tipo, Almacen almacen, String proveedor, Long stock) {
        this.id = id;
        this.SKU = SKU;
        this.name = name;
        this.tipo = tipo;
        this.almacen = almacen;
        this.proveedor = proveedor;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
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

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Product{" +
                "id=" + id +
                ", SKU='" + SKU + '\'' +
                ", name='" + name + '\'' +
                ", tipo='" + tipo + '\'' +
                ", almacen=" + almacen.toString() + '\'' +
                ", proveedor='" + proveedor + '\'' +
                ", stock=" + stock +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(SKU, product.SKU) &&
                Objects.equals(name, product.name) &&
                Objects.equals(tipo, product.tipo) &&
                Objects.equals(almacen, product.almacen) &&
                Objects.equals(proveedor, product.proveedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, SKU, name, tipo, stock, almacen, proveedor);
    }
}
