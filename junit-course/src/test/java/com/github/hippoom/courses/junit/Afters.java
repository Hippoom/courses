package com.github.hippoom.courses.junit;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Test;

public class Afters {
	private static int target = 2;

	@After
	public void clean() {
		// reset values or relinquishes resources
	}

	@Test
	public void test1() throws Throwable {
		target -= 1;
		assertThat(target, greaterThan(0));

	}

	@Test
	public void resetTargetForCleanFixture() throws Throwable {
		target -= 1;
		assertThat(target, greaterThan(0));
	}

	@Test
	public void resetTargetForCleanFixture2() throws Throwable {
		target -= 1;
		assertThat(target, greaterThan(0));
	}
}
