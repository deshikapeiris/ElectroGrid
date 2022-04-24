<%@page import = "model.billManagement.Unit"%>
<%@page import = "com.billManagement.UnitService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%
	
	//Insert item----------------------------------
	if (request.getParameter("UnitAmount") != null)
	 {
		Unit billObj = new Unit();
		 String stsMsg = billObj.insertUnitDetails(request.getParameter("UnitAmount"),
		 request.getParameter("UnitPrice"));
		 session.setAttribute("statusMsg", stsMsg);
	 }

	//Delete item----------------------------------
	if (request.getParameter("idUnit") != null)
	{
		 Unit billObj = new Unit();
		String stsMsg =  billObj.deleteUnitDetails(request.getParameter("idUnit"));
		session.setAttribute("statusMsg", stsMsg);
	} 

%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Electrogrid</title>
		<link rel="stylesheet" href="Views/bootstrap.min.css">

	</head>
		<body>
			<h1>Unit Management</h1>
				<form method="post" action="bill.jsp">
					 Unit Count: <input name="UnitAmount" type="text"><br> 
					 Unit Price:<input name="UnitPrice" type="text"><br> 
					 <input name="btnSubmit" type="submit" value="Save">
				</form>
					<%
					 out.print(session.getAttribute("statusMsg"));
					%>
				<br>
					<%
					Unit billObj = new  Unit();
					 out.print(billObj.readUnitDetails());
					%>
		</body>
</html>
