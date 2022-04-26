package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
import model.Feedback;

@Path("/feedbackDetails")
public class FeedbackManage {
	Feedback feedbackDetailsObj = new Feedback();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String feedbackDetails() {
		return feedbackDetailsObj.readfeedbackDetails();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertfeedbackDetails(@FormParam("FeedbackID") String FeedbackID,			
	 @FormParam("UserID") String UserID,
	 @FormParam("UserName") String UserName,
	 @FormParam("Subject") String Subject,
	 @FormParam("Date") String Date,
	 @FormParam("Comment") String Comment
	 )
	{
	 String output = feedbackDetailsObj.insertfeedbackDetails(FeedbackID, UserID, UserName, Subject, Date, Comment);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatefeedbackDetails(String feedbackData)
	{
	//Convert the input string to a JSON object
	 JsonObject feedbackObject = new JsonParser().parse(feedbackData).getAsJsonObject();
	//Read the values from the JSON object
	 String FeedbackID = feedbackObject.get("FeedbackID").getAsString();
	 String UserID = feedbackObject.get("UserID").getAsString();
	 String UserName = feedbackObject.get("UserName").getAsString();
	 String Subject = feedbackObject.get("Subject").getAsString();
	 String Date = feedbackObject.get("Date").getAsString();
	 String Comment = feedbackObject.get("Comment").getAsString();
	
	 String output = feedbackDetailsObj.updatefeedbackDetails(FeedbackID, UserID, UserName, Subject, Date, Comment);
	return output;
	} 
	
	@DELETE 
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletefeedbackDetails(String feedbackData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(feedbackData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String FeedbackID = doc.select("FeedbackID").text();
	 String output = feedbackDetailsObj.deletefeedbackDetails(FeedbackID);
	return output;
	}
}
