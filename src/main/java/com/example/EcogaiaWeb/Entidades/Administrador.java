package com.example.EcogaiaWeb.Entidades;


import jakarta.persistence.*;

@Entity
@Table(name = "administrador")
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_admin;
    @Column(nullable = false, length = 100)
    private String admin_nombre;
    @Column(nullable = false, length = 100)
    private String admin_correo;
    @Column(nullable = false, length = 100)
    private String admin_telefono;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Administrador(String admin_nombre, String admin_correo, String admin_telefono, Usuario usuario) {
        this.admin_nombre = admin_nombre;
        this.admin_correo = admin_correo;
        this.admin_telefono = admin_telefono;
        this.usuario = usuario;
    }

    public Administrador() {
    }

    public Integer getId_admin() {
        return id_admin;
    }

    public void setId_admin(Integer id_admin) {
        this.id_admin = id_admin;
    }

    public String getAdmin_nombre() {
        return admin_nombre;
    }

    public void setAdmin_nombre(String admin_nombre) {
        this.admin_nombre = admin_nombre;
    }

    public String getAdmin_correo() {
        return admin_correo;
    }

    public void setAdmin_correo(String admin_correo) {
        this.admin_correo = admin_correo;
    }

    public String getAdmin_telefono() {
        return admin_telefono;
    }

    public void setAdmin_telefono(String admin_telefono) {
        this.admin_telefono = admin_telefono;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "id_admin=" + id_admin +
                ", admin_nombre='" + admin_nombre + '\'' +
                ", admin_correo='" + admin_correo + '\'' +
                ", admin_telefono='" + admin_telefono + '\'' +
                '}';
    }
}
