package com.example.EcogaiaWeb.Entidades;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Usuario;
    @Column(nullable = false, length = 100)
    private String usu_nombre;
    @Column(nullable = false, length = 100)
    private String usu_telefono;
    @Column(nullable = false, length = 20)
    private String usu_direccion;
    @Column(nullable = false, length = 100)
    private String usu_correo;
    @Column(nullable = false, length = 20)
    private String usu_contrasenia;
    @Column(nullable = false,length = 50)
    private String rol = "usuario";

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Prod_tips> prod_tips;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Venta> venta;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Favoritos> favoritos;

    public Usuario(Integer id_Usuario, String usu_nombre, String usu_telefono, String usu_direccion, String usu_correo, String usu_contrasenia) {
        this.id_Usuario = id_Usuario;
        this.usu_nombre = usu_nombre;
        this.usu_telefono = usu_telefono;
        this.usu_direccion = usu_direccion;
        this.usu_correo = usu_correo;
        this.usu_contrasenia = usu_contrasenia;
    }

    public Usuario() {
    }

    public Integer getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(Integer id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getUsu_nombre() {
        return usu_nombre;
    }

    public void setUsu_nombre(String usu_nombre) {
        this.usu_nombre = usu_nombre;
    }

    public String getUsu_telefono() {
        return usu_telefono;
    }

    public void setUsu_telefono(String usu_telefono) {
        this.usu_telefono = usu_telefono;
    }

    public String getUsu_direccion() {
        return usu_direccion;
    }

    public void setUsu_direccion(String usu_direccion) {
        this.usu_direccion = usu_direccion;
    }

    public String getUsu_correo() {
        return usu_correo;
    }

    public void setUsu_correo(String usu_correo) {
        this.usu_correo = usu_correo;
    }

    public String getUsu_contrasenia() {
        return usu_contrasenia;
    }

    public void setUsu_contrasenia(String usu_contrasenia) {
        this.usu_contrasenia = usu_contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Set<Prod_tips> getProd_tips() {
        return prod_tips;
    }

    public void setProd_tips(Set<Prod_tips> prod_tips) {
        this.prod_tips = prod_tips;
    }

    public Set<Venta> getVenta() {
        return venta;
    }

    public void setVenta(Set<Venta> venta) {
        this.venta = venta;
    }

    public Set<Favoritos> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(Set<Favoritos> favoritos) {
        this.favoritos = favoritos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_Usuario=" + id_Usuario +
                ", usu_nombre='" + usu_nombre + '\'' +
                ", usu_telefono='" + usu_telefono + '\'' +
                ", usu_direccion='" + usu_direccion + '\'' +
                ", usu_correo='" + usu_correo + '\'' +
                ", usu_contrasenia='" + usu_contrasenia + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
