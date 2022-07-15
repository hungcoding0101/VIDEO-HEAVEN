package com.hung.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hung.Dao.VideoDao;
import com.hung.Model.Video;

/**
 * Servlet implementation class DisplayControl
 */
public class DisplayControl extends HttpServlet {
	private static final long serialVersionUID = 4L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	ArrayList<Video> Results;
	
    public DisplayControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		VideoDao vids;
//		ResultSet result;
//		this.Results = new ArrayList<Video>();
//		
//		try {
//			
//			vids = new VideoDao();
//			result = vids.showAllVideos();
//					
//			while(result.next()) {
//				
//				int VID = result.getInt("VID");
//				String name = result.getString("name");
//				String image_path = result.getString("image_path");
//				String file_path = result.getString("file_path");
//				int price = result.getInt("price");
//				int likes = result.getInt("likes");
//				int dislikes = result.getInt("dislikes");
//				String uploader = result.getString("uploader");
//				
//				Video video = new Video(VID, name, image_path, file_path, price, likes, dislikes, uploader);
//				
//				this.Results.add(video);				
//				
//			}
//			
//			ServletContext cont = getServletContext();		
//			cont.setAttribute("allvids", Results);
//			response.sendRedirect("homepage.jsp");
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
