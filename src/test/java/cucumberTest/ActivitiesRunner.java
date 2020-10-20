package cucumberTest;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "C:\\Users\\DebashisPaul\\eclipse-workspace\\CucumberProject\\Features",
		glue = {"stepDefinitions"},
		tags = "@crm_4",
		monochrome = true,
		plugin = {"html: test-reports.html"},
		publish = true
		)

public class ActivitiesRunner {}

