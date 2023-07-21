import { on_session } from "./index.js";
import { mostrarOcultoSuccess } from "./index.js";
import { mostrarOcultoWarning } from "./index.js";
import { mostrarOcultoError } from "./index.js";

$(document).ready(() => {
  const val = sessionStorage.getItem("user");
  var nombre = $("#nombre");
  var telefono = $("#telefono");
  var direccion = $("#direccion");
  var correo = $("#correo");
  var contrasenia = $("#contrasenia");
  var rol = "";
  $.ajax({
    url: "https://ecogaiaweb-production.up.railway.app/usuario/" + val,
    type: "GET",
    datatype: "JSON",
    success: (res) => {
      nombre.val(res.usu_nombre);
      telefono.val(res.usu_telefono);
      direccion.val(res.usu_direccion);
      correo.val(res.usu_correo);
      contrasenia.val(res.usu_contrasenia);
      rol = res.rol;
    },
  });

  $("#actualizar").on("click", () => {
    if (contrasenia.val() == $("#con_contrasenia").val()) {
      $.ajax({
        url: "https://ecogaiaweb-production.up.railway.app/actualizarUsuario/"+ val,
        type: "PUT",
        data: {
          id_Usuario: 0,
          usu_nombre: nombre.val(),
          usu_telefono: telefono.val(),
          usu_direccion: direccion.val(),
          usu_correo: correo.val(),
          usu_contrasenia: contrasenia.val(),
          rol: rol,
        },
        success: (res) => {
          mostrarOcultoSuccess(res)
          window.location.href = "login"
          sessionStorage.removeItem("user")
          sessionStorage.removeItem("status")

        },
        error: (xhr, status, error) => {
          console.log(error);
          console.log(xhr.responseText); // Información sobre el error
        },
      });
    } else {
      mostrarOcultoError("Las contraseñas no coinciden")
    }
  });
});
