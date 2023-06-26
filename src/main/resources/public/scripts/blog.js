import { on_session } from "./index.js";

$(document).ready((e) => {
  if(!on_session()) {
    $(".insertar_blog")[0].style.display = "none"
  }
  
  if(window.location.pathname == "/blog"){
    const container = document.getElementById("container")
    const listar = ()=> {
    $.ajax({
      url: "http://localhost:8080/listarTip",
      type: "GET",
      datatype: "JSON",
      success: (res) => {
        res.forEach((blog) => {
          let cuerpo = ""
          let titulo = ""
          if(blog.cuerpo.length>236){
            cuerpo = blog.cuerpo.slice(0,234) + " ..."
          } else {
            cuerpo = blog.cuerpo
          }
  
          if (blog.titulo.length > 20) {
            titulo = blog.titulo.slice(0,21) + " ..."
          } else {
            titulo = blog.titulo
          }
          container.innerHTML += "<div class='blog-box' id='tip"+blog.codigo_tip+"'><div class='blog-text2'><span>"+blog.fecha+"</span><p>"+blog.comp_usuario+"</p><img src='../public/assets/imgblosgs.jpg' class='blog_in' alt='' /><h2>"+titulo+"</h2><p>"+cuerpo+"</p></div></div>"
        })
      },
    });
  }

  listar()

  $("#blog-search").on("input", (e) => {
    const titulo = e.target.value
    if (titulo == "") {
      container.innerHTML=""
      listar()
    } else {
      $.ajax({
        url: "http://localhost:8080/tituloTip/" + titulo,
        type: "GET",
        datatype: "JSON",
        success: (res) => {
          if (res.length == 0){
            container.innerHTML= '<p>No se encontraron resultados para "'+titulo+'"</p>'
          } else {
            container.innerHTML=""
            res.forEach((blog) => {
              let cuerpo = ""
              let titulo = ""
              if(blog.cuerpo.length>236){
                cuerpo = blog.cuerpo.slice(0,234) + " ..."
              } else {
                cuerpo = blog.cuerpo
              }
      
              if (blog.titulo.length > 20) {
                titulo = blog.titulo.slice(0,21) + " ..."
              } else {
                titulo = blog.titulo
              }
              container.innerHTML += "<div class='blog-box' id='tip"+blog.codigo_tip+"'><div class='blog-text2'><span>"+blog.fecha+"</span><p>"+blog.comp_usuario+"</p><img src='../public/assets/imgblosgs.jpg' class='blog_in' alt='' /><h2>"+titulo+"</h2><p>"+cuerpo+"</p></div></div>"
            })
          }
        },
      })
    }
  })
}
  
  
  $('#send_tip').on("click", () =>{
    const val = sessionStorage.getItem("user")
    console.log(val)
    const blog = {
      usuario: "",
      titulo: $("#title_blog").val(),
      cuerpo: $("#content_blog").val(),
    };
    if (blog.titulo == "" || blog.content == "") {
      alert("Completar todos los campos")
  } else {

    $.ajax({
      url: "http://localhost:8080/insertarTip/"+val,
      type: "POST",
      data: blog,
      dataType: "text",
      success: (res) => {
        if(res != "No se agrego el tip"){
          alert(res)
          window.location.href = "/blog"
        }else{
          alert(res)
        }
        console.log(res);
      },
      error:(xhr,status,error)=>{
        console.log(error)
        console.log(xhr.responseText)
      }
    });
  
  }
  })
})  







