package com.github.hippoom.courses.end2end;

public class ConfigurationsForTest {

	private String appServerHost;

	private String appServerPort;

	private String appServerContextPath;

	public String getAppServerAddress() {
		return "http://" + appServerHost + ":" + appServerPort
				+ appServerContextPath;
	}

	public void setAppServerHost(String appServerHost) {
		this.appServerHost = appServerHost;
	}

	public void setAppServerPort(String appServerPort) {
		this.appServerPort = appServerPort;
	}

	public void setAppServerContextPath(String appServerContextPath) {
		this.appServerContextPath = appServerContextPath;
	}
}
