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
		<jsp:param value="DRIVE MARKET" name="title"/>
		<jsp:param value="style" name="style"/>
		<jsp:param value="script" name="scriptjs"/>
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
					<a href="../producto_imprimir.php?producto={{id}}"><button id ="boton-imprimir"> Imprimir</button></a>
					<span>&nbsp;</span>
					<button class="comentarios" id="boton-comentarios" type="button">Comentarios</button>
				</div>
	
	
				<div class="iconos-producto">
					<%if(user!=null && user.isAdmin()){ %>
						<a href = "editar_producto.php?producto={{ id }}">
							<i class="material-icons">edit</i>
						</a>
	
						<a href = "borrar_producto.php?producto={{ id }}">
							<i class="material-icons">delete</i>
						</a>
					<%} %>
				</div>
		
			</div>
		
			<div class="imagenes">
				<%for(Image i : images){ %>
					<div class="img-producto">
						<img src=<%=i.getPath() %> >
						<span><%=i.getPie() %></span>
						<%if(user!=null && user.isAdmin()){ %>
							<a href = "editar_pie.php?imagen={{ imagen.id }}">
								<i class="material-icons">edit</i>
							</a>
						<%} %>
					</div>
				<%} %>
			</div>
		</div>
	
		<jsp:include page="/WEB-INF/pagine/aside.jsp"/>
	
	</div>
	<jsp:include page="/WEB-INF/pagine/footer.jsp"/>
</body>
</html>