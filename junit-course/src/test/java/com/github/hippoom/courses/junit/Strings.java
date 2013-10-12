package com.github.hippoom.courses.junit;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class Strings {

	@Test
	public void assertEquals() throws Throwable {
		// Useful when compare with exact value
		assertThat("", equalTo("Hi there"));
	}

	@Test
	public void assertNullString() throws Throwable {
		// Useful when compare with exact value
		assertThat("", nullValue());
	}

	@Test
	public void assertContains() throws Throwable {
		// Useful when resolving a large amount of text
		assertThat("", containsString("i t"));
	}

}
