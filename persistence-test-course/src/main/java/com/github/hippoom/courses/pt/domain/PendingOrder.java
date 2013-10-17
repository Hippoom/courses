package com.github.hippoom.courses.pt.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_f2g_pending_order")
public class PendingOrder {
	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "value", column = @Column(name = "TRACKING_ID")) })
	private TrackingId trackingId;
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "street1", column = @Column(name = "DELIVERY_ADDRESS_STREET1")),
			@AttributeOverride(name = "street2", column = @Column(name = "DELIVERY_ADDRESS_STREET2")),
			@AttributeOverride(name = "postCode", column = @Column(name = "post_code")) })
	private Address deliveryAddress;
	@Column(name = "delivery_Time")
	private Date deliveryTime;
	@Embedded
	@AttributeOverride(name = "value", column = @Column(name = "RESTAURANT_ID"))
	private RestaurantIdentity restaurantIdentity;

	/**
	 * This field is used to detect whether this {@link PendingOrder} needs save
	 * or update.
	 * 
	 * The default value is set to true(when this {@link PendingOrder} is
	 * retrieved from {@link PendingOrderRepository}).
	 * 
	 * When a {@link PendingOrder} is created via
	 * {@link PendingOrder#PendingOrder(TrackingId, Address, Date)}, this field
	 * is set to false, so
	 */

	public PendingOrder(TrackingId trackingId, Address deliveryAddress,
			Date deliveryTime) {
		this.trackingId = trackingId;
		this.deliveryAddress = deliveryAddress;
		this.deliveryTime = deliveryTime;
	}

	public void update(Restaurant restaurant) {
		if (restaurant.isAvalableFor(this.deliveryAddress, this.deliveryTime)) {
			this.restaurantIdentity = restaurant.getId();
		} else {
			throw new IllegalArgumentException("The restaurant["
					+ restaurant.getId() + "] is not available for order["
					+ this.trackingId + "].");
		}
	}

	public TrackingId getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(TrackingId trackingId) {
		// TODO Auto-generated method stub

	}

	private PendingOrder() {
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public void setRestaurantIdentity(RestaurantIdentity restaurantIdentity) {
		this.restaurantIdentity = restaurantIdentity;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public RestaurantIdentity getRestaurantIdentity() {
		return restaurantIdentity;
	}

}
