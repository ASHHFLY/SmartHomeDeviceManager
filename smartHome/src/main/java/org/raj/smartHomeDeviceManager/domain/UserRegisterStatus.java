package org.raj.smartHomeDeviceManager.domain;

public class UserRegisterStatus {
		private boolean status;
		private String errorMsg;
		public UserRegisterStatus(boolean status,String errorMsg) {
			this.status=status;
			this.errorMsg=errorMsg;
		}
		public Boolean getStatus() {
			return status;
		}
		
		public String getErrorMsg() {
			return errorMsg;
		}
		
		public void setStatus(boolean status) {
			this.status=status;
		}
		
		public void setErrorMsg(String errorMsg) {
			this.errorMsg=errorMsg;
		}
}
