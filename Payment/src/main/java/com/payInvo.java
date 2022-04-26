package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
import model.payment;

@Path("/paymentInvo")
public class payInvo {
	payment payDetailsObj = new payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String payDetails() {
		return payDetailsObj.readPayDetails();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayDetails(@FormParam("payID") String payID,			
	 @FormParam("userID") String userID,
	 @FormParam("userName") String userName,
	  @FormParam("method") String method,
	 @FormParam("date") String date,
	 @FormParam("amount") String amount
	 )
	{
	 String output = payDetailsObj.insertPayDetails(payID, userID,userName, method, date, amount);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayDetails(String payData)
	{
	//Convert the input string to a JSON object
	 JsonObject payObject = new JsonParser().parse(payData).getAsJsonObject();
	//Read the values from the JSON object
	 String payID = payObject.get("payID").getAsString();
	 String userID = payObject.get("userID").getAsString();
	 String userName = payObject.get("userName").getAsString();

	 String method = payObject.get("method").getAsString();
	 
	 String date = payObject.get("date").getAsString();
	 String amount = payObject.get("amount").getAsString();
	
	 String output = payDetailsObj.updatePayDetails(payID, userID, userName, method, date, amount);
	return output;
	} 
	
	@DELETE 
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayDetails(String payData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(payData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String payID = doc.select("payID").text();
	 String output = payDetailsObj.deletePayDetails(payID);
	return output;
	}
}
