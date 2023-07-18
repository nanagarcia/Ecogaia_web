import { on_session } from "./index.js";

$(document).ready(function () {

    $(".barra")[0].style.backgroundColor = "#000000"
    $(".barra-responsive")[0].style.backgroundColor = "#000000";

    var cartas_repartidor = $("#inventario-repartidor")[0]

    function listarnormal() {
        $.ajax({
            url: "http://localhost:8080/distribuir/" + sessionStorage.getItem("user"),
            type: "GET",
            dataType: "JSON",
            success: function (respuesta) {
                console.log(respuesta)
                respuesta.forEach(function (rep) {
                    tabla.innerHTML += '<tr><td>'
                        + rep.venta_Codigo + '</td><td>'
                        + rep.id_Usuario + '</td><td>'
                        + rep.usu_nombre + '</td><td>'
                        + rep.usu_direccion + '</td><td>'
                        + rep.usu_telefono + '</td><td><button class="btn-insert" data-bs-toggle="modal"  data-bs-target="#exampleModal' + rep.venta_Codigo + '"><img src="../../public/assets/productos.png" alt=""></button></td>'

                        + '<div  class="modal m-auto"  id="exampleModal' + rep.venta_Codigo +
                        '"  tabindex="-1"  aria-labelledby="exampleModalLabel"  aria-hidden="true"><div class="modal-dialog modal-dialog-centered"><div class="modal-content" style="background-color: #000000"><div class="modal-header"><h1 class="modal-title fs-5 text-success"id="exampleModalLabel">Productos</h1><button type="button"class="fa fa-xmark"data-bs-dismiss="modal"aria-label="Close" style="color: white;background-color: transparent;"></button></div><div class="modal-body"><i>' +
                        '</i><h1 class="text-center text-success">' + rep.usu_nombre + '</h1><p>' + rep.usu_direccion + '</p><div id="p' + rep.id_Usuario + '"></div></div></div></div></div></div></div>';

                    $.ajax({
                        url: "http://localhost:8080/ventasRepartidor/" + sessionStorage.getItem("user") + "/" + rep.id_Usuario,
                        type: "GET",
                        datatype: "JSON",
                        success: (res) => {
                            res.forEach((prod) => {
                                $("#p" + rep.id_Usuario)[0].innerHTML += '<div class="det-rep"><img width="100" src="'+prod.prod_imagen+'" alt=""><div><p>' + prod.prod_nombre + '</p><p>' + prod.prod_precio + '</p><p>' + prod.prod_cantidad + '</p><p>' + prod.prod_categoria + '</p></div></div>'
                            })
                        }
                    })
                });
            }
        })
    }

    function listarresponsive() {
    

        $.ajax({
            url: "http://localhost:8080/distribuir/" + sessionStorage.getItem("user"),
            type: "GET",
            dataType: "JSON",
            success: function (respuesta) {
                console.log(respuesta)
                respuesta.forEach(function (rep) { 
                    cartas_repartidor.innerHTML += '<div class="contenido-repartidor">'+
                        '<button class="btn-insert" data-bs-toggle="modal" data-bs-target="#Modal'+rep.venta_Codigo+'"><imgsrc="../../public/assets/productos.png" alt=""></button>'+

                        '<img class="usu-repartidor" src="../../public/assets/usuario.png" alt="">'+

                            '<h2 class="nombre-rep text-success" id="prod_Nom">'+rep.usu_nombre+'</h2>'+
                            '<span class="direccion-rep">'+rep.usu_telefono+'</span>'+
                            '<span class="Telefono-rep">'+rep.usu_direccion+'</span>'+

                    '</div>' +
                    '<div  class="modal m-auto"  id="Modal' + rep.venta_Codigo +
                        '"  tabindex="-1"  aria-labelledby="exampleModalLabel"  aria-hidden="true"><div class="modal-dialog modal-dialog-centered"><div class="modal-content" style="background-color: #000000"><div class="modal-header"><h1 class="modal-title fs-5 text-success"id="exampleModalLabel">Productos</h1><button type="button"class="fa fa-xmark"data-bs-dismiss="modal"aria-label="Close" style="color: white;background-color: transparent;"></button></div><div class="modal-body"><i>' +
                        '</i><h1 class="text-center text-success">' + rep.usu_nombre + '</h1><p>' + rep.usu_direccion + '</p><div id="content' + rep.venta_Codigo + '"></div></div></div></div></div></div></div>';

                    $.ajax({
                        url: "http://localhost:8080/ventasRepartidor/" + sessionStorage.getItem("user") + "/" + rep.id_Usuario,
                        type: "GET",
                        datatype: "JSON",
                        success: (res) => {
                            res.forEach((prod) => {
                                $("#content" + rep.venta_Codigo)[0].innerHTML += '<div class="det-rep"><img width="100" src="'+prod.prod_imagen+'" alt=""><div><p>' + prod.prod_nombre + '</p><p>' + prod.prod_precio + '</p><p>' + prod.prod_cantidad + '</p><p>' + prod.prod_categoria + '</p></div></div>'
                            })
                        }
                    })
                });
            }
        })
    }

    listarnormal ()
    listarresponsive ()
})