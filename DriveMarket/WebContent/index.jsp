<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="WEB-INF/pagine/head.jsp">
		<jsp:param value="DRIVE MARKET" name="title"/>
		<jsp:param value="style" name="style"/>
		<jsp:param value="script" name="scriptjs"/>
	</jsp:include>
</head>
<body>
	<jsp:include page="WEB-INF/pagine/header.jsp"/>
	<jsp:include page="WEB-INF/pagine/contenuti.jsp"/>
	<jsp:include page="WEB-INF/pagine/footer.jsp"/>
</body>
</html>