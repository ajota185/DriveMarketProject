<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.user.User" %>
<%@ page import="model.product.Product" %>
<%@ page import="model.shoppingCart.ShoppingCart" %>
<%User user = (User) session.getAttribute("user"); %>
<%
ArrayList<Product> products = (ArrayList<Product> ) request.getAttribute("products");
%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/pagine/head.jsp">
		<jsp:param value="DRIVE MARKET" name="title"/>
		<jsp:param value="style" name="style"/>
		<jsp:param value="scriptAjax" name="script"/>
	</jsp:include>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/pagine/header.jsp"/>
	<div class="container">
	
		<div class="content-lista-productos">

	        <h2>Search some product</h2>
	        
			<input class="input-buscador" id="search" placeholder="Write to search" type="text" name="text"/><br>
	
	        <h3 style="text-align:left;">Products</h3>
	        <div id="lista-productos" class="lista-productos">
	            <%for(Product product : products){ %>
	                    <div class="producto-lista">
	                        <div class="imagen-producto-lista">
	                            <a href=<%= "ServletProduct?id_prod="+product.getId_prod()%>>
	                                <img src=<%= product.getMain_photo() %>>
	                            </a>
	                        </div>
	
	                        <div class="informacion-producto-lista">
	                            <a href=<%= "ServletProduct?id_prod="+product.getId_prod()%>>
	                                <h3><%=product.getName() %></h3>
	                            </a>
	                            <p>Price: <%=product.getPrice() %> €</p>
	                        </div>
	                    </div>
	            <%} %>
	
	        </div>
	    </div>
		

		<jsp:include page="/WEB-INF/pagine/aside.jsp"/>
	
	</div>
	<jsp:include page="/WEB-INF/pagine/footer.jsp"/>
</body>
</html>