package com.github.hippoom.courses.junit;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class Collections {

	@Test
	public void assertEquals() throws Throwable {

		final List<String> actual = Arrays.asList("1", "2");
		final List<String> expect = Arrays.asList("2", "1");

		assertThat(actual, equalTo(expect));
	}

	@Test
	public void assertHasItem() throws Throwable {
		final List<String> actual = Arrays.asList("1", "2");

		assertThat(actual, hasItem("3"));
	}

	@Test
	public void assertHasItems() throws Throwable {
		final List<String> actual = Arrays.asList("1", "2", "4");

		assertThat(actual, hasItems("1", "3"));
	}

	@Test
	public void assertContains() throws Throwable {
		final List<String> actual = Arrays.asList("1", "2", "4");

		assertThat(actual, contains("1", "3"));
	}

	@Test
	public void assertHasKey() throws Throwable {
		final Map<String, String> actual = new HashMap<String, String>();

		assertThat(actual, hasKey("1"));
	}

	@Test
	public void assertHasValue() throws Throwable {
		final Map<String, String> actual = new HashMap<String, String>();

		assertThat(actual, hasValue("1"));
	}

	@Test
	public void assertHasEntry() throws Throwable {
		final Map<String, String> actual = new HashMap<String, String>();

		assertThat(actual, hasEntry("1", "2"));
	}

	@Test
	public void assertNull() throws Throwable {
		final List<String> actual = Arrays.asList("1", "2");
		assertThat(actual, nullValue());
	}

}
