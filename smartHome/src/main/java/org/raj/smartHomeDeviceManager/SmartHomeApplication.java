
package org.raj.smartHomeDeviceManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Collections;

@SpringBootApplication
public class SmartHomeApplication {

	public static void main(String[] args) {
		SpringApplication app=new SpringApplication(SmartHomeApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port","8085"));
		app.run(args);
	}
}
