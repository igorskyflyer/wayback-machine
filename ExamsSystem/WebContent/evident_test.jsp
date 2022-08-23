<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Factory.*"%>
<%@ page import="Model.*"%>
<%@ page import="Service.*"%>
<%@ page import="java.util.*"%>

<%
	String test_id = (String) request.getParameter("test");
	String item_id = (String) request.getParameter("item");

	HttpSession sesija = request.getSession();
	Model_Student student = (Model_Student) sesija
			.getAttribute("Student");

	Model_Exam exam = new Model_Exam();
	exam.setGrade(5);
	exam.setItem_id(Integer.valueOf(item_id));
	exam.setPercent(0);
	exam.setTest_id(Integer.valueOf(test_id));
	exam.setStudent_id(student.getId());
	Factory_Exam fe = new Factory_Exam();
	exam = fe.insert(exam);

	  
   
		
	
	if (exam.getId() != 0) {

		
		response.sendRedirect("test.jsp?test=" + test_id + "&item=" + item_id + "&exam=" + exam.getId());

	} else {

		
		//request.getRequestDispatcher("student_prikaz.jsp").forward(request,
			//	response);
		response.sendRedirect("student_prikaz.jsp");

	}
%>