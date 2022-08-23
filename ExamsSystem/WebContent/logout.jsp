<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

HttpSession sess = request.getSession();
sess.removeAttribute("Student");
sess.invalidate();
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0); 


request.getRequestDispatcher("index.jsp").forward(request,
		response);
%>