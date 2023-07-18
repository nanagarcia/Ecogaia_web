import { on_session } from "./index.js";
import { mostrarOcultoSuccess } from "./index.js";
import { mostrarOcultoWarning } from "./index.js";
import { mostrarOcultoError } from "./index.js";

$(document).ready((e) => {
  $("#log_in").on("click", () => {
    const email = $("#correo").val();
    const pass = $("#contrasenia").val();
    console.log(email + pass);

    if (email == "" || pass == "") {
      mostrarOcultoWarning("Completa todos los campos")


    } else {
      $.ajax({
        url: "https://ecogaiaweb-production.up.railway.app/validarUsuario/"+email+"/"+pass,
        type: "GET",
        dataType: "JSON",
        success: (res) => {
          if (res.error != "Usuario o contraseña incorrectos") {
            mostrarOcultoError(res.error)
            sessionStorage.setItem("status", res.rol);
            sessionStorage.setItem("user", res.res)
            window.location.href = "/";
          } else {
            mostrarOcultoError(res.error)
          }
        },
        error: (xhr, status, error) => {
          console.log(error);
          console.log(xhr.responseText); // Información sobre el error
        },
      });
    }
  });
});

