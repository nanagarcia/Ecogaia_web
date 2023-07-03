package com.example.EcogaiaWeb.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Venta_Codigo;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_Usuario", referencedColumnName = "id_Usuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_repartidor", referencedColumnName = "id_Rep", nullable = false)
    @JsonIgnore
    private Repartidor repartidor;
    @Column(nullable = false, length = 15)
    private String Venta_Estado;
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<detalle_venta> detalleventa;
    @Temporal(TemporalType.DATE)
    private Date Venta_Fecha = new Date();

    public Venta(Usuario usuario, Repartidor repartidor,String venta_Estado) {
        this.usuario = usuario;
        this.repartidor = repartidor;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
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

    public Set<detalle_venta> getCotizacion() {
        return detalleventa;
    }

    public void setCotizacion(Set<detalle_venta> detalleventa) {
        this.detalleventa = detalleventa;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "Venta_Codigo=" + Venta_Codigo +
                ", usuario=" + usuario +
                ", repartidor=" + repartidor +
                ", Venta_Fecha=" + Venta_Fecha +
                ", Venta_Estado='" + Venta_Estado + '\'' +
                '}';
    }
}
