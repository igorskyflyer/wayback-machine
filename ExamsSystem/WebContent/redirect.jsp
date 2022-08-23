<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%

String test_id = (String)request.getParameter("test");
%>   
<script>

window.open('test.jsp?test=<%=test_id%>');

</script>