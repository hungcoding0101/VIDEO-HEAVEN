package com.hung.Dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.mysql.cj.result.SqlDateValueFactory;

import static java.lang.System.*;

public class VideoDao extends DaoObject {
		
	public VideoDao() throws SQLException {
		super();
	}
	
	public ResultSet showAllVideos() throws SQLException { //Fetch all current videos in database
		
		this.command = "SELECT * FROM videos WHERE image_path LIKE 'video-image%' ;";
		this.statement = this.connect.prepareStatement(command);
		
		ResultSet result = statement.executeQuery();	
		
		return result;
	}
	
	public ResultSet getLikes(int VID) throws SQLException { //Fetch emails of users who like this video
		
		this.command = "SELECT email FROM userslike WHERE VID = ?";
		this.statement = this.connect.prepareStatement(command);
		statement.setInt(1, VID);
		ResultSet result = statement.executeQuery();	
		
		return result;
	}

	
	public ResultSet getDiskLikes(int VID) throws SQLException { //Fetch emails of users who dislike this video
	
	this.command = "SELECT email FROM usersdislike WHERE VID = ?";
	this.statement = this.connect.prepareStatement(command);
	statement.setInt(1, VID);
	ResultSet result = statement.executeQuery();	
	
	return result;
	}

	
	public void insertVideoInfo(int VID, String name, String image_path,
			String file_path, Double price, String uploader, String description, LocalDate uploadDate) throws SQLException {
		
		this.command = "INSERT INTO videos VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		this.statement = this.connect.prepareStatement(command);
		
		statement.setInt(1, VID);
		statement.setString(2, name);
		statement.setString(3, image_path);
		statement.setString(4, file_path);
		statement.setDouble(5, price);
		statement.setString(6, uploader);
		statement.setString(7, description);
		java.sql.Date update = Date.valueOf(uploadDate);
		statement.setDate(8, update);
		
	
		statement.executeUpdate();
		statement.close();
	}
	
	public int getMaxVID() throws SQLException {
		this.command = "SELECT MAX(VID) FROM videos;";
		this.statement = this.connect.prepareStatement(command);
		
		ResultSet result = statement.executeQuery();
		
		if(!result.isBeforeFirst()) {return 0;}
		
		result.next();
		
		return result.getInt("MAX(VID)");	
	}
}
