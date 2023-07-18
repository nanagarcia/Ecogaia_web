
function add(codigo, id) {
  $.ajax({
    url: "https://ecogaiaweb-production.up.railway.app/usuario/" + id,
    type: "GET",
    datatype: "JSON",
    success: (res) => {
      $.ajax({
        url:
          "https://ecogaiaweb-production.up.railway.app/insertarFavoritos/" +
          codigo +
          "/" +
          res.id_usuario,
        type: "POST",
        success: (res) => {
          if (res) {
            alerta.style.background = "#EBD166";
            mostrarOcultoSuccess("El producto se agrego a tus favoritos");
          } else if (!res) {
            alerta.style.background = "#EBD166";
            mostrarOcultoSuccess("El producto se elimino de tus favoritos");
          } else {
            alerta.style.background = "#EBD166";
            mostrarOcultoWarning("No se puedo agregar el producto a tus favoritos");
          }
        },
      });
    },
  });
}

function addCar(codigo, correo) {
  $.ajax({
    url:
      "https://ecogaiaweb-production.up.railway.app/insertarCarrito/" +
      correo +
      "/" +
      codigo +
      "/" +
      1,
    type: "POST",
    success: (res) => {
      if (res) {
        mostrarOcultoSuccess("El producto se agrego a tu carrito");
      } else if (!res) {
        mostrarOcultoWarning("El producto se elimino de carrito");
      } else {
        mostrarOcultoError("No se puedo agregar el producto al carrito");
      }
    },
  });
}

function delProducto(id) {
  $.ajax({
    url: "https://ecogaiaweb-production.up.railway.app/eliminarProducto/" + id,
    type: "DELETE",
    success: (res) => {
      mostrarOcultoSuccess(res);
    },
  });
}

function insertuser() {
  const newuser = {
    usu_nombre: $("#usu_nombre").val(),
    usu_telefono: $("#usu_telefono").val(),
    usu_direccion: $("#usu_direccion").val(),
    usu_correo: $("#usu_correo").val(),
    usu_contrasenia: $("#usu_contrasenia").val(),
  };

  $.ajax({
    url: "https://ecogaiaweb-production.up.railway.app/insertarUsuario",
    type: "POST",
    data: newuser,
    datatype: "text/plain",
    success: (res) => {
      mostrarOcultoSuccess(res)
    },
  });
}

function delUsuario(id) {
  $.ajax({
    url: "https://ecogaiaweb-production.up.railway.app/eliminarUsuario/" + id,
    type: "DELETE",
    success: (res) => {
      mostrarOcultoWarning(res)
    },
  });
}

function deleteFav(codigo) {
  $.ajax({
    url: "https://ecogaiaweb-production.up.railway.app/eliminarFavoritos/" + codigo,
    type: "DELETE",
    success: (res) => {
      mostrarOcultoWarning(res);
      window.location.reload();
    },
  });
}

function deleteCar(codigo) {
  $.ajax({
    url: "https://ecogaiaweb-production.up.railway.app/eliminarCarrito/" + codigo,
    type: "DELETE",
    success: (res) => {
      mostrarOcultoWarning(res);
      window.location.reload();
    },
  });
}

function sumCar(id, total, nombre, codigo) {
  var tdcantidad = $("#" + id)[0];
  var tdtotal = $("#" + total)[0];
  var sumtotal = parseInt(tdtotal.innerHTML);
  var cantidad = parseInt(tdcantidad.innerHTML);
  tdcantidad.innerHTML = cantidad + 1;

  $.ajax({
    url: "https://ecogaiaweb-production.up.railway.app/nombreProducto/" + nombre,
    type: "GET",
    datatype: "JSON",
    success: (res) => {
      tdtotal.innerHTML = sumtotal + res[0].prod_Precio;
      $.ajax({
        url: "https://ecogaiaweb-production.up.railway.app/sumarCarrito/" + codigo,
        type: "POST",
        datatype: "JSON",
        success: (res1) => {
          mostrarOcultoSuccess(res1);
        },
      });
    },
  });
}

function resCar(id, total, nombre, codigo) {
  var tdcantidad = $("#" + id)[0];
  var tdtotal = $("#" + total)[0];
  var restotal = parseInt(tdtotal.innerHTML);
  var cantidad = parseInt(tdcantidad.innerHTML);
  tdcantidad.innerHTML = cantidad - 1;

  $.ajax({
    url: "https://ecogaiaweb-production.up.railway.app/nombreProducto/" + nombre,
    type: "GET",
    datatype: "JSON",
    success: (res) => {
      tdtotal.innerHTML = restotal - res[0].prod_Precio;
      $.ajax({
        url: "https://ecogaiaweb-production.up.railway.app/restarCarrito/" + codigo,
        type: "POST",
        datatype: "JSON",
        success: (res1) => {
          mostrarOcultoWarning(res1);
        },
      });
    },
  });
}

function mostrarOcultoSuccess(frase){
  var alerta = document.getElementById("alerta");
  alerta.innerHTML= "<img id='img_alert' src='public/assets/alert_success.png'><span id='mensaje'></span>"
  alerta.style.backgroundColor="#198754"
  var mensaje = document.getElementById("mensaje");
  alerta.classList.add("mostrar");
  mensaje.innerHTML=frase
    setTimeout(function() {
      alerta.classList.remove("mostrar");
    }, 3000);
  }
  function mostrarOcultoWarning(frase){
  var alerta = document.getElementById("alerta");
  alerta.innerHTML= "<img id='img_alert' src='public/assets/alert_danger.png'><span id='mensaje'></span>"
  alerta.style.backgroundColor="#ffc107"
  var mensaje = document.getElementById("mensaje");
  alerta.classList.add("mostrar");
  mensaje.innerHTML=frase
    setTimeout(function() {
      alerta.classList.remove("mostrar");
    }, 3000);
  }
  function mostrarOcultoError(frase){
  var alerta = document.getElementById("alerta");
  alerta.innerHTML= "<img id='img_alert' src='public/assets/alert_error.png'><span id='mensaje'></span>"
  alerta.style.backgroundColor="#dc3545"
  var mensaje = document.getElementById("mensaje");
  alerta.classList.add("mostrar");
  mensaje.innerHTML=frase
    setTimeout(function() {
      alerta.classList.remove("mostrar");
    }, 3000);
  }


function actProd(codigo) {
  $.ajax({
    url: "https://ecogaiaweb-production.up.railway.app/productoCodigo/" + codigo,
    type: "GET",
    datatype: "JSON",
    success: (res) => {
      document.getElementById("actProdNombre").value = res.prod_Nombre
      document.getElementById("actProdCategoria").value = res.prod_Categoria
      document.getElementById("actProdCantidad").value = res.prod_Cantidad
      document.getElementById("actProdPrecio").value = res.prod_Precio
    }
  })
}

function actUsu(correo) {
  $.ajax({
    url: "https://ecogaiaweb-production.up.railway.app/usuario/" + correo,
    type: "GET",
    datatype: "JSON",
    success: (res) => {
      document.getElementById("actUsuNombre").value = res.usu_nombre
      document.getElementById("actRol").value = res.rol
      document.getElementById("actUsuCorreo").value = res.usu_correo
      document.getElementById("actUsuDireccion").value = res.usu_direccion
      document.getElementById("actUsuTelefono").value = res.usu_telefono
      document.getElementById("actUsuContrasenia").value = res.usu_contrasenia
    }
  })
}
