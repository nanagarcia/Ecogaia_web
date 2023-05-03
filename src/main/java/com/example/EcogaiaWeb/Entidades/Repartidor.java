package com.example.EcogaiaWeb.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "repartidor")
public class Repartidor {
    @Id
    @Column(nullable = false, length = 100)
    private String nombre_Repartidor;
    @Column(nullable = false,length = 100)
    private String telefono_Repartidor;
    @Column(nullable = false,length = 50)
    private String direccion_Repartidor;
    @Column(nullable = false,length = 20)
    private String dispo_Repartidor;

    public Repartidor(String nombre_Repartidor, String telefono_Repartidor, String direccion_Repartidor, String dispo_Repartidor) {
        this.nombre_Repartidor = nombre_Repartidor;
        this.telefono_Repartidor = telefono_Repartidor;
        this.direccion_Repartidor = direccion_Repartidor;
        this.dispo_Repartidor = dispo_Repartidor;
    }

    public Repartidor() {
    }

    public String getNombre_Repartidor() {
        return nombre_Repartidor;
    }

    public void setNombre_Repartidor(String nombre_Repartidor) {
        this.nombre_Repartidor = nombre_Repartidor;
    }

    public String getTelefono_Repartidor() {
        return telefono_Repartidor;
    }

    public void setTelefono_Repartidor(String telefono_Repartidor) {
        this.telefono_Repartidor = telefono_Repartidor;
    }

    public String getDireccion_Repartidor() {
        return direccion_Repartidor;
    }

    public void setDireccion_Repartidor(String direccion_Repartidor) {
        this.direccion_Repartidor = direccion_Repartidor;
    }

    public String getDispo_Repartidor() {
        return dispo_Repartidor;
    }

    public void setDispo_Repartidor(String dispo_Repartidor) {
        this.dispo_Repartidor = dispo_Repartidor;
    }

    @Override
    public String toString() {
        return "Repartidor{" +
                "nombre_Repartidor='" + nombre_Repartidor + '\'' +
                ", telefono_Repartidor='" + telefono_Repartidor + '\'' +
                ", direccion_Repartidor='" + direccion_Repartidor + '\'' +
                ", dispo_Repartidor='" + dispo_Repartidor + '\'' +
                '}';
    }
}
