
var numero_formulario=2;
/* Función para mostrar y esconder la barra de comentarios en un evento*/
function ver_comentarios(){
    var coments = document.getElementById("apartado-forms");
    
    var comentsStyle = window.getComputedStyle(coments);

    var display = comentsStyle.getPropertyValue('display');

    if(display=='none'){
        coments.style.display='block';
    }
    else{
        coments.style.display='none';
    }
}


function enviar_form(){
    var nombre = document.getElementById('nombre');
    var email = document.getElementById('email');
    var comentario = document.getElementById('comentario');

    var campo_vacio=false;

    var expre_reg_correo = /\S+@\S+\.\S+/;

    if(nombre.value==''){
        campo_vacio=true;
    }

    if(email.value==''){
        campo_vacio=true;
    }

    if(comentario.value==''){
        campo_vacio=true;
    }

    if(campo_vacio){
        var dialogo=document.getElementById("dialogo");
        var texto_dialogo = document.getElementById("texto-dialogo");
        texto_dialogo.innerHTML = "Faltan campos por rellenar!"
        dialogo.showModal();
    }
    else{
        if(!expre_reg_correo.test(email.value)){
            var dialogo=document.getElementById("dialogo");
            var texto_dialogo = document.getElementById("texto-dialogo");
            texto_dialogo.innerHTML = "El email no es válido!"
            dialogo.showModal();
        }
        else{
            numero_formulario++;
            var fecha = new Date();
            
            var nuevo_formulario = 
            "<div class=\"form\">"+
                "<span class=\"titulo-hora\">"+"Comentario --------- "+fecha.toLocaleDateString()+"&nbsp;&nbsp"+fecha.toLocaleTimeString()+"</span>"+
                "<span>"+"<b>"+"Autor:"+"</b>"+"&nbsp;&nbsp;"+nombre.value+"</span>"+
                "<span>"+"<b>"+"Comentario:"+"</b>"+"&nbsp;&nbsp;"+comentario.value+"</span>"+
            "</div>";
            
            document.getElementById("content-forms").insertAdjacentHTML("afterbegin",nuevo_formulario);
            
        }
    }
}


async function corrijo_palabras(){

    /* var palabras_malas;
    const options = {
        method: "GET"
      };
      
    // Petición HTTP
    await fetch("palabras_prohibidas.php", options)
    .then(response => response.text())
    .then(data => {
        palabras_malas = data;
    });

    alert(palabras_malas); */



    var i;
    var palabras = [];

    
    // Creo las palabras prohibidas con sintaxis para el replace
    for(i = 0; i < palabras_prohibidas.length; i++){
        palabras.push(new RegExp(palabras_prohibidas[i], 'g'));
    }



    var texto = document.getElementById("comentario").value;

    var texto_corregido = texto;

    for (i = 0; i < palabras.length; i++){
        texto_corregido = texto_corregido.replace(palabras[i], "*".repeat(palabras_prohibidas[i].length));
    }

    document.getElementById("comentario").value = texto_corregido;


    
    
    /* var palabras_malas = ["culo", "caca", "tonto", "mierda","puta","gilipollas"];
    var palabras_corregidas = ["****","****","*****","******", "****","**********"];

    var texto = document.getElementById("comentario").value;
    
    var i;
    for (i = 0; i < palabras_malas.length; i++){
        texto = texto.replace(palabras_malas[i], palabras_corregidas[i]);
    }
    
    document.getElementById("comentario").value = texto; */
    
}


/* Boton hacer aparecer y desaparecer el apartado comentarios */
/*document.getElementById("boton-comentarios").onclick = ver_comentarios;*/

/* Boton Enviar */
// document.getElementById("enviar").onclick = enviar_form;

/* Boton para cerrar el dialogo modal */
/*document.getElementById("cerrar").onclick = () => document.getElementById("dialogo").close();*/

/* Las palabras se corrigen  */
/*document.getElementById("comentario").onkeyup = corrijo_palabras;*/

/* Botones sociales */
document.getElementById("boton-twitter").onclick = () => window.location.href="https://twitter.com/ClubTeslaES";
document.getElementById("boton-facebook").onclick = () => window.location.href='https://www.facebook.com/ClubTeslaEspana/';
/* document.getElementById("boton-imprimir").onclick = () => window.location.href="producto_imprimir.html"; */
