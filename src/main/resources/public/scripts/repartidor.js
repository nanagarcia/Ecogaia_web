import { on_session } from "./index.js";

$(document).ready(function(){
    $(".barra")[0].style.backgroundColor = "#000000"
    $.ajax({
        url:"http://localhost:8080/distribuir/" + sessionStorage.getItem("user"),
        type: "GET",
        dataType: "JSON",
        success: function(respuesta){
            console.log(respuesta)
            respuesta.forEach(function(rep) {
                tabla.innerHTML += '<tr><td>'
                + rep.venta_Codigo + '</td><td>'
                + rep.id_Usuario + '</td><td>'
                + rep.usu_nombre + '</td><td>'
                + rep.usu_direccion + '</td><td>'
                + rep.usu_telefono + '</td><td><button class="btn-insert" data-bs-toggle="modal"  data-bs-target="#exampleModal'+rep.venta_Codigo+'"><span>Productos</span></button></td>'
                + '<div  class="modal m-auto"  id="exampleModal' + rep.venta_Codigo +
                '"  tabindex="-1"  aria-labelledby="exampleModalLabel"  aria-hidden="true"><div class="modal-dialog modal-dialog-centered"><div class="modal-content" style="background-color: #000000"><div class="modal-header"><h1 class="modal-title fs-5 text-success"id="exampleModalLabel">Productos</h1><button type="button"class="fa fa-xmark"data-bs-dismiss="modal"aria-label="Close" style="color: white;background-color: transparent;"></button></div><div class="modal-body"><i>' +
                 '</i><h1 class="text-center text-success">' + rep.usu_nombre + '</h1><p>'+rep.usu_direccion+'</p><div id="p'+rep.id_Usuario+'"></div></div></div></div></div></div></div>';

                 $.ajax({
                       url: "http://localhost:8080/ventasRepartidor/" + sessionStorage.getItem("user") + "/" + rep.id_Usuario,
                       type: "GET",
                       datatype: "JSON",
                       success: (res) => {
                             res.forEach((prod) => {
                                $("#p"+rep.id_Usuario)[0].innerHTML += '<div class="det-rep"><img width="100" src="https://frutosalvaje.com/wp-content/uploads/2021/11/Cepillo-de-Bambu_1-1-1536x1536.png" alt=""><div><p>'+prod.prod_nombre+'</p><p>'+prod.prod_precio+'</p><p>'+prod.prod_cantidad+'</p><p>'+prod.prod_categoria+'</p></div></div>'
                             })
                       }
                 })
            });
        }
    })
})