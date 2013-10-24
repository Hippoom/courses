package com.github.hippoom.courses.cucumber.end2end;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.github.hippoom.courses.cucumber.MU5678Constants;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration("classpath:cucumber.xml")
public class ViewStopsSteps {

	private static final Integer PREDIFINED_STOP_QUANTITY = MU5678Constants.STOP_QUANTITY;

	private static final String FLIGHT_NUMBER = MU5678Constants.FLIGHT_NUMBER;

	private WebDriver driver = new ChromeDriver(chromeSet());

	@Autowired
	private ConfigurationsForTest configurations;

	private AirSearchPage airSearchPage = new AirSearchPage(configurations,
			driver);

	private String stops;

	@Given("客服已查询单程航班")
	public void operatorSearchesOneway() {
		airSearchPage.open();
		airSearchPage.searchOneway("上海", "北京");
	}

	private DesiredCapabilities chromeSet() {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("chrome.binary",
				"C:/Program Files/Google/Chrome/Application");
		return capabilities;
	}

	@And("显示航班的经停次数")
	public void thereShouldBeStopsQuantitiesIfExsists() {
		Map<String, Integer> stops = airSearchPage.findStops();
		assertThat(stops.get(FLIGHT_NUMBER), equalTo(PREDIFINED_STOP_QUANTITY));
	}

	@When("客服查看经停信息")
	public void operationsViewsStops() {
		stops = airSearchPage.displayStops(FLIGHT_NUMBER);
	}

	@Then("显示航班的经停信息")
	public void thereShouldBeAReportDisplayingStops() {
		String info = airSearchPage.stopsInfo(stops);
		assertThat(info, containsString("上海虹桥机场"));
		assertThat(info, containsString("首都国际机场"));
		assertThat(info, containsString("天津机场"));
	}

}
