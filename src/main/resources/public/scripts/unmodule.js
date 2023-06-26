function add(codigo, id) {
  $.ajax({
    url: "http://localhost:8080/usuario/" + id,
    type: "GET",
    datatype: "JSON",
    success: (res) => {
      $.ajax({
        url:
          "http://localhost:8080/insertarFavoritos/" +
          codigo +
          "/" +
          res.id_usuario,
        type: "POST",
        success: (res) => {
          if (res) {
            alert("El producto se agrego a tu carrito");
          } else if (!res) {
            alert("El producto se elimino de carrito");
          } else {
            alert("No se puedo agregar el producto al carrito");
          }
        },
      });
    },
  });
}

function addCar(codigo, correo) {
  $.ajax({
    url:
      "http://localhost:8080/insertarCarrito/" +
      correo +
      "/" +
      codigo +
      "/" +
      1,
    type: "POST",
    success: (res) => {
      if (res) {
        alert("El producto se agrego a tu carrito");
      } else if (!res) {
        alert("El producto se elimino de carrito");
      } else {
        alert("No se puedo agregar el producto al carrito");
      }
    },
  });
}

function insertproduct() {
  const newprod = {
    prod_Nombre: $("#prod_Nombre").val(),
    prod_Imagen: $("#prod_Imagen").val(),
    prod_Categoria: $("#prod_Categoria").val(),
    prod_Cantidad: $("#prod_Cantidad").val(),
    prod_Precio: $("#prod_Precio").val(),
  };

  $.ajax({
    url: "http://localhost:8080/insertarProducto",
    type: "POST",
    data: newprod,
    datatype: "text/plain",
    success: (res) => {
      alert(res);
      window.location.reload();
    },
  });
}

function delProducto (id) {
    $.ajax({
        url: "http://localhost:8080/eliminarProducto/" + id,
        type: "DELETE",
        success: (res) => {
            alert(res)
            window.location.reload()
        }
    })
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
    url: "http://localhost:8080/insertarUsuario",
    type: "POST",
    data: newuser,
    datatype: "text/plain",
    success: (res) => {
      alert(res);
      window.location.reload();
    },
  });
}

function delUsuario (id) {
    $.ajax({
        url: "http://localhost:8080/eliminarUsuario/" + id,
        type: "DELETE",
        success: (res) => {
            alert(res)
            window.location.reload()
        }
    })
}

function deleteFav(codigo) {
  $.ajax({
    url: "http://localhost:8080/eliminarFavoritos/" + codigo,
    type: "DELETE",
    success: (res) => {
      alert(res);
      window.location.reload();
    },
  });
}

function deleteCar(codigo) {
  $.ajax({
    url: "http://localhost:8080/eliminarCarrito/" + codigo,
    type: "DELETE",
    success: (res) => {
      alert(res);
      window.location.reload();
    },
  });
}

function comprar() {
  $.ajax({
    url: "http://localhost:8080/compra/" + sessionStorage.getItem("user"),
    type: "GET",
    datatype: "JSON",
    success: (res) => {
      alert(res);
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
    url: "http://localhost:8080/nombreProducto/"+ nombre,
    type: "GET",
    datatype: "JSON",
    success: (res) => {
        tdtotal.innerHTML = sumtotal + res[0].prod_Precio
        $.ajax({
            url: "http://localhost:8080/sumarCarrito/" + codigo,
            type: "POST",
            datatype: "JSON",
            success: (res1) => {
                alert(res1)
            },
          });
    }
  })
}

function resCar(id, total, nombre, codigo) {
    var tdcantidad = $("#" + id)[0];
    var tdtotal = $("#" + total)[0];
    var restotal = parseInt(tdtotal.innerHTML);
    var cantidad = parseInt(tdcantidad.innerHTML);
    tdcantidad.innerHTML = cantidad - 1;
  
    $.ajax({
      url: "http://localhost:8080/nombreProducto/"+ nombre,
      type: "GET",
      datatype: "JSON",
      success: (res) => {
          tdtotal.innerHTML = restotal - res[0].prod_Precio
          $.ajax({
              url: "http://localhost:8080/restarCarrito/" + codigo,
              type: "POST",
              datatype: "JSON",
              success: (res1) => {
                  alert(res1)
              },
            });
      }
    })
  }
