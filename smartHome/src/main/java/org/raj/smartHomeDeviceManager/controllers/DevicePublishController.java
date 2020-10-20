package org.raj.smartHomeDeviceManager.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.raj.smartHomeDeviceManager.domain.DevicePublish;

@RestController
public class DevicePublishController {
	@CrossOrigin(origins = "*")
	@PostMapping("/publish")
	public boolean publisher(@RequestBody DevicePublish publish)throws Exception {
		boolean status=true;
		MqttClient obj=new MqttClient("tcp://localhost:1883","test");
		obj.connect();
		MqttMessage message=new MqttMessage(publish.getMessage().getBytes());
		obj.publish("SHD/SUB/"+publish.getClientid(),message);
		obj.disconnect();
		obj.close();
		return status;
	}
}
