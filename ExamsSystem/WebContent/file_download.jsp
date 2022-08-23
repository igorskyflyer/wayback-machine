
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.io.*, java.net.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP Reading Text File</title>
</head>
<body>
	<%
		String name = (String) request.getParameter("name");

	   request.getRequestDispatcher("files/" + name).forward(request,
			response);
	%>
</body>
</html>