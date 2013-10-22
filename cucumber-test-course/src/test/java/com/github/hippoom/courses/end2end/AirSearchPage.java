package com.github.hippoom.courses.end2end;

import static java.util.Calendar.SECOND;

import java.util.*;

import org.openqa.selenium.*;

public class AirSearchPage {
	private static final String DEFAULT_DEPARTURE_DATE = "2019-04-01";

	private ConfigurationsForTest configurations;

	private WebDriver driver;

	public AirSearchPage(ConfigurationsForTest configurations, WebDriver driver) {
		this.configurations = configurations;
		this.driver = driver;
	}

	public void open() {
		driver.get(configurations.getAppServerAddress() + "/booking/list.htm");
	}

	public void searchOneway(String departureLocationCode,
			String arrivalLocationCode) {
		WebElement departureLocation = driver.findElement(By
				.id("departureCityName"));
		WebElement arrivalLocation = driver.findElement(By
				.id("arrivalCityName"));
		WebElement departureDate = driver.findElement(By.id("depDatepicker"));

		WebElement search = driver.findElement(By.id("searchButton"));

		input(departureLocationCode, departureLocation);
		input(arrivalLocationCode, arrivalLocation);
		input(DEFAULT_DEPARTURE_DATE, departureDate);
		search.click();
		until(2, SECOND);
	}

	public Map<String, Integer> findStops() {
		Map<String, Integer> result = new HashMap<String, Integer>();
		List<WebElement> flightSegments = findFlightSegments();

		for (WebElement flightSegment : flightSegments) {
			if (thereAreStopsIn(flightSegment)) {
				WebElement flightNumber = findFlightNumberOf(flightSegment);
				WebElement stopNumber = findStopQuantity(flightSegment);
				result.put(flightNumber.getText(),
						Integer.valueOf(stopNumber.getText()));
			}
		}
		return result;
	}

	private boolean thereAreStopsIn(WebElement flightSegment) {
		return flightSegment.getText().contains("经停次数");
	}

	private WebElement findStopQuantity(WebElement flightSegment) {
		WebElement stopNumber = flightSegment.findElement(By
				.xpath(".//a[@test='stopQuantity']"));
		return stopNumber;
	}

	private WebElement findFlightNumberOf(WebElement flightSegment) {
		WebElement flightNumber = flightSegment.findElement(By
				.xpath(".//div[@test='flightNumber']"));
		return flightNumber;
	}

	public String displayStops(String flightNo) {
		List<WebElement> flightSegments = findFlightSegments();
		for (WebElement flightSegment : flightSegments) {
			WebElement flightNumber = findFlightNumberOf(flightSegment);
			if (flightNumber.getText().equals(flightNo)) {
				WebElement stopNumber = findStopQuantity(flightSegment);
				stopNumber.click();
				return findStopsDivId(flightSegment);
			}
		}
		return "";
	}

	private String findStopsDivId(WebElement flightSegment) {
		return flightSegment.findElement(By.xpath(".//div[@test='stops']"))
				.getAttribute("id");
	}

	private List<WebElement> findFlightSegments() {
		List<WebElement> flightSegments = driver.findElements(By
				.xpath("//table[@test='flightSegment']"));
		return flightSegments;
	}

	public String stopsInfo(String divId) {
		until(2, SECOND);
		return driver.findElement(By.id(divId)).getText();
	}

	protected void until(int amount, int unit) {
		if (Calendar.SECOND == unit) {
			try {
				Thread.sleep(amount * 1000);
			} catch (InterruptedException e) {
				throw new UnsupportedOperationException();
			}
		} else {
			throw new UnsupportedOperationException();
		}
	}

	protected void input(String penaltyAmount, WebElement penalty) {
		penalty.clear();
		penalty.sendKeys(penaltyAmount);
	}
}
