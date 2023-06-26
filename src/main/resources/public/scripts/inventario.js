import { on_session } from "./index.js";

$(document).ready(function(){
    var tabla_p = $("#tabla-productos")[0]
    var tabla_u = $("#tabla-usuarios")[0]
    $(".barra")[0].style.backgroundColor = "#000000"
    if (sessionStorage.getItem("status") == "repartidor") {
        $("#buttons")[0].style.display = "none"
    }

    $.ajax({
        url:"http://localhost:8080/listarProducto",
        type: "GET",
        dataType: "JSON",
        success: function(respuesta){
            respuesta.forEach(function(invt) {
                tabla_p.innerHTML += '<tr><td><i class="fas fa-trash" onclick="delProducto('+invt.prod_Codigo+')"></i></td><td style="text-align: start;">'
                + invt.prod_Nombre + '</td><td>'
                + invt.prod_Imagen + '</td><td>'
                + invt.prod_Categoria + '</td><td>'
                + invt.prod_Cantidad + '</td><td>'
                + invt.prod_Precio + '</td>' +
                '</td><td><i class="fas fa-pencil"></i></td>'
            });
    
        }
    })

    $("#btn_add_prod").on ("click",() =>{
        var inoots = "<td><button class='btn-insert' onclick='insertproduct()'><span class='fa-solid fa-check'></span></button></td>  <td><input type='text' class='text-light' id='prod_Nombre' placeholder='Nombre'></td> <td><input type='text' id='prod_Imagen' class='text-light' placeholder='URL'></td>    <td><input type='text' id='prod_Categoria' class='text-light' placeholder='Categoria'></td>   <td><input type='text' id='prod_Cantidad' class='text-light' placeholder='Cantidad'></td><td><input type='text' id='prod_Precio' class='text-light' placeholder='Precio'>"
        tabla_p.innerHTML += inoots
        var container = $("#scroll-products")[0]
        container.scrollTop = container.scrollHeight;
    })  

    $.ajax({
        url:"http://localhost:8080/listarUsuario",
        type: "GET",
        dataType: "JSON",
        success: function(respuesta){
            respuesta.forEach(function(invt) {
                tabla_u.innerHTML += '<tr><td><i class="fas fa-trash" onclick="delUsuario('+invt.id_Usuario+')"></i></td><td style="text-align: start;">'
                + invt.usu_nombre + '</td><td>'
                + invt.rol + '</td><td>'
                + invt.usu_correo + '</td><td>'
                + invt.usu_direccion + '</td><td>'
                + invt.usu_telefono + '</td>' +
                '<td><i class="fas fa-pencil"></i></td>'
            });
    
        }
    })

    $("#btn_add_user").on ("click",() =>{
        var inoots = '<td><button class="btn-insert" onclick="insertuser()"><span class="fa-solid fa-check"></span></button></td><td><input type="text" class="text-light" placeholder="Nombre" id="usu_nombre"></td><td><input type="text" class="text-light" placeholder="Rol" id="rol"></td><td><input type="text" class="text-light" placeholder="Correo" id="usu_correo"></td><td><input type="text" class="text-light" placeholder="Dirección" id="usu_direccion"></td><td><input type="text" class="text-light" placeholder="Telefono" id="usu_telefono"></td><td><input type="text" class="text-light" placeholder="Contraseña" id="usu_contrasenia"></td>'
        tabla_u.innerHTML += inoots
        var container = $("#scroll-users")[0]
        container.scrollTop = container.scrollHeight;
    }) 

})