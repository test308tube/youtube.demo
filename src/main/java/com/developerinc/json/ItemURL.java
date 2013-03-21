package com.developerinc.json;

final public class ItemURL {
	private final String name;
	private final String server;
	private final int port;
	private final String username;
	private final String password;
	private final String servicename;
	private final String drivertype;
	private final String protocol;
	private final String type;
	
	
	ItemURL(){
		protocol = getProtocol();
		name = getName();
		server = getServer();
		port = getPort();
		username = getUsername();
		password = getPassword();
		servicename = getServicename();
		drivertype = getDrivertype();
		type = getType();
		
	}
	public final String getName() {
		return name;
	}
	
	//public final void setName(String name) {
	//	this.name = name;
	//}
	public String getType() {
		return type;
	}
	public final String getServer() {
		return server;
	}
	//public final void setServer(String server) {
	//	this.server = server;
	//}
	public final int getPort() {
		return port;
	}
	//public final void setPort(int port) {
	//	this.port = port;
	//}
	public final String getUsername() {
		return username;
	}
	//public final void setUsername(String username) {
	//	this.username = username;
	//}
	public final String getPassword() {
		return password;
	}
	//public final void setPassword(String password) {
	//	this.password = password;
	//}
	public final String getServicename() {
		return servicename;
	}
	//public final void setServicename(String servicename) {
	//	this.servicename = servicename;
	//}
	public final String getDrivertype() {
		return drivertype;
	}
	//public final void setDrivertype(String drivertype) {
	//	this.drivertype = drivertype;
	//}
	public final String getProtocol() {
		return protocol;
	}
	
	
	
}
