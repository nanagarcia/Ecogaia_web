package com.example.EcogaiaWeb.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Time;
@Entity
@Table(name = "orden_pedido")
public class Orden_pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Codigo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RUC_Prov", referencedColumnName = "RUC", nullable = false)
    @JsonIgnore
    private Proveedor proveedor;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Codigo_Prod", referencedColumnName = "Prod_Codigo", nullable = false)
    @JsonIgnore
    private Producto producto;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_admin", referencedColumnName = "id_admin", nullable = false)
    @JsonIgnore
    private Administrador administrador;
    @Column(nullable = false)
    private Integer Ped_Valor;
    @Column(nullable = false)
    private Integer Ped_Cantidad;
    @Column(nullable = false)
    private Time Ped_HoraEntrada;
    @Column(nullable = false)
    private Time Ped_HoraSalida;

    public Orden_pedido(Integer codigo, Proveedor proveedor, Producto producto, Administrador administrador, Integer ped_Valor, Integer ped_Cantidad, Time ped_HoraEntrada, Time ped_HoraSalida) {
        Codigo = codigo;
        this.proveedor = proveedor;
        this.producto = producto;
        this.administrador = administrador;
        Ped_Valor = ped_Valor;
        Ped_Cantidad = ped_Cantidad;
        Ped_HoraEntrada = ped_HoraEntrada;
        Ped_HoraSalida = ped_HoraSalida;
    }

    public Orden_pedido() {
    }

    public Integer getCodigo() {
        return Codigo;
    }

    public void setCodigo(Integer codigo) {
        Codigo = codigo;
    }

    public Integer getPed_Valor() {
        return Ped_Valor;
    }

    public void setPed_Valor(Integer ped_Valor) {
        Ped_Valor = ped_Valor;
    }

    public Integer getPed_Cantidad() {
        return Ped_Cantidad;
    }

    public void setPed_Cantidad(Integer ped_Cantidad) {
        Ped_Cantidad = ped_Cantidad;
    }

    public Time getPed_HoraEntrada() {
        return Ped_HoraEntrada;
    }

    public void setPed_HoraEntrada(Time ped_HoraEntrada) {
        Ped_HoraEntrada = ped_HoraEntrada;
    }

    public Time getPed_HoraSalida() {
        return Ped_HoraSalida;
    }

    public void setPed_HoraSalida(Time ped_HoraSalida) {
        Ped_HoraSalida = ped_HoraSalida;
    }

    @Override
    public String toString() {
        return "Orden_pedido{" +
                "Codigo=" + Codigo +
                ", Ped_Valor=" + Ped_Valor +
                ", Ped_Cantidad=" + Ped_Cantidad +
                ", Ped_HoraEntrada=" + Ped_HoraEntrada +
                ", Ped_HoraSalida=" + Ped_HoraSalida +
                '}';
    }
}
