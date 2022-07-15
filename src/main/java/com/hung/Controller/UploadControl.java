package com.hung.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hung.Dao.CommentDao;
import com.hung.Dao.UserDao;
import com.hung.Dao.VideoDao;
import com.hung.Model.Comment;
import com.hung.Model.Video;
import com.hung.Utility.InputValiResult;
import com.hung.Utility.InputValidation;

/**
 * Servlet implementation class UploadControl
 */
@MultipartConfig
public class UploadControl extends HttpServlet {
	private static final long serialVersionUID = 6L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Part videoContent =request.getPart("file");
		String videoTitle = request.getParameter("title");
		System.out.println(videoTitle);
		
		// Check name and type of the file uploaded
		InputValiResult fileCheckResult = InputValidation.checkFileInput(videoContent);
		InputValiResult nameCheckResult = InputValidation.checkTextInput(videoTitle, InputValidation.getFileNamePattern());
		
		// If some Error occurred
		if( fileCheckResult != InputValiResult.GOOD || nameCheckResult != InputValiResult.GOOD) { 
				RequestDispatcher sendBack = request.getRequestDispatcher("Upload.jsp");
				String titleErrorMessage = "";
				String fileErrorMessage = "";
				
				if(nameCheckResult != InputValiResult.GOOD) {
					if(nameCheckResult == InputValiResult.MISSING) {
						titleErrorMessage = "Please set a title for the video";
					}
					
					if(nameCheckResult == InputValiResult.ERROR) {
						titleErrorMessage = "Video title can not contain following characters: * : ? \" < > |  /  \\ "
								+ "and must cotain at least one letter or number";
					}
				}
				
				if(fileCheckResult != InputValiResult.GOOD) {
					
					if(fileCheckResult == InputValiResult.MISSING) {// User didn't send any file
						fileErrorMessage = "You must choose a video to upload";
					}
					
					if(fileCheckResult == InputValiResult.ERROR) {// User attempted to send a file which is not a video
						fileErrorMessage = "Please choose a .mp4 file";
					}			
				}

				request.setAttribute("fileErrorMessage", fileErrorMessage);
				request.setAttribute("titleErrorMessage", titleErrorMessage);
				sendBack.forward(request, response);		
		}
		
		
		else { // Great! user sent a valid mp4 file, now we proceed
			
		VideoDao videoCreator = null;  // insert info of new video to database
		
		int VID = 0;
		
		try {
			videoCreator = new VideoDao();
			VID= videoCreator.getMaxVID() + 1;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 
		 String videoName = request.getParameter("title"); // set videoName of the video using user input
		 
		 String imageLocation = "C:/Users/quoch/eclipse-workspace/VIDEOHEAVEN.COM/src/main/webapp/video-image/"
		 +videoName+"-heavenvidcode-"+VID+".jpg"; // determine location for storing thumbnail
		 
		 String fileLocation = "C:/Users/quoch/eclipse-workspace/VIDEOHEAVEN.COM/src/main/webapp/video-file/"
		 +videoName+"-heavenvidcode-"+VID+".mp4"; //determine location for storing the video file
		  
		// the relative path of the thumbnail stored in database
		 String image_path = imageLocation.substring(imageLocation.indexOf("video-image")); 
		 
		// the relative path of the video file stored in database
		 String file_path = fileLocation.substring(fileLocation.indexOf("video-file")); 
		 
		 HttpSession thisUser = request.getSession();
		 String uploader = (String) thisUser.getAttribute("email"); // the user who uploaded this video
		 
		 String description = request.getParameter("description"); // get description
		 
		 LocalDate uploadDate = LocalDate.now(); // set current date as update date
		 
			
		 Double videoSize = 0.0;
			
			try {	
				File path = new File(fileLocation);
				videoContent.write(path.getAbsolutePath());
				videoSize = videoContent.getSize()/(1024.0*1024);// transforming file size from byte to megabyte
				
				// Rounding up viedoSize to 2 decimal spaces using DecimalFormat
				DecimalFormat roundUp_er = new DecimalFormat("###.#"); 
				
				Double price = Double.parseDouble(roundUp_er.format(videoSize)); 
				Double coinEarned = price; // number of coin this user earns for uploading this video

				
				
			/* Because this fileLocation and imageLocation contains white spaces, we have to surround it with "" to
			  embed it into cmd command */				
			String fileLocationWithQuoMark = "\"" + fileLocation + "\"";
			String imageLocationWithQuoMark = "\"" + imageLocation + "\"";
			
			// Automatically creating thumbnail for this video
			Runtime.getRuntime().exec( "cmd /c start C://Users//quoch//eclipse-workspace//ClipShare//src//main//webapp//includes//AutoCreateThumbnail.bat "
			+fileLocationWithQuoMark + " "+ imageLocationWithQuoMark); 
			

				
				videoCreator.insertVideoInfo(VID, videoName, image_path, file_path, price, uploader, description, uploadDate);
				
				videoCreator.getConnect().close();
				UserDao userEditor = new UserDao(); // add number of coin for this user earned
				userEditor.updateCoin((String)thisUser.getAttribute("email"), coinEarned);				
				
				ServletContext context = getServletContext();
				ArrayList<Video> allVideo = (ArrayList<Video>) context.getAttribute("allvideo");
				
				Video thisVideo = new Video();
				thisVideo.setVID(VID);
				thisVideo.setName(videoName);
				thisVideo.setImagepath(image_path);
				thisVideo.setFilepath(file_path);
				thisVideo.setPrice(price);
				thisVideo.setUploadDate(uploadDate);
				thisVideo.setDescription(description);
				thisVideo.setUploader(uploader);
				
				allVideo.add(thisVideo);
			
			RequestDispatcher rqd = request.getRequestDispatcher("Upload-result.jsp");
			request.setAttribute("coinEarned", coinEarned); // if update successfully, move to Upload-result page

			rqd.forward(request, response);
			
			}catch (Exception e) {
				e.printStackTrace();
				}
			
		}
		
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}
	
	
}


