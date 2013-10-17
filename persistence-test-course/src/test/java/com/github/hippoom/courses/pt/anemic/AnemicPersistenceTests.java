package com.github.hippoom.courses.pt.anemic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:persistence.xml",
		"classpath:test-dbdeploy.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class AnemicPersistenceTests {

	@Autowired
	private JdbcPendingOrderDao dao;

	@DatabaseSetup(value = "classpath:data/anemic_pending_order_fixture.xml")
	@ExpectedDatabase(value = "classpath:data/anemic_pending_order_expect.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	@Test
	public void orderInserted() throws Throwable {
		PendingOrderDto prototype = dao.findBy("2");

		PendingOrderDto copy = copy(prototype, "3");

		dao.save(copy);
	}

	@DatabaseSetup(value = "classpath:data/anemic_pending_order_update_fixture.xml")
	@ExpectedDatabase(value = "classpath:data/anemic_pending_order_update_expect.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	@Test
	public void orderUpdated() throws Throwable {
		PendingOrderDto prototype = dao.findBy("4");
		PendingOrderDto copy = dao.findBy("5");

		copy(prototype, copy);

		dao.update(copy);
	}

	private void copy(PendingOrderDto prototype, PendingOrderDto copy) {
		final String copyId = copy.getTrackingId();

		ModelMapper mapper = new ModelMapper();
		mapper.createTypeMap(PendingOrderDto.class, PendingOrderDto.class);//otherwise mapper does not work
		mapper.map(prototype, copy);
		copy.setTrackingId(copyId);
	}

	private PendingOrderDto copy(PendingOrderDto prototype, String copyId) {
		ModelMapper mapper = new ModelMapper();
		PendingOrderDto copy = mapper.map(prototype, PendingOrderDto.class);
		copy.setTrackingId(copyId);
		return copy;
	}
}
