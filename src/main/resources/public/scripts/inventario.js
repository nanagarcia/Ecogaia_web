import { on_session } from "./index.js";

$(document).ready(function () {
  var tabla_p = $("#tabla-productos")[0];
  var tabla_u = $("#tabla-usuarios")[0];
  $(".barra")[0].style.backgroundColor = "#000000";
  if (sessionStorage.getItem("status") == "repartidor") {
    $("#buttons")[0].style.display = "none";
  }

  $.ajax({
    url: "http://localhost:8080/listarProducto",
    type: "GET",
    dataType: "JSON",
    success: function (respuesta) {
      respuesta.forEach(function (invt) {
        tabla_p.innerHTML +=
          '<tr><td><i class="fas fa-trash" onclick="delProducto(' +
          invt.prod_Codigo +
          ')"></i></td><td style="text-align: start;">' +
          invt.prod_Nombre +
          "</td><td>" +
          invt.prod_Imagen +
          "</td><td>" +
          invt.prod_Categoria +
          "</td><td>" +
          invt.prod_Cantidad +
          "</td><td>" +
          invt.prod_Precio +
          "</td>" +
          '</td><td><i class="fas fa-pencil"></i></td></tr>';
      });
    },
  });

  $.ajax({
    url: "http://localhost:8080/listarUsuario",
    type: "GET",
    dataType: "JSON",
    success: function (respuesta) {
      respuesta.forEach(function (invt) {
        tabla_u.innerHTML +=
          '<tr><td><i class="fas fa-trash" onclick="delUsuario(' +
          invt.id_Usuario +
          ')"></i></td><td style="text-align: start;">' +
          invt.usu_nombre +
          "</td><td>" +
          invt.rol +
          "</td><td>" +
          invt.usu_correo +
          "</td><td>" +
          invt.usu_direccion +
          "</td><td>" +
          invt.usu_telefono +
          "</td>" +
          '<td><i class="fas fa-pencil"></i></td>';
      });
    },
  });
  
  $("#insertProducto").submit(function (event) {
    event.preventDefault();
  
    const newprod = {
      prod_Nombre: $("#prod_Nombre").val(),
      prod_Imagen: $("#prod_Imagen").val(),
      prod_Categoria: $("#prod_Categoria").val(),
      prod_Cantidad: $("#prod_Cantidad").val(),
      prod_Precio: $("#prod_Precio").val(),
    };
  
    $.ajax({
      url: "http://localhost:8080/insertarProducto",
      type: "POST",
      data: newprod,
      datatype: "text/plain",
      success: (res) => {
        if (res != "El producto no se agrego" && res != "El producto ya existe") {
          var formData = new FormData($(this)[0]);
          $.ajax({
            url: "http://localhost:8080/guardarImagen/" + res,
            type: "POST",
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function (response) {
              alert(response);
            },
            error: function () {
              alert("Failed to upload image!");
            },
          });
        } else {
          alert(res);
        }
      },
    });
  });
});

$(".btn-hamburguesa").on("click", () => {
  $(".barra")[0].style.display = "block";
});

$(".cerrar_barra").on("click", () => {
  $(".barra")[0].style.display = "none";
});
