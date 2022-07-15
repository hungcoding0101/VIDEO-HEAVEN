package com.hung.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DaoObject {
	protected String url;
	protected String user;
	protected String pass;
	protected Connection connect;
	protected String command;
	protected PreparedStatement statement;
	
	
	 //a general constructor setting all prerequisite conditions for database connection	
	public DaoObject() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.url = "";
		this.user = "";
		this.pass = "";
		this.connect = DriverManager.getConnection(url, user,pass);
	}


	public Connection getConnect() {
		return connect;
	}


	public void setConnect(Connection connect) {
		this.connect = connect;
	}


	public String getCommand() {
		return command;
	}


	public void setCommand(String command) {
		this.command = command;
	}


	public PreparedStatement getStatement() {
		return statement;
	}


	public void setStatement(PreparedStatement statement) {
		this.statement = statement;
	}
	
	
		
}

