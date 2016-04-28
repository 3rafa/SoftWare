package com.application;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@Path("/")
public class checkinControler {
	


	
		@Context
		HttpServletRequest request;
		

		@GET
		@Path("/addNewcheckin")
		@Produces(MediaType.TEXT_HTML)
		public Response showPage() {
			return Response.ok(new Viewable("/successCheckIn.jsp")).build();
		}
		

		
		
		
		@POST
		@Path("/addNewcheckin")
		@Produces(MediaType.TEXT_HTML)
		public Response addNewcheckin(@FormParam("userId") int userId,
				@FormParam("placeID") int placeID,@FormParam("description") String description) {
			//String serviceUrl = "http://se2firstapp-softwareeng2.rhcloud.com/FCISquare/rest/login";

			String serviceUrl = "http://localhost:8080/FCISquare/rest/addNewcheckin";

			String urlParameters = "userId=" + userId + "&placeID=" + placeID + "&description="+ description;

			String retJson = Connection.connect(serviceUrl, urlParameters, "POST","application/x-www-form-urlencoded;charset=UTF-8");
			HttpSession session = request.getSession();
			JSONObject obj = new JSONObject();
			JSONParser parser = new JSONParser();
			
			try {
				obj = (JSONObject) parser.parse(retJson);
				session.setAttribute("userID", obj.get("userId"));
				session.setAttribute("placeID", obj.get("placeID"));
				session.setAttribute("checkDescription", obj.get("description"));
				//session.setAttribute("checkInPlaceName",request.getParameter("placeName"));
				return Response.ok(new Viewable("/successCheckIn.jsp")).build();

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

		}
		
		

}
