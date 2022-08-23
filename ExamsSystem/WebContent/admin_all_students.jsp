<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="Factory.*"%>
<%@ page import="Model.*"%>
<%@ page import="java.util.*"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/styles.css" type="text/css" />
<script src="script/index.js"></script>
<script src="script/admin.js"></script>
<title>Prikaz dodeljenih testova</title>
<style type="text/css">
<!-- CSS goes in the document HEAD or added to your external stylesheet -->
<style type="text/css">


</style>
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
			
			<input  type="button" value="Odjavi se" style="float: right;" class="button_input" onclick = "LogOutClick()"><br>
			<input  type="button" value="Nazad" style="float: right;" class="button_input" onclick = "toMainPanel()"><br>
			<h2>Pregled svih studenata</h2>
			<hr class="myhr"><br><br>
		</div>
       <div class="content">
                  <%
                  
                  Factory_Course factory_course = new Factory_Course();
                  List<Model_Course>courses = new ArrayList<Model_Course>();
                  courses = factory_course.getAllCoursers();
                  
                  HashMap<Integer,String> cmap = new HashMap<Integer,String>();
                  for(Model_Course c : courses){
                	  cmap.put(c.getId(),c.getName());
                  }
                  
                  Factory_Student fs = new Factory_Student();
                  List<Model_Student>students = new ArrayList<Model_Student>();
                  students = fs.getAll();
                  
                  %>
                
                  <table border="1" width="100%"> 
            	  <tr style="color:white; background:blue;" >
            	    <th>Ime</th>
            	    <th>Prezime</th>
            	    <th>Broj indeksa</th>
            	    <th>Smer</th>
            	    <th>Email</th>
            	   </tr>
                  <%
                  for(Model_Student s : students){
                	  
                	  %>
                	  <tr>
                	     <th><%=s.getName()%></th>
            	         <th><%=s.getSurname()%></th>
            	         <th><%=s.getIndex_num() %></th>
            	         <th><%=cmap.get(s.getCourse())%></th>
            	         <th><%=s.getEmail() %></th>
                	</tr>
                	 <% 
                	  
                	  }
                  
                  %>
		 </table>
		   
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