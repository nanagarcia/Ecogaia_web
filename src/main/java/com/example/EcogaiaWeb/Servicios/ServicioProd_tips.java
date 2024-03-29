package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Prod_tips;
import com.example.EcogaiaWeb.Repositorios.RepositorioProd_tips;
import com.example.EcogaiaWeb.Repositorios.RepositorioUsuario;
import com.example.EcogaiaWeb.Entidades.Usuario;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class ServicioProd_tips {

    RepositorioProd_tips repositorio;
    RepositorioUsuario repositorioUsuario;
    ArrayList<Prod_tips> mostrar = new ArrayList<Prod_tips>();

    public ServicioProd_tips(RepositorioProd_tips repository, RepositorioUsuario repositorioUsuario){
        this.repositorio = repository;
        this.repositorioUsuario = repositorioUsuario;
    }

    public String insertar(String correo, Prod_tips prod){
        String ms = "No se agrego el tip";
        if (repositorio.insert(prod.getCuerpo(), prod.getFecha(), prod.getTitulo(), correo) == 1) {
            ms = "El tip se agrego correctamente";
        };
        return ms;
    }

    public ArrayList<Prod_tips> listar(){
        return (ArrayList<Prod_tips>) repositorio.findAll();
    }

    public String eliminar(Integer codigo){
        String ms = "No se pudo eliminar el tip";
        if(repositorio.existsById(codigo)){
            repositorio.deleteById(codigo);
            ms = "El tip se elimino correctamente";
        }
        return ms;
    }

    public ArrayList<Prod_tips> titulo (String titulo) {
        ArrayList<Prod_tips> tips = (ArrayList<Prod_tips>) repositorio.findAll();
        mostrar.clear();
        for (Prod_tips i: tips){
            if (i.getTitulo().toLowerCase(Locale.ROOT).startsWith(titulo.toLowerCase(Locale.ROOT))){
                mostrar.add(i);
            }
        }
        return mostrar;
    }


    public ArrayList<Prod_tips> tipsUsuario (String correo) {
        List<Object[]> Usuario = repositorioUsuario.usuario(correo);
        ArrayList<Prod_tips> pt =  (ArrayList<Prod_tips>) repositorio.findAll();
        ArrayList<Prod_tips> mostrar = new ArrayList<Prod_tips>();
        int id_usuario = Integer.parseInt(Usuario.get(0)[0].toString());

        Usuario u = repositorioUsuario.findById(id_usuario).get();

        for (Prod_tips p : pt) {
            if (p.getUsuario().getId_Usuario() == u.getId_Usuario()) {
                mostrar.add(p);
            }
        }

        return mostrar;
    }
    public List<Map<String,String>> ordenarTipUsuario (){
        List<Object[]> tips = repositorio.sortTipUs();
        List<Map<String,String>> mostrar = new ArrayList<Map<String, String>>();

        for (Object[] obj : tips){
            Map<String, String> Datos = new HashMap<String,String>();
            Datos.put("codigo_tip",obj[0].toString());
            Datos.put("comp_usuario",obj[1].toString());
            Datos.put("fecha",obj[2].toString());
            Datos.put("id_usuario",obj[3].toString());
            Datos.put("titulo",obj[4].toString());
            Datos.put("cuerpo",obj[5].toString());


            mostrar.add(Datos);
        }
        return mostrar;
    }
    public List<Map<String,String>> ordenarTipFecha (){
        List<Object[]> tips = repositorio.sortTipDate();
        List<Map<String,String>> mostrar = new ArrayList<Map<String, String>>();

        for (Object[] obj : tips){
            Map<String, String> Datos = new HashMap<String,String>();
            Datos.put("codigo_tip",obj[0].toString());
            Datos.put("comp_usuario",obj[1].toString());
            Datos.put("fecha",obj[2].toString());
            Datos.put("id_usuario",obj[3].toString());
            Datos.put("titulo",obj[4].toString());
            Datos.put("cuerpo",obj[5].toString());


            mostrar.add(Datos);
        }
        return mostrar;
    }
    public List<Map<String,String>> ordenarTipAz (){
        List<Object[]> tips = repositorio.sortTipAz();
        List<Map<String,String>> mostrar = new ArrayList<Map<String, String>>();

        for (Object[] obj : tips){
            Map<String, String> Datos = new HashMap<String,String>();
            Datos.put("codigo_tip",obj[0].toString());
            Datos.put("comp_usuario",obj[1].toString());
            Datos.put("fecha",obj[2].toString());
            Datos.put("id_usuario",obj[3].toString());
            Datos.put("titulo",obj[4].toString());
            Datos.put("cuerpo",obj[5].toString());


            mostrar.add(Datos);
        }
        return mostrar;
    }


}
