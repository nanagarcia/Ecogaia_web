package com.example.EcogaiaWeb.Servicios;

import com.example.EcogaiaWeb.Entidades.Administrador;
import com.example.EcogaiaWeb.Entidades.Repartidor;
import com.example.EcogaiaWeb.Entidades.Usuario;
import com.example.EcogaiaWeb.Repositorios.RepositorioAdministrador;
import com.example.EcogaiaWeb.Repositorios.RepositorioRepartidor;
import com.example.EcogaiaWeb.Repositorios.RepositorioUsuario;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServicioUsuario {

    RepositorioUsuario repositorio;
    RepositorioAdministrador repositorioAdministrador;
    RepositorioRepartidor repositorioRepartidor;

    public ServicioUsuario(RepositorioUsuario repository,RepositorioRepartidor repositorioRepartidor, RepositorioAdministrador repositorioAdministrador){
        this.repositorio = repository;
        this.repositorioAdministrador = repositorioAdministrador;
        this.repositorioRepartidor = repositorioRepartidor;
    }

    public String insertar(Usuario u){
        ArrayList<Usuario> usuarios = this.listar();
        String ms = "";
        for (Usuario usuario: usuarios) {
            if (u.getUsu_correo().equals(usuario.getUsu_correo())) {
                ms = "El correo ya se encuentra registrado";
            } else {
                repositorio.save(u);
                if (repositorio.findById(u.getId_Usuario()).isPresent()) {
                    ms = "El usuario se registro correctamente";
                } else {
                    ms = "El usuario no se registro";
                }
            }
        }
        return ms;
    }

    public Map<String, String> login (String email, String password) {
        ArrayList<Usuario> usuarios = this.listar();
        Map<String, String> res = new HashMap<String, String>();
        res.put("error", "Usuario o contraseña incorrectos");
        for (Usuario u: usuarios){
            if (u.getUsu_correo().equals(email) && u.getUsu_contrasenia().equals(password)){
                res.put("res", u.getUsu_correo());
                res.put("rol", u.getRol());
                res.replace("error", "Usuario o contraseña incorrectos", null);
            }
        }
        return res;
    }

    public ArrayList<Usuario> listar(){
        return (ArrayList<Usuario>) repositorio.findAll();
    }

    public String eliminar(Integer id){
        String ms = "No se elimino el usuario";
        if (repositorio.existsById(id)){
            repositorio.deleteById(id);
            ms = "El usuario se elimino correctamente";
        }
        return ms;
    }

    public String actualizar (Usuario u, String correo) {
        String msg = "No se actualizo";
        if (repositorio.actualizar(u.getUsu_nombre(), u.getUsu_telefono(), u.getUsu_direccion(), u.getUsu_correo(), u.getUsu_contrasenia(), correo) > 0) {
            msg = "Actualizado correctamente";
        }
        return msg;
    }

    public List<Object[]> perfil (String correo) {
        return repositorio.usuario(correo);
    }

    public String rol (String correo, String rol) {
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) repositorio.findAll();
        String ms = "no se cambio el rol";

        Usuario user = new Usuario();
        for (Usuario u: usuarios) {
            if (u.getUsu_correo().equals(correo)) user = u;
        }
        if (!Objects.equals(user.getRol(), rol)) {
            user.setRol(rol);
            repositorio.actualizar(user.getUsu_nombre(), user.getUsu_telefono(), user.getUsu_direccion(), user.getUsu_correo(), user.getUsu_contrasenia(), user.getUsu_correo());
            if (user.getRol().equals("admin")) {
                Administrador a = new Administrador(user.getUsu_nombre(), user.getUsu_correo(), user.getUsu_telefono(), user);
                repositorioAdministrador.save(a);
                ms = "el rol se cambio a " + user.getRol();
            } else if (user.getRol().equals("repartidor")) {
                Repartidor r = new Repartidor(user.getUsu_nombre(), user.getUsu_telefono(), user.getUsu_direccion(), "Fuera de servicio");
                repositorioRepartidor.save(r);
                ms = "el rol se cambio a " + user.getRol();
            }
        } else {
            ms = "el rol ya es " + rol;
        }
        return ms;
    }
}
