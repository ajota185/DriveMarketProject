<%@ page import="model.user.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
    
<%User user = (User) session.getAttribute("user"); %>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/pagine/head.jsp">
		<jsp:param value="DRIVE MARKET" name="title"/>
		<jsp:param value="style" name="style"/>
		<jsp:param value="script" name="script"/>
	</jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/pagine/header.jsp"/>
	<div class="container">
		<div class="content-modificar-usuario">
	        <h2>My Account</h2>
	
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
	          
	        <form action="ServletUpdateUser" method="post">
	            Nick:<br>
	            <input type="text" name="nick" value="<%= user.getNickName() %>" /><br>
	            Password: (Enter a new one or the current one if you do not want to change it).<br>
	            <input type="password" name="password" /><br>
	            Email:<br>
	            <input class="email" type="email" name="email" value="<%=user.getEmail() %>" /><br>
	            <input type="submit" value="Update user" />
	        </form>
	    </div>
	    <jsp:include page="/WEB-INF/pagine/aside.jsp"/>
    </div>
    
	<jsp:include page="/WEB-INF/pagine/footer.jsp"/>
</body>
</html>