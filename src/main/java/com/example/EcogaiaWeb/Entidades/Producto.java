package com.example.EcogaiaWeb.Entidades;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Prod_Codigo;
    @Column(nullable = false)
    private Integer Prod_Precio;
    @Column(nullable = false, length = 150)
    private String Prod_Nombre;
    @Column(nullable = true)
    private String Prod_Imagen;
    @Column(nullable = false)
    private Integer Prod_Cantidad;
    @Column(nullable = false, length = 100)
    private String Prod_Categoria;
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<detalle_venta> detalleventa;
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Carrito> carrito;
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Favoritos> favoritos;

    public Producto(Integer prod_Codigo, Integer prod_Precio, String prod_Nombre, String prod_Imagen,
            Integer prod_Cantidad, String prod_Categoria) {
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

    public Set<detalle_venta> getCotizacion() {
        return detalleventa;
    }

    public void setCotizacion(Set<detalle_venta> detalleventa) {
        this.detalleventa = detalleventa;
    }

    public Set<Favoritos> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(Set<Favoritos> favoritos) {
        this.favoritos = favoritos;
    }

    public Set<detalle_venta> getVenta() {
        return detalleventa;
    }

    public void setVenta(Set<detalle_venta> venta) {
        this.detalleventa = venta;
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
