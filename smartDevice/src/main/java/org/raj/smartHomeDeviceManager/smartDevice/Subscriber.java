package org.raj.smartHomeDeviceManager.smartDevice;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
public class Subscriber implements MqttCallback,Runnable{
 String MQTT_BROKER;
 String MQTT_TOPIC;
 String MQTT_PORT;
 String CLIENT_ID;
 String recievedMessage;
 MqttClient obj;
 boolean running;
 
 public Subscriber(String CLIENT_ID) {
	 this.CLIENT_ID=CLIENT_ID;
	 MQTT_TOPIC="SHD/SUB/"+CLIENT_ID;
	 MQTT_BROKER="tcp://localhost:1883";
 }
 
	public synchronized boolean isAlive() {
		return running;
	}
 
 public void run() {
	 try {
	 obj=new MqttClient(MQTT_BROKER,"123");
	 obj.connect();
	 obj.subscribe(MQTT_TOPIC);
	 obj.setCallback(this);
	 }
	 catch(Exception e) {
		 e.printStackTrace();
	 }
 }
 
 public void stop()throws Exception {
	 running=false;
	 obj.disconnect();
	 obj.close();
 }
 
public void connectionLost(Throwable arg0) {
	// TODO Auto-generated method stub
	
}
public void deliveryComplete(IMqttDeliveryToken arg0) {
	// TODO Auto-generated method stub
	
}
public void messageArrived(String topic,MqttMessage message) throws Exception {
	// TODO Auto-generated method stub
	//System.out.println(""+new String(message.getPayload()));
	recievedMessage=""+new String(message.getPayload());
	System.out.println(recievedMessage);
}
}

