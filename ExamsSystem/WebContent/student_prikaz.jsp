<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Factory.*"%>
<%@ page import="Model.*"%>
<%@ page import="Service.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/styles.css" type="text/css" />
<script src="script/index.js"></script>
<title>Student</title>
<script type="text/javascript">
   
</script>

</head>
<body>
<body>
<script>

function sendTo(url){
	window.location.replace(url);
}


</script>
<%
	HttpSession sesija = request.getSession();
	Model_Student student = (Model_Student) sesija
			.getAttribute("Student");

	if (student != null) {
%>
	<div id="main_div_student">
		<div class="header">
			<div class="logo"></div>
			<h1>Dobrodosli  : <%=(student.getName() + " " + student.getSurname())%></h1>
			<input type="button" value="Odjavi se" class="button_input" onclick = "LogOutClick()">
			 
			<hr class="myhr">

		</div>

		<div class="content">
		<%
		
		Factory_Course fc = new Factory_Course();
		Model_Course course = fc.getcourseById(student.getCourse());
		Factory_Student fs = new Factory_Student();
		
	    List<Model_Item>items = fs.getItems(student);
		
		%>
		
                	  
                	      
	<div class="course_div">
	<h2><%=course.getName() %></h2>
	</div>	
	<br><br>
	
	
	<%
	for(Model_Item i : items){
		%>
		<div class="item_div" style="border: thin solid black">
		<div class="item_name"><%=i.getName()%></div>
		<ul>
		<div class="type">Testovi</div> 
		<%
		

 	    Factory_ItemTestRel itr = new Factory_ItemTestRel();
        List<Model_ItemTestRel>rels = new ArrayList<Model_ItemTestRel>();
        rels = itr.getRelsForItem(i);
        
        Factory_File ff = new Factory_File();
        List<Model_File>files = new ArrayList<Model_File>();
        files = ff.getAllByItemId(i.getId());
	      
        for(Model_ItemTestRel r : rels){
		
        	Model_Exam exam = student.isTestAveliable(r.getTest_id());
        	
        	
		%>
		
		<%if(exam == null){ %>
		   <ul>
               <li class="test_icon"><a href="evident_test.jsp?test=<%=r.getTest_id()%>&item=<%=i.getId()%>"><%=r.getTest_name()%></a></li>
              
           </ul>
           
          <%
		}else{
			
			if(exam.getGrade() == 5){
				%>
				<ul align="left" padding-left="10px" style="width:200px;">
	               <li class="test_icon"><a href="#" ><p style="color:red; "><%=r.getTest_name()%> ------- Polagan : <%=exam.getDate_time()%> ,  Ocena : <%=exam.getGrade()%> ,  Procenat: <%=exam.getPercent()%> %</p></a></li>
	              
	           </ul>
				<%
				
			}else{
			%>
			<ul align="left" padding-left="10px" style="width:200px;">
               <li class="test_icon"><a href="#" ><p style="color:green; "><%=r.getTest_name()%> ------- Polagan : <%=exam.getDate_time()%> ,  Ocena : <%=exam.getGrade()%> ,  Procenat: <%=exam.getPercent()%> %</p></a></li>
              
           </ul>
			<%
			}
		}
        }
          
          %> 
          <div class="type"> Fajlovi</div> 
          <% 
          for(Model_File f : files){
          %>
           <ul>
               <li class="file_icon"><a href="file_download.jsp?name=<%=f.getName()%>"><%=f.getName()%></a></li>
               
           </ul> 
           <%} %>     
	    </ul>
		</div>
		<hr>
	<% 
	}
	
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
