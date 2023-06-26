<%@ page import="model.user.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%User user= (User) session.getAttribute("user"); %>

<aside>
	<h2>Options of User</h2>
	<ul>
		<%if(user == null){ %>
			<li>
				<a href="ServletLink?option=login">Login</a>
			</li>
		<%}else{ %>
			<li>User: <%= user.getNickName() %></li>
			<li>
				<a href="ServletLink?option=updateUser">My Account</a>
			</li>
			<%if(user.getOrders()!=null){ %>
				<li>
					<a href="ServletLink?option=orders">Orders</a>
				</li>
			<%} %>
			<li>
				<a href="ServletLogout">Logout</a>
			</li>
			<%if(user.isAdmin()){ %>
				<li>
					<a href="ServletLink?option=changeUser">Change type of user</a>
				</li>
				<li>
					<a href="ServletLink?option=deleteUser">Delete user</a>
				</li>
				<li>
					<a href="ServletLink?option=insertProduct">Add Product</a>
				</li>
				<li>
					<a href="ServletOrders">See all the Orders</a>
				</li>
			<%} %>
		<%} %>
			
			

			
		
	</ul>
	<br><br>
	<h2>Description</h2>
	
	<ul>
		<li>
			<a href="ServletHome">Our history</a>
		</li>
		<li>
			<a href="ServletHome">Old models</a>
		</li>
         	</ul>

	<br>
	<p>Page dedicated to offer information on the various products offered by the Tesla brand.</p>
</aside>