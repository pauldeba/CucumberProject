Feature:

Background:
	Given Open the browser to the Alchemy CRM site and login with "admin" and "pa$$w0rd"
@crm_1	
Scenario: Counting Dashlets
	Then Count the number of Dashlets on the homepage and print
	And Print the title of each Dashlet into the console
	And Close the SuiteCRM browser

@crm_2	
Scenario: Create leads using parameterization
	Then Navigate to Sales -> Leads -> Create Lead
	Then Fill deatils with "Chandu" as FirstName "Chetri" as LastName "SD" as Department "Testing" as AccountName 
	And Click Save to Finish and Navigate to the View Leads page to see results
	And Close the SuiteCRM browser
	#Then Fill in the necessary details to create lead accounts using the values passed from the Feature file
	#Then Click Save to finish.
	#Then Navigate to the View Leads page to see results.

@crm_3 
Scenario Outline:
	Then Navigate to Activities -> Meetings -> Schedule a Meeting
	Then Enter the details of the meeting
	And Search for member "<Attendies1Name>" and "<Attendies2Name>" and add them to the meeting
	And Click Save and Navigate to View Meetings page and confirm creation of the meeting
	And Close the SuiteCRM browser
	#Then The names to be searched will be written in the Feature file in a Examples table
	#Then Click Save.
	#Then Navigate to View Meetings page and confirm creation of the meeting.
	#And Close the SuiteCRM browser
Examples:
|	Attendies1Name	|	Attendies2Name	|
|	Chris Olliver		|	Jim Brennan			|
#|	Max Jensen	|	Sally Bronsen	|

@crm_4
Scenario Outline: To use an Examples table to add products.
Then Navigate to All -> Products-> Create Product
Then Enter "<ProductName>" as ProductName, "<PartNumber>" as PartNumber, "<Price>" with Price and "<Description>"
And Click Save and Go to the View Products page to see all products listed
And Close the SuiteCRM browser
#Then Retrieve information about the product from an Examples table in the Feature file.
#And Using the data from the table, enter the details of the product.
#And Click Save.
#And Go to the “View Products” page to see all products listed.
#And Close the SuiteCRM browser
Examples:
|	ProductName	| PartNumber	| Price	| Description|
|	Testing Materials	| TR2356	| 1268	| Auto Parts|
