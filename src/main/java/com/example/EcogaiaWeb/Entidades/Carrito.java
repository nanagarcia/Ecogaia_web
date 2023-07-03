package com.example.EcogaiaWeb.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "carrito")
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo_Carrito;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Codigo_Prod", referencedColumnName = "Prod_Codigo", nullable = false)
    @JsonIgnore
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_Usuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    @Column(nullable = false, length = 100)
    private Integer cantidad;

    @Column(nullable = false, length = 100)
    private Integer total;

    public Carrito(Producto producto, Usuario usuario, Integer cantidad) {
        this.producto = producto;
        this.usuario = usuario;
        this.cantidad = cantidad;
        this.total = producto.getProd_Precio() * this.cantidad;
    }

    public Carrito() {

    }

    public Integer getCodigo_Carrito () {
        return codigo_Carrito;
    }

    public void setCodigo_Carrito (Integer codigo_Carrito) {
        this.codigo_Carrito = codigo_Carrito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getUsuario () {
        return this.usuario;
    }

    public void setUsuario (Usuario usuario) {
        this.usuario = usuario;
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

    public void setTotal(int cantidad) {
        this.total = producto.getProd_Precio() * cantidad;
    }

    @Override
    public String toString() {
        return "Cotizacion{" +
                " usuario="+ usuario +
                " producto=" + producto +
                ", cantidad=" + cantidad +
                ", total=" + total +
                '}';
    }

}
