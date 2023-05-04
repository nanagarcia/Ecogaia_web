package com.example.EcogaiaWeb.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Favoritos")
public class Favoritos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Codigo_favoritos;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Codigo_Prod", referencedColumnName = "Codigo_prod", nullable = false)
    @JsonIgnore
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_Usuario", referencedColumnName = "id_Usuario", nullable = false)
    @JsonIgnore
    private Comprador usuario;

    public Favoritos(Integer codigo_favoritos, Producto producto, Comprador usuario) {
        Codigo_favoritos = codigo_favoritos;
        this.producto = producto;
        this.usuario = usuario;
    }

    public Favoritos(){

    }

    public Integer getCodigo_favoritos() {
        return Codigo_favoritos;
    }

    public void setCodigo_favoritos(Integer codigo_favoritos) {
        Codigo_favoritos = codigo_favoritos;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Comprador getUsuario() {
        return usuario;
    }

    public void setUsuario(Comprador usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Favoritos{" +
                "Codigo_favoritos=" + Codigo_favoritos +
                ", producto=" + producto +
                ", usuario=" + usuario +
                '}';
    }
}
