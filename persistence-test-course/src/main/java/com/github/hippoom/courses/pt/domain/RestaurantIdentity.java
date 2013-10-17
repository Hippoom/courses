package com.github.hippoom.courses.pt.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RestaurantIdentity implements Serializable {

	private String value;

	public RestaurantIdentity(String value) {
		this.value = value;
	}

	public RestaurantIdentity(int value) {
		this(String.valueOf(value));
	}

	private RestaurantIdentity() {
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
