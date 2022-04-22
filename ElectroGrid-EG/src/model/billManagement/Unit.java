package model.billManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Unit {

	//method to connect to the DB
		private Connection connect()
		 {
			 Connection con = null;
				 try
				 {
					 Class.forName("com.mysql.jdbc.Driver");
				
					 //Provide the correct details: DBServer/DBName, username, password
					 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/billManagement", "root", "User1994");
				 }
				 catch (Exception e)
				 {	
					 e.printStackTrace();
				 }
				 return con;
		  }

	//method to insert unit details into db
		public String insertUnitDetails(String unit, String amount)
		{
			 String output = "";
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {
					 	return "Error while connecting to the database for inserting."; }
						 
				 		// create a prepared statement
						 String query = " insert into unitchargers (`idUnit`,`UnitAmount`,`UnitPrice`)"+ " values (?, ?, ?)";
						 PreparedStatement preparedStmt = con.prepareStatement(query);
						 
						 // binding values
						 preparedStmt.setInt(1, 0);
						 preparedStmt.setString(2, unit);
						 preparedStmt.setDouble(3, Double.parseDouble(amount));
						
						
						 
						// execute the statement
			 
						 preparedStmt.execute();
						 con.close();
						 output = "Inserted Unit Details successfully";
				}
			    catch (Exception e)
				{
					output = "Error while inserting the unit details.";
					System.err.println(e.getMessage());
				}
			 
				 return output;
		}

	//method to read unit details in db
		public String readUnitDetails()
		{
			 String output = "";
			 try
			 {
				 Connection con = connect();
				 if (con == null)
			 {
					 return "Error while connecting to the database for reading."; }
					
				 	// Prepare the html table to be displayed
					 output = "<table border='1'><tr><th>Unit ID</th><th>Unit</th><th>Chargers</th>" +
					 "<th>Action</th></tr>";
		
					 String query = "select * from unitchargers";
					 Statement stmt = con.createStatement();
					 ResultSet rs = stmt.executeQuery(query);
					 
					 
					 // iterate through the rows in the result set
					 while (rs.next())
					 {
					 String idUnit = Integer.toString(rs.getInt("idUnit"));
					 String UnitAmount = rs.getString("UnitAmount");
					 String UnitPrice = Double.toString(rs.getDouble("UnitPrice"));
			
					 
					 // Add into the html table
					 output += "<tr><td>" + idUnit + "</td>";
					 output += "<td>" + UnitAmount + "</td>";
					 output += "<td>" + UnitPrice + "</td>";
					
					 
					 // buttons
					 output += "<td><form method='post' action='units.jsp'>"+ "<br/>"+"<input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'> " + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'> "
					 + "<input name='idUnit' type='hidden' value='+ idUnit'>" + "</form></td>"+"</tr>";
					 }
				 con.close();
					 
				 // Complete the html table
				 output += "</table>";
				}
			 catch (Exception e)
			 {
				 output = "Error while reading the Unit Details.";
				 System.err.println(e.getMessage());
			 }
			 return output;
		} 

	//method to update unit details in db
		public String updateUnitDetails(String idUnit, String unit, String amount)
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
					 String query = "UPDATE unitchargers SET UnitAmount=?,UnitPrice=? WHERE idUnit=?";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
					 // binding values
					 preparedStmt.setString(1, unit);
					 preparedStmt.setDouble(2, Double.parseDouble(amount));
					 preparedStmt.setInt(3, Integer.parseInt(idUnit));
					 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 output = "Updated unit details successfully";
				}
				catch (Exception e)
				{
					 output = "Error while updating the Unit Details.";
					 System.err.println(e.getMessage());
				 }
				 return output;
			}

    //method to delete unit details in db
		public String deleteUnitDetails(String idUnit)
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
				 String query = "delete from unitchargers where idUnit=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(idUnit));
				 
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 output = "Deleted successfully";
	    	}
			catch (Exception e)
		    {
			 output = "Error while deleting the unit details.";
			 System.err.println(e.getMessage());
		    }
			 return output;
		}

		//method to search unit details in db
				public String getUnitDetails(String idUnit)
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
							 output = "<table border='1'><tr><th>Unit ID</th><th>Unit</th><th>Chargers</th>" +
							 "<th>Action</th></tr>";
				
							 String query = "select * from unitchargers where idUnit='"+idUnit+"'";
							 Statement stmt = con.createStatement();
							 ResultSet rs = stmt.executeQuery(query);
							 
							 
							 // iterate through the rows in the result set
							 while (rs.next())
							 {
								 String UnitAmount = rs.getString("UnitAmount");
								 String UnitPrice = Double.toString(rs.getDouble("UnitPrice"));
						
								 
								 // Add into the html table
								 output += "<tr><td>" + idUnit + "</td>";
								 output += "<td>" + UnitAmount + "</td>";
								 output += "<td>" + UnitPrice + "</td>";
								
								 
								 // buttons
								 output += "<td><form method='post' action='units.jsp'>"
								 + "<input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'> " 
								 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'> "
								 + "<input name='idUnit' type='hidden' value='+ idUnit'>" 
								 + "</form></td>"+"</tr>";
							 }
						 con.close();
							 
						 // Complete the html table
						 output += "</table>";
					}
					catch (Exception e)
					 {
						 output = "Error while reading the Unit Details.";
						 System.err.println(e.getMessage());
					 }
					 return output;
				} 

}