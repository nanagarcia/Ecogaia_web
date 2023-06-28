import { on_session } from "./index.js";
import { mostrarOculto } from "./index.js";

$(document).ready((e) => {
  $("#sign_in").on("click", () => {
    const user = {
      id_usuario: 0,
      usu_nombre: $("#nombre").val(),
      usu_telefono: $("#telefono").val(),
      usu_direccion: $("#direccion").val(),
      usu_correo: $("#correo").val(),
      usu_contrasenia: $("#contrasenia").val(),
    };
    
    if (user.usu_nombre == "" || user.usu_telefono == "" || user.usu_direccion == "" || user.usu_correo == "" || user.usu_contrasenia == "") {
      alerta.style.background="#dc3545"
      mostrarOculto("Completar todos los campos")
    } else {
        $.ajax({
            url: "http://localhost:8080/insertarUsuario",
            type: "POST",
            data: user,
            dataType: "text",
            success: (res) => {
                alerta.style.background="#dc3545"
                mostrarOculto("Completar todos los campos")
            },
          });
    }
  });
});

$(".btn-hamburguesa").on("click", () => {
  $(".barra")[0].style.display = "block"
})

$(".cerrar_barra").on("click", () => {
  $(".barra")[0].style.display = "none"
})