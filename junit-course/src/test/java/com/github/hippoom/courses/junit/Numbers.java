package com.github.hippoom.courses.junit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class Numbers {

	@Test
	public void assertEquals() throws Throwable {

		assertThat(10, equalTo(9));
	}

	@Test
	public void assertGreaterThan() throws Throwable {

		assertThat(10, greaterThan(12));

		// what about greater equal
	}

	@Test
	public void assertLessThan() throws Throwable {

		assertThat(10, lessThan(9));

		// what about less equal
	}

	@Test
	public void comparesBigDecimal() throws Throwable {

		final BigDecimal actual = new BigDecimal("1.00");
		final BigDecimal expect = new BigDecimal("1");
		assertThat(actual, equalTo(expect));

		// try comparesEqualTo
	}

	@Test
	public void assertNull() throws Throwable {
		final Integer number = 10;
		assertThat(number, nullValue());
	}

}
