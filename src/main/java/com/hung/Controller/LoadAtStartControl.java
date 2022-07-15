package com.hung.Controller;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hung.Dao.CommentDao;
import com.hung.Dao.VideoDao;
import com.hung.Model.Comment;
import com.hung.Model.Video;

public class LoadAtStartControl implements ServletContextListener{
	
	public void contextInitialized(ServletContextEvent event) {
			
		VideoDao vids;
		ResultSet result;
		ArrayList<Video> Results= new ArrayList<Video>();
		
		try {				
			vids = new VideoDao();
			result = vids.showAllVideos();
					
			while(result.next()) {
				
				Video video = new Video();
				
				video.setVID(result.getInt("VID"));
				video.setName(result.getString("name"));
				video.setImagepath(result.getString("image_path"));
				video.setFilepath(result.getString("file_path"));
				video.setPrice(result.getDouble("price"));
				video.setUploader(result.getString("uploader"));
				video.setDescription(result.getString("description"));
				
				java.sql.Date sqldate = result.getDate("uploaddate");
				video.setUploadDate(sqldate.toLocalDate());

				
				ResultSet likes = vids.getLikes(video.getVID());
				while(likes.next()) {
					video.getLikes().add(likes.getString("email"));}
				
				ResultSet dislikes = vids.getDiskLikes(video.getVID());
				while(likes.next()) {
					video.getDiskLikes().add(dislikes.getString("email"));}
				
				ResultSet comments = new CommentDao().getComments(video.getVID());
				while(comments.next()) {
					String commenter = comments.getString("username");
					String content = comments.getString("content");
					video.getComments().add(new Comment(video.getVID(), commenter, content));
				}
				
				Results.add(video);								
			}
			
			vids.getConnect().close();
						
	}catch(SQLException e) {e.printStackTrace();}
		
		ServletContext context = event.getServletContext();
		context.setAttribute("allvideo", Results);
		System.out.print("CONTEXT GOT IT DONE");
}	
		
//---------------------------------------------		
		public void contextDestroyed(ServletContextEvent event) {
			
			final ClassLoader cl = Thread.currentThread().getContextClassLoader();
		      final Enumeration<Driver> drivers = DriverManager.getDrivers();
		      while (drivers.hasMoreElements()) {
		         final Driver driver = drivers.nextElement();
		         // We deregister only the classes loaded by this application's classloader
		         if (driver.getClass().getClassLoader() == cl) {
		            try {
		               DriverManager.deregisterDriver(driver);
		            } catch (SQLException e) {
		               event.getServletContext().log("JDBC Driver deregistration problem.", e);
		            }
		         }
		      }
		}	
}
