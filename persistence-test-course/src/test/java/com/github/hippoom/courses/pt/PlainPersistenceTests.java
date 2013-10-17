package com.github.hippoom.courses.pt;

import static java.util.Calendar.APRIL;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.hippoom.courses.pt.anemic.JdbcPendingOrderDao;
import com.github.hippoom.courses.pt.anemic.PendingOrderDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:persistence.xml",
		"classpath:test-dbdeploy.xml" })
public class PlainPersistenceTests {

	@Autowired
	private JdbcPendingOrderDao dao;

	@Test
	public void orderInserted() throws Throwable {
		PendingOrderDto order = new PendingOrderDto();
		order.setTrackingId("1");
		order.setDeliveryAddressStreet1("Kui Zhao road");
		order.setDeliveryAddressStreet2("North Che Zhan road");
		order.setDeliveryTime(april(1, 2012, "20:00"));

		dao.save(order);

		PendingOrderDto saved = dao.findBy(order.getTrackingId());

		assertThat(saved.getDeliveryAddressStreet1(),
				equalTo(order.getDeliveryAddressStreet1()));
		assertThat(saved.getDeliveryAddressStreet2(),
				equalTo(order.getDeliveryAddressStreet2()));
		assertThat(saved.getDeliveryTime(),
				comparesEqualTo(order.getDeliveryTime()));
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
