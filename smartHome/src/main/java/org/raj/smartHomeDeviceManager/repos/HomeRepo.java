package org.raj.smartHomeDeviceManager.repos;
import java.sql.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.raj.smartHomeDeviceManager.domain.UserHome;

public class HomeRepo {
	String username;
	public void setUsername(String username) {
		this.username=username;
	}
		public ArrayList<UserHome> get() {
			ArrayList<UserHome> devices=new ArrayList<UserHome>();
			try {
				Connection connect=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
				Statement statement=connect.createStatement();
				ResultSet rs=statement.executeQuery("Select clientid,devicename,devicetype from devices");
				int i=1;
				while(rs.next()) {
						System.out.println("hi"+username);
						UserHome obj=new UserHome(i,rs.getString(2),rs.getString(3),"alive",username,rs.getString(1));
						devices.add(obj);
						i++;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return devices;
		}
}
