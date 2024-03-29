import { on_session } from "./index.js";

$(document).ready(() => {
    $(".barra")[0].style.backgroundColor = "#000000"
    $(".barra-responsive")[0].style.backgroundColor = "#000000";
    var mas = $("#mas")[0]
    var menos = $("#menos")[0]
    var est = $("#estadisticas-content")[0]
    var fecha = new Date()
    for (let i = 0; i<=3; i++) {
        $.ajax({
            url: "https://ecogaiaweb-production.up.railway.app/ventasAnuales/" + (fecha.getFullYear()-i).toString() + "-01-01" + "/"+ (fecha.getFullYear()-i+1).toString() + "-01-01",
            type: "GET",
            datatype: "JSON",
            success: (res) => {
                var porcentaje = parseFloat(res.toString().slice(0,4))
                est.innerHTML += '<p>'+(fecha.getFullYear()-i).toString()+'</p><div class="barra2 border-bottom" role="progressbar" aria-label="Success example" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"><div id="barcontenedor" class="progress-bar bg-success" style="width: '+porcentaje+'%"><span>&nbsp;</span></div><span class="porcentaje">'+porcentaje+'%</span></div>'
                
            }
        })
    } 

    $.ajax({
        url: "https://ecogaiaweb-production.up.railway.app/listadoDesc",
        type: "GET",
        datatype: "JSON",
        success: (res) => {
            res.forEach(p => {
                mas.innerHTML += '<tr><th>'+p.cantidad+'</th><th colspan="2" style="font-size: 13px;">'+p.prod_Nombre+'</th></tr>'
            });
        }
    })

    $.ajax({
        url: "https://ecogaiaweb-production.up.railway.app/listadoAsc",
        type: "GET",
        datatype: "JSON",
        success: (res) => {
            res.forEach(p => {
                menos.innerHTML += '<tr><th>'+p.cantidad+'</th><th colspan="2" style="font-size: 13px;">'+p.prod_Nombre+'</th></tr>'
            });
        }
    })

    $.ajax({
        url: "https://ecogaiaweb-production.up.railway.app/listarUsuario",
        type: "GET",
        datatype: "JSON",
        success: (res) => {
            $("#cant_users")[0].innerHTML = "- "+ res.length + " -"
        }
    })

    $.ajax({
        url: "https://ecogaiaweb-production.up.railway.app/listarVenta",
        type: "GET",
        datatype: "JSON",
        success: (res) => {
            $("#cant_ventas")[0].innerHTML = "- "+ res.length + " -"
        }
    })
})
