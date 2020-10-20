package org.raj.smartHomeDeviceManager.repos;

import java.sql.*;

import org.raj.smartHomeDeviceManager.domain.*;

public class RegisterRepo {
	
	private String name;
	private String email;
	private String username;
	private String password;
	UserRegisterStatus stat;
	public RegisterRepo(UserRegister register) {
		this.name=register.getName();
		this.email=register.getEmail();
		this.username=register.getUserName();
		this.password=register.getPassword();
	}
	public UserRegisterStatus check() {
		UserRegisterStatus stat=new UserRegisterStatus(true,"");
		try {
			Connection connect=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			Statement statement=connect.createStatement();
			ResultSet rs=statement.executeQuery("Select username from users where username='"+username+"'");
			if(rs.next()) {
				stat.setStatus(false);
				stat.setErrorMsg("Username already exists");
			}
			else {
				stat.setStatus(true);
				stat.setErrorMsg("");
			}
			return stat;
		}
		catch(Exception e) {
			return stat;
			}
		
	}
	public void write() {
		try {
			Connection connect=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			PreparedStatement ps=connect.prepareStatement("insert into users(name,email,username,password) values(?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.executeUpdate();
			ps.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
	}
}
