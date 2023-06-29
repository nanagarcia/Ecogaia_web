package com.example.EcogaiaWeb.Servicios;

import java.util.*;

import com.example.EcogaiaWeb.Entidades.*;
import com.example.EcogaiaWeb.Repositorios.*;
import org.springframework.stereotype.Service;

@Service
public class ServicioCarrito {
    RepositorioCarrito repositorio;
    RepositorioProducto repositorioProducto;
    RepositorioUsuario repositorioUsuario;
    RepositorioRepartidor repositorioRepartidor;
    RepositorioVenta repositorioVenta;
    RepositorioDetalle_venta repositorioDetalle_venta;

    public ServicioCarrito(RepositorioCarrito repositorioCarrito, RepositorioProducto repositorioProducto,
            RepositorioUsuario repositorioUsuario, RepositorioRepartidor repositorioRepartidor,
            RepositorioVenta repositorioVenta, RepositorioDetalle_venta repositorioDetalle_venta) {
        this.repositorio = repositorioCarrito;
        this.repositorioProducto = repositorioProducto;
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioRepartidor = repositorioRepartidor;
        this.repositorioVenta = repositorioVenta;
        this.repositorioDetalle_venta = repositorioDetalle_venta;
    }

    public ArrayList<Carrito> listar() {
        return (ArrayList<Carrito>) repositorio.findAll();
    }

    public List<Object[]> cotizacionesUsuario(String correo) {
        return repositorio.cotizaciones(correo);
    }

    public boolean insertar(String correo, Integer codigo, int cantidad) {
        String id_usuario = repositorioUsuario.usuario(correo).get(0)[0].toString();
        Usuario u = repositorioUsuario.findById(Integer.parseInt(id_usuario)).get();
        Producto p = repositorioProducto.findById(codigo).get();

        boolean res = true;

        List<Object[]> cotizaciones = this.cotizacionesUsuario(correo);

        Carrito c = new Carrito(p, u, cantidad);

        for (Object[] objects : cotizaciones) {
            Carrito ct = repositorio.findById(Integer.parseInt(objects[0].toString())).get();
            if (c.getProducto().getProd_Codigo() == ct.getProducto().getProd_Codigo()) {
                res = false;
                repositorio.deleteById(ct.getCodigo_Carrito());
                repositorioProducto.actualizar(p.getProd_Cantidad()+1, p.getProd_Categoria(), p.getProd_Imagen(), p.getProd_Nombre(), p.getProd_Precio(), p.getProd_Codigo());
            }
        }

        if (res) {
            repositorio.save(c);
            repositorioProducto.actualizar(p.getProd_Cantidad()-1, p.getProd_Categoria(), p.getProd_Imagen(), p.getProd_Nombre(), p.getProd_Precio(), p.getProd_Codigo());
        }

        return res;
    }

    public String compra(String correo) {
        /// obtenemos el id del usuario
        String id = repositorioUsuario.usuario(correo).get(0)[0].toString();
        /// obtenemos el usuario apartir del id
        Usuario user = repositorioUsuario.findById(Integer.parseInt(id)).get();
        /// obtenemos un repartidor al azar
        Repartidor repartidor = repositorioRepartidor.findById((int) (Math.random() * 5 + 1)).get();

        /// listamos el carrito
        List<Object[]> productos = this.cotizacionesUsuario(correo);

        /// mensaje de respuesta
        String ms = "No se pudo registrar la compra";

        /// Validamos si las cotizaciones del usuario estan vacias
        if (productos.isEmpty()) {
            ms = "No hay productos que comprar";
        } else {
            /// creamos una venta para asignar la compra
            Venta venta = new Venta(user, repartidor, "En distribucion");
            /// enviamos la venta a la base de datos
            repositorioVenta.save(venta);
            /// validamos la creacion de la venta
            if (repositorioVenta.findById(venta.getVenta_Codigo()).isPresent()) {
                /// recorremos las cotizaciones del usuario
                for (Object[] o : productos) {
                    /// buscamos el carrito mediante el codigo_carrito del objeto[]
                    Carrito c = repositorio.findById(Integer.parseInt(o[0].toString())).get();
                    /// creamos un detalle de venta
                    detalle_venta dt = new detalle_venta(c.getProducto(), venta, c.getCantidad());
                    /// guardamos el detalle de venta
                    repositorioDetalle_venta.save(dt);
                    /// borramos el carrito que se envio a la venta
                    repositorio.delete(c);
                    /// validamos el ingreso del detalle venta
                    if (repositorioDetalle_venta.findById(dt.getCodigo_cotizacion()).isPresent()) {
                        ms = "Se registro una compra de sus productos";
                    }
                }
            }
        }
        return ms;
    }

    public String eliminar(Integer codigo) {
        String ms = "No se elimino el producto";
        if (repositorio.findById(codigo).isPresent()) {
            repositorio.delete(repositorio.findById(codigo).get());
            ms = "El producto se elimino";
        }
        return ms;
    }

    public boolean sumar(Integer codigo) {
        Carrito c = repositorio.findById(codigo).get();

        int prod_codigo = c.getProducto().getProd_Codigo();
        Producto p = repositorioProducto.findById(prod_codigo).get();

        int newCantidad = c.getCantidad() + 1;
        c.setCantidad(newCantidad);
        c.setTotal(newCantidad);

        if (p.getProd_Cantidad() > 0) {
            repositorio.actualizar(c.getCantidad(), c.getTotal(), codigo);
            repositorioProducto.actualizar(p.getProd_Cantidad()-1, p.getProd_Categoria(), p.getProd_Imagen(), p.getProd_Nombre(), p.getProd_Precio(), p.getProd_Codigo());
            return true;
        } else if (p.getProd_Cantidad() == 1) {
            return false;
        }else {
            return false;
        }
    }

    public boolean restar(Integer codigo) {
         Carrito c = repositorio.findById(codigo).get();

        int prod_codigo = c.getProducto().getProd_Codigo();
        Producto p = repositorioProducto.findById(prod_codigo).get();

        int newCantidad = c.getCantidad() - 1;
        c.setCantidad(newCantidad);
        c.setTotal(newCantidad);

        if (c.getCantidad() > 0) {
            repositorio.actualizar(c.getCantidad(), c.getTotal(), codigo);
            repositorioProducto.actualizar(p.getProd_Cantidad()+1, p.getProd_Categoria(), p.getProd_Imagen(), p.getProd_Nombre(), p.getProd_Precio(), p.getProd_Codigo());
            return true;
        } else if (c.getCantidad() == 0) {
            repositorio.deleteById(c.getCodigo_Carrito());;
            return false;
        }else {
            return false;
        }
    }

    public List<Object[]> carritoUsuario(String correo) {
        return repositorio.buscar(correo);
    }

    public List<Object[]> filtrar (String nombre, String correo) {
        List<Object[]>  buscar = this.carritoUsuario(correo);
        List<Object[]> mostrar = new ArrayList<Object[]>();
        mostrar.clear();

        for (Object[] obj: buscar){
            if (obj[5].toString().toLowerCase(Locale.ROOT).startsWith(nombre.toLowerCase(Locale.ROOT))){
                mostrar.add(obj);
            }
        }
        return mostrar;
    }
}
