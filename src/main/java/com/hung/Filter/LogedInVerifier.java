package com.hung.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LogedInVerifier
 */
public class LogedInVerifier implements Filter {

    /**
     * Default constructor. 
     */
    public LogedInVerifier() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession thisUser = httpRequest.getSession();
		String userName = (String) thisUser.getAttribute("name");
		
//		System.out.println(httpRequest.getRequestURI());
		
		if(thisUser != null && userName != null && !userName.isEmpty()) {
			
			httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			httpResponse.setDateHeader ("Expires", System.currentTimeMillis() - 1000000);
			chain.doFilter(request, response);
		}

		// If this person hasn't logged in yet, send them to log in page
		else {
			
			httpRequest.setAttribute("Message", "PLEASE LOG IN OR SIGN UP TO PERFORM THIS FEATURE");
			RequestDispatcher rqd = httpRequest.getRequestDispatcher("index.jsp");
			rqd.forward(httpRequest, httpResponse);
			
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
