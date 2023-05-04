package com.example.EcogaiaWeb.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Venta_Codigo;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Codigo_Prod", referencedColumnName = "Prod_Codigo", nullable = false)
    @JsonIgnore
    private Producto producto;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_Usuario", referencedColumnName = "id_Usuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Dis_Codigo", referencedColumnName = "codigo_Dis", nullable = false)
    @JsonIgnore
    private Distribuir distribuir;
    @Column(nullable = false, length = 100)
    private Integer Venta_Cantidad;
    @Column(nullable = false)
    private Integer Venta_Valor;
    @Temporal(TemporalType.DATE)
    private Date Venta_Fecha;
    @Column(nullable = false, length = 15)
    private String Venta_Estado;
    @PrePersist
    public void PrePersist() {
        this.Venta_Fecha = new Date();
    }

    public Venta(Integer venta_Codigo, Producto producto, Usuario usuario, Distribuir distribuir, Integer venta_Cantidad, Integer venta_Valor, Date venta_Fecha, String venta_Estado) {
        Venta_Codigo = venta_Codigo;
        this.producto = producto;
        this.usuario = usuario;
        this.distribuir = distribuir;
        Venta_Cantidad = venta_Cantidad;
        Venta_Valor = venta_Valor;
        Venta_Fecha = venta_Fecha;
        Venta_Estado = venta_Estado;
    }

    public Venta() {
    }

    public Integer getVenta_Codigo() {
        return Venta_Codigo;
    }

    public void setVenta_Codigo(Integer venta_Codigo) {
        Venta_Codigo = venta_Codigo;
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

    public Distribuir getDistribuir() {
        return distribuir;
    }

    public void setDistribuir(Distribuir distribuir) {
        this.distribuir = distribuir;
    }

    public Integer getVenta_Cantidad() {
        return Venta_Cantidad;
    }

    public void setVenta_Cantidad(Integer venta_Cantidad) {
        Venta_Cantidad = venta_Cantidad;
    }

    public Integer getVenta_Valor() {
        return Venta_Valor;
    }

    public void setVenta_Valor(Integer venta_Valor) {
        Venta_Valor = venta_Valor;
    }

    public Date getVenta_Fecha() {
        return Venta_Fecha;
    }

    public void setVenta_Fecha(Date venta_Fecha) {
        Venta_Fecha = venta_Fecha;
    }

    public String getVenta_Estado() {
        return Venta_Estado;
    }

    public void setVenta_Estado(String venta_Estado) {
        Venta_Estado = venta_Estado;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "Venta_Codigo=" + Venta_Codigo +
                ", producto=" + producto +
                ", usuario=" + usuario +
                ", distribuir=" + distribuir +
                ", Venta_Cantidad=" + Venta_Cantidad +
                ", Venta_Valor=" + Venta_Valor +
                ", Venta_Fecha=" + Venta_Fecha +
                ", Venta_Estado='" + Venta_Estado + '\'' +
                '}';
    }
}
