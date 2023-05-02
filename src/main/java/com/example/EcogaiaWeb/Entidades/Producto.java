package com.example.EcogaiaWeb.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Prod_Codigo;
    @Column(nullable = false)
    private Integer Prod_Precio;
    @Column(nullable = false, length = 150)
    private String Prod_Nombre;
    @Column(nullable = false)
    private String Prod_Imagen;
    @Column(nullable = false)
    private Integer Prod_Cantidad;
    @Column(nullable = false, length = 100)
    private String Prod_Categoria;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "Codigo", referencedColumnName = "Codigo", nullable = false)
    @JsonIgnore
    private Orden_pedido orden_pedido;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "Codigo", referencedColumnName = "Codigo", nullable = false)
    @JsonIgnore
    private Exportar exportar;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "Codigo", referencedColumnName = "Codigo", nullable = false)
    @JsonIgnore
    private Cotizacion cotizacion;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "Codigo", referencedColumnName = "Codigo", nullable = false)
    @JsonIgnore
    private Favoritos favoritos;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "Venta_Codigo", referencedColumnName = "Venta_Codigo", nullable = false)
    @JsonIgnore
    private Venta venta;

    public Producto(Integer prod_Codigo, Integer prod_Precio, String prod_Nombre, String prod_Imagen, Integer prod_Cantidad, String prod_Categoria) {

        Prod_Codigo = prod_Codigo;
        Prod_Precio = prod_Precio;
        Prod_Nombre = prod_Nombre;
        Prod_Imagen = prod_Imagen;
        Prod_Cantidad = prod_Cantidad;
        Prod_Categoria = prod_Categoria;
    }

    public Producto() {

    }

    public Integer getProd_Codigo() {
        return Prod_Codigo;
    }

    public void setProd_Codigo(Integer prod_Codigo) {
        Prod_Codigo = prod_Codigo;
    }

    public Integer getProd_Precio() {
        return Prod_Precio;
    }

    public void setProd_Precio(Integer prod_Precio) {
        Prod_Precio = prod_Precio;
    }

    public String getProd_Nombre() {
        return Prod_Nombre;
    }

    public void setProd_Nombre(String prod_Nombre) {
        Prod_Nombre = prod_Nombre;
    }

    public String getProd_Imagen() {
        return Prod_Imagen;
    }

    public void setProd_Imagen(String prod_Imagen) {
        Prod_Imagen = prod_Imagen;
    }

    public Integer getProd_Cantidad() {
        return Prod_Cantidad;
    }

    public void setProd_Cantidad(Integer prod_Cantidad) {
        Prod_Cantidad = prod_Cantidad;
    }

    public String getProd_Categoria() {
        return Prod_Categoria;
    }

    public void setProd_Categoria(String prod_Categoria) {
        Prod_Categoria = prod_Categoria;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "Prod_Codigo=" + Prod_Codigo +
                ", Prod_Precio=" + Prod_Precio +
                ", Prod_Nombre='" + Prod_Nombre + '\'' +
                ", Prod_Imagen='" + Prod_Imagen + '\'' +
                ", Prod_Cantidad=" + Prod_Cantidad +
                ", Prod_Categoria='" + Prod_Categoria + '\'' +
                '}';
    }
}
