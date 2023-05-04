package com.example.EcogaiaWeb.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "exportar")
public class Exportar {
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
    @Column(nullable = false)
    private Float Exp_Peso;
    @Column(nullable = false, length = 100)
    private Integer Exp_Cantidad;

    public Exportar(Integer codigo, Proveedor proveedor, Producto producto, Float exp_Peso, Integer exp_Cantidad) {
        Codigo = codigo;
        this.proveedor = proveedor;
        this.producto = producto;
        Exp_Peso = exp_Peso;
        Exp_Cantidad = exp_Cantidad;
    }

    public Exportar() {

    }

    public Integer getCodigo() {
        return Codigo;
    }

    public void setCodigo(Integer codigo) {
        Codigo = codigo;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Float getExp_Peso() {
        return Exp_Peso;
    }

    public void setExp_Peso(Float exp_Peso) {
        Exp_Peso = exp_Peso;
    }

    public Integer getExp_Cantidad() {
        return Exp_Cantidad;
    }

    public void setExp_Cantidad(Integer exp_Cantidad) {
        Exp_Cantidad = exp_Cantidad;
    }

    @Override
    public String toString() {
        return "Exportar{" +
                "Codigo=" + Codigo +
                ", proveedor=" + proveedor +
                ", producto=" + producto +
                ", Exp_Peso=" + Exp_Peso +
                ", Exp_Cantidad=" + Exp_Cantidad +
                '}';
    }
}
