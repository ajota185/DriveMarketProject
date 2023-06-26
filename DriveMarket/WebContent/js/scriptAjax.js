$(document).ready(function(){

    $("#search").keyup(hacerPeticionAjax);
});

function hacerPeticionAjax(){
    var text = $("#search").val();

    configuracion = {
        data: {text},
        url: "ServletSearch", 
        type: 'get',
        success: function(result){
            procesaRespuestaAjax(result);
        }
    }

    $.ajax(configuracion);

}

function procesaRespuestaAjax(result){
	var out=""
	for(let i=0; i<result.products.length; i++){
		let product = result.products[i];
		out+='<div class="producto-lista">';
			out+='<div class="imagen-producto-lista">';
				out+='<a href=ServletProduct?id_prod='+product.id_prod +'>';
					out+='<img src='+ product.main_photo +'>';
				out+='</a>';
			out+='</div>';
			
			out+='<div class="informacion-producto-lista">';
				out+='<a href=ServletProduct?id_prod='+product.id_prod +'>';	
					out+='<h3>'+product.name+'</h3>';
				out+='</a>';
				out+='<p>Price: '+ product.price +' €</p>';
			out+='</div>';
		out+='</div>';
	}
	
    $("#lista-productos").fadeIn();
    $("#lista-productos").html(out);
}