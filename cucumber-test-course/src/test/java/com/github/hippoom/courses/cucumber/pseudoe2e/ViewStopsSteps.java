package com.github.hippoom.courses.cucumber.pseudoe2e;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/booking-servlet.xml",
		"classpath:root.xml" })
@WebAppConfiguration
public class ViewStopsSteps {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Given("客服已查询单程航班")
	public void operatorSearchesOneway() {
		mockMvc = webAppContextSetup(this.wac).build();
	}

	@And("显示航班的经停次数")
	public void thereShouldBeStopsQuantitiesIfExsists() {

	}

	@When("客服查看经停信息")
	public void operationsViewsStops() {

	}

	@Then("显示航班的经停信息")
	public void thereShouldBeAReportDisplayingStops() throws Exception {

		mockMvc.perform(
				get("/stops/MU5678/2019-04-01").contentType(
						MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("statusCode").value("STATUS_CODE_SUCCESS"));
	}

}
