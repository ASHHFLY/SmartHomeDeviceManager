package org.raj.smartHomeDeviceManager.smartDevice;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
public class HeartBeat implements Runnable {
	String clientId;
	String topic;
	String broker;
	String port;
	String mesg;
	MqttClient client;
	int criteria;
	MqttMessage message;
	boolean running;
	
	HeartBeat(String cId,String t,String p,String b){
		clientId=cId;
		topic=t;
		broker=b;
		port=p;
		mesg=clientId+",H";
		criteria=1;
		running=false;
	}
       
	public void run() {
		try {
			message=new MqttMessage(mesg.getBytes());
			client=new MqttClient(broker,clientId);
			client.connect();
			running=true;
			while(criteria==1) {
				client.publish(topic,message);
				Thread.sleep(1*1000);
			}
			if(client.isConnected()==true) {
				client.disconnect();
				client.close();
			}
			running=false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized boolean isAlive() {
		return running;
	}
	public void stop() {
		criteria=0;
	}
}