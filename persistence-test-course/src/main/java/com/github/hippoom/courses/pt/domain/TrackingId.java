package com.github.hippoom.courses.pt.domain;

import java.io.Serializable;

/**
 * <pre>
 * Implements {@link Serializable} for using @EmbeddedId.
 * An alternative solution is using primitive type for @Id and
 * provide a trackingId() query method returning {@link TrackingId} for domain model
 * </pre>
 */
@SuppressWarnings("serial")
public class TrackingId implements Serializable {

	private String value;

	public static TrackingId of(String value) {
		return new TrackingId(value);
	}

	private TrackingId(String value) {
		this.value = value;
	}

	/**
	 * for frameworks only
	 */
	public TrackingId() {
	}

	@Override
	public String toString() {
		return value;
	}
}
