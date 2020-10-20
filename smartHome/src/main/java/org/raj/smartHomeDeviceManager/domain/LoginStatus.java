package org.raj.smartHomeDeviceManager.domain;

public class LoginStatus {
		private boolean status;
		private String message;
		
		public LoginStatus(boolean status,String message) {
			this.status=status;
			this.message=message;
		}
		
		public boolean getStatus() {
			return status;
		}
		
		public void setStatus(boolean status) {
			this.status=status;
		}
		
		public String getMessage() {
			return message;
		}
		
		public void setMessage(String message) {
			this.message=message;
		}
}
