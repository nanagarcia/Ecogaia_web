import { on_session } from "./index.js";

$(document).ready(() => {
    $(".barra")[0].style.backgroundColor = "#000000"
    var mas = $("#mas")[0]
    var menos = $("#menos")[0]
    var est = $("#estadisticas-content")[0]
    var fecha = new Date()
    for (let i = 0; i<=3; i++) {
        $.ajax({
            url: "http://localhost:8080/ventasAnuales/" + (fecha.getFullYear()-i).toString() + "-01-01" + "/"+ (fecha.getFullYear()-i+1).toString() + "-01-01",
            type: "GET",
            datatype: "JSON",
            success: (res) => {
                var porcentaje = parseFloat(res.toString().slice(0,4))
                est.innerHTML += '<p>'+(fecha.getFullYear()-i).toString()+'</p><div class="barra2 border border-secondary" role="progressbar" aria-label="Success example" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"><div id="barcontenedor" class="progress-bar bg-secondary" style="width: '+porcentaje+'%"><span>&nbsp;</span></div><span class="porcentaje">'+porcentaje+'%</span></div>'
                
            }
        })
    } 

    $.ajax({
        url: "http://localhost:8080/listadoDesc",
        type: "GET",
        datatype: "JSON",
        success: (res) => {
            res.forEach(p => {
                mas.innerHTML += '<tr><th>'+p.cantidad+'</th><th colspan="2" style="font-size: 13px;">'+p.prod_Nombre+'</th></tr>'
            });
        }
    })

    $.ajax({
        url: "http://localhost:8080/listadoAsc",
        type: "GET",
        datatype: "JSON",
        success: (res) => {
            res.forEach(p => {
                menos.innerHTML += '<tr><th>'+p.cantidad+'</th><th colspan="2" style="font-size: 13px;">'+p.prod_Nombre+'</th></tr>'
            });
        }
    })

    $.ajax({
        url: "http://localhost:8080/listarUsuario",
        type: "GET",
        datatype: "JSON",
        success: (res) => {
            $("#cant_users")[0].innerHTML = "- "+ res.length + " -"
        }
    })

    $.ajax({
        url: "http://localhost:8080/listarVenta",
        type: "GET",
        datatype: "JSON",
        success: (res) => {
            $("#cant_ventas")[0].innerHTML = "- "+ res.length + " -"
        }
    })
})