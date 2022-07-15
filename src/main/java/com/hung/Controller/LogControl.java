package com.hung.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hung.Dao.UserDao;
import com.hung.Utility.InputValiResult;
import com.hung.Utility.InputValidation;

/**
 * Servlet implementation class LogControll
 */
public class LogControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String message="";
		RequestDispatcher rqd = null;
		

		String email = (String) request.getParameter("email");
		String pass = (String) request.getParameter("pass");
		
		InputValiResult emailCheck = InputValidation.checkTextInput(email, InputValidation.getEmailPattern());
		InputValiResult passCheck = InputValidation.checkTextInput(pass, InputValidation.getPassWordPattern());
		
		// in case user missed some field
		if(emailCheck == InputValiResult.MISSING || passCheck == InputValiResult.MISSING) {
			message = "PLEASE FILL IN ALL FIELDS";
			request.setAttribute("email", email);
			request.setAttribute("pass", pass);
			rqd = request.getRequestDispatcher("index.jsp");
		}
			
		// in case user inputed fields with errors in syntax
		else if(emailCheck == InputValiResult.ERROR || passCheck == InputValiResult.ERROR) {
			message = "ERROR";
			if(emailCheck == InputValiResult.ERROR) {request.setAttribute("EmailMessage", "email must contain @ symbol");}
			if(passCheck == InputValiResult.ERROR) {request.setAttribute("PassMessage", "password must contain at least 8 characters");}
			rqd = request.getRequestDispatcher("index.jsp");
		}
		
		
		else {
		UserDao userChecker;
		ResultSet result;
		
		try {
			userChecker = new UserDao();		
			result = userChecker.checkExistence(email, pass);
			
			
			if(result != null) { 
				HttpSession thisSession = request.getSession();			
				result.next();
				thisSession.setAttribute("email", result.getString("email"));
				thisSession.setAttribute("name", result.getString("name"));
				thisSession.setAttribute("password", result.getString("password"));
				thisSession.setAttribute("coint_left", result.getString("coin_left"));
				
				rqd = request.getRequestDispatcher("homepage.jsp");
	
			}
			
			else {
				message = "INCORRECT EMAIL OR PASSWORD";
				rqd = request.getRequestDispatcher("index.jsp");
				}			
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
		request.setAttribute("Message", message);
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
