<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="Factory.*"%>
<%@ page import="Model.*"%>
<%@ page import="Service.*"%>
<%@ page import="java.util.*"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/styles.css" type="text/css" />
<script src="script/index.js"></script>
<script src="script/admin.js"></script>
<title>Prikaz dodeljenih testova</title>
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
			<h2>Pregled svih polaganja</h2>
			<hr class="myhr"><br><br>
		</div>
       <div class="content">
                  <%
                  
               
                  Factory_Student fs = new Factory_Student();
                  List<Model_Student>students = new ArrayList<Model_Student>();
                  students = fs.getAll();
                 
                   Factory_Item factory_item = new Factory_Item();
                  List<Model_Item>items = new ArrayList<Model_Item>();
                  items = factory_item.getItemsByAdmin(admin);
                  
                  Factory_Test f_test = new Factory_Test();
                  List<Model_Test>tests = new ArrayList<Model_Test>();
                  tests = f_test.getAll();
               
                  
                  %>
                  
                  <table border="1" width="100%">
                 
                  <tr style="color:white; background:blue;">
                      <th>Predmet</th>
                      <th>Test</th>
                      <th>Student</th>
                      <th>Broj indeksa</th>
                      <th>Ocena</th>
                      <th>Procenat</th>
                       <th>Datum vreme</th>
                      </tr>
                  
                  <% 
                  for(Model_Item i : items){
                	  
                	      Factory_Exam fe = new Factory_Exam();
                          List<Model_Exam>exams = new ArrayList<Model_Exam>();
                          exams = fe.getAllByItemId(i.getId());
                	      
                          for(Model_Exam e : exams){
                        	  
                        	  Model_Student student = Service_Student.selectById(students,e.getStudent_id());
                              Model_Item item = Service_Item.selectById(items,e.getItem_id());
                              Model_Test test = Service_Test.selectById(tests,e.getTest_id());
                        	  
                           %>
                          <tr>
                          <th><%=item.getName()%></th>
                          <th><%=test.getName()%></th>
                          <th><%=student.getName() + " " +student.getSurname()%></th>
                          <th><%=student.getIndex_num()%></th>
                          <th><%=e.getGrade()%></th>
                          <th><%=e.getPercent() + " %"%></th>
                           <th><%=e.getDate_time()%></th>
                          </tr>
                	      
                	      <%
                          }
                	      %>
                	 
                	  
                	  
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