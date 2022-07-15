package com.hung.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class InputValidation {
	
	   private static String emailPattern = ".+@.+";
	   private static String userNamePattern = "[\\w]+";
	   private static String passWordPattern = ".{8,}";
	   private static String fileNamePattern = "^(?=.*([\\S]+))(?=.*([^\\W\\/\\*\\:\\?\"\\<\\>\\|\\\\]+)).*$";

	  
	   
	   public static InputValiResult checkTextInput(String textInput, String pattern) {
		   	if(textInput == null || textInput.isEmpty()) {return InputValiResult.MISSING;}
		   	if(!textInput.matches(pattern)) {return InputValiResult.ERROR;}
		   	return InputValiResult.GOOD;
	   }
	   
	   public static InputValiResult checkFileInput(Part fileContent) {
		   	if(fileContent == null) {
		   		return InputValiResult.MISSING;
		   	}
		   	
		   	if(!fileContent.getContentType().equals("video/mp4")) {
		   		return InputValiResult.ERROR;
		   	}
		   	
		   	return InputValiResult.GOOD;
	   }


	public static String getEmailPattern() {
		return emailPattern;
	}


	public static String getUserNamePattern() {
		return userNamePattern;
	}


	public static String getPassWordPattern() {
		return passWordPattern;
	}


	public static String getFileNamePattern() {
		return fileNamePattern;
	}
	   
	   

}


