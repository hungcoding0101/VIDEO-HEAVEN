package com.hung.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hung.Dao.CommentDao;
import com.hung.Dao.VideoDao;
import com.hung.Model.Comment;
import com.hung.Model.Video;

/**
 * Servlet implementation class HomePageViewControl
 */
public class HomePageViewControl extends HttpServlet {
	private static final long serialVersionUID = 10L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomePageViewControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
			
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
		RequestDispatcher rqd = request.getRequestDispatcher("homepage.jsp");
		request.setAttribute("allvideo", Results);
		rqd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
