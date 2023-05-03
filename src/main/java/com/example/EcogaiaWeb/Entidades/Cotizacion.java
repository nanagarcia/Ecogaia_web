package com.example.EcogaiaWeb.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Cotizacion")
public class Cotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Codigo_cotizacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Codigo_Prod", referencedColumnName = "Codigo_Prod", nullable = false)
    @JsonIgnore
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_Usuario", referencedColumnName = "id_Usuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    @Column(nullable = false, length = 100)
    private Integer Cant_producto;

    @Temporal(TemporalType.DATE)
    private Date cot_fecha;

    @PrePersist
    public void PrePersist(){
        this.cot_fecha = new Date();
    }

    public Cotizacion(Integer codigo_cotizacion, Producto producto, Usuario usuario, Integer cant_producto, Date cot_fecha) {
        Codigo_cotizacion = codigo_cotizacion;
        this.producto = producto;
        this.usuario = usuario;
        Cant_producto = cant_producto;
        this.cot_fecha = cot_fecha;
    }

    public Cotizacion(){

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getCant_producto() {
        return Cant_producto;
    }

    public void setCant_producto(Integer cant_producto) {
        Cant_producto = cant_producto;
    }

    public Date getCot_fecha() {
        return cot_fecha;
    }

    public void setCot_fecha(Date cot_fecha) {
        this.cot_fecha = cot_fecha;
    }

    @Override
    public String toString() {
        return "Cotizacion{" +
                "Codigo_cotizacion=" + Codigo_cotizacion +
                ", producto=" + producto +
                ", usuario=" + usuario +
                ", Cant_producto=" + Cant_producto +
                ", cot_fecha=" + cot_fecha +
                '}';
    }
}
