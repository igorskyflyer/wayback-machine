<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>Dodeljivanje testova</title>
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
			<h2>Dodeljivanje testova</h2>
			<hr class="myhr"><br><br>
		</div>
       <div class="content" >
                  <%
                  
                  Factory_Item factory_item = new Factory_Item();
                  List<Model_Item>items = new ArrayList<Model_Item>();
                  items = factory_item.getItemsByAdmin(admin);
                  
                  Factory_Test f_test = new Factory_Test();
                  List<Model_Test>tests = new ArrayList<Model_Test>();
                  tests = f_test.getAll();
               
                  Factory_Course fc = new  Factory_Course();
                  List<Model_Course>courses = new ArrayList<Model_Course>();
                  courses = fc.getAllCoursers();
                  
                  
                  
                  
                  %>
                  
                  
                  <form action="AdminService" method="POST">
                  
                    
					<label>Odaberite test:</label><br>
					<select class="text_input" name="test">
							<%
							

								for (Model_Test t : tests) {
							%>
							<option value=<%=t.getId()%>><%=t.getName()%></option>
							<%
								}
							%>

					</select><br><br><br>
					
					
					<label>Odaberite predmet:</label><br>
					<select class="text_input" name="item">
							<%
							

								for (Model_Item i : items) {
							%>
							<option value=<%=i.getId()%>><%=i.getName()%> (<%=Service_Course.selectById(courses, i.getCourse()).getShort_name()%>)</option>
							<%
								}
							%>

					</select><br><br><br>
					
					<input type="submit" value = "Sačuvaj" class="button_input">
                  
                </form>
                <br><br>
                  
                  
                  <div style="overflow-y:scroll; height:400px">
                  <table border="1" width="100%" >
                 
                  <tr style="color:white; background:blue;">
                      <th>Predmet</th>
                      <th>Smer</th>
                      <th>Test</th>
                      
                      </tr>
                  
                  <% 
                  for(Model_Item i : items){
                	  
                	   Factory_ItemTestRel itr = new Factory_ItemTestRel();
                       List<Model_ItemTestRel>rels = new ArrayList<Model_ItemTestRel>();
                       rels = itr.getRelsForItem(i);
             	      
                       for(Model_ItemTestRel r : rels){
                	      
                        
                        	 %>
                          <tr>
                           <th><%=i.getName()%></th>
                           
                           <th><%=Service_Course.selectById(courses, i.getCourse()).getShort_name()%></th>
                            <th><%=r.getTest_name()%></th>
                         </tr>
                	      
                	      <%
                          }
                	      %>
                	 
                	  
                	  
                	  <% 
                	  
                	  
                  }
                  
                  String res = (String)request.getAttribute("msg");
                  if(res != null){
                	  
                 
                  if(!res.matches("")){
                	  %>
                	  
                	  <script>
                	  alert('<%=res%>');
                	  </script>
                	  
                	  <% 
                	  
                	  request.removeAttribute("msg");
                  }
                  
                  }
                  
                  %>
		
		   </table>
		   </div>
		 
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