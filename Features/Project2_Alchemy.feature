@Alchemy_2
Feature: Searching for jobs using XPath

Background:
	Given Open Browser with Alchemy Jobs Site

Scenario: Searching for jobs using XPath
	Given Navigate to Job Search Page
	Then Type in keywords to search for jobs and change the Job type
	Then Find the filter using XPath and filter job type to show only Full Time jobs
	And Find a job listing using XPath and it to see job details
	And Find the title of the job listing using XPath and print it to the console
	And Find and Click on the Apply for job button
	And Close the site

	

