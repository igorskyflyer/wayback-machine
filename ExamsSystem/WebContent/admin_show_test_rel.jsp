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
			<h2>Pregled dodeljenih testova</h2>
			<hr class="myhr"><br><br>
		</div>
       <div class="content" style="overflow-y:scroll; height:650px">
                  <%
                  
                  Factory_Item factory_item = new Factory_Item();
                  List<Model_Item>items = new ArrayList<Model_Item>();
                  items = factory_item.getItemsByAdmin(admin);
                  

                  Factory_Course fc = new  Factory_Course();
                  List<Model_Course>courses = new ArrayList<Model_Course>();
                  courses = fc.getAllCoursers();
                  
                  
                  for(Model_Item i : items){
                	  
                	  %>
                	  
                	  <div class="item_div">
		              <div class="item_name"><%=i.getName()%>( <%=Service_Course.selectById(courses, i.getCourse()).getShort_name()%> )</div>
                	   <ul>
                	      <%
                	      Factory_ItemTestRel itr = new Factory_ItemTestRel();
                          List<Model_ItemTestRel>rels = new ArrayList<Model_ItemTestRel>();
                          rels = itr.getRelsForItem(i);
                	      
                          for(Model_ItemTestRel r : rels){
                        	  
                           %>
                           <li ><a href="#"><%=r.getTest_name()%></a><a id="dell_href" href="delete_rel.jsp?id=<%=r.getId()%>">Obrisi relaciju</a></li>
                	      
                	      <%
                          }
                	      %>
                	  
                	    </ul>
                	  
                	  
                	  <% 
                	  
                	  
                  }
                  
                  %>
		
		   </div>
		   <%
		   String del_msg = (String)request.getAttribute("del_msg");
		   if(del_msg != null){
			   if(!del_msg.matches("")){
				   %>
				   
				   <script>alert('<%=del_msg%>')</script>
				   <% 
			   }
				   
		   }
		
		   request.removeAttribute("del_msg");
		   %>
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