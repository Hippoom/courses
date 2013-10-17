package com.github.hippoom.courses.pt.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PendingOrderFixture {

	private TrackingId trackingId = TrackingId.of("1");
	private Address deliveryAddress = defaultDeliveryAddressFixture().build();
	private Date deliveryTime = new Date();

	public PendingOrderFixture with(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
		return this;
	}

	public PendingOrderFixture at(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
		return this;
	}

	public PendingOrder build() {
		return new PendingOrder(trackingId, deliveryAddress, deliveryTime);
	}

	public static AddressFixture defaultDeliveryAddressFixture() {
		return new AddressFixture();
	}

	public static String rightAfter(int number, int unit) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(after(number, unit));
	}

	public static Date after(int number, int unit) {
		Calendar c = Calendar.getInstance();
		c.add(unit, number);
		return c.getTime();
	}

}
