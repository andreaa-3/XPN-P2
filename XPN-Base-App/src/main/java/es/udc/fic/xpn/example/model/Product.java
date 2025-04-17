package es.udc.fic.xpn.example.model;

import java.util.Objects;

import static es.udc.fic.xpn.example.model.ModelConstants.MAX_STOCK_BY_DEFECT;
import static es.udc.fic.xpn.example.model.ModelConstants.MIN_STOCK_BY_DEFECT;

public class Product {
    private Long id;
    private String sku; // Código alfanumérico que identifica al producto.
    private String name;
    private String tipo;
    private String almacen;
    private String proveedor;
    private Long stock;
    private Long maxStock;
    private Long minStock;

    public Product() {
    }

    public Product(Long id, String sku, String name, String tipo, String almacen, String proveedor, Long stock, Long maxStock, Long minStock) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.tipo = tipo;
        this.almacen = almacen;
        this.proveedor = proveedor;
        this.stock = stock;
        this.maxStock = maxStock;
        this.minStock = minStock;
    }

    public Product(Long id, String sku, String name, String tipo, String almacen, String proveedor, Long stock) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.tipo = tipo;
        this.almacen = almacen;
        this.proveedor = proveedor;
        this.stock = stock;
        this.maxStock = MAX_STOCK_BY_DEFECT;
        this.minStock = MIN_STOCK_BY_DEFECT;
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

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
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
        return "Product{" +
                "id=" + id +
                ", SKU='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", tipo='" + tipo + '\'' +
                ", almacen=" + almacen.toString() + '\'' +
                ", proveedor='" + proveedor + '\'' +
                ", stock='" + stock + '\'' +
                ", maxStock='" + maxStock + '\'' +
                ", minStock='" + minStock +
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
                Objects.equals(almacen, product.almacen) &&
                Objects.equals(proveedor, product.proveedor) &&
                Objects.equals(stock, product.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sku, name, tipo, stock, almacen, proveedor);
    }
}
