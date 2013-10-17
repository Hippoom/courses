package com.github.hippoom.courses.pt.domain;

import static java.util.Calendar.APRIL;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PendingOrderUnitTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void updatesRestaurantIdentifierWhenDeliveryAddressAndDeliveryDateSatisfied()
			throws Throwable {
		final PendingOrder order = order().build();

		final Restaurant restaurant = restaurant().build();

		order.update(restaurant);

		assertThat(order.getRestaurantIdentity(), equalTo(restaurant.getId()));
	}

	@Test
	public void throwsWhenDeliveryTimeUnsatisfied() throws Throwable {
		final PendingOrder order = order().at(april(1, 2012, "12:20")).build();

		final Restaurant restaurant = deliveryAddressSatisfied()
				.serveFor("Sun", "10:00", "12:00")
				.serveFor("Mon", "10:00", "20:00").build();

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(equalTo("The restaurant[1] is not available for order[1]."));

		order.update(restaurant);

		assertThat(order.getRestaurantIdentity(), equalTo(restaurant.getId()));
	}

	@Test
	public void throwsWhenDeliveryAddressUnsatisfied() throws Throwable {
		final PendingOrder order = order().with(postCode("200093")).build();

		final Restaurant restaurant = deliveryTimeSatisfied().serveFor("20001")
				.build();

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(equalTo("The restaurant[1] is not available for order[1]."));

		order.update(restaurant);

		assertThat(order.getRestaurantIdentity(), equalTo(restaurant.getId()));
	}

	private Address postCode(String string) {
		return new AddressFixture().witPostCode(string).build();
	}

	private RestaurantFixture restaurant() {
		return deliveryTimeSatisfied().serveFor("200093").serveFor("200094");
	}

	private PendingOrderFixture order() {
		return new PendingOrderFixture().with(postCode("200093")).at(
				april(1, 2012, "12:20"));
	}

	private RestaurantFixture deliveryTimeSatisfied() {
		return new RestaurantFixture().serveFor("Sat", "10:00", "20:00")
				.serveFor("Sun", "10:00", "20:00");
	}

	private RestaurantFixture deliveryAddressSatisfied() {
		return new RestaurantFixture().serveFor("200093");
	}

	private Date april(int day, int year, String time) {
		Calendar c = Calendar.getInstance();
		c.set(YEAR, year);
		c.set(MONTH, APRIL);
		c.set(DAY_OF_MONTH, day);
		c.set(HOUR_OF_DAY, Integer.valueOf(time.split(":")[0]));
		c.set(MINUTE, Integer.valueOf(time.split(":")[1]));
		c.set(SECOND, 0);
		c.set(MILLISECOND, 0);
		return c.getTime();
	}
}
