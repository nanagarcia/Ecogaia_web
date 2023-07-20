import { mostrarOcultoWarning, mostrarOcultoSuccess, on_session } from "./index.js";

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
      mostrarOcultoWarning("Completar todos los campos")
    } else {
        $.ajax({
            url: "https://ecogaiaweb-production.up.railway.app/insertarUsuario",
            type: "POST",
            data: user,
            dataType: "text",
            success: (res) => {
                mostrarOcultoSuccess("Su usuario se registro correctamente")
            },
          });
    }
  });
});