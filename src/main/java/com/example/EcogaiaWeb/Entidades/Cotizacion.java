package com.example.EcogaiaWeb.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "detalle_venta")
public class Cotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Codigo_cotizacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Codigo_Prod", referencedColumnName = "Prod_Codigo", nullable = false)
    @JsonIgnore
    private Producto producto = new Producto();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Codigo_Venta", referencedColumnName = "Venta_Codigo", nullable = false)
    @JsonIgnore
    private Venta venta;

    @Column(nullable = false, length = 100)
    private Integer cantidad;

    @Column(nullable = false, length = 100)
    private Integer total;

    public Cotizacion(Integer codigo_cotizacion, Producto producto, Venta venta, Integer cantidad) {
        Codigo_cotizacion = codigo_cotizacion;
        this.producto = producto;
        this.venta = venta;
        this.cantidad = cantidad;
        this.total = producto.getProd_Precio() * this.cantidad;
    }

    public Cotizacion() {
    }

    public Integer getCodigo_cotizacion() {
        return Codigo_cotizacion;
    }

    public void setCodigo_cotizacion(Integer codigo_cotizacion) {
        Codigo_cotizacion = codigo_cotizacion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Cotizacion{" +
                "Codigo_cotizacion=" + Codigo_cotizacion +
                ", producto=" + producto +
                ", venta=" + venta +
                ", cantidad=" + cantidad +
                ", total=" + total +
                '}';
    }
}
