package com.example.EcogaiaWeb.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.repository.cdi.Eager;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

@Entity
@Table(name = "distribuir")
public class Distribuir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo_dis;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_Repartidor", referencedColumnName = "id_Rep", nullable = false)
    @JsonIgnore
    private Repartidor repartidor;

    @Column(nullable = false)
    private Time dis_tiempo;
    @Column(nullable = false)
    private String dis_estado;
    @Temporal(TemporalType.DATE)
    private Date dis_fecha;

    @OneToMany(mappedBy = "distribuir", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Venta> venta;

    public Distribuir(Integer codigo_dis, Time dis_tiempo, String dis_estado, Date dis_fecha) {
        this.codigo_dis = codigo_dis;
        this.dis_tiempo = dis_tiempo;
        this.dis_estado = dis_estado;
        this.dis_fecha = dis_fecha;
    }

    public Distribuir() {
    }

    public Integer getCodigo_dis() {
        return codigo_dis;
    }

    public void setCodigo_dis(Integer codigo_dis) {
        this.codigo_dis = codigo_dis;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public Time getDis_tiempo() {
        return dis_tiempo;
    }

    public void setDis_tiempo(Time dis_tiempo) {
        this.dis_tiempo = dis_tiempo;
    }

    public String getDis_estado() {
        return dis_estado;
    }

    public void setDis_estado(String dis_estado) {
        this.dis_estado = dis_estado;
    }

    public Date getDis_fecha() {
        return dis_fecha;
    }

    public void setDis_fecha(Date dis_fecha) {
        this.dis_fecha = dis_fecha;
    }

    public Set<Venta> getVenta() {
        return venta;
    }

    public void setVenta(Set<Venta> venta) {
        this.venta = venta;
    }

    @Override
    public String toString() {
        return "Distribuir{" +
                "codigo_dis=" + codigo_dis +
                ", dis_tiempo=" + dis_tiempo +
                ", dis_estado='" + dis_estado + '\'' +
                ", dis_fecha=" + dis_fecha +
                '}';
    }
}
