package com.github.hippoom.courses.pt.domain;

import static com.github.hippoom.courses.pt.domain.TrackingId.of;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

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
public class DomainPersistenceTests {

	@Autowired
	private JpaPendingOrderRepository pendingOrderRepository;
	@Autowired
	private PlatformTransactionManager transactionManager;

	@DatabaseSetup(value = "classpath:data/pending_order_fixture.xml")
	@ExpectedDatabase(value = "classpath:data/pending_order_expect.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	@Test
	public void orderSaved() throws Throwable {
		PendingOrder prototype = pendingOrderRepository.findBy(of("2"));

		PendingOrder copy = new PendingOrder(of("3"),
				prototype.getDeliveryAddress(), prototype.getDeliveryTime());

		pendingOrderRepository.store(copy);
	}

	@DatabaseSetup(value = "classpath:data/pending_order_update_fixture.xml")
	@ExpectedDatabase(value = "classpath:data/pending_order_update_expect.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	@Test
	public void orderUpdated() throws Throwable {// use transaction to enable
													// lazy loading
		new TransactionTemplate(transactionManager)
				.execute(new TransactionCallback<PendingOrder>() {
					@Override
					public PendingOrder doInTransaction(TransactionStatus status) {
						PendingOrder prototype = pendingOrderRepository
								.findBy(of("4"));
						PendingOrder copy = pendingOrderRepository
								.findBy(of("5"));

						copy(prototype, copy);

						pendingOrderRepository.store(copy);

						return null;
					}
				});
	}

	private PendingOrder copy(PendingOrder prototype, PendingOrder copy) {

		ModelMapper mapper = new ModelMapper();
		mapper.createTypeMap(PendingOrder.class, PendingOrder.class);// otherwise
																		// mapper
																		// does
																		// not
																		// work
		mapper.addMappings(new PendingOrderMap());
		mapper.map(prototype, copy);
		return copy;
	}

	public static class PendingOrderMap extends
			PropertyMap<PendingOrder, PendingOrder> {
		@Override
		protected void configure() {
			skip().setTrackingId(null);
		}
	}

}
