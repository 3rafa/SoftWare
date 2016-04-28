package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class PlaceModel {
	
	private Integer id;
	private Double lat;
	private Double lon;
	private String name;
	private String description;
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}


	public void setLon(Double lon) {
		this.lon = lon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public static PlaceModel addNewPlace(String name, String description, double lon, double lat) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into places (`name`,`description`,`lat`,`long`) VALUES  (?,?,?,?)";
			// System.out.println(sql);

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, name);
			stmt.setString(2, description);
			stmt.setDouble(3, lon);
			stmt.setDouble(4, lat);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				PlaceModel place = new PlaceModel();
				place.id = rs.getInt(1);
				place.name = name;
				place.description = description;
				place.lon = lon;
				place.lat = lat;
				
				return place;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static PlaceModel searchForPlace(String name) throws SQLException{
		try {
		Connection conn = DBConnection.getActiveConnection();
		String sql = "select * from places where name=?";
		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, name);
		ResultSet rs=stmt.executeQuery();
		PlaceModel place = new PlaceModel();

		if (rs.next()) {
			place.id = rs.getInt("id");
			place.name =rs.getString("name");
			
			return place;
		}	
		else{
		return place;}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}


}
