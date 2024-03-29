$("#op4").on("click", function () {
  var user = sessionStorage.getItem("user");
  tablaCarrito.innerHTML = ""
  $.ajax({
    url: "https://ecogaiaweb-production.up.railway.app/cotizacionesUsuario/" + user,
    type: "GET",
    dataType: "JSON",
    success: function (res) {
      if (res.length > 0) {
        res.forEach((cotiza) => {
          var nombre = cotiza.Prod_Nombre;
          if (cotiza.Prod_Nombre.length > 8) {
            nombre = cotiza.Prod_Nombre.slice(0, 8) + "...";
          }
          tablaCarrito.innerHTML +=
            "<tr data-bs-toggle='modal'  data-bs-target='#car" + cotiza.codigo_carrito + "'><td>" +
            cotiza.codigo_carrito +
            "</td><td>" +
            nombre +
            "</td><td id='price" + cotiza.codigo_carrito + "'>" +
            cotiza.cantidad +
            "</td><td id='" + cotiza.total + "'>" +
            cotiza.total +
            '</td><td><i class="fa fa-trash" onclick="deleteCar(' +
            cotiza.codigo_carrito +
            ')"></i></td></tr>' +
            '<!-- Modal --><div  class="modal fade "  id="car' + cotiza.codigo_carrito + '"  tabindex="-1"  aria-labelledby="exampleModalLabel"  aria-hidden="true"><div class="modal-dialog modal-dialog-centered"><div class="modal-content"><div class="modal-header"><h1 class="modal-title fs-5 text-success"id="exampleModalLabel">Mas información</h1><button type="button"class="btn-close"data-bs-dismiss="modal"aria-label="Close"></button></div><div class="modal-body">' +
            "<h1 class='text-center text-success'>" +
            cotiza.Prod_Nombre +
            "</h1><p class='contenido '>$" +
            cotiza.total +
            '</p><p class="contenido" >Manejar cantidad</p><div class="body-car"><i onclick="resCar(\'price' + cotiza.codigo_carrito + '\', \'' + cotiza.total + '\', \'' + cotiza.Prod_Nombre + '\', \'' + cotiza.codigo_carrito + '\')" class="fas fa-circle-minus"></i><i onclick="sumCar(\'price' + cotiza.codigo_carrito + '\', \'' + cotiza.total + '\', \'' + cotiza.Prod_Nombre + '\', \'' + cotiza.codigo_carrito + '\')" class="fas fa-circle-plus"></i></div></div></div></div></div></div></div>';
        });
      } else {
        tablaCarrito.innerHTML +=
          "<tr><td colspan='5'><p class='text-center'>No tienes nada en carrito</p></td></tr>";
      }
    },
  });
});

$("#comprar").on("click", () => {
  $.ajax({
    url: "https://ecogaiaweb-production.up.railway.app/compra/" + sessionStorage.getItem("user"),
    type: "GET",
    datatype: "JSON",
    success: (res) => {
      if (res == "Se registro una compra de sus productos") {
        var alerta = document.getElementById("alerta");
        alerta.innerHTML= "<img id='img_alert' src='public/assets/alert_success.png'><span id='mensaje'></span>"
        alerta.style.backgroundColor="#198754"
        var mensaje = document.getElementById("mensaje");
        alerta.classList.add("mostrar");
        mensaje.innerHTML=res
          setTimeout(function() {
            alerta.classList.remove("mostrar");
          }, 3000);
      } else {
        var alerta = document.getElementById("alerta");
        alerta.innerHTML= "<img id='img_alert' src='public/assets/alert_error.png'><span id='mensaje'></span>"
        alerta.style.backgroundColor="#dc3545"
        var mensaje = document.getElementById("mensaje");
        alerta.classList.add("mostrar");
        mensaje.innerHTML=res
          setTimeout(function() {
            alerta.classList.remove("mostrar");
          }, 3000);
      }
    },
  });
})
