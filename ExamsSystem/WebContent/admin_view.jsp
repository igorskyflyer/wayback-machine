<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="Factory.*"%>
<%@ page import="Model.*"%>
<%@ page import="java.util.*"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administrator</title>
<link rel="stylesheet" href="./css/styles.css" type="text/css" />
<script src="script/index.js"></script>
<script src="script/admin.js"></script>

</head>
<body>
<%
	HttpSession sesija = request.getSession();
	Model_Administrator admin = (Model_Administrator) sesija.getAttribute("Admin");

	if (admin != null) {
%>
	<div class="main_div">
		<div class="header">
			<div class="logo"></div>
			<h1>Dobrodosli  : <%=(admin.getName() + " " + admin.getSurname())%></h1>
			<input  type="button" value="Odjavi se" class="button_input" style="float: right;" onclick = "LogOutClick()"><br>
			 <h2>Panel Administratora</h2>
			<hr class="myhr"><br><br>
		

		</div>

		<div class="content">
		<input  class="admin_menu_button" type="button" value = "Prikaz dodeljenoh testova" onClick="onShowRelsClick()"><br><br>
		<input  class="admin_menu_button" type="button" value = "Uredjivanje testova" onClick="onEdit()"><br><br>
		<input  class="admin_menu_button" type="button" value = "Studenti koji su polozili predmet" onClick = "onShowPassedExams()" ><br><br>
		<input  class="admin_menu_button" type="button" value = "Prikaz svih polaganja" onClick = "onShowAllExams()" ><br><br>
		<input  class="admin_menu_button" type="button" value = "Svi studenti" onClick="onShowAllStudents()" ><br><br>
		
		</div>
	</div>

</body>
</html>
<%
	} else {

		request.getRequestDispatcher("index.jsp").forward(request,
				response);
	}
%>