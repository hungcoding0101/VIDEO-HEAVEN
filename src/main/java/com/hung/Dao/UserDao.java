package com.hung.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao extends DaoObject{

	Statement testStatement;
	
	public UserDao() throws SQLException {
		super();
	}
	
	
	public Boolean checkFieldDuplication(String fieldToCheck, String value) throws SQLException {
		this.command = "SELECT * FROM myusers WHERE ? = ?;";
		this.statement = this.connect.prepareStatement(command);
		System.out.println(fieldToCheck + "     " + value);
		
		statement.setString(1, fieldToCheck);
		statement.setString(2, value);
		
		Boolean finalResult;
		
		ResultSet result = statement.executeQuery();
		if(result.next()) {finalResult =  true;}
		else {finalResult = false;}
		statement.clearParameters();
		statement.close();
		System.out.println(finalResult);
		return finalResult;
	}
	
	public ResultSet checkExistence(String email, String password) throws SQLException {
			this.command = "SELECT * FROM myusers WHERE email = ? AND password = ?;";
			
			this.statement = this.connect.prepareStatement(command);
			statement.setString(1, email);
			statement.setString(2, password);
			
//			ResultSet existence = testStatement.executeQuery(command);
			
			ResultSet existence = statement.executeQuery();
			
			if(existence.isBeforeFirst()) {return existence;}
			else {return null;}
	}
	
	public String Register(String email, String name, String password) throws SQLException {
		this.command = "INSERT INTO myusers VALUES(?, ?, ?, 0);";
		try {
			this.statement = this.connect.prepareStatement(command) ;
			

			statement.setString(1, email);
			statement.setString(2, name);
			statement.setString(3, password);	
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
		return name;	
	}
	
	public void updateCoin(String email, Double coinNumber) throws SQLException {
			this.command = "UPDATE  myusers SET coin_left = ? WHERE email = ?;";
			this.statement = this.connect.prepareStatement(command);
			
			statement.setDouble(1, coinNumber);
			statement.setString(2, email);
			statement.executeUpdate();
	}
	
	public void updatePassword(String email, String newPassword) throws SQLException {
		this.command = "UPDATE  myusers SET password = ? WHERE email = ?;";
		this.statement = this.connect.prepareStatement(command);
		
		statement.setString(1, newPassword);
		statement.setString(2, email);
		statement.executeUpdate();
	}
	
	
}