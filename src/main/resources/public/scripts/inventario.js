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
                '</td><td><i class="fas fa-pencil"></i></td></tr>'
            });
        }
    })

    $("#btn_add_prod").on ("click",() =>{
        var inoots = "<tr><td colspan='7'><form class='addGestion'><div><button class='btn-insert' onclick='insertproduct()'><span class='fa-solid fa-check'></span></button></div><input type='text' class='text-light' id='prod_Nombre' placeholder='Nombre'><input type='file' id='prod_Imagen' class='text-light' placeholder='URL'><input type='text' id='prod_Categoria' class='text-light' placeholder='Categoria'><input type='text' id='prod_Cantidad' class='text-light' placeholder='Cantidad'><input type='text' id='prod_Precio' class='text-light' placeholder='Precio'></form></td></tr>"
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
        var inoots = '<tr><td colspan="7"><form class="addGestion" method="post" enctype="multipart/form-data"><div><button class="btn-insert" onclick="insertuser()"><span class="fa-solid fa-check"></span></button></div><input type="text" class="text-light" placeholder="Nombre" id="usu_nombre"><input type="text" class="text-light" placeholder="Correo" id="usu_correo"><input type="text" class="text-light" placeholder="Dirección" id="usu_direccion"><input type="text" class="text-light" placeholder="Telefono" id="usu_telefono"><input type="text" class="text-light" placeholder="Contraseña" id="usu_contrasenia"></form></td></tr>'
        tabla_u.innerHTML += inoots
        var container = $("#scroll-users")[0]
        container.scrollTop = container.scrollHeight;
    }) 

})

$(".btn-hamburguesa").on("click", () => {
    $(".barra")[0].style.display = "block"
  })

  $(".cerrar_barra").on("click", () => {
    $(".barra")[0].style.display = "none"
  })