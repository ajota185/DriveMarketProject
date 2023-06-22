<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.product.Product"%>

<%
	Product product = (Product) request.getAttribute("product");
%>


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
	        <h2>Update Product</h2>
	
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
	        <%String id_prod = Integer.toString(product.getId_prod()); %>
	        <form action="ServletUpdateProduct" method="post" enctype="multipart/form-data">
	            Id_prod: <br>
	            <input type="text" name="id_prod" value=<%=id_prod%> readonly/><br>
	            Name:<br>
	            <input type="text" name="name" value="<%=product.getName() %>" /><br>
	            Price (Euros):<br>
	            <input type="number" name="price" value="<%=product.getPrice() %>" /><br>
	            Description:<br>
	            <textarea name="description"><%=product.getDescription() %></textarea><br>
	            Main Photo (if image is not selected, image doesn't change):<br>
	            <input type="file" name="main_photo"  /><br>
	            Link:<br>
	            <input class="enlace" type="text" name="link" value="<%=product.getLink() %>" /><br>
	            
	            <br><br>
	            Images (if images are not selected, images don't change):<br>
            	<input type="file" name="images" multiple="multiple" /><br>
	            
	            <input class="submit" type="submit" value="Update product" />
	        </form>
	    </div>
	    <jsp:include page="/WEB-INF/pagine/aside.jsp"/>
    </div>
    
	<jsp:include page="/WEB-INF/pagine/footer.jsp"/>
</body>
</html>