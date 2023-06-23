<%@ page import="model.product.Product" %>
<%@ page import="model.user.User" %>
<%@ page import="model.image.Image" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
	Product product=(Product) request.getAttribute("product");
	User user = (User) session.getAttribute("user");
	ArrayList<Image> images = (ArrayList<Image>) request.getAttribute("images");
%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/pagine/head.jsp">
		<jsp:param value="Product" name="title"/>
		<jsp:param value="style" name="style"/>
		<jsp:param value="script" name="script"/>
	</jsp:include>
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
	<jsp:include page="/WEB-INF/pagine/header.jsp"/>
	<div class="container">
		<div class="content-producto">
			<div class="info-producto">
				
				<h1><%=product.getName() %></h1>
				<h2>Tesla</h2>
				<h3>Precio: <%=product.getPrice() %> €</h3>
				
				<%=product.getDescription() %>
	
				<%if(!product.getLink().isEmpty()){ %>
					<p>
						Página oficial: <a href=<%=product.getLink() %>><%=product.getLink() %></a>
					</p>
				<%} %>
	
		
		
				<div class="botones-sociales">
					<span>Twitter: </span>
					<button id="boton-twitter"type="button"> <img src="./immagini/twitter-logo.png" /></button>
					<span>&nbsp;Facebook: </span>
					<button id="boton-facebook" type="button"> <img src="./immagini/facebook-logo.png" /></button>
					<span>&nbsp;&nbsp;</span>
				</div>
	
	
				<div class="iconos-producto">
					<%if(user!=null){ %>
						<%if(user.isAdmin()) {%>
							<% String link = "ServletLink?option=updateProduct&id_prod="+product.getId_prod(); %>
							<a href = <%=link %>>
								<i class="material-icons">edit</i>
							</a>
		
							<a href = "ServletDeleteProduct?id_prod=<%= product.getId_prod()%>">
								<i class="material-icons">delete</i>
							</a>
						<%}else{ %>
							<%
								int quantity=0;
								if(user.getShoppingCart()!=null){
									ArrayList<Product> products = user.getShoppingCart().getProducts();
									int index = products.indexOf(product);
									if(index>-1){
										quantity = user.getShoppingCart().getQuantity().get(index);
									} 
								}
							%>
							<form action="ServletAddShoppingCart" method="post">
					            Id_prod:<br>
					            <input type="text" name="id_prod" value="<%=product.getId_prod() %>" readonly/><br>
					            Quantity:<br>
					            <input type="number" name="quantity" value="<%=quantity %>" min="0"/><br>
					            <input class="submit" type="submit" value="Add to ShoppingCart" />
					        </form>
						<%} %>
					<%} %>
				</div>
		
			</div>
		
			<div class="imagenes">
				<%for(Image i : images){ %>
					<div class="img-producto">
						<img src=<%=i.getPath() %> >
					</div>
				<%} %>
			</div>
		</div>
	
		<jsp:include page="/WEB-INF/pagine/aside.jsp"/>
	
	</div>
	<jsp:include page="/WEB-INF/pagine/footer.jsp"/>
</body>
</html>