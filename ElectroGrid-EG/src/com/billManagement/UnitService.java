package com.billManagement;

//For import model
import model.billManagement.Unit;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Units")
public class UnitService {
	
		//read unit details
		Unit billObj = new Unit(); 
		
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readUnitDetails()
		{
			return billObj.readUnitDetails();
		}
		
		//search unit details
		@GET
		@Path("{idUnit}")
		@Produces(MediaType.TEXT_HTML)
		public String getUnitDetails(@PathParam("idUnit") String idUnit)
		{
			return billObj.getUnitDetails(idUnit);
		}
		
		// insert unit details() 
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertUnitDetails(@FormParam("UnitAmount") String UnitAmount,
		 @FormParam("UnitPrice") String UnitPrice)
		{
			 String output = billObj.insertUnitDetails(UnitAmount,UnitPrice);
			 return output;
		}

		
		//update unit details
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateUnitDetails(String unitData)
		{
			//Convert the input string to a JSON object
			 JsonObject billObject = new JsonParser().parse(unitData).getAsJsonObject();
			 
			//Read the values from the JSON object
			 String idUnit = billObject.get("idUnit").getAsString();
			 String UnitAmount = billObject.get("UnitAmount").getAsString();
			 String UnitPrice = billObject.get("UnitPrice").getAsString();
			 String output = billObj.updateUnitDetails(idUnit, UnitAmount, UnitPrice);
			 return output;
		}
		
		
		//delete unit details
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteUnitDetails(String unitData)
		{
			//Convert the input string to an XML document
			 Document doc = Jsoup.parse(unitData, "", Parser.xmlParser());
	
			//Read the value from the element <idUnit>
			 String idUnit = doc.select("idUnit").text();
			 String output = billObj.deleteUnitDetails(idUnit);
			 return output;
		}
}
