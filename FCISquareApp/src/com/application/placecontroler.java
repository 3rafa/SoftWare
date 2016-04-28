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
public class placecontroler {
	@Context
	HttpServletRequest request;
	

	@GET
	@Path("/searchForPlace")
	@Produces(MediaType.TEXT_HTML)
	public Response showHomePage() {
		return Response.ok(new Viewable("/checkin.jsp")).build();
	}
	
	@POST
	@Path("/searchForPlace")
	@Produces(MediaType.TEXT_HTML)
	public Response showHomePage(@FormParam("placeName") String placeName) {
		//String serviceUrl = "http://se2firstapp-softwareeng2.rhcloud.com/FCISquare/rest/login";

		String serviceUrl = "http://localhost:8080/FCISquare/rest/searchForPlace";

		String urlParameters = "placeName=" + placeName ;
		
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST","application/x-www-form-urlencoded;charset=UTF-8");
		HttpSession session = request.getSession();
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
			obj = (JSONObject) parser.parse(retJson);
			
			session.setAttribute("Placeid", obj.get("id"));
			session.setAttribute("Placename", obj.get("name"));
			return Response.ok(new Viewable("/checkin.jsp")).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	
	
	
	
}
