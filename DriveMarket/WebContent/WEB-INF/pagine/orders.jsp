<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.user.User" %>
<%@ page import="model.product.Product" %>
<%@ page import="model.shoppingCart.ShoppingCart" %>
<%User user = (User) session.getAttribute("user"); %>
<% ArrayList<ShoppingCart> shoppingCarts = user.getOrders();%>
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
	
		<div class="content-lista-productos">

	        <h2>Orders</h2>
	        
	
	
	        <h3 style="text-align:left;">All of your Orders</h3>
	        <div class="lista-productos">
	            <%for(int i=0; i<shoppingCarts.size(); i++){ %>
	                    <div class="producto-lista">
	                        <div class="informacion-producto-lista">
	                            
                                <h3>ID: <%=shoppingCarts.get(i).getOrder().getId_order() %></h3>
	                            <b>Date: <%=shoppingCarts.get(i).getOrder().getDate() %></b><br>
	                        	<b>Products:</b>
	                        	<%float total=0; %> 
	                        	<%for(int j=0; j<shoppingCarts.get(i).getProducts().size(); j++){ %>
	                        		<%Product product = shoppingCarts.get(i).getProducts().get(j); %>
	                        		<%total+=product.getPrice()*shoppingCarts.get(i).getQuantity().get(j); %>
	                        		<div class="producto-lista-order">
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
				                            <p>Quantity: <%=shoppingCarts.get(i).getQuantity().get(j) %></p>
				                        </div>
			                        </div>
		                        <%} %>
		                        <h3 style="text-align:left;">Total: <%=total %> €</h3>
		                        
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