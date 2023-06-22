<%@ page import="java.util.ArrayList" %>
<%@ page import="model.product.Product" %>
<%@ page import="model.user.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
	ArrayList<Product> products=(ArrayList<Product>) request.getAttribute("products");
	if(products == null){
		products=(ArrayList<Product>) application.getAttribute("products");
	}
%>

<div class="container">
	<div class="content">
		<% for(Product p : products){ %>
			<div class="producto">
			<% String link = "ServletProduct?id_prod="+p.getId_prod(); %>
				<a href=<%=link %>>
					<img src=<%= p.getMain_photo() %>>
					<h3><%= p.getName() %></h3>
				</a>
			</div>
		<% } %> 
	</div>

	<jsp:include page="/WEB-INF/pagine/aside.jsp"/>

</div>