package com.github.hippoom.courses.cucumber.pseudoe2e;

import org.junit.runner.*;

import cucumber.api.junit.*;

@RunWith(Cucumber.class)
@Cucumber.Options(features = "classpath:", format = { "pretty",
		"html:target/cucumber" }, glue = { "com.github.hippoom.courses.cucumber.pseudoe2e" })
public class PseudoEnd2EndAcceptanceTests {

}