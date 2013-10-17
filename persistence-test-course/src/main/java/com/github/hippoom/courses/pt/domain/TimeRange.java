package com.github.hippoom.courses.pt.domain;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * This is a value object.
 * 
 */

public class TimeRange {
	private String day;
	private String start;
	private String end;

	public TimeRange(String day, String start, String end) {
		this.day = day;
		this.start = start;
		this.end = end;
	}

	public boolean isAvailableFor(Date deliveryTime) {
		boolean dayMatchedWith = dayMatchedWith(deliveryTime);
		boolean timeMatched = timeMatched(deliveryTime);

		return dayMatchedWith && timeMatched;
	}

	private boolean timeMatched(Date deliveryTime) {

		return derive(deliveryTime, start).compareTo(deliveryTime) <= 0
				&& derive(deliveryTime, end).compareTo(deliveryTime) >= 0;
	}

	private boolean dayMatchedWith(Date deliveryTime) {
		return this.day.equals(getDayOfWeek(with(deliveryTime)));
	}

	private Calendar with(Date deliveryTime) {
		Calendar c = Calendar.getInstance();
		c.setTime(deliveryTime);
		return c;
	}

	private Date derive(Date deliveryTime, String hhmm) {
		Calendar c = Calendar.getInstance();
		c.setTime(deliveryTime);
		c.set(HOUR_OF_DAY, Integer.valueOf(hhmm.split(":")[0]));
		c.set(MINUTE, Integer.valueOf(hhmm.split(":")[1]));
		return c.getTime();
	}

	private String getDayOfWeek(Calendar c) {
		return c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US);
	}
}
