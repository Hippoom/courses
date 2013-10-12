package com.github.hippoom.courses.junit;

import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Throwables {
	// @Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void assertThrown() throws Throwable {
		
		//thrown.expect(type);
		//thrown.expectMessage(equalTo());
		//thrown.expectCause(expectedCause)
		
		throw new RuntimeException("Should be caught",
				new IllegalStateException("cause"));

	}
}
