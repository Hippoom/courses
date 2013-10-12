package com.github.hippoom.courses.junit;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class Booleans {

	@Test
	public void assertTrue() throws Throwable {
		assertThat(false, is(true));
	}

	@Test
	public void assertFalse() throws Throwable {
		assertThat(true, is(false));
	}

	@Test
	public void assertNull() throws Throwable {
		assertThat(true, nullValue());
	}

}
