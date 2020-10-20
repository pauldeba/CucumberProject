
Feature: Creating a job vacancy

Background:
	Given Open the OrangeHRM page
@orange_1
Scenario: Creating a job vacancy
	Then Login with "orange" as user name and "orangepassword123" as password
	Then Navigate to the Recruitment page and click Vacancies menu item
	#Then Click on the Vacancies menu item to navigate to the vacancies page
	And Click on the Add button to navigate to the Add Job Vacancy form
	And Fill out details for Add Job Vacancy and Click Save button
	And Verify that the vacancy was created
	And Close the OrangeHRM Browser
@orange_2	
Scenario: Adding a candidate for recruitment
	Then Login with "orange" as user name and "orangepassword123" as password
	Then Navigate to the Recruitment page and click on the Add button to add candidate information
	And Fill in the details of the candidate
	And Upload a resume docx or pdf to the form and Click Save
	And Navigate back to the Recruitments page to confirm candidate entry
	And Close the OrangeHRM Browser
	
@orange_3
Scenario Outline: Add multiple employees
	Then Login with "orange" as user name and "orangepassword123" as password
	Then Find and Click PIM option in the menu and click Add button to add a new Employee
	##And Make sure the “Create Login Details” checkbox is checked.
	##And Fill in the required fields using the data from the Examples table and click Save.
	And Fill required fields "<FirstName>" and "<LastName>" for Add Employee and click Save
	##And Repeat this until all the employees and their accounts have been created.
	And Verify that the employees with "<FirstName>" and "<LastName>" have been created
	And Close the OrangeHRM Browser
Examples:
|	FirstName	| LastName	|	 
|	John			|	Malia			|
|	Johns			|	Kati			|
|	Ketty			|	Charcol		|

@orange_4
Scenario Outline: Creating multiple vacancies
	Then Login with "orange" as user name and "orangepassword123" as password
	Then Navigate to the Recruitment page and click Vacancies menu item
	And Click on the Add button to navigate to the Add Job Vacancy form
	And Fill details "<JobTitle>" and "<HiringManager>" and "<Descriptions>" to Add Job Vacancy
	And Verify that the vacancy was created
	And Close the OrangeHRM Browser
	##Then Click on the “Vacancies” menu item to navigate to the vacancies page.
	##Then Click on the “Add” button to navigate to the “Add Job Vacancy” form.
	#And Fill out the necessary details using data from the Examples tables.
	#And Click the “Save” button to save the vacancy.
	#And Repeat until all the vacancies have been created.
	#And Verify that all the vacancies have been successfully created.
	#And Close the OrangeHRM Browser
Examples:
|	JobTitle	|	HiringManager	|	Descriptions	|
|	Android Developer|	AAAtestfirstname1 AAAtestlastname1	|	Android Developer Position|
|	Automation Test Engineer	| aaabbbccc aaabbbccc	|	Automation Test Engineer|
		
	
	