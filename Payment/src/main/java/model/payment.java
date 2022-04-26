package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class payment {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/pay?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPayDetails(String payID, String userID, String userName, String method, String date, String amount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into payment(`payID`,`userID`,`userName`,`method`,`date`,`amount`)"
					+ " values (?, ?, ?, ? , ?,? )";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, payID);
			preparedStmt.setString(2, userID);
			preparedStmt.setString(3, userName);
			preparedStmt.setString(4, method);
			preparedStmt.setString(5, date);
			preparedStmt.setString(6, amount);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Payment Details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPayDetails() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Pay ID</th><th>Customer ID</th><th>Customer Name</th><th>Payment method</th><th>Date</th><th>Total Amount</th></tr>";
			String query = "select * from payment";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				
				String payID = rs.getString("payID");
				String userID = rs.getString("userID");
				String userName = rs.getString("userName");
				String method = rs.getString("method");
				String date = rs.getString("date");
				String amount = rs.getString("amount");
				

				// Add into the html table
				output += "<tr><td>" + payID + "</td>";
				output += "<td>" + userID + "</td>";
				output += "<td>" + userName + "</td>";
				output += "<td>" + method + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + amount + "</td>";
				
				
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Payment Details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePayDetails(String payID, String userID, String userName, String method, String date, String amount) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE payment SET userID=?,userName=?,method=?,date=?,amount=?" + "WHERE payID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			
			preparedStmt.setString(1, userID);
			preparedStmt.setString(2, userName);
			preparedStmt.setString(3, method);
			preparedStmt.setString(4, date);
			preparedStmt.setString(5, amount);
			preparedStmt.setString(6, payID);
			
			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Payment Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deletePayDetails(String payID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from payment where payID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, (payID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the payment Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
