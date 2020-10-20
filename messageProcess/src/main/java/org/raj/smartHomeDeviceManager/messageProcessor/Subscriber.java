package org.raj.smartHomeDeviceManager.messageProcessor;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
public class Subscriber implements MqttCallback{
 String MQTT_BROKER="tcp://localhost:1883";
 String MQTT_TOPIC="SHD";
 String MQTT_PORT="1883";
 String CLIENT_ID="sub";
 String recievedMessage;
 public void run()throws Exception {
	 MqttClient obj=new MqttClient(MQTT_BROKER,CLIENT_ID);
	 obj.connect();
	 obj.subscribe(MQTT_TOPIC);
	 obj.setCallback(this);
 }
 public static void main(String args[])throws Exception{
	 Subscriber obj1=new Subscriber();
	 obj1.run();
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
	MessageProcessor obj=new MessageProcessor(recievedMessage);
	obj.display();
}
}
