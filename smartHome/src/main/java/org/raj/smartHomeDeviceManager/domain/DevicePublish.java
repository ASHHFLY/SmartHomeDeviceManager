package org.raj.smartHomeDeviceManager.domain;

public class DevicePublish {
		private String clientid;
		private String message;
		
		public String getClientid() {
			return clientid;
		}
		
		public String getMessage() {
			return message;
		}
		
		public void setClientid(String clientid) {
			this.clientid=clientid;
		}
		
		public void setMessage(String message) {
			this.message=message;
		}
}
