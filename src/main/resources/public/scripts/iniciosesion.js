import { on_session } from "./index.js";

$(document).ready((e) => {
  $("#log_in").on("click", () => {
    const email = $("#correo").val();
    const pass = $("#contrasenia").val();
    console.log(email + pass);

    if (email == "" || pass == "") {
      alert("Debes completar todos los campos");
    } else {
      $.ajax({
        url: "http://localhost:8080/validarUsuario/" + email + "/" + pass,
        type: "GET",
        datatype: "JSON",
        success: (res) => {
          if (res.error != "Usuario o contraseña incorrectos") {
            sessionStorage.setItem("status", res.rol);
            sessionStorage.setItem("user", res.res)
            window.location.href = "/";
          } else {
            alert(res.error);
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
