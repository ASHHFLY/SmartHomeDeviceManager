package org.raj.smartHomeDeviceManager.repos;

import java.sql.*;

public class LoginRepo {
	public boolean validate(String username,String password) {
		try {
			Connection connect=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			Statement statement=connect.createStatement();
			ResultSet rs=statement.executeQuery("select password from users where username='"+username+"'");
			rs.next();
			String result=rs.getNString(1);
			if(result.equalsIgnoreCase(password)) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception e) {
			return false;
		}
	}
}
