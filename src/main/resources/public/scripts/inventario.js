import { on_session } from "./index.js";
import { mostrarOcultoSuccess } from "./index.js";
import { mostrarOcultoWarning } from "./index.js";
import { mostrarOcultoError } from "./index.js";

$(document).ready(function () {
  var tabla_p = $("#tabla-productos")[0];
  var carrusel_u = $("#carrusel_u")[0];
  var cartas_producto = $("#cartas_producto")[0]; 

  $(".barra")[0].style.backgroundColor = "#000000";
  $(".barra-responsive")[0].style.backgroundColor = "#000000";
  if (sessionStorage.getItem("status") == "repartidor") {
    $("#buttons")[0].style.display = "none";
  }

  function listarnormal (respuesta, tabla_p ){
    
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
        '</td><td><i onClick="actProd(' + invt.prod_Codigo + ')" class="fas fa-pencil"></i></td></tr>'
    });

  }

  function listarresponsive( respuesta, cartas_producto) {

    var cartas_producto = $("#cartas_producto")[0];

    respuesta.forEach(function (invt) {
      cartas_producto.innerHTML +=
      '<div class="contenido-inventario">'+

      '<img src="'+
      invt.prod_Imagen +'" alt="inventario">'+


      '<h2 class="nombre-pro text-success" id="prod_Nom">'+invt.prod_Nombre+'</h2>'+
      '<p class="cate-pro">'+ invt.prod_Categoria +'</p>'+
      '<span class="precio-pro">'+ invt.prod_Precio +'</span>'+
      '<p class="cant-prod">'+ invt.prod_Cantidad +' </p>'+

      '<div class="iconos-invt">'+
          '<button class="boton-pro elimi" type="button" id="eliminar-pro"  onclick="delProducto(' +
          invt.prod_Codigo +')" ><span>Eliminar</span></button>'+
          '<button class="boton-pro  actu" type="button" id="actualizar-pro" onClick="actProd(' + invt.prod_Codigo + ')" ><span>Actualizar</span></button>'+
      '</div>'+

    '</div>'

    });

  }

  $.ajax({
    url: "https://ecogaiaweb-production.up.railway.app/listarProducto",
    type: "GET",
    dataType: "JSON",
    success: function (respuesta) {
        listarnormal(respuesta, tabla_p) 
        listarresponsive(respuesta, cartas_producto)
    },

  });

  $.ajax({
    url: "https://ecogaiaweb-production.up.railway.app/listarUsuario",
    type: "GET",
    dataType: "JSON",
    success: function (respuesta) {
      respuesta.forEach(function (invt) {
        carrusel_u.innerHTML +=
        
          '<li class="card-invt">' +
          '<div class="img"><img src="public/assets/usuario.png" alt="img" draggable="false"></div>' +
          '<h2 class="name">' + invt.usu_nombre + '</h2>' +
          '<span class="rol">' + invt.rol + '</span>' +
          '<span class="direccion">' + invt.usu_direccion + '</span>' +
          '<p class="correo-invt">' + invt.usu_correo + '</p>' +
          '<p class="telefono-invt">' + invt.usu_telefono + '</p>' +

          '<div class="botones-invt">' +
          '<button class="btninvt eliminar" type="button" onclick="delUsuario(' +
          invt.id_Usuario +
          ')"><span>Eliminar</span></button>' +
          '<button class="btninvt actualizar" type="button" onClick="actUsu(\'' +
          invt.usu_correo + '\')"><span>Actualizar</span></button>' +
          '</div>' +

          '</li>'
      });
    },
  });

  $("#insertProducto").submit(function (event) {
    event.preventDefault();

    const newprod = {
      prod_Nombre: $("#prod_Nombre").val(),
      prod_Imagen: "https://www.hods.eu/wp-content/uploads/vasopla_hods_web_01.jpg",
      prod_Categoria: $("#prod_Categoria").val(),
      prod_Cantidad: $("#prod_Cantidad").val(),
      prod_Precio: $("#prod_Precio").val(),
    };

    $.ajax({
      url: "https://ecogaiaweb-production.up.railway.app/insertarProducto",
      type: "POST",
      data: newprod,
      datatype: "text/plain",
      success: (res) => {
        if (res != "El producto no se agrego" && res != "El producto ya existe") {
          var formData = new FormData($(this)[0]);
          $.ajax({
            url: "https://ecogaiaweb-production.up.railway.app/guardarImagen/" + res,
            type: "POST",
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function (response) {
              mostrarOcultoSuccess("El productos se registro correctamente");
            },
            error: function () {
              mostrarOcultoError(res);
            },
          });
        } else {
          mostrarOcultoError(res);
        }
      },
    });
  });
});


$("#actProducto").submit(function (event) {
  event.preventDefault();

  const newprod = {
    prod_Codigo: 0,
    prod_Nombre: $("#actProdNombre").val(),
    prod_Imagen: $("#actProdImagen").val(),
    prod_Categoria: $("#actProdCategoria").val(),
    prod_Cantidad: $("#actProdCantidad").val(),
    prod_Precio: $("#actProdPrecio").val(),
  };

  if ($("#actProdImagen").val() == "") {
    $.ajax({
      url: "https://ecogaiaweb-production.up.railway.app/nombreProducto/" + newprod.prod_Nombre,
      type: "GET",
      datatype: "JSON",
      success: (res) => {
        newprod.prod_Codigo = res[0].prod_Codigo
        newprod.prod_Imagen = res[0].prod_Imagen
        $.ajax({
          url: "https://ecogaiaweb-production.up.railway.app/actualizarProducto",
          type: "PUT",
          data: newprod,
          success: (res) => {
            mostrarOcultoSuccess(res)
          }
        })
      }
    })
  } else {
    $.ajax({
      url: "https://ecogaiaweb-production.up.railway.app/nombreProducto/" + newprod.prod_Nombre,
      type: "GET",
      datatype: "JSON",
      success: (res) => {
        newprod.prod_Codigo = res[0].prod_Codigo
        $.ajax({
          url: "https://ecogaiaweb-production.up.railway.app/actualizarProducto",
          type: "PUT",
          data: newprod,
          success: (res2) => {
            var formData = new FormData($(this)[0]);
            $.ajax({
              url: "https://ecogaiaweb-production.up.railway.app/guardarImagen/" + res[0].prod_Codigo,
              type: "POST",
              data: formData,
              cache: false,
              contentType: false,
              processData: false,
              success: function (response) {
                mostrarOcultoSuccess(res2)
              },
              error: function () {
                mostrarOcultoError("Failed to upload image!");
              },
            });
          }
        })
      }
    })
  }
});

$("#userAct").submit(function (event) {
  event.preventDefault();

  const newuser = {
    usu_nombre: $("#actUsuNombre").val(),
    usu_correo: $("#actUsuCorreo").val(),
    usu_telefono: $("#actUsuTelefono").val(),
    usu_direccion: $("#actUsuDireccion").val(),
    rol: $("#actRol").val(),
    usu_contrasenia: $("#actUsuContrasenia").val(),
  };

  $.ajax({
    url: "https://ecogaiaweb-production.up.railway.app/actualizarUsuario/" + newuser.usu_correo,
    type: "PUT",
    data: newuser,
    datatype: "text/plain",
    success: (res) => {
      mostrarOcultoSuccess(res)
    },
  });
});

/* Carrusel usuarios */

const carousel = document.querySelector(".carousel");
const arrowBtns = document.querySelectorAll(".wrapper i");
const firstCardWidth = carousel.querySelector("card-invt").offsetWidth;

let isDragging = false, startX, startScrollLeft;

arrowBtns.forEach(btn => {

  btn.addEventListener("click", () => {
    carousel.scrollLeft += btn.id === "left" ? -firstCardWidth : firstCardWidth;
  })

});

const dragStart = (e) => {
  isDragging = true;
  carousel.classList.add("dragging");
  startX = e.pageX;
  startScrollLeft = carousel.scrollLeft;
}

const dragging = (e) => {
  if (!isDragging) return;
  carousel.scrollLeft = startScrollLeft - (e.pageX - startX);
}

const dragStop = () => {
  isDragging = false;
  carousel.classList.remove("dragging")
}

carousel.addEventListener("mousemove", dragStart);
carousel.addEventListener("mousemove", dragging);
document.addEventListener("mouseup", dragStop);

