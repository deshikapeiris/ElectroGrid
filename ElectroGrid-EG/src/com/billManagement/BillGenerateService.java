package com.billManagement;

//For import model
import model.billManagement.Bill;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Bill")
public class BillGenerateService {
	//read unit details
			Bill billObj = new Bill(); 
			
			@GET
			@Path("/")
			@Produces(MediaType.TEXT_HTML)
			public String readAllBills()
			{
				return billObj.readAllBills();
			}
			
			//search unit details
			@GET
			@Path("{billNo}")
			@Produces(MediaType.TEXT_HTML)
			public String getBillDetails(@PathParam("billNo") String billNo)
			{
				return billObj.getBillDetails(billNo);
			}
			
			// insert unit details() 
			@POST
			@Path("/insert")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces(MediaType.TEXT_PLAIN)
			public String insertBillDetails(@FormParam("CustomerID") String CustomerID,
			 @FormParam("CustomerName") String CustomerName,
			 @FormParam("Unit") String Unit,
             @FormParam("Total") String Total,
             @FormParam("Date") String Date) 
			{
				 String output = billObj.insertBillDetails(CustomerID,CustomerName,Unit,Total,Date);
				 return output;
			}

			
			//update unit details
			@PUT
			@Path("/update")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.TEXT_PLAIN)
			public String updateBillDetails(String billData)
			{
				//Convert the input string to a JSON object
				 JsonObject billObject = new JsonParser().parse(billData).getAsJsonObject();
				 
				//Read the values from the JSON object
				 String billNo = billObject.get("billNo").getAsString();
				 String CustomerID = billObject.get("CustomerID").getAsString();
				 String CustomerName = billObject.get("CustomerName").getAsString();
				 String Unit = billObject.get("Unit").getAsString();
				 String Total = billObject.get("Total").getAsString();
				 String Date = billObject.get("Date").getAsString();
				 String output = billObj.updateBillDetails(billNo,CustomerID,CustomerName,Unit,Total,Date);
				 return output;
			}
			
			
			//delete unit details
			@DELETE
			@Path("/delete")
			@Consumes(MediaType.APPLICATION_XML)
			@Produces(MediaType.TEXT_PLAIN)
			public String deleteBill(String billData)
			{
				//Convert the input string to an XML document
				 Document doc = Jsoup.parse(billData, "", Parser.xmlParser());
		
				//Read the value from the element <idUnit>
				 String billNo = doc.select("billNo").text();
				 String output = billObj.deleteBill(billNo);
				 return output;
			}
}
