package com.hung.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hung.Dao.UserDao;
import com.hung.Model.User;
import com.hung.Utility.InputValiResult;
import com.hung.Utility.InputValidation;

/**
 * Servlet implementation class SignupControl
 */
public class SignupControl extends HttpServlet {
	private static final long serialVersionUID = 10L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		
		String Message="";
		RequestDispatcher rqd = null;
		
		String email = (String) request.getParameter("email");
		String pass = (String) request.getParameter("pass");
		String name = (String) request.getParameter("username");
		
		InputValiResult emailCheck = InputValidation.checkTextInput(email, InputValidation.getEmailPattern());
		InputValiResult passCheck = InputValidation.checkTextInput(pass, InputValidation.getPassWordPattern());
		InputValiResult nameCheck = InputValidation.checkTextInput(name, InputValidation.getUserNamePattern());
		
		
		// in case user left some field blank
		if(emailCheck == InputValiResult.MISSING || passCheck == InputValiResult.MISSING 
			 || nameCheck == InputValiResult.MISSING) {
					
					Message = "PLEASE FILL IN ALL FIELDS";
					request.setAttribute("emailSignup", email);
					request.setAttribute("passSignup", pass);
					request.setAttribute("username", name);
					rqd = request.getRequestDispatcher("index.jsp");
				}
				
		// in case user inputed fields with errors in syntax
		else if(emailCheck == InputValiResult.ERROR || passCheck == InputValiResult.ERROR
						|| nameCheck == InputValiResult.ERROR) {
			
					Message = "ERROR";
					if(emailCheck == InputValiResult.ERROR) {request.setAttribute("emailSignUpMessage", "Email must contain @ symbol");}
					if(passCheck == InputValiResult.ERROR) {request.setAttribute("passSignUpMessage", "Password must contain at least 8 characters");}
					if(nameCheck == InputValiResult.ERROR) {request.setAttribute("nameSignUpMessage", "User name can contain only letters, numbers or _");}
					rqd = request.getRequestDispatcher("index.jsp");
				}
		
				
		// User submitted all necessary fields and they are all valid
		else {
		UserDao register ;
		try {
			register  = new UserDao();

			// We try to insert user inputs to database and see if there is any duplication
				register.Register(email, name, pass);
				
				// everything is good - we created new account successfully!
				HttpSession thisSession = request.getSession();
				thisSession.setAttribute("email", email);
				thisSession.setAttribute("name", name);
				thisSession.setAttribute("password", pass);
				thisSession.setAttribute("coint_left", 0);
				
				rqd = request.getRequestDispatcher("homepage.jsp");
			
		} catch (SQLException e1) {
			// Duplication - failed - send warning message to user
			if(e1 instanceof SQLIntegrityConstraintViolationException) { 
				String errorMessage = e1.getLocalizedMessage();
				String duplicatedField = errorMessage.substring(errorMessage.indexOf("entry '")+7, 
																										errorMessage.indexOf("' for"));
				if(duplicatedField.equals(email)) {
					request.setAttribute("emailSignUpMessage", "This email has been registered by other user");}
				if(duplicatedField.equals(name)){
					request.setAttribute("nameSignUpMessage", "This user name has been registered by other user");}
				
				Message = "ERROR";
				rqd = request.getRequestDispatcher("index.jsp");
			}
		}	
	}
				request.setAttribute("Message", Message);
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
