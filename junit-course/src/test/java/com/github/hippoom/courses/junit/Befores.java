package com.github.hippoom.courses.junit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class Befores {
	private String hello;

	@Before
	public void prepare() {
		// remove duplication
	}

	@Test
	public void testHelloWorld() throws Throwable {

		assertThat(hello + " world", equalTo("hello world"));
	}

	@Test
	public void testHelloThere() throws Throwable {

		assertThat(hello + " there", equalTo("hello there"));
	}

	@Test
	public void testHelloJohn() throws Throwable {

		assertThat(hello + " John", equalTo("hello John"));
	}
}
