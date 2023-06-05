<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
    


<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/pagine/head.jsp">
		<jsp:param value="Insert Product" name="title"/>
		<jsp:param value="style" name="style"/>
		<jsp:param value="script" name="script"/>
	</jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/pagine/header.jsp"/>
	<div class="container">
		<div class="content-editar-producto">
	        <h2>Insert Product</h2>
	
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
	          
	        <form action="ServletInsertProduct" method="post" enctype="multipart/form-data">
	            Name (*):<br>
	            <input type="text" name="name" /><br>
	            Price (Euros) (*):<br>
	            <input type="number" name="price"  /><br>
	            Description (*):<br>
	            <textarea type="text" name="description" ></textarea><br>
	            Main photo (*):<br>
	            <input type="file" name="main_photo"  /><br>
	            Link:<br>
	            <input class="enlace" type="text" name="link" /><br>
	            <br><br>
	            Images:<br>
	            <input type="file" name="images" multiple="multiple" /><br>
	            
	            <input class="submit" type="submit" value="Add product" />
	        </form>
	    </div>
	    <jsp:include page="/WEB-INF/pagine/aside.jsp"/>
    </div>
    
	<jsp:include page="/WEB-INF/pagine/footer.jsp"/>
</body>
</html>