package com.github.hippoom.courses.pt;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:persistence.xml",
		"classpath:test-dbdeploy.xml" })
public class DbdeployTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void schemaImportedSmokeTest() throws Throwable {
		// see outputs in log
		assertThat(
				jdbcTemplate.queryForObject(
						"select count(0) from t_f2g_pending_order",
						Number.class).intValue(), equalTo(0));

		assertThat(
				jdbcTemplate.queryForObject(
						"select seq_f2g_pending_order.nextval from dual",
						Number.class).intValue(), equalTo(1));
	}
}
