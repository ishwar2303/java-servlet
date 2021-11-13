Admin Dashboard

<% 
	String studentEmail = (String)request.getAttribute("studentEmail");
	String studentFirstName = (String)request.getAttribute("studentFirstName");
%>
<h1>Name   : <%= studentFirstName %></h1>
<h3>E-mail : <%= studentEmail %></h3>

<a href="logout?page=student-login">Logout</a>