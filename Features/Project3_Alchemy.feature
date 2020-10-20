
Feature:

Background:
	Given Open Browser with Alchemy Jobs Site
@Alchemy_3	
Scenario: Posting a job using parameterization
	Given Go to Post a Job page
	#Then Read job information from the Feature file table and fill in the details
	Then Enter "Tester" as JobTitle and Description as "Automation Tester with Selenium" and company as "IBM"
	Then Click submit and Go to Jobs Page
	And Confirm job listing is shown on page as "Tester"
	And Close the site

@Alchemy_4	
Scenario Outline: 
	Given Go to Post a Job page
	#Then Read job information from the Feature file table and fill in the details
	Then Enter "<JobTitle>" as JobTitle and Description as "<Descriptions>" and company as "<Company>"
	Then Click submit and Go to Jobs Page
	And Confirm job listing is shown on page as "<JobTitle>"
	And Close the site
	
Examples:
|	JobTitle	|	Descriptions			|	Company	|
|	Tester		| Auotomation tester with selenium 	|	IBM	|
|	Developer	| Java Developer		|	IBM	|
	
