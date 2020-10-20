package org.raj.smartHomeDeviceManager.messageProcessor;
import java.sql.*;
public class MessageProcessor {
	String message;
	String splitted[];
	Connection connect;
	String clientId;
	String deviceName;
	String topic;
	String deviceType;
	MessageProcessor(String m){
			message=m;
	}
	
	void splitter() {
		splitted=message.split(",");
		clientId=splitted[0];
		deviceName=splitted[1];
		topic=splitted[2];
		deviceType=splitted[3];
	}
	
	void display()throws Exception {
		splitter();
		for(int i=0;i<splitted.length;i++) {
			System.out.println(splitted[i]);
		}
		writeToDb();
	}
	
	void writeToDb()throws Exception {
		try {
			connect=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			PreparedStatement ps=connect.prepareStatement("insert into devices(clientId,deviceName,topic,deviceType) values(?,?,?,?)");
			ps.setString(1,clientId);
			ps.setString(2,deviceName);
			ps.setString(3,topic);
			ps.setString(4,deviceType);
			ps.executeUpdate();
			ps.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
