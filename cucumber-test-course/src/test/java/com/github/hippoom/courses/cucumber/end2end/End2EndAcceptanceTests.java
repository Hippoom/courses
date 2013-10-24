package com.github.hippoom.courses.cucumber.end2end;

import org.junit.runner.*;

import cucumber.api.junit.*;

@RunWith(Cucumber.class)
@Cucumber.Options(features = "classpath:", format = { "pretty",
		"html:target/cucumber" })

public class End2EndAcceptanceTests {

}