import { on_session } from "./index.js";
import { mostrarOculto } from "./index.js";

$(document).ready((e) => {
  $(".barra")[0].style.backgroundColor = "#000000";
  var user = sessionStorage.getItem("user");

  const header = document.getElementById("header");
  const table = document.querySelector(".table-content");

  favoritos(user, table, header)

  $("#com").on("click", () => {
    header.innerHTML =
    '<th scope="col">#</th><th scope="col">Estado</th><th scope="col">Fecha</th><th scope="col">Repartidor</th><th scope="col">Info</th>';
    $.ajax({
        url: "http://localhost:8080/ventasUsuario/"+user,
        type: "GET",
        datatype: "JSON",
        success: (res) => {
            table.innerHTML = ""
            var i = 0
            if (res.length > 0) {
                res.forEach(vent => {
                    i++
                    table.innerHTML +="<tr id='fav"+i+"'><th scope='row'>"+i+"</th><td><p>"+vent.venta_estado+"</p></td><td><h5>"+vent.venta_fecha+"</h5></td><td><p>"+vent.rep_nombre+"</p></td><td><button class='btn btn-success mb-2'>Mas</button></td></tr>"
                });
            } else {
                table.innerHTML = "<tr><td colspan='7'><p class='text-center'>No tienes compras</p></td></tr>"
            }
        }
    });
  })

  $("#fav").on("click", () => {
    favoritos(user, table)
  })

  $.ajax({
    url: "http://localhost:8080/usuario/" + user,
    type: "GET",
    datatype: "JSON",
    success: (res) => {
      if (res.usu_nombre.length > 20) {
        $("#userName")[0].innerHTML = res.usu_nombre.slice(0, 15) + " ...";
      } else {
        $("#userName")[0].innerHTML = res.usu_nombre;
      }

      $("#userEmail")[0].innerHTML = res.usu_correo;
    },
  });

  $.ajax({
    url: "http://localhost:8080/tipsUsuario/" + user,
    type: "GET",
    datatype: "JSON",
    success: (res) => {
      $("#cantBlog")[0].innerHTML = res.length;
    },
  });

  $("#deleteAll").on("click", () => {
    $.ajax({
      url: "http://localhost:8080/usuario/" + sessionStorage.getItem("user"),
      type: "GET",
      datatype: "JSON",
      success: (res) => {
        $.ajax({
          url: "http://localhost:8080/eliminarTodoFavoritos/" + res.id_usuario,
          type: "DELETE",
          success: (res) => {
            alerta.style.background = "#dc3545";
            mostrarOculto(res);
            window.location.reload();
          },
        });
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


function favoritos (user, table) {
    header.innerHTML =
    '<th scope="col">#</th><th scope="col">Imagen</th><th scope="col">Producto</th><th scope="col">Precio</th><th scope="col">Cantidad</th><th scope="col"><i class="fas fa-trash" id="deleteAll"></i></th>';
    $.ajax({
        url: "http://localhost:8080/favoritosUsuario/"+user,
        type: "GET",
        datatype: "JSON",
        success: (res) => {
            table.innerHTML = ""
            $("#cantFav")[0].innerHTML = res.length
            var i = 0
            if (res.length > 0) {
                res.forEach(fav => {
                    i++
                    table.innerHTML +="<tr id='fav"+i+"'><th scope='row'>"+i+"</th><td><img width='20' src='https://frutosalvaje.com/wp-content/uploads/2021/11/Cepillo-de-Bambu_1-1-1536x1536.png' alt='imgProd'></td><td><h5>"+fav.prod_Nombre+"</h5></td><td><p>"+fav.prod_Precio+"</p></td><td><p>"+fav.prod_Cantidad+"</p></td><td><i class='fa fa-trash' onclick='deleteFav("+fav.codigo_favoritos+")'></i></td></tr>"
                });
            } else {
                table.innerHTML = "<tr><td colspan='7'><p class='text-center'>No tienes favoritos</p></td></tr>"
            }
        }
    });
}