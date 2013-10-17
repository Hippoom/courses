package com.github.hippoom.courses.pt.domain;

import java.util.ArrayList;
import java.util.List;

public class RestaurantFixture {

	private RestaurantIdentity restaurantIdentity = new RestaurantIdentity(1);
	private List<String> serviceAreas = new ArrayList<String>();
	private List<TimeRange> timeRanges = new ArrayList<TimeRange>();

	public RestaurantFixture() {
	}

	public RestaurantFixture(RestaurantIdentity restaurantIdentity) {
		this.restaurantIdentity = restaurantIdentity;
	}

	public RestaurantFixture serveFor(String postCode) {
		serviceAreas.add(postCode);
		return this;
	}

	public RestaurantFixture serveFor(String dayOfWeek, String start, String end) {
		this.timeRanges.add(new TimeRange(dayOfWeek, start, end));
		return this;
	}

	public Restaurant build() {
		Restaurant restaurant = new Restaurant(restaurantIdentity,
				"Lan zhou noodle");
		restaurant.updateServiceAreas(serviceAreas);
		restaurant.updateServiceTimeRanges(timeRanges);
		return restaurant;
	}

}
