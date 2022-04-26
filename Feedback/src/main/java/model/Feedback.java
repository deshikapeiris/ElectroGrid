package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Feedback {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/feedbackdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertfeedbackDetails(String FeedbackID, String UserID, String UserName, String Subject, String Date, String Comment) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into feedback(`FeedbackID`,`UserID`,`UserName`,`Subject`,`Date`,`Comment`)"
					+ " values (?, ?, ?, ?,?,? )";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, FeedbackID);
			preparedStmt.setString(2, UserID);
			preparedStmt.setString(3, UserName);
			preparedStmt.setString(4, Subject);
			preparedStmt.setString(5, Date);
			preparedStmt.setString(6, Comment);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Feedback Details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readfeedbackDetails() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Feedback ID</th><th>User ID</th><th>User Name</th><th>Subject</th><th>Date</th><th>Comment</th></tr>";
			String query = "select * from feedback";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				
				String FeedbackID = rs.getString("FeedbackID");
				String UserID = rs.getString("UserID");
				String UserName = rs.getString("UserName");
				String Subject = rs.getString("Subject");
				String Date = rs.getString("Date");
				String Comment = rs.getString("Comment");
				

				// Add into the html table
				output += "<tr><td>" + FeedbackID + "</td>";
				output += "<td>" + UserID + "</td>";
				output += "<td>" + UserName + "</td>";
				output += "<td>" + Subject + "</td>";
				output += "<td>" + Date + "</td>";
				output += "<td>" + Comment + "</td>";
				
				
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Feedback Details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatefeedbackDetails(String FeedbackID, String UserID, String UserName, String Subject, String Date, String Comment) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE feedback SET UserID=?,UserName=?,Subject=?,Date=?,Comment=?" + "WHERE FeedbackID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// feedback values
			
			
			preparedStmt.setString(1, UserID);
			preparedStmt.setString(2, UserName);
			preparedStmt.setString(3, Subject);
			preparedStmt.setString(4, Date);
			preparedStmt.setString(5, Comment);
			preparedStmt.setString(6, FeedbackID);
			
			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Feedback Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deletefeedbackDetails(String FeedbackID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from feedback where FeedbackID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// feedback values
			preparedStmt.setString(1, (FeedbackID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the feedback Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
