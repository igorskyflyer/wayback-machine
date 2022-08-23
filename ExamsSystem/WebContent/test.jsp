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
<script src="script/index.js"></script>
<script src="test.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test</title>
</head>
<body>

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

		<div class="content" align="left" padding-left="10px">
		<div class="test_info_div">
			<h2>Test se može polagati samo jednom.Izlaskom sa strane,test će
				se smatrati polaganim sa osvojenih 0%.</h2>
       </div>
       <br>

			<%
				String test_id = (String) request.getParameter("test");
				String item_id = (String) request.getParameter("item");
				String exam_id = (String) request.getParameter("exam");

				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", -1);
				response.setDateHeader("Last-Modified", 0);

				Factory_Test factory_test = new Factory_Test();

				Model_Test test = factory_test.getById(test_id);

				List<String> titles = test.getTitlesList();

				List<String> values = test.getValuesList();
			%>
			<input type="hidden" id="answers" value="<%=test.getAnswers()%>">
			<input type="hidden" id="exam_id" value="<%=exam_id%>">
			




			<%
				for (int i = 0; i < 5; i++) {
			%>
			<div style="padding-left: 10px;">
				<form action="">


					<b><%=(i + 1) + ". "%><%=titles.get(i)%></b><br>

					<%
						for (int n = 0; n < 3; n++) {

								List<String> vals_sub = Model_Test.getValuesSubList(values
										.get(i));
					%>
					<input type="radio" name="q<%=i + 1%>" value="<%=(n + 1)%>">
					<%=String.valueOf(vals_sub.get(n))%><br>


					<%
						}
					%>
				</form>

			</div>
			<hr>
			<br>
			<%
				}
			%>

			<input type="button" class="button_input" value="Kraj testa"
				onClick="finish()"><br> <br>

			<%
				
			%>

		</div>
	</div>







</body>
</html>