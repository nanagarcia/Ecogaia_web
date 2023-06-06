package com.example.EcogaiaWeb.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "prod_tips")
public class Prod_tips {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo_tip;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_Usuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    @Column(nullable = false, length = 100)
    private String comp_usuario;
    @Column(nullable = false, length = 200)
    private String titulo;
    @Column(nullable = false, length = 500)
    private String cuerpo;
    @Temporal(TemporalType.DATE)
    private Date fecha = new Date();

    public Prod_tips(Integer codigo_tip, Usuario usuario, String comp_usuario, String titulo, String cuerpo) {
        this.codigo_tip = codigo_tip;
        this.usuario = usuario;
        this.comp_usuario = comp_usuario;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
    }

    public Prod_tips() {
    }

    public Integer getCodigo_tip() {
        return codigo_tip;
    }

    public void setCodigo_tip(Integer codigo_tip) {
        this.codigo_tip = codigo_tip;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getComp_usuario() {
        return comp_usuario;
    }

    public void setComp_usuario(String comp_usuario) {
        this.comp_usuario = comp_usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Prod_tips{" +
                "codigo_tip=" + codigo_tip +
                ", usuario=" + usuario +
                ", comp_usuario='" + comp_usuario + '\'' +
                ", titulo='" + titulo + '\'' +
                ", cuerpo='" + cuerpo + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
