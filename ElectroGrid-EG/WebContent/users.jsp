<%@page import="model.userManagement.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <% 
    
//Insert User----------------------------------
if (request.getParameter("regNo") != null)
 {
 User userObj = new User();
 String stsMsg = userObj.insertUser(request.getParameter("regNo"),
 request.getParameter("name"),
 request.getParameter("address"),
 request.getParameter("email"),
 request.getParameter("phone"),
 request.getParameter("userName"),
 request.getParameter("password"));
 session.setAttribute("statusMsg", stsMsg);
 }

//Delete User----------------------------------
if (request.getParameter("userID") != null)
{
User userObj = new User();
String stsMsg = userObj.deleteUser(request.getParameter("userID"));
session.setAttribute("statusMsg", stsMsg);
}   

%>

   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
</head>

<body>

<h1>User Management</h1>
<h3><u>User Registration</u></h3>

<form method="post" action="users.jsp">
 Reg No:    <input name="regNo" type="text"><br><br> 
 Name: <input name="name" type="text"><br><br> 
 Address:   <input name="address" type="text"><br><br> 
 Email:     <input name="email" type="text"><br><br> 
 Phone:     <input name="phone" type="text"><br><br> 
 User Name:     <input name="userName" type="text"><br><br> 
 Password:     <input name="password" type="text"><br><br> 
 <input name="btnSubmit" type="submit" value="Submit"><br><br>
</form>

<%
 out.print(session.getAttribute("statusMsg"));
%>

<br>

<%
 User userObj = new User();
 out.print(userObj.readUser());
%>

</body>
</html>