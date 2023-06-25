<%@ page import="model.user.User" %>
<header>
	<div class="container-logo">
		<img  src="immagini/tesla-logo.jpg" >
	</div>
	<div class="cont-tittle-menu">
		<div class="tittle">
			<span>Drive Market</span>
		</div>
		<div class="menu">
			<ul>
				<li><a href="ServletHome">Home</a></li>
				<li><a href="ServletSearch">Search</a></li>
				<% User user = (User) session.getAttribute("user"); %>
				<%if(user!=null && user.getShoppingCart()!=null){ %>
					<li><a href="ServletLink?option=shoppingCart">Shopping Cart</a></li>
				<%} %>
			</ul>
		</div>
	</div>
</header>
