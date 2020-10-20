package org.raj.smartHomeDeviceManager.smartDevice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class OfflineUpdater implements Runnable {
	String clientId;
	Connection connect;
		public OfflineUpdater(String clientId) {
			this.clientId=clientId;
		}
		
		public void run() {
			try {
				String status="offline";
				String query="update devices set status= ? where clientid= ?";
				connect=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
				PreparedStatement ps=connect.prepareStatement(query);
				ps.setString(1,status);
				ps.setString(2,clientId);
				ps.executeUpdate();
				ps.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
}
