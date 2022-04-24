package model.billManagement;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill {
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

		//method to insert bill details into db
			public String insertBillDetails(String accNo, String name,String unit, String total, String date)
			{
				 String output = "";
				 try
				 {
					 Connection con = connect();
					 if (con == null)
					 {
						 	return "Error while connecting to the database for inserting."; }
							 
					 		// create a prepared statement
							 String query = " insert into bill (`billNo`,`CustomerAccNo`,`CustomerName`,`Unit`,`Total`,`Date`)"+ " values (?, ?, ?, ?, ?, ?)";
							 PreparedStatement preparedStmt = con.prepareStatement(query);
							 
							 
							 // binding values
							 preparedStmt.setInt(1, 0);
							 preparedStmt.setString(2, accNo);
							 preparedStmt.setString(3, name);
							 preparedStmt.setString(4, unit);
							 preparedStmt.setDouble(5, Double.parseDouble(total));
							 preparedStmt.setString(6, date);
							 
							// execute the statement
				 
							 preparedStmt.execute();
							 con.close();
							 output = "Inserted Bill Details successfully";
					}
				    catch (Exception e)
					{
						output = "Error while inserting the bill details.";
						System.err.println(e.getMessage());
					}
				 
					 return output;
			}

		//method to read bill details in db
			public String readAllBills()
			{
				 String output = "";
				 try
				 {
					 Connection con = connect();
					 if (con == null)
				 {
						 return "Error while connecting to the database for reading."; }
						
					 	// Prepare the html table to be displayed
						 output = "<table border='1'><tr><th>Bill No</th><th>Customer Account No</th><th>Customer Name</th><th>Unit</th><th>Total</th><th>Date</th>" +
						 "<th>Action</th></tr>";
			
						 String query = "select * from bill";
						 Statement stmt = con.createStatement();
						 ResultSet rs = stmt.executeQuery(query);
						 
						 
						 // iterate through the rows in the result set
						 while (rs.next())
						 {
						 String billNo = Integer.toString(rs.getInt("billNo"));
						 String CustomerAccNo = rs.getString("CustomerAccNo");
						 String CustomerName = rs.getString("CustomerName");
						 String Unit = rs.getString("Unit");
						 String Total = Double.toString(rs.getDouble("Total"));
						 String Date = rs.getString("Date");
				
						 
						 // Add into the html table
						 output += "<tr><td>" + billNo + "</td>";
						 output += "<td>" + CustomerAccNo + "</td>";
						 output += "<td>" + CustomerName + "</td>";
						 output += "<td>" + Unit + "</td>";
						 output += "<td>" + Total + "</td>";
						 output += "<td>" + Date + "</td>";
						 
						 // buttons
						 output += "<td><form method='post' action='units.jsp'>"+ "<br/>"+"<input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'> " + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'> "
						 + "<input name='billNo' type='hidden' value='+ billNo'>" + "</form></td>"+"</tr>";
						 }
					 con.close();
						 
					 // Complete the html table
					 output += "</table>";
					}
				 catch (Exception e)
				 {
					 output = "Error while reading the Bill Details.";
					 System.err.println(e.getMessage());
				 }
				 return output;
			} 

		//method to update bill details in db
			public String updateBillDetails(String billNo,String accNo, String name,String unit, String total, String date)
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
						 String query = "UPDATE bill SET CustomerAccNo=?,CustomerName=?,Unit=?,Total=?,Date=? WHERE billNo=?";
						 PreparedStatement preparedStmt = con.prepareStatement(query);
						 
						 // binding values
						 preparedStmt.setString(1, accNo);
						 preparedStmt.setString(2, name);
						 preparedStmt.setString(3, unit);
						 preparedStmt.setDouble(4, Double.parseDouble(total));
						 preparedStmt.setString(5, date);
						 preparedStmt.setInt(6, Integer.parseInt(billNo));
						 
						 // execute the statement
						 preparedStmt.execute();
						 con.close();
						 output = "Updated bill details successfully";
					}
					catch (Exception e)
					{
						 output = "Error while updating the Bill Details.";
						 System.err.println(e.getMessage());
					 }
					 return output;
				}

	    //method to delete bill details in db
			public String deleteBill(String billNo)
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
					 String query = "delete from bill where billNo=?";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(billNo));
					 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 output = "Deleted successfully";
		    	}
				catch (Exception e)
			    {
				 output = "Error while deleting the bill.";
				 System.err.println(e.getMessage());
			    }
				 return output;
			}

			//method to search bill details in db
					public String getBillDetails(String billNo)
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
								 output = "<table border='1'><tr><th>Bill No</th><th>Customer Account No</th><th>Customer Name</th><th>Unit</th><th>Total</th><th>Date</th>" +
								 "<th>Action</th></tr>";
					
								 String query = "select * from bill where billNo='"+billNo+"'";
								 Statement stmt = con.createStatement();
								 ResultSet rs = stmt.executeQuery(query);
								 
								 
								 // iterate through the rows in the result set
								 while (rs.next())
								 {
									 String CustomerAccNo = rs.getString("CustomerAccNo");
									 String CustomerName = rs.getString("CustomerName");
									 String Unit = rs.getString("Unit");
									 String Total = Double.toString(rs.getDouble("Total"));
							         String Date = rs.getString("Date");
									 
									 // Add into the html table
									 output += "<tr><td>" + billNo + "</td>";
									 output += "<td>" + CustomerAccNo + "</td>";
									 output += "<td>" + CustomerName + "</td>";
									 output += "<td>" + Unit + "</td>";
									 output += "<td>" + Total + "</td>";
									 output += "<td>" + Date + "</td>";
									 
									 // buttons
									 output += "<td><form method='post' action='units.jsp'>"
									 + "<input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'> " 
									 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'> "
									 + "<input name='billNo' type='hidden' value='+ billNo'>" 
									 + "</form></td>"+"</tr>";
								 }
							 con.close();
								 
							 // Complete the html table
							 output += "</table>";
						}
						catch (Exception e)
						 {
							 output = "Error while reading the Bill Details.";
							 System.err.println(e.getMessage());
						 }
						 return output;
					} 
}
