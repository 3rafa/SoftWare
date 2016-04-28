package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.CheckinModel;
import com.models.DBConnection;
import com.models.PlaceModel;
import com.models.UserModel;
import com.models.commentsModel;
import com.models.likesModel;

@Path("/")
public class Services {

	/*
	 * @GET
	 * 
	 * @Path("/signup")
	 * 
	 * @Produces(MediaType.TEXT_HTML) public Response signUp(){ return
	 * Response.ok(new Viewable("/Signup.jsp")).build(); }
	 */

	@POST
	@Path("/signup")
	@Produces(MediaType.TEXT_PLAIN)
	public String signUp(@FormParam("name") String name,
			@FormParam("email") String email, @FormParam("pass") String pass) {
		UserModel user = UserModel.addNewUser(name, email, pass);
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		json.put("email", user.getEmail());
		json.put("pass", user.getPass());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
	}

	
	
	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("email") String email,
			@FormParam("pass") String pass) {
		UserModel user = UserModel.login(email, pass);
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		json.put("email", user.getEmail());
		json.put("pass", user.getPass());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
	}
	
	@POST
	@Path("/updatePosition")
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePosition(@FormParam("id") String id,
			@FormParam("lat") String lat, @FormParam("long") String lon) {
		Boolean status = UserModel.updateUserPosition(Integer.parseInt(id), Double.parseDouble(lat), Double.parseDouble(lon));
		JSONObject json = new JSONObject();
		json.put("status", status ? 1 : 0);
		return json.toJSONString();
	}

	
	
	@POST
	@Path("/follow")
	@Produces(MediaType.TEXT_PLAIN)
	public String follow(@FormParam("userID") int userID,@FormParam("followerID") int followerID) {
		
		int user = UserModel.follow(userID, followerID);
		JSONObject json = new JSONObject();
		json.put("userID",user );
	
		return json.toJSONString();
	
		// Connection URL:
		// mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
	}	
	
	
	@POST
	@Path("/unfollow")
	@Produces(MediaType.TEXT_PLAIN)
	public String unfollow(@FormParam("userID") int userID,@FormParam("followerID") int followerID) {
		
		int user = UserModel.unfollow(userID, followerID);
		JSONObject json = new JSONObject();
		json.put("userID",user );
	
		return json.toJSONString();
	
		// Connection URL:
		// mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
	}	
	
	@POST
	@Path("/getCurrentLocation")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCurrentLocation(@FormParam("userID") int userID) {
		
		UserModel user = UserModel.getCurrentLocation(userID);
		JSONObject json = new JSONObject();
		json.put("name", user.getName());
		json.put("email", user.getEmail());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
		// Connection URL:
		// mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
	}	
	
	
	
	
	
	
	
	
//	@POST
//	@Path("/followerList")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String followerList(@FormParam("userID") int userID) {
//		List<UserModel> user = new ArrayList<UserModel>();
//		user = UserModel.followerList(userID);
//		
//		 JSONObject jResult = new JSONObject();// main object
//	        JSONArray jArray = new JSONArray();// /ItemDetail jsonArray
//
//	        for (int i = 0; i < user.size(); i++) {
//	            JSONObject jGroup = new JSONObject();// /sub Object
//
//	            try {
//	                jGroup.put("name", user.get(i).getName());
//	                jGroup.put("email", user.get(i).getEmail());
//
//	                 jArray.add(jGroup); 
//	                // /itemDetail Name is JsonArray Name
//	                //jResult.put("itemDetail", jArray);
//		
//	            }
//                  
//	        catch (Exception e) {
//				// TODO: handle exception
//	        }
//	            }
//            return jArray.toJSONString();
//
//	      
//	
//	}	
	
	
	
	
	
	
	
	
	
	
	@POST
	@Path("/getfollowers")
	@Produces(MediaType.TEXT_PLAIN)

	public String getfollowers(@FormParam("userID") int userID) {

		List<UserModel> retUsers = new ArrayList<UserModel>();
		retUsers = UserModel.followerList(userID);
		org.json.simple.JSONArray retArry = new org.json.simple.JSONArray();
		int i=1 ;
		String  str ="";
		for (UserModel user : retUsers) {
			JSONObject object = new JSONObject();
			
  			object.put("name", user.getName());
			object.put("email", user.getEmail());
			str+="Foloower#"+(i++)+":- {name="+user.getName()+"   ,  "+"email="+user.getEmail()+"}<br>";
    	retArry.add(object);
		}
 		return str;
	}
	
	
	@POST
	@Path("/addNewPlace")
	@Produces(MediaType.TEXT_PLAIN)
	public String addNewPlace(@FormParam("name") String name,
			@FormParam("description") String description, @FormParam("long") double lon, @FormParam("lat") double lat) {
		PlaceModel place = PlaceModel.addNewPlace(name, description, lon, lat);
		JSONObject json = new JSONObject();
		json.put("id", place.getId());
		json.put("name", place.getName());
		json.put("description", place.getDescription());
		json.put("lat", place.getLat());
		json.put("long", place.getLon());
		return json.toJSONString();
	}

	
	@POST
	@Path("/addNewcheckin")
	@Produces(MediaType.TEXT_PLAIN)
	public String addNewcheckIN(@FormParam("userId") int userId,
			 @FormParam("placeID") int placeID,@FormParam("description") String description) {
		CheckinModel check = CheckinModel.addcheckin(userId, placeID, description);
		JSONObject json = new JSONObject();
		json.put("userId", check.getUserId());
		json.put("placeID", check.getPlaceId());
		json.put("description", check.getDescription());
		return json.toJSONString();
	}

	
	
	
	@POST
	@Path("/addComment")
	@Produces(MediaType.TEXT_PLAIN)
	public String addComment(@FormParam("userId") int userId,
			 @FormParam("checkinID") int checkinID,@FormParam("commentBody") String commentBody) {
		commentsModel comm = commentsModel.addComment(userId, checkinID, commentBody);
		JSONObject json = new JSONObject();
		json.put("userID", comm.getUserIDComment());
		json.put("checkinID", comm.getCheckinID());
		json.put("commentBody", comm.getCommentBody());
		return json.toJSONString();
	}
 
	
	
	@POST
	@Path("/addlike")
	@Produces(MediaType.TEXT_PLAIN)
	public String addlike(@FormParam("userId") int userId,
			 @FormParam("checkinID") int checkinID) {
		likesModel like = likesModel.addlike(userId, checkinID);
		JSONObject json = new JSONObject();
		json.put("userID", like.getUserID());
		json.put("checkinID", like.getCheckinID());
		return json.toJSONString();
	}
 
	
	
	@POST
	@Path("/addsavePlace")
	@Produces(MediaType.TEXT_PLAIN)
	public String addsavePlace(@FormParam("UserID") int UserID,@FormParam("PlaceID") int PlaceID) {
		
		int Place = UserModel.addsavePlace(UserID, PlaceID);
		JSONObject json = new JSONObject();
		json.put("PlaceID",Place );
	
		return json.toJSONString();
	
		// Connection URL:
		// mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
	}
	
	@POST
	@Path("/searchForPlace")
	@Produces(MediaType.TEXT_PLAIN)
	public String searchForPlace(@FormParam("placeName") String  PlaceName) throws SQLException {
		PlaceModel place =PlaceModel.searchForPlace(PlaceName);
		JSONObject json = new JSONObject();
		json.put("placeID", place.getId());
		json.put("name", place.getName());
		json.put("description", place.getDescription());
		json.put("long", place.getLon());
		json.put("lat", place.getLat());
		return json.toJSONString();
	}
 
	
	
	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String getJson() {
		return "Hello after editing";
		// Connection URL:
		// mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
	}
	
	
	
}
