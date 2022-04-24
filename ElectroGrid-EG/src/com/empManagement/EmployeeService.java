package com.empManagement;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


//For JSON
import com.google.gson.*;

import model.empManagement.Employee;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Employee")
public class EmployeeService {
	//read unit details
			Employee empObj = new Employee(); 
			
			@GET
			@Path("/")
			@Produces(MediaType.TEXT_HTML)
			public String readEmpDetails()
			{
				return empObj.readEmpDetails();
			}
			
			//search unit details
			@GET
			@Path("search/{empID}")
			@Produces(MediaType.TEXT_HTML)
			public String getEmpDetails(@PathParam("empID") String empID)
			{
				return empObj.getEmpDetails(empID);
			}
			
			// insert unit details() 
			@POST
			@Path("/insert")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces(MediaType.TEXT_PLAIN)
			public String insertEmpDetails(
			 @FormParam("empName") String empName,
			 @FormParam("empNIC") String empNIC,
			 @FormParam("empBdate") String empBdate,
			 @FormParam("empDep") String empDep,
			 @FormParam("empAddress") String empAddress,
			 @FormParam("empPhone") String empPhone)
			{
				 String output = empObj.insertEmpDetails(empName,empNIC,empBdate,empDep,empAddress,empPhone);
				 return output;
			}

			//update unit details
			@PUT
			@Path("/update")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.TEXT_PLAIN)
			public String updateEmpDetails(String empData)
			{
				//Convert the input string to a JSON object
				 JsonObject empObject = new JsonParser().parse(empData).getAsJsonObject();
				 
				//Read the values from the JSON object
				 String empID = empObject.get("empID").getAsString();
				 String empName = empObject.get("empName").getAsString();
				 String empNIC = empObject.get("empNIC").getAsString();
				 String empBdate = empObject.get("empBdate").getAsString();
				 String empDep = empObject.get("empDep").getAsString();
				 String empAddress = empObject.get("empAddress").getAsString();
				 String empPhone = empObject.get("empPhone").getAsString();
				 String output = empObj.updateEmpDetails(empID,empName,empNIC,empBdate,empDep,empAddress,empPhone);
				 return output;
			}
			
			//delete unit details
			@DELETE
			@Path("/delete")
			@Consumes(MediaType.APPLICATION_XML)
			@Produces(MediaType.TEXT_PLAIN)
			public String deleteEmpDetails(String empData)
			{
				//Convert the input string to an XML document
				 Document doc = Jsoup.parse(empData, "", Parser.xmlParser());
		
				//Read the value from the element <idUnit>
				 String empID = doc.select("empID").text();
				 String output = empObj.deleteEmpDetails(empID);
				 return output;
			}
}
