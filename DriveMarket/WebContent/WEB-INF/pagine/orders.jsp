<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.user.User" %>
<%@ page import="model.product.Product" %>
<%User user = (User) session.getAttribute("user"); %>
<%ArrayList<Product> products = user.getShoppingCart().getProducts(); %>
<%ArrayList<Integer> quantity = user.getShoppingCart().getQuantity(); %>
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

	        <h2>Shopping Cart</h2>
	        
	
	
	        <h3 style="text-align:left;">Products in the ShoppingCart</h3>
	        <div class="lista-productos">
	        	<%float total=0; %>
	            <%for(int i=0; i<products.size(); i++){ %>
	            	<%if(quantity.get(i)>0){ %>
	            		<%total+=(quantity.get(i)*products.get(i).getPrice()); %>
	                    <div class="producto-lista">
	                        <div class="imagen-producto-lista">
	                            <a href=<%= "ServletProduct?id_prod="+products.get(i).getId_prod()%>>
	                                <img src=<%= products.get(i).getMain_photo() %>>
	                            </a>
	                        </div>
	
	                        <div class="informacion-producto-lista">
	                            <a href=<%= "ServletProduct?id_prod="+products.get(i).getId_prod()%>>
	                                <h3><%=products.get(i).getName() %></h3>
	                            </a>
	                            <p>Prize: <%=products.get(i).getPrice() %> €</p>
	                            <p>Quantity: <%=quantity.get(i) %></p>
	                        </div>
	                    </div>
                    <%} %>
	            <%} %>
	
	        </div>
	        <h3 style="text-align:left;">Total: <%=total %> €</h3>
	        <a href="ServletAddOrder"><button >Buy</button></a>
	
	    </div>
		

		<jsp:include page="/WEB-INF/pagine/aside.jsp"/>
	
	</div>
	<jsp:include page="/WEB-INF/pagine/footer.jsp"/>
</body>
</html>