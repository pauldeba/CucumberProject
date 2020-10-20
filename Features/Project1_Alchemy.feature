@Alchemy_1
Feature: Alchemy Project

Background:
 Given Navigate to Alchemy Page
 Then Login into Alchemy with "root" and "pa$$w0rd"
 
Scenario: Create a new user
	Then Locate the left hand menu and click on User
	Then Locate the Add New button and click it
	Then Fill deatils and click Add New User button
	And Verify the user has created
	And Close the Browser
	

	