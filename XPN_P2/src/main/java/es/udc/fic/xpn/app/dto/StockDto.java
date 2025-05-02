package es.udc.fic.xpn.app.dto;

import static es.udc.fic.xpn.app.model.ModelConstants.MAX_STOCK_BY_DEFECT;
import static es.udc.fic.xpn.app.model.ModelConstants.MIN_STOCK_BY_DEFECT;

public class StockDto {
    private Long id;
    private Long stock;
    private Long maxStock;
    private Long minStock;
    private Long idAlmacen;
    private Long idProducto;

    public StockDto(Long id, Long stock, Long maxStock, Long minStock,  Long idAlmacen, Long idProducto){
        this.id = id;
        this.stock = stock;
        this.maxStock = maxStock;
        this.minStock = minStock; 
        this.idAlmacen = idAlmacen;
        this.idProducto = idProducto;
    }

    public StockDto(Long stock, Long idAlmacen, Long idProducto){
        this.stock = stock;
        this.maxStock = MAX_STOCK_BY_DEFECT;
        this.minStock = MIN_STOCK_BY_DEFECT; 
        this.idAlmacen = idAlmacen;
        this.idProducto = idProducto;
    }

    public Long getId(){
        return this.id;
    }

    public Long getStock(){
        return this.stock;
    }

    public Long getMaxStock(){
        return this.maxStock;
    }

    public Long getMinStock(){
        return this.minStock;
    }

    public Long getIdAlmacen(){
        return this.idAlmacen;
    }

    public Long getIdProducto(){
        return this.idProducto;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setStock(Long stock){
        this.stock = stock;
    }

    public void setMaxStock(Long maxStock){
        this.maxStock = maxStock;
    }

    public void setMinStock(Long minStock){
        this.minStock = minStock;
    }

    public void setIdAlmacen(Long idAlmacen){
        this.idAlmacen = idAlmacen;
    }

    public void setIdProducto(Long idProducto){
        this.idProducto = idProducto;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Stock{" +
                "id=" + id +
                ", stock='" + stock + '\'' +
                ", maxStock='" + maxStock + '\'' +
                ", minStock='" + minStock +
                ", idAlmacen= " + idAlmacen + 
                ", idProducto= "+ idProducto +
                '}';
    }
}
