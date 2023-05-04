package com.example.EcogaiaWeb.Entidades;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Repartidor")
public class Repartidor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Rep;
    @Column(nullable = false, length = 100)
    private String Rep_Nombre;
    @Column(nullable = false,length = 100)
    private String telefono;
    @Column(nullable = false,length = 50)
    private String direccion;
    @Column(nullable = false,length = 20)
    private String Rep_Disponibilidad;

    @OneToMany(mappedBy = "Repartidor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Distribuir> Distribuir;

    public Repartidor(Integer id_Rep, String rep_Nombre, String telefono, String direccion, String rep_Disponibilidad) {
        this.id_Rep = id_Rep;
        Rep_Nombre = rep_Nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        Rep_Disponibilidad = rep_Disponibilidad;
    }

    public Repartidor(){

    }

    public Integer getId_Rep() {
        return id_Rep;
    }

    public void setId_Rep(Integer id_Rep) {
        this.id_Rep = id_Rep;
    }

    public String getRep_Nombre() {
        return Rep_Nombre;
    }

    public void setRep_Nombre(String rep_Nombre) {
        Rep_Nombre = rep_Nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRep_Disponibilidad() {
        return Rep_Disponibilidad;
    }

    public void setRep_Disponibilidad(String rep_Disponibilidad) {
        Rep_Disponibilidad = rep_Disponibilidad;
    }

    @Override
    public String toString() {
        return "Repartidor{" +
                "id_Rep=" + id_Rep +
                ", Rep_Nombre='" + Rep_Nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", Rep_Disponibilidad='" + Rep_Disponibilidad + '\'' +
                '}';
    }
}
