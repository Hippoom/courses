package com.github.hippoom.courses.pt.domain;

public class AddressFixture {

	private String postCode = "200012";

	public AddressFixture witPostCode(String value) {
		this.postCode = value;
		return this;
	}

	public Address build() {
		return new Address("North che zhan road", "Kui zhao road", postCode);
	}

}
