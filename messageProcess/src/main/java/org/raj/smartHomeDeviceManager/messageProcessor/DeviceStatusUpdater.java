package org.raj.smartHomeDeviceManager.messageProcessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeviceStatusUpdater {
	String message;
	Connection connect;
	String clientId;
	String splitted[];
		public DeviceStatusUpdater(String message) {
			this.message=message;
		}
		
		void splitter() {
			splitted=message.split(",");
			clientId=splitted[0];
		}
		
		public void writeToDb()throws Exception {
			String status="online";
			String query="update devices set status= ? where clientid= ?";
			connect=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			PreparedStatement ps=connect.prepareStatement(query);
			ps.setString(1,status);
			ps.setString(2,clientId);
			ps.executeUpdate();
			ps.close();
		}
		
		
}
