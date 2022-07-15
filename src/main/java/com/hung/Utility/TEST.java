package com.hung.Utility;

import com.hung.Dao.VideoDao;
import com.hung.Model.VIDCounter;
import com.hung.Model.Video;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.lang.System.*;
import java.time.*;
import java.util.*;
import java.io.*;
import java.lang.reflect.Array;
import java.time.Month;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletContext;

import java.math.BigDecimal;

//import org.jsoup.nodes.Element;  
 
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.file.*;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.locks.*;


public class TEST {
	
	public static void main(String[] arg) throws SQLException {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		String url = "jdbc:mysql://localhost:3306/clip_share";
//		String user = "root";
//		String pass = "icancode96";
//		Connection connect = DriverManager.getConnection(url, user,pass);
//		
//		String command = "INSERT INTO myusers VALUES(?,?,?,?);";
//		
//		PreparedStatement stat = connect.prepareStatement(command);
//		stat.setString(1, "user5@gmail.com");
//		stat.setString(2, "hùng");
//		stat.setString(3, "javaforever");
//		stat.setDouble(4, 0.0);
//		stat.executeUpdate();
		
		
		
//		String regex = "^(?=.*([\\S]+))(?=.*([^\\W\\/\\*\\:\\?\"\\<\\>\\|\\\\]+)).*$";
//		String input = "";
//		do {
//		
//		Scanner read = new Scanner(in);
//		input = read.nextLine();
//out.println(input.matches(regex));
//		}while(!input.equals("no"));
	}
	
	public static int add(int a, int b) {
		return a+b;
	}
}
