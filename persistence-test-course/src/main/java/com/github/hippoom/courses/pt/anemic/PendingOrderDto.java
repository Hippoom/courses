package com.github.hippoom.courses.pt.anemic;

import java.util.Date;

public class PendingOrderDto {
	private String trackingId;
	private String deliveryAddressStreet1;
	private String deliveryAddressStreet2;
	private Date deliveryTime;

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public String getDeliveryAddressStreet1() {
		return deliveryAddressStreet1;
	}

	public void setDeliveryAddressStreet1(String deliveryStreet1) {
		this.deliveryAddressStreet1 = deliveryStreet1;
	}

	public String getDeliveryAddressStreet2() {
		return deliveryAddressStreet2;
	}

	public void setDeliveryAddressStreet2(String deliveryStreet2) {
		this.deliveryAddressStreet2 = deliveryStreet2;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
}
