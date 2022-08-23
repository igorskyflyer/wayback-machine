<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ page import="Factory.*"%>
<%@ page import="Model.*"%>
<%@ page import="java.util.*"%>
<%
	String id = (String) request.getParameter("id");
	
   Factory_ItemTestRel fr = new Factory_ItemTestRel();
	if(fr.deleteById(Integer.valueOf(id))){
		request.setAttribute("del_msg", "Relacija uspesno obrisana");
	}else{
		request.setAttribute("del_msg", "Relacija uspesno obrisana");
	}
	
	request.getRequestDispatcher("admin_show_test_rel.jsp").forward(request,
			response);
%>
