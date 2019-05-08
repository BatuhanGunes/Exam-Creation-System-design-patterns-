package Model;

import java.sql.*;
import javax.swing.*;

public class DBConnection {
	 static Connection con = null;
	 
	 public static Connection dbConnector() {
		 
		 String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=SBDB;integratedSecurity=true;";
		 
		    try {
		      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		      con = DriverManager.getConnection(connectionUrl); 
		      Statement stmt = con.createStatement();
		    } catch (Exception x) {
		      JOptionPane.showMessageDialog(null, "Connection Unsuccessful.");
		    }
		    return con;
		  }
}
