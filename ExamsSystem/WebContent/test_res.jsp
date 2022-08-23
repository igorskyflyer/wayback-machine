<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="Factory.*"%>
<%@ page import="Model.*"%>
<%@ page import="Service.*"%>
<%@ page import="java.util.*"%>
<link rel="stylesheet" href="./css/styles.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rezultat testa</title>
<script src="script/index.js"></script>
</head>
<body>

	<%
		String exam_id = (String) request.getParameter("exam_id");
		String percent = (String) request.getParameter("percent");
		int grade = 0;

		int pr = Integer.valueOf(percent);
		switch (pr) {

		case 0:
			grade = 5;
			break;

		case 20:
			grade = 6;
			break;

		case 40:
			grade = 7;
			break;

		case 60:
			grade = 8;
			break;

		case 80:
			grade = 9;
			break;

		case 100:
			grade = 10;
			break;

		}
		
		
		Factory_Exam fe = new Factory_Exam();
		fe.setTestResult(Integer.valueOf(exam_id), pr, grade);
		
	%>
	<div id="main_div_student">
		<div class="header">
			<div class="logo"></div>
			<h1>Polaganje testa</h1>
			<input type="button" value="Odjavi se" style="float: right;"
				class="button_input" onclick="LogOutClick()"> <input
				type="button" value="Na početnu" style="float: right;"
				class="button_input" onclick="toMainPage()">

			<hr class="myhr">

		</div>

		<div class="content" align="left" padding-left="10px" sytle="height:600px;"></div>

         <h2>Tacno ste odgovorili na : <%=pr%> % pitanja</h2>
		

	</div>




</body>
</html>