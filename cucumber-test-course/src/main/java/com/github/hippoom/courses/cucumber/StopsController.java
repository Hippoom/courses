package com.github.hippoom.courses.cucumber;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StopsController {
	@Autowired
	private AirAvailService airAvailService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping(value = "/stops/{flightNumber}/{departureDate}", method = RequestMethod.GET)
	@ResponseBody
	public RestResponse<Stops> display(
			@PathVariable("flightNumber") String flightNumber,
			@PathVariable("departureDate") Date departureDate) {
		final Stops stops = airAvailService
				.stopsOf(flightNumber, departureDate);
		return new RestResponse<Stops>(stops);

	}

}
