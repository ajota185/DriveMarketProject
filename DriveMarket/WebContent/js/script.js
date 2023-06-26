function validateFormLogin(){
	let nick = document.forms["FormLogin"]["nick"].value;
	let password = document.forms["FormLogin"]["password"].value;
	let result="";
	if(nick==""){
		result+="<p>Nick must be filled out.</p>";
	}
	if(password==""){
		result+="<p>Password must be filled out.</p>";
	}
	if(result!=""){
		document.getElementById("error").innerHTML=result;
		return false;
	}
}

function validateFormRegister(){
	let nick = document.forms["FormRegister"]["nick"].value;
	let password = document.forms["FormRegister"]["password"].value;
	let email = document.forms["FormRegister"]["email"].value;
	let result="";
	var mailformat = /^\S+@\S+\.\S+$/;
	
	if(nick==""){
		result+="Nick must be filled out.";
	}
	if(password==""){
		result+="\nPassword must be filled out.";
	}
	if(email==""){
		result+="\nEmail must be filled out.";
	}else{
		if(!mailformat.test(email)){
			result+='\nEmail must be like "username"@"dominio"."dominio".';
		}
	}
	
	if(result!=""){
		alert(result);
		return false;
	}
}

function validateFormUpdateUser(){
	let nick = document.forms["FormUpdateUser"]["nick"].value;
	let email = document.forms["FormUpdateUser"]["email"].value;
	let result="";
	var mailformat = /^\S+@\S+\.\S+$/;
	
	if(nick==""){
		result+="<p>Nick must be filled out.</p>";
	}
	if(email==""){
		result+="<p>Email must be filled out.</p>";
	}else{
		if(!mailformat.test(email)){
			result+='<p>Email must be like "username"@"dominio"."dominio".</p>';
		}
	}
	
	if(result!=""){
		document.getElementById("error").innerHTML=result;
		return false;
	}
}



/* Botones sociales */
document.getElementById("boton-twitter").onclick = () => window.location.href="https://twitter.com/ClubTeslaES";
document.getElementById("boton-facebook").onclick = () => window.location.href='https://www.facebook.com/ClubTeslaEspana/';
/* document.getElementById("boton-imprimir").onclick = () => window.location.href="producto_imprimir.html"; */
