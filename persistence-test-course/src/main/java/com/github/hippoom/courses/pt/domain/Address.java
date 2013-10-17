package com.github.hippoom.courses.pt.domain;

public class Address {
	private String street1;
	private String street2;
	private String postCode;

	public Address(String street1, String street2, String postCode) {
		this.street1 = street1;
		this.street2 = street2;
		this.postCode = postCode;
	}

	private Address() {
	}

	public String getPostCode() {
		return postCode;
	}

}
