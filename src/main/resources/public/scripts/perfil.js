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
        url: "http://localhost:8080/comprasUsuario/"+user,
        type: "GET",
        datatype: "JSON",
        success: (res) => {
            table.innerHTML = ""
            var i = 0
            if (res.length > 0) {
                res.forEach(vent => {
                    i++
                    table.innerHTML +="<tr id='fav"+i+"'><th scope='row'>"+i+"</th><td><p>"+vent.venta_estado+"</p></td><td><h5>"+vent.venta_fecha+"</h5></td><td><p>"+vent.rep_nombre+"</p></td><td><button data-bs-toggle='modal'  data-bs-target='#car"+vent.venta_codigo+"'>Mas</button></td></tr>" +
                      '<!-- Modal --><div  class="modal fade w-25"  id="car'+vent.venta_codigo+'"  tabindex="-1"  aria-labelledby="exampleModalLabel"  aria-hidden="true"><div class="modal-dialog modal-dialog-centered"><div class="modal-content"><div class="modal-header"><h1 class="modal-title fs-5 text-success"id="exampleModalLabel">Mas información</h1><button type="button"class="btn-close"data-bs-dismiss="modal"aria-label="Close"></button></div><div class="modal-body">' +
                        "<h1 class='text-center text-success'>" +
                        vent.rep_nombre +
                        "</h1><p class='contenido '>" +
                        vent.venta_fecha +
                        '</p><p class="contenido" >Productos</p><div id="body'+vent.venta_codigo+'" class="body-compra"></div></div></div></div></div></div></div>'
                    $.ajax({
                      url: "http://localhost:8080/productosCompra/" + user + "/" + vent.venta_codigo,
                      type: "GET",
                      datatype: "JSON",
                      success: (res1) => {
                        const prods = document.getElementById("body"+vent.venta_codigo)  
                        res1.forEach((prod) => {
                          prods.innerHTML += "<div class='compra'><h1 class='articulos text-center text-success'>" + prod.prod_nombre +
                          "</h1><p>Categoria: " + prod.prod_categoria + "</p>"+
                          "<p>Cantidad: " + prod.prod_cantidad + "</p>"
                          "<p>Precio: $" + prod.prod_precio + "</p></div>";
                        })
                      }
                    })
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