<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="Factory.*"%>
<%@ page import="Model.*"%>
<%@ page import="java.util.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Početna strana</title>
<link rel="stylesheet" href="./css/styles.css" type="text/css" />
<script src="script/index.js"></script>
</head>
<body>

<%
HttpSession sess = request.getSession();
Model_Student student = (Model_Student)sess.getAttribute("Student");
if(student != null){
	sess.removeAttribute("Student");
}



%>
	<div class="main_div">
		<div class="header">
			<div class="logo"></div><br>
			<h1>Sistem za elektronsko učenje</h1>
			<br>
			<hr class="myhr">

		</div>

		<div class="content">
			<div id="login_div">
				<form action="UserService" method="POST">
				<input type="hidden" name="log_type" value="student">
					<label>Korisničko ime: </label><br> <input class="text_input"
						type="text" id="username" name="username" placeholder="unesite korisničko ime"><br>
					<label>Lozinka :</label><br>
					<input class="text_input"type="password" id="password" placeholder="unesite lozinku" name="password" ><br>
					<input type="submit" class="button_input" value="Prijavi se">
					<br> <br> <input type="button" class="button_input"
						value="Registruj se" onClick="onRegClick()"><br> <br>
						 <input type="button" class="button_input"
						value="Administrator" onClick="onAdminLogin()">
				</form>

				<%
					String msg = (String) request.getAttribute("msg");
				 
					
					if (msg != null && msg.length() > 0) {
						%>
						<script>
						alert('<%=msg%>');
						</script>
				  <%
				  request.removeAttribute("msg");
					}
				%>
			</div>
		</div>
	</div>


</body>
</html>