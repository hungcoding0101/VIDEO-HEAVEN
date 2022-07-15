package com.hung.Model;

import java.io.Serializable;

public class Comment implements Serializable{

	private static final long serialVersionUID = 5L;
	
	private int VID;
	private String commenter;
	private String content;
	
	
	public Comment() {
		super();
		VID = 0;
		this.commenter = "";
		this.content = "";
	}
	
	
	public Comment(int vID, String commenter, String content) {
		super();
		VID = vID;
		this.commenter = commenter;
		this.content = content;
	}


	public int getVID() {
		return VID;
	}


	public void setVID(int vID) {
		VID = vID;
	}


	public String getCommenter() {
		return commenter;
	}


	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public String toString() {
		return "Comment [VID=" + VID + ", commenter=" + commenter + ", content=" + content + "]";
	}
	

}
