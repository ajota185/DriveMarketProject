<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
    


<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/pagine/head.jsp">
		<jsp:param value="DRIVE MARKET" name="title"/>
		<jsp:param value="style" name="style"/>
		<jsp:param value="script" name="scriptjs"/>
	</jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/pagine/header.jsp"/>
	<div class="container">
		<div class="content-registro">
	        <h2>Registro</h2>
	
	        <div id="error">
	            <% 
					List<String> errors = (List<String>) request.getAttribute("errors");
					if (errors != null){
						for (String error: errors){ %>
							<%=error %> <br>		
						<%
						}
					}
				%>
	        </div>
	          
	        <form action="ServletRegister" method="post">
	            Nick:<br>
	            <input type="text" name="nick" /><br>
	            Password:<br>
	            <input type="password" name="password" /><br>
	            Email:<br>
	            <input class="email" type="email" name="email" /><br>
	            <input type="submit" value="Registrarse" />
	        </form>
	    </div>
	    <jsp:include page="/WEB-INF/pagine/aside.jsp"/>
    </div>
    
	<jsp:include page="/WEB-INF/pagine/footer.jsp"/>
</body>
</html>