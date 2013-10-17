package com.github.hippoom.courses.pt.domain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class JpaPendingOrderRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public PendingOrder findBy(TrackingId trackingId) {
		return entityManager.find(PendingOrder.class, trackingId);
	}

	@Transactional
	public void store(PendingOrder copy) {
		entityManager.persist(copy);
	}

}
