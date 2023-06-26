<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String error=(String) request.getAttribute("error");
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
		<div class="content-login">
	        <h2>Login</h2>
	
	        <div id="error">
	            <%if(error!=null){ %>
	                <p><%=error %></p>
	            <%} %>
	        </div>
	          
	        <form name="FormLogin" action="ServletLogin" method="post" onsubmit="return validateFormLogin()">
	            Nick:<br>
	            <input type="text" name="nick" /><br>
	            Password:<br>
	            <input type="password" name="password" /><br>
	            <input type="submit" value="Login" />
	        </form>
	
	        <p>¿Do you have an account? <a href="ServletLink?option=register"> Register</a></p>
	    </div>
	    <jsp:include page="/WEB-INF/pagine/aside.jsp"/>
    </div>
    
	<jsp:include page="/WEB-INF/pagine/footer.jsp"/>
</body>
</html>