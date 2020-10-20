@crm_extra 
Feature:

Background:
	Given Open the browser to the Alchemy CRM site and login with "admin" and "pa$$w0rd"
	
Scenario Outline:
	Then Navigate to Activities -> Meetings -> Schedule a Meeting
	Then Enter the details of the meeting
	And Search for member "<AttendiesName>" and add them to the meeting
	#Then The names to be searched will be written in the Feature file in a Examples table
	#Then Click Save.
	#Then Navigate to View Meetings page and confirm creation of the meeting.
	#And Close the SuiteCRM browser
Examples:
|	AttendiesName	|
|	Administrator	|
#|	Chris Olliver	|
#|	Jim Brennan	|