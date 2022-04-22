package model.empManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Employee {
	//method to connect to the DB
			private Connection connect()
			 {
				 Connection con = null;
					 try
					 {
						 Class.forName("com.mysql.jdbc.Driver");
					
						 //Provide the correct details: DBServer/DBName, username, password
						 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employeemanagement", "root", "User1994");
					 }
					 catch (Exception e)
					 {	
						 e.printStackTrace();
					 }
					 return con;
			  }

		//method to insert employee details into db
			public String insertEmpDetails(String empName,String empNIC,String empBdate,String empDep,String empAddress,String empPhone)
			{
				 String output = "";
				 try
				 {
					 Connection con = connect();
					 if (con == null)
					 {
						 	return "Error while connecting to the database for inserting."; }
							 
					 		// create a prepared statement
							 String query = " insert into employee ( `empID`,`empName`,`empNIC`,`empBdate`,`empDep`,`empAddress`,`empPhone`)"+ " values (?, ?, ?, ?, ?, ?, ?)";
							 PreparedStatement preparedStmt = con.prepareStatement(query);
							 
							 // binding values
							 preparedStmt.setInt(1, 0);
							 preparedStmt.setString(2, empName);
							 preparedStmt.setString(3, empNIC);
							 preparedStmt.setString(4, empBdate);
							 preparedStmt.setString(5, empDep);
							 preparedStmt.setString(6, empAddress);
							 preparedStmt.setString(7, empPhone);
							 
							// execute the statement
				 
							 preparedStmt.execute();
							 con.close();
							 output = "Inserted Employee Details successfully";
					}
				    catch (Exception e)
					{
						output = "Error while inserting the employee details.";
						System.err.println(e.getMessage());
					}
				 
					 return output;
			}

		//method to read employee details in db
			public String readEmpDetails()
			{
				 String output = "";
				 try
				 {
					 Connection con = connect();
					 if (con == null)
				 {
						 return "Error while connecting to the database for reading."; }
						
					 	// Prepare the html table to be displayed
						 output = "<table border='1'><tr><th>Employee ID</th><th>Employee Name</th><th>NIC</th><th>Birth Date</th><th>Department</th><th>Address</th><th>Phone Number</th>" +
						 "<th>Action</th></tr>";
			
						 String query = "select * from employee";
						 Statement stmt = con.createStatement();
						 ResultSet rs = stmt.executeQuery(query);
						 
						  
						 // iterate through the rows in the result set
						 while (rs.next())
						 {
						 String empID = Integer.toString(rs.getInt("empID"));
						 String empName = rs.getString("empName");
						 String empNIC = rs.getString("empNIC");
						 String empBdate = rs.getString("empBdate");
						 String empDep = rs.getString("empDep");
						 String empAddress = rs.getString("empAddress");
						 String empPhone = rs.getString("empPhone");
						 
						 // Add into the html table
						 output += "<tr><td>" + empID + "</td>";
						 output += "<td>" + empName + "</td>";
						 output += "<td>" + empNIC + "</td>";
						 output += "<td>" + empBdate + "</td>";
						 output += "<td>" + empDep + "</td>";
						 output += "<td>" + empAddress + "</td>";
						 output += "<td>" + empPhone + "</td>";
						 
						 
						 // buttons
						 output += "<td><form method='post' action='units.jsp'>"+ "<br/>"+"<input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'> " + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'> "
						 + "<input name='empID' type='hidden' value='+ empID'>" + "</form></td>"+"</tr>";
						 }
					 con.close();
						 
					 // Complete the html table
					 output += "</table>";
					}
				 catch (Exception e)
				 {
					 output = "Error while reading the Employee Details.";
					 System.err.println(e.getMessage());
				 }
				 return output;
			} 

			//method to update emp details in db
			public String updateEmpDetails(String empID,String empName,String empNIC,String empBdate,String empDep,String empAddress,String empPhone)
			{
				 String output = "";
					 try
					 {
						 Connection con = connect();
						 if (con == null)
						 {
							 return "Error while connecting to the database for updating."; 
						 }
						 
						 // create a prepared statement
						 String query = "UPDATE employee SET empName=?,empNIC=?,empBdate=?,empDep=?,empAddress=?,empPhone=? WHERE empID=?";
						 PreparedStatement preparedStmt = con.prepareStatement(query);
						 
						 // binding values
						 preparedStmt.setString(1, empName);
						 preparedStmt.setString(2, empNIC);
						 preparedStmt.setString(3, empBdate);
						 preparedStmt.setString(4, empDep);
						 preparedStmt.setString(5, empAddress);
						 preparedStmt.setString(6, empPhone);
						 preparedStmt.setInt(7, Integer.parseInt(empID));
						 
						 // execute the statement
						 preparedStmt.execute();
						 con.close();
						 output = "Updated employee details successfully";
					}
					catch (Exception e)
					{
						 output = "Error while updating the employee Details.";
						 System.err.println(e.getMessage());
					 }
					 return output;
				}
	    //method to delete employee details in db
			public String deleteEmpDetails(String empID)
			{
				 String output = "";
				 try
				 {
					 Connection con = connect();
					 if (con == null)
					 {
						 return "Error while connecting to the database for deleting.";
					 }
					 
					 // create a prepared statement
					 String query = "delete from employee where empID=?";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(empID));
					 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 output = "Profile Deleted successfully";
		    	}
				catch (Exception e)
			    {
				 output = "Error while deleting the employee details.";
				 System.err.println(e.getMessage());
			    }
				 return output;
			}

			//method to search employee details in db
					public String getEmpDetails(String empID)
					{
						 String output = "";
						 try
						 {
							 Connection con = connect();
							 if (con == null)
						     {
								 return "Error while connecting to the database for reading."; 
						     }
								 
							 	// Prepare the html table to be displayed
								 output = "<table border='1'><tr><th>Employee ID</th><th>Employee Name</th><th>NIC</th><th>Birth Date</th><th>Department</th><th>Address</th><th>Phone Number</th>" +
								 "<th>Action</th></tr>";
					
								 String query = "select * from employee where empID='"+empID+"'";
								 Statement stmt = con.createStatement();
								 ResultSet rs = stmt.executeQuery(query);
								 
								
								 // iterate through the rows in the result set
								 while (rs.next())
								 {
									 String empName = rs.getString("empName");
									 String empNIC = rs.getString("empNIC");
									 String empBdate = rs.getString("empBdate");
									 String empDep = rs.getString("empDep");
									 String empAddress = rs.getString("empAddress");
									 String empPhone = rs.getString("empPhone");
									 
									 // Add into the html table
									 output += "<tr><td>" + empID + "</td>";
									 output += "<td>" + empName + "</td>";
									 output += "<td>" + empNIC + "</td>";
									 output += "<td>" + empBdate + "</td>";
									 output += "<td>" + empDep + "</td>";
									 output += "<td>" + empAddress + "</td>";
									 output += "<td>" + empPhone + "</td>";
									
									 
									 // buttons
									 output += "<td><form method='post' action='units.jsp'>"
									 + "<input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'> " 
									 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'> "
									 + "<input name='empID' type='hidden' value='+ empID'>" 
									 + "</form></td>"+"</tr>";
								 }
							 con.close();
								 
							 // Complete the html table
							 output += "</table>";
						}
						catch (Exception e)
						 {
							 output = "Error while reading the Employee Details.";
							 System.err.println(e.getMessage());
						 }
						 return output;
					} 


}
