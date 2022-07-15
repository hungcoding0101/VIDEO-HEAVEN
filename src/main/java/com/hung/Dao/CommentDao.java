package com.hung.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentDao extends DaoObject{

	public CommentDao() throws SQLException {
		super();
	}
	
	
	
	public ResultSet getComments(int VID) throws SQLException {
		this.command = "SELECT content, username FROM userscomment WHERE VID = ? ;";
		this.statement = this.connect.prepareStatement(command);
		
		statement.setInt(1, VID);
		return statement.executeQuery();
	}
}
