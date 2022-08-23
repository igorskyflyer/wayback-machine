<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="Factory.*"%>
<%@ page import="Model.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<link rel="stylesheet" href="./css/styles.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="script/index.js"></script>
<title>Registracija</title>
</head>
<body>
	<div class="main_div">
		<div class="header">
			<div class="logo"></div>
			<h1>Registracija Korisnika</h1>
			<br>
			<hr class="myhr">

		</div>

		<div class="content">
		<div class="reg">
				<form action="Registration" method="post">

                    <label>Korisnicko ime:</label><br> 
					<input class="text_input"type="text" name="user"> <br>
						
					<label>Broj indeksa:</label><br> 
					<input type="text" class="text_input" name="index_num"><br>
						
					<label>Lozinka:</label><br> 
					<input type="password" name="pass" class="text_input"><br>
					
					<label>Ime</label><br> 
					<input type="text" name="name" class="text_input"><br>
						
					<label>Prezime</label><br> 
					<input type="text" name="surname" class="text_input"><br>
					
					<label>Email</label><br> 
				    <input type="text" name="email" class="text_input"><br>
					
                    <label>Adresa</label><br> 
					<input type="text" name="address" class="text_input"><br>
					
					<label>Telefon</label><br> 
				    <input type="text" name="phone" class="text_input"><br>
						
					



					<label>Smer:</label><br>
					<select class="text_input" name="course">
							<%
								List<Model_Course> l = new ArrayList<Model_Course>();
								Factory_Course fc = new Factory_Course();
								l = fc.getAllCoursers();

								for (Model_Course s : l) {
							%>
							<option value=<%=s.getId()%>><%=s.getName()%></option>
							<%
								}
							%>

					</select><br><br><br>
					

					
						
						<input type="submit" value="Registruj se" class="button_input"><br><br>
				
					
					
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
						
					
					


				</form>
				
				<input type="submit" value="Nazad" class="button_input" onclick = "LogOutClick()"><br>
					
				
				<div>
			</div>
		</div>
</body>
</html>