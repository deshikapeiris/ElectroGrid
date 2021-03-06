package com.userManagement;

import model.userManagement.User; 

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 


@Path("/Users") 
public class UserService {
	 User userObj = new User(); 
	 @GET
	 @Path("/") 
	 @Produces(MediaType.TEXT_HTML) 
	 public String readItems() 
	  { 
	 	return userObj.readUser(); 
	  } 
	
	 
//search user details
@GET
@Path("{regNo}")
@Produces(MediaType.TEXT_HTML)
public String getuserDetails(@PathParam("regNo") String regNo)
	{
		return userObj.getUser(regNo);
	}



// insert user details
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser
	(@FormParam("userID") String userID,
	@FormParam("regNo") String regNo,
	@FormParam("name") String name,
	@FormParam("address") String address,
	@FormParam("email") String email,
	@FormParam("phone") String phone,
	@FormParam("userName") String userName,
	@FormParam("password") String password) 
			{
				 String output = userObj.insertUser(regNo,name,address,email,phone,userName, password);
				 return output;
			}
					
					
					
//update user details
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData)
	{
	   //Convert the input string to a JSON object
		JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
						 
	    //Read the values from the JSON object
						
			String userID = userObject.get("userID").getAsString();
			String regNo = userObject.get("regNo").getAsString();
			String name = userObject.get("name").getAsString();
		    String address = userObject.get("address").getAsString();
		    String email = userObject.get("email").getAsString();
			String phone = userObject.get("phone").getAsString();
		    String userName = userObject.get("userName").getAsString();
			String password = userObject.get("password").getAsString();
			String output = userObj.updateUser(userID,regNo,name,address,email,phone,userName,password);
			return output;
	}
					
	
	
//delete user details
@DELETE
@Path("/delete")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteUser(String userData)
	{
	 //Convert the input string to an XML document
	 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());
				
	//Read the value from the element <idUnit>
	 String userID = doc.select("userID").text();
	 String output = userObj.deleteUser(userID);
	 return output;
	 
	}			
	
									
}
