package com.example.EcogaiaWeb.Entidades;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @Column(nullable = false, length = 20)
    private Integer RUC;
    @Column(nullable = false, length = 100)
    private String Prov_Nombre;
    @Column(nullable = false, length = 15)
    private String Prov_Direccion;
    @Column(nullable = false, length = 100)
    private String Prov_Telefono;
    @Column(nullable = false, length = 100)
    private String Prov_Correo;
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Exportar> exportar;
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Orden_pedido> orden_pedido;

    public Proveedor(Integer RUC, String prov_Nombre, String prov_Direccion, String prov_Telefono, String prov_Correo) {
        this.RUC = RUC;
        Prov_Nombre = prov_Nombre;
        Prov_Direccion = prov_Direccion;
        Prov_Telefono = prov_Telefono;
        Prov_Correo = prov_Correo;
    }

    public Proveedor() {

    }

    public Integer getRUC() {
        return RUC;
    }

    public void setRUC(Integer RUC) {
        this.RUC = RUC;
    }

    public String getProv_Nombre() {
        return Prov_Nombre;
    }

    public void setProv_Nombre(String prov_Nombre) {
        Prov_Nombre = prov_Nombre;
    }

    public String getProv_Direccion() {
        return Prov_Direccion;
    }

    public void setProv_Direccion(String prov_Direccion) {
        Prov_Direccion = prov_Direccion;
    }

    public String getProv_Telefono() {
        return Prov_Telefono;
    }

    public void setProv_Telefono(String prov_Telefono) {
        Prov_Telefono = prov_Telefono;
    }

    public String getProv_Correo() {
        return Prov_Correo;
    }

    public void setProv_Correo(String prov_Correo) {
        Prov_Correo = prov_Correo;
    }

    public Set<Exportar> getExportar() {
        return exportar;
    }

    public void setExportar(Set<Exportar> exportar) {
        this.exportar = exportar;
    }

    public Set<Orden_pedido> getOrden_pedido() {
        return orden_pedido;
    }

    public void setOrden_pedido(Set<Orden_pedido> orden_pedido) {
        this.orden_pedido = orden_pedido;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "RUC=" + RUC +
                ", Prov_Nombre='" + Prov_Nombre + '\'' +
                ", Prov_Direccion='" + Prov_Direccion + '\'' +
                ", Prov_Telefono='" + Prov_Telefono + '\'' +
                ", Prov_Correo='" + Prov_Correo + '\'' +
                '}';
    }
}
