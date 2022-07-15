package com.hung.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	private static int totalusers = 0 ;

	private String name;
	private String password;
	private String email;
	private Double coin_left;
	private List<Video> uploads;
	private List<Video> downloads;

	
	public User( String name, String password, String email, List<Video> uploads, List<Video> downloads, Double coin_left) {
		super();
		
		++totalusers;
		this.name = name;
		this.password = password;
		this.email = email;
		this.uploads = uploads;
		this.downloads = downloads;
		this.coin_left = coin_left;

	}


	public User() {
		super();
		++totalusers;
		this.name = "";
		this.password = "";
		this.email = "";
		this.uploads = new LinkedList<>();
		this.coin_left = 0.0;
		this.downloads = new LinkedList<>();
	}
	

	public static int getTotalusers() {
		return totalusers;
	}


	public static void setTotalusers(int totalusers) {
		User.totalusers = totalusers;
	}


	public List<Video> getDownloaded() {
		return downloads;
	}


	public void setDownloaded(List<Video> downloads) {
		this.downloads = downloads;
	}



	public List<Video> getUploaded() {
		return uploads;
	}


	public void setUploaded(List<Video> uploads) {
		this.uploads = uploads;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getCoinleft() {
		return coin_left;
	}

	public void setCoinleft(Double coin_left) {
		this.coin_left = coin_left;
	}
	

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}




	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", email=" + email + ", coin_left="
				+ coin_left + ", uploads=" + uploads + ", downloads=" + downloads + "]";
	}
	
}
