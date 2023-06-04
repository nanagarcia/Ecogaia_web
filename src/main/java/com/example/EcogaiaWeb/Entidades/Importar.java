package com.example.EcogaiaWeb.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "importar")
public class Importar {
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
    private Float Imp_Peso;
    @Column(nullable = false, length = 100)
    private Integer Imp_Cantidad;

    public Importar(Integer codigo, Proveedor proveedor, Producto producto, Float imp_Peso, Integer imp_Cantidad) {
        Codigo = codigo;
        this.proveedor = proveedor;
        this.producto = producto;
        Imp_Peso = imp_Peso;
        Imp_Cantidad = imp_Cantidad;
    }

    public Importar() {
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

    public Float getImp_Peso() {
        return Imp_Peso;
    }

    public void setImp_Peso(Float imp_Peso) {
        Imp_Peso = imp_Peso;
    }

    public Integer getImp_Cantidad() {
        return Imp_Cantidad;
    }

    public void setImp_Cantidad(Integer imp_Cantidad) {
        Imp_Cantidad = imp_Cantidad;
    }

    @Override
    public String toString() {
        return "Importar{" +
                "Codigo=" + Codigo +
                ", proveedor=" + proveedor +
                ", producto=" + producto +
                ", Imp_Peso=" + Imp_Peso +
                ", Imp_Cantidad=" + Imp_Cantidad +
                '}';
    }
}
