package org.raj.smartHomeDeviceManager.smartDevice;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.io.*;
import java.io.RandomAccessFile;  
public class Simulator{
	 String device_name;
     String MQTT_BROKER;
     String MQTT_TOPIC;
     String MQTT_PORT;
     String MQTT_MESSAGE;
     String CLIENT_ID;
     MqttClient mqttClient;
     String device_type;
     File cid;
     public void initialize()throws Exception{
    	 InputStreamReader isr=new InputStreamReader(System.in);
    	 BufferedReader br=new BufferedReader(isr);
    	 //System.out.println("Enter the name of the device");
    	 //device_name=br.readLine();
    	 //System.out.println("Enter the topic : ");
    	 //MQTT_TOPIC=br.readLine();
    	 cid=new File("cid.txt");
    	 MQTT_BROKER="tcp://localhost:1883";
    	 if(cid.exists()==true) {
    		 RandomAccessFile raf=new RandomAccessFile("cid.txt","rw");
    	 	 CLIENT_ID=raf.readLine();
    	 	 MQTT_TOPIC="SHD/"+CLIENT_ID;
    	 	 raf.close();
    	 	}
    	 else {
    		 CLIENT_ID=MqttClient.generateClientId();
    		 MQTT_TOPIC="SHD/"+CLIENT_ID;
    		 System.out.println("Enter the name of the device : ");
    		 device_name=br.readLine();
    		 System.out.println("Enter the type of device : ");
    		 device_type=br.readLine();
    		 RandomAccessFile raf=new RandomAccessFile("cid.txt","rw");
    		 raf.writeBytes(CLIENT_ID);
    		 raf.close();
    	 	}
    	 mqttClient=new MqttClient(MQTT_BROKER,CLIENT_ID);
    	 this.online();
    	 MQTT_PORT="1883";
     }

     public String[] set() {
    	 String all[]=new String[4];
    	 all[0]=CLIENT_ID;
    	 all[1]=MQTT_TOPIC;
    	 all[2]=MQTT_PORT;
    	 all[3]=MQTT_BROKER;
    	 return all;
     }
     public void end()throws Exception{
    	 if(mqttClient.isConnected()==true) {
    		 mqttClient.disconnect();
    	 }
    	 mqttClient.close();
     }
     public void online()throws Exception{
    	 mqttClient.connect();
    	 String topic_shd="SHD";
    	 cid=new File("cid.txt");
    	 	if(cid.exists()==true) {
    	 		MQTT_MESSAGE=CLIENT_ID;
    	 	}
    	 	else {
    	 		MQTT_MESSAGE=CLIENT_ID+","+device_name+","+MQTT_TOPIC+","+device_type;
    	 	}
    	 	MqttMessage message=new MqttMessage(MQTT_MESSAGE.getBytes());
    	 	mqttClient.publish(topic_shd,message);
    	 	//mqttClient.disconnect();
    	 	//mqttClient.close();
     }
     public static void main(String args[]){
    	 try {
	    	 Simulator simulate=new Simulator();
	    	 simulate.initialize();
	    	 String all[]=new String[4];
	    	 all=simulate.set();
	    	 Subscriber sub=new Subscriber(all[0]);
	    	 Thread subT=new Thread(sub);
	    	 HeartBeat heart=new HeartBeat(all[0],all[1],all[2],all[3]);
	    	 Thread heartT=new Thread(heart);
	    	 OfflineUpdater updater=new OfflineUpdater(all[0]);
	    	 Thread updateT=new Thread(updater);
	    	 heartT.start();
	    	 subT.start();
	    	 System.out.println("Enter Stop to stop the device!");
	    	 InputStreamReader isr=new InputStreamReader(System.in);
	    	 BufferedReader br=new BufferedReader(isr);
	    	 for(;;) {
	    		 if(br.readLine().equalsIgnoreCase("stop")==true) {
	    			 updateT.start();
	    			 heart.stop();
	    			 sub.stop();
	    			 break;
	    		 }
	    	 }
	    	 while(true) {
	    		boolean temp=heart.isAlive();
	    		boolean temp1=sub.isAlive();
	    		if(temp==false&&temp1==false) {
	    			break;
	    		}
	    	 }
	    	 simulate.end();
	    	 System.out.println("Stopped the device!");
    	 } catch(Exception e) {
    		 e.printStackTrace();
    	 }
     }

}
