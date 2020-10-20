package org.raj.smartHomeDeviceManager.smartDevice;
import java.io.*;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
class Publisher{
	String MQTT_BROKER="tcp://localhost:1883";
	String MQTT_TOPIC="telematics/10";
	String MQTT_PORT="1883";
	String MQTT_MESSAGE="hi";
	String KEEP_ALIVE_INTERVAL="60";
	String CLIENT_ID="java sample";
		void run()throws Exception{
				MqttClient obj=new MqttClient(MQTT_BROKER,CLIENT_ID);
				obj.connect();
				MqttMessage message=new MqttMessage(MQTT_MESSAGE.getBytes());
				obj.publish(MQTT_TOPIC,message);
				obj.close();
		}
		public static void main(String args[]) throws Exception{
				Publisher obj1=new Publisher();
				obj1.run();
		}
}

