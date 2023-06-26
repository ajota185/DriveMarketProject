<%@ page import="model.user.User" %>
<%@ page import="model.user.UserDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
    
<%User user = (User) session.getAttribute("user"); 
UserDAO userDAO = new UserDAO();
ArrayList<User> users = userDAO.getAllUsers();
%>

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
		<div class="content-cambiar-tipo-usuario">
	        <h2>Change type of user</h2>
	
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
	          
	        <form action="ServletChangeUser" method="post">
	            Nick:<br>
	            <select name="nick">
	            	<%for(User u : users){ %>
		                <option value="<%=u.getNickName() %>"><%=u.getNickName() %></option>
	                <%} %>
	            </select><br><br>
	            Type of user:<br>
	            <select name="type_user">
	                <option value="normal">Normal user</option>
	                <option value="admin">Admin user</option>
	            </select>
	            <br><br>
	            <input type="submit" value="Change" />
        	</form>
	    </div>
	    <jsp:include page="/WEB-INF/pagine/aside.jsp"/>
    </div>
    
	<jsp:include page="/WEB-INF/pagine/footer.jsp"/>
</body>
</html>