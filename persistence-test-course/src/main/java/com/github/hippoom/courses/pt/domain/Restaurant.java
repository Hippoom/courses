package com.github.hippoom.courses.pt.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "t_f2g_restaurant", uniqueConstraints = @UniqueConstraint(columnNames = { "NAME" }))
public class Restaurant {

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "value", column = @Column(name = "ID")) })
	private RestaurantIdentity id;

	@Column(name = "name")
	private String name;

	@ElementCollection
	@CollectionTable(name = "t_f2g_restaurant_srv_area", joinColumns = @JoinColumn(name = "RESTAURANT_ID"))
	@Column(name = "POST_CODE")
	private List<String> serviceAreas = new ArrayList<String>();

	@ElementCollection
	@CollectionTable(name = "t_f2g_restaurant_srv_time", joinColumns = @JoinColumn(name = "RESTAURANT_ID"))
	@AttributeOverrides({
			@AttributeOverride(name = "day", column = @Column(name = "DAY")),
			@AttributeOverride(name = "start", column = @Column(name = "TIME_RANGE_START")),
			@AttributeOverride(name = "end", column = @Column(name = "TIME_RANGE_END")) })
	private List<TimeRange> serviceTimeRanges = new ArrayList<TimeRange>();

	public Restaurant(RestaurantIdentity id, String name) {
		this.id = id;
		this.name = name;
	}

	public RestaurantIdentity getId() {
		return id;
	}

	public void updateServiceAreas(List<String> serviceAreas) {
		this.serviceAreas = serviceAreas;
	}

	public void updateServiceTimeRanges(List<TimeRange> timeRanges) {
		this.serviceTimeRanges = timeRanges;
	}

	public boolean isAvalableFor(Address deliveryAddress, Date deliveryTime) {
		if (isAvailableFor(deliveryAddress) && isAvailableFor(deliveryTime)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isAvailableFor(Date deliveryTime) {
		for (TimeRange range : this.serviceTimeRanges) {
			if (range.isAvailableFor(deliveryTime)) {
				return true;
			}
		}
		return false;
	}

	private boolean isAvailableFor(Address deliveryAddress) {
		return this.serviceAreas.contains(deliveryAddress.getPostCode());
	}

}
