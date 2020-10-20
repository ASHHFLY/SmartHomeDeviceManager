package org.raj.smartHomeDeviceManager.messageProcessor;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class HbSubscriber implements MqttCallback  {
	 String MQTT_BROKER="tcp://localhost:1883";
	 String MQTT_TOPIC="SHD";
	 String MQTT_PORT="1883";
	 String CLIENT_ID="sub";
	 String recievedMessage;
	
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
		
	}
	public void messageArrived(String topic,MqttMessage message) throws Exception {
		// TODO Auto-generated method stub
		String msg=""+new String(message.getPayload());
		DeviceStatusUpdater updater=new DeviceStatusUpdater(msg);
		updater.splitter();
		updater.writeToDb();
	}
	
	 public void sub()throws Exception {
		 MqttClient client=new MqttClient(MQTT_BROKER,CLIENT_ID);
		 client.connect();
		 client.subscribe(MQTT_TOPIC);
		 client.setCallback(this);
	 }
	
	public static void main(String args[])throws Exception {
		HbSubscriber obj=new HbSubscriber();
		obj.sub();
	}
}
