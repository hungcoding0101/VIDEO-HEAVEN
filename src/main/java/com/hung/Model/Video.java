package com.hung.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Video implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	
	public static int currentVID;
	private String name;
	private String image_path;
	private String file_path;
	private int VID;
	private Double price;
	private ArrayList<String> likes;
	private ArrayList<String> dislikes;
	private String uploader;
	private ArrayList<Comment> comments;
	private String description;
	private LocalDate uploadDate;
	
	
	public  Video() {
		super();
		VID = 0;
		name = "";
		image_path = "";
		file_path = "";
		price = 0.0;
		likes = new ArrayList<String>();
		dislikes = new ArrayList<String>();
		uploader = "";
		comments = new ArrayList<Comment>();
		description = "";
		uploadDate = LocalDate.now();
	}

	
	public Video(int VID, String name, String image_path, String file_path, Double price, 
			ArrayList<String> likes, ArrayList<String> dislike, String uploader,  String description, 
			LocalDate uploadDate, ArrayList<Comment> comments) {		
		
		super();
		this.VID = VID;
		this.name = name;
		this.image_path = image_path;
		this.file_path = file_path;
		this.price = price;
		this.likes = likes;
		this.dislikes = dislike;
		this.uploader = uploader;
		this.comments = comments;
		this.description = description;
		this.uploadDate = uploadDate;
		this.comments = comments;
	}
	
	
	public String getUploader() {
		return uploader;
	}


	public void setUploader(String uploader) {
		this.uploader = uploader;
	}


	public int getVID() {
		return VID;
	}

	public void setVID(int VID) {
		this.VID = VID;
	}


	
	public ArrayList<String> getLikes() {
		return likes;
	}


	public void setLikes(ArrayList<String> likes) {
		this.likes = likes;
	}


	public ArrayList<String> getDiskLikes() {
		return dislikes;
	}


	public void setDiskLikes(ArrayList<String> dislikes) {
		this.dislikes = dislikes;
	}


	
	public static int getCurrentVID() {
		return currentVID;
	}


	public static void setCurrentVID(int currentVID) {
		Video.currentVID = currentVID;
	}


	public String getFilepath() {
		return file_path;
	}


	public void setFilepath(String file_path) {
		this.file_path = file_path;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagepath() {
		return image_path;
	}

	public void setImagepath(String image_path) {
		this.image_path = image_path;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	
	public ArrayList<Comment> getComments() {
		return comments;
	}


	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public LocalDate getUploadDate() {
		return uploadDate;
	}


	public void setUploadDate(LocalDate uploadDate) {
		this.uploadDate = uploadDate;
	}


	@Override
	public String toString() {
		return "Video [name=" + name + ", image_path=" + image_path + ", file_path=" + file_path + ", VID=" + VID
				+ ", price=" + price + ", likes=" + likes + ", dislikes=" + dislikes + ", uploader=" + uploader
				+ ", comments=" + comments + ", description=" + description + ", uploadDate=" + uploadDate + "]";
	}
	
}
