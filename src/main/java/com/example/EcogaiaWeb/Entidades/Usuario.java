package com.example.EcogaiaWeb.Entidades;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Comprador")
public class Comprador {

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
    private String usu_contraseña;

    @OneToMany(mappedBy = "Comprador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Prod_tips> prod_tips;

    @OneToMany(mappedBy = "Comprador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Venta> venta;

    @OneToMany(mappedBy = "Comprador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Cotizacion> cotizacion;


    public Comprador(Integer id_Usuario, String usu_nombre, String usu_telefono, String usu_direccion, String usu_correo, String usu_contraseña) {
        this.id_Usuario = id_Usuario;
        this.usu_nombre = usu_nombre;
        this.usu_telefono = usu_telefono;
        this.usu_direccion = usu_direccion;
        this.usu_correo = usu_correo;
        this.usu_contraseña = usu_contraseña;
    }

    public Comprador() {
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

    public String getUsu_contraseña() {
        return usu_contraseña;
    }

    public void setUsu_contraseña(String usu_contraseña) {
        this.usu_contraseña = usu_contraseña;
    }

    @Override
    public String toString() {
        return "Comprador{" +
                "id_Usuario=" + id_Usuario +
                ", usu_nombre='" + usu_nombre + '\'' +
                ", usu_telefono='" + usu_telefono + '\'' +
                ", usu_direccion='" + usu_direccion + '\'' +
                ", usu_correo='" + usu_correo + '\'' +
                ", usu_contraseña='" + usu_contraseña + '\'' +
                '}';
    }
}
