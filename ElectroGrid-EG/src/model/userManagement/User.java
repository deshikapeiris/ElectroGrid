package model.userManagement;

import java.sql.*; 


public class User {
	
	
//A common method to connect to the DB
		private Connection connect() 
		 { 
		 Connection con = null; 
		 try
		 { 
		 Class.forName("com.mysql.jdbc.Driver"); 
		 
		 //Provide the correct details: DBServer/DBName, username, password 
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electogrid", "root", "deshika"); 
		 } 
		 catch (Exception e) 
		 {e.printStackTrace();} 
		 return con; 
		 } 
		
		
//Insert User details	
		
		public String insertUser(String regNo, String name, String address,String email, String phone, String userName, String password)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for inserting. ";
				}
			
				// create a prepared statement
				String query = " insert into user (`userID`,`regNo`,`name`,`address`,`email`,`phone`,`userName`,`password`)"
				       + " values (?, ?, ?, ?, ?,?,?,?)";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, regNo);
				preparedStmt.setString(3, name);
				preparedStmt.setString(4, address);
				preparedStmt.setString(5, email);
				preparedStmt.setString(6, phone);
				preparedStmt.setString(7, userName);
				preparedStmt.setString(8, password);
				
				
				//execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
			}
			catch (Exception e)
			{
				output = "Error while inserting the User Details";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		
//Read User Details	
		
		public String readUser() 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for reading."; } 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Reg No</th><th>Name</th>" +
		 "<th>Address</th>" +  "<th>Email</th>" + 
		 "<th>Phone</th>" + "<th>User Name</th>" +  "<th>Password</th>" + 
		 "<th>Update</th><th>Remove</th></tr>"; 
		 
		 String query = "select * from user"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String userID = Integer.toString(rs.getInt("userID")); 
		 String regNo = rs.getString("regNo"); 
		 String name = rs.getString("name"); 
		 String address = rs.getString("address"); 
		 String email = rs.getString("email"); 
		 String phone = rs.getString("phone"); 
		 String userName = rs.getString("userName"); 
		 String password = rs.getString("password"); 
		 
		 // Add into the html table
		 output += "<tr><td>" + regNo + "</td>"; 
		 output += "<td>" + name + "</td>"; 
		 output += "<td>" + address + "</td>"; 
		 output += "<td>" +email + "</td>"; 
		 output += "<td>" +phone + "</td>"; 
		 output += "<td>" +userName + "</td>"; 
		 output += "<td>" +password + "</td>"; 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update'  class='btn btn-secondary'></td>" + "<td><form method='post' action='users.jsp'>"  + "<input name='btnRemove' type='submit' value='Remove'  class='btn btn-danger'>"
		 + "<input name='userID' type='hidden' value='" + userID 
		 + "'>" + "</form></td></tr>"; 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while reading the users."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }
	
		
	
//Update User Details

		
		public String updateUser(String userID, String regNo, String name, String address,String email, String phone, String userName, String password)
		
		{

		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE user SET regNo=?,name=?,address=?,email=?,phone=?,userName=?,password=? WHERE userID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, regNo); 
		 preparedStmt.setString(2, name); 
		 preparedStmt.setString(3, address); 
		 preparedStmt.setString(4, email); 
		 preparedStmt.setString(5, phone); 
		 preparedStmt.setString(6, userName); 
		 preparedStmt.setString(7, password); 
		 preparedStmt.setInt(8, Integer.parseInt(userID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the user."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		
		
	
//Delete User Details	
		
		public String deleteUser(String userID)
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
				 String query = "delete from user where userID=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(userID));
			
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 output = "Deleted successfully";
			 }
			 catch (Exception e)
			 {
				 output = "Error while deleting the user.";
				 System.err.println(e.getMessage());
			 }
			return output;
		}

		
		
//Search User Details
		
		public String getUser(String regNo)
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
					 output = "<table border='1'><tr><th>Reg No</th><th> Name</th><th>Address</th><th>Email</th><th>Phone</th><th>Phone</th><th>User Name</th><th>Password</th>" +
					 "<th>Delete</th></tr>";
		
					 String query = "select * from user where regNo='"+regNo+"'";
					 Statement stmt = con.createStatement();
					 ResultSet rs = stmt.executeQuery(query);
					 
					 
					 // iterate through the rows in the result set
					 while (rs.next())
					 {
						 String userID = Integer.toString(rs.getInt("userID")); 
						 String rNo = rs.getString("regNo"); 
						 String name = rs.getString("name"); 
						 String address = rs.getString("address"); 
						 String email = rs.getString("email");
						 String phone = rs.getString("phone");
						 String userName = rs.getString("userName");
						 String password = rs.getString("password");
						 
						 // Add into the html table
						 output += "<tr><td>" + regNo + "</td>"; 
						 output += "<td>" + name + "</td>"; 
						 output += "<td>" + address + "</td>"; 
						 output += "<td>" +email + "</td>";
						 output += "<td>" +phone + "</td>";
						 output += "<td>" +userName + "</td>";
						 output += "<td>" +password + "</td>";
						 
						 // buttons
						 output += "<td><input name='btnUpdate' type='button' value='Update'  class='btn btn-secondary'></td>" + "<td><form method='post' action='users.jsp'>"  + "<input name='btnRemove' type='submit' value='Remove'  class='btn btn-danger'>"
								 + "<input name='userID' type='hidden' value='" + userID 
								 + "'>" + "</form></td></tr>"; 
								 } 
				 con.close();
					 
				 // Complete the html table
				 output += "</table>";
			}
			catch (Exception e)
			 {
				 output = "Error while reading the User Details.";
				 System.err.println(e.getMessage());
			 }
			 return output;
		}	
}
