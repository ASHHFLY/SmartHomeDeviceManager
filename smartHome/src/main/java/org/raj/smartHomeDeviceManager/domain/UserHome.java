package org.raj.smartHomeDeviceManager.domain;

public class UserHome {
	private final long slno;
	private final String name;
	private final String type;
	private final String status;
	private final String username;
	private final String clientid;
	
	public UserHome(long slno,String name,String type,String status,String username,String clientid) {
		this.slno=slno;
		this.name=name;
		this.type=type;
		this.status=status;
		this.username=username;
		this.clientid=clientid;
	}
	
	public long getSlno() {
		return slno;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public String getStatus() {
		return status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		username=username;
	}
	
	public String getClientid() {
		return clientid;
	}
}
