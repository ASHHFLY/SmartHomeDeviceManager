package org.raj.smartHomeDeviceManager.domain;

public class UserLogin {
		private String username;
		private String password;
		public UserLogin(String username,String password) {
			this.username=username;
			this.password=password;
		}
		
		public String getUsername() {
			return username;
		}
		
		public String getPassword() {
			return password;
		}
		
		public void setUsername(String username) {
			this.username=username;
		}
		
		public void setPassword(String password) {
			this.password=password;
		}
}
