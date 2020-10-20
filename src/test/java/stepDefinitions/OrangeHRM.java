package stepDefinitions;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class OrangeHRM {

	WebDriver driver = new FirefoxDriver();
	WebDriverWait wait;
	//creating random number with timestamp
	LocalTime myObj = LocalTime.now();
	String test = "Test_"+myObj;
	String testnew = test.replace(":", "");
	String logid = testnew.replace(".", "");
	String email = logid+"@gmail.com";
	
	@Given("^Open the OrangeHRM page$")
	public void openOrangeHRMBrowser() {
		//driver = new FirefoxDriver();
		wait = new WebDriverWait(driver,15);
		driver.get("http://alchemy.hguy.co/orangehrm");
		driver.manage().window().maximize();
	}
	
	@Then("^Login with \"(.*)\" as user name and \"(.*)\" as password$")
	public void loginIntoOrangeHRM(String userName, String password) {
		driver.findElement(By.id("txtUsername")).sendKeys(userName);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();
	}
	
	@Then("^Navigate to the Recruitment page and click Vacancies menu item$")
	public void navigateToRecruitementAndClickVacancies() throws Throwable {
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Thread.sleep(9000);
		//driver.findElement(By.linkText("Recruitment")).click();
		driver.findElement(By.xpath("//a[@id='menu_recruitment_viewRecruitmentModule']")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@id='menu_recruitment_viewJobVacancy']")));
		driver.findElement(By.xpath("//a[@id='menu_recruitment_viewJobVacancy']")).click();
	}
	
	@Then("^Navigate to the Recruitment page and click on the Add button to add candidate information$")
	public void navigatetoRecruitementAndClickAdd() throws Throwable {
		Thread.sleep(9000);
		//driver.findElement(By.linkText("Recruitment")).click();
		driver.findElement(By.xpath("//a[@id='menu_recruitment_viewRecruitmentModule']")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@id='menu_recruitment_viewJobVacancy']")));
		driver.findElement(By.xpath("//input[@id='btnAdd']")).click();
	}
	
	@Then("^Find and Click PIM option in the menu and click Add button to add a new Employee$")
	public void clickPIMandAddButton() throws Throwable {
		Thread.sleep(9000);
		driver.findElement(By.linkText("PIM")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("btnAdd")).click();
		
	}
	
	@Then("^Navigate to Recruitment page and Click on Vacancies menu item and click Add button$")
	public void navigateToRecuitmentVacanciesAndClickAdd() {
		
	}
	
	@And("^Click on the Add button to navigate to the Add Job Vacancy form$")
	public void clickAddButtonToAddJobVacancy() throws Throwable {
		Thread.sleep(5000);
		driver.findElement(By.id("btnAdd")).click();
	}
	
	@And("^Fill out details for Add Job Vacancy and Click Save button$")
	public void detailsAddJobVacancy() {
		driver.findElement(By.xpath("//select"));
		Select jobTitle = new Select(driver.findElement(By.xpath("//select")));
		jobTitle.selectByIndex(2);
		driver.findElement(By.xpath("//input[@name='addJobVacancy[name]']")).sendKeys(logid);
		driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys("AfirstName1 AlastName1");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("btnSave")).click();
	}
	
	@And("^Verify that the vacancy was created$")
	public void verifyVacancyCreation() throws Throwable {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String vacancyName = driver.findElement(By.id("addJobVacancy_name")).getAttribute("value");
		System.out.println("Vacancy Name: "+vacancyName);
		driver.findElement(By.xpath("//a[@id='menu_recruitment_viewJobVacancy']")).click();
		Thread.sleep(3000);
		Select vacancy = new Select(driver.findElement(By.xpath("//select[@id='vacancySearch_jobVacancy']")));
		//vacancy.selectByValue(vacancyName);
		vacancy.selectByVisibleText(vacancyName);
		driver.findElement(By.id("btnSrch")).click();
		Thread.sleep(3000);
		String vacancySearch=driver.findElement(By.xpath("//td[@class='left']")).getText();
		System.out.println("Vacancy Name from Search: "+vacancySearch);
		Assert.assertEquals(vacancySearch, vacancyName);
	}
	
	@And("^Fill in the details of the candidate$")
	public void fillDetailsCandidate() {
		driver.findElement(By.id("addCandidate_firstName")).sendKeys("FirstName");
		driver.findElement(By.id("addCandidate_lastName")).sendKeys("LastName");
		driver.findElement(By.id("addCandidate_email")).sendKeys(email);
		Select jobVacancy = new Select(driver.findElement(By.id("addCandidate_vacancy")));
		//jobVacancy.selectByIndex(3);
		jobVacancy.selectByVisibleText("Test_20_38_53_366840800");
		driver.findElement(By.id("addCandidate_comment")).sendKeys("Automation Tester Position");
	}
	
	@And("^Upload a resume docx or pdf to the form and Click Save$")
	public void uploadResume() throws Throwable {
		driver.findElement(By.id("addCandidate_resume")).sendKeys("C:\\Users\\DebashisPaul\\eclipse-workspace\\CucumberProject\\Test.docx");
		Thread.sleep(3000);
		driver.findElement(By.id("btnSave")).click();		
	}
	
	@And("^Navigate back to the Recruitments page to confirm candidate entry$")
	public void navigateRecrutingPageAndConfirmCandidate() throws Throwable {
		String emailid = driver.findElement(By.id("addCandidate_email")).getAttribute("value");
		System.out.println("EMail Id: "+emailid);
		driver.findElement(By.id("btnBack")).click();
		Thread.sleep(3000);
		Select selectVacancy = new Select(driver.findElement(By.id("candidateSearch_jobVacancy")));
		selectVacancy.selectByVisibleText("Test_20_38_53_366840800");
		Select status = new Select(driver.findElement(By.id("candidateSearch_status")));
		status.selectByVisibleText("Application Initiated");
		driver.findElement(By.id("btnSrch")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr[1]/td[3]")).click();
		String emailidSearch = driver.findElement(By.id("addCandidate_email")).getAttribute("value");
		System.out.println("Email Id from Search: "+emailidSearch);
		Assert.assertEquals(emailid, emailidSearch);
		
		// Delete the records
		Thread.sleep(3000);
		driver.findElement(By.id("btnBack")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr[1]/td[1]")).click();
		driver.findElement(By.id("btnDelete")).click();
		driver.findElement(By.id("dialogDeleteBtn")).click();
	}
	
	@And("^Fill required fields \"(.*)\" and \"(.*)\" for Add Employee and click Save$")
	public void addEmployeeDetails(String firstName, String lastName) throws Throwable {
		driver.findElement(By.id("firstName")).sendKeys(firstName);
		driver.findElement(By.id("lastName")).sendKeys(lastName);
		
		driver.findElement(By.id("photofile")).sendKeys("C:\\Users\\DebashisPaul\\eclipse-workspace\\CucumberProject\\image.png");
		driver.findElement(By.xpath("//input[@name='chkLogin']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("user_name")).sendKeys(logid);
		driver.findElement(By.id("user_password")).sendKeys("pa$$w0rd");
		driver.findElement(By.id("re_password")).sendKeys("pa$$w0rd");
		Thread.sleep(5000);
		driver.findElement(By.id("btnSave")).click();
		Thread.sleep(5000);
	}
	
	@And("^Verify that the employees with \"(.*)\" and \"(.*)\" have been created$")
	public void verifyEmployee(String firstName, String lastName) throws Throwable {
		String empId = driver.findElement(By.id("personal_txtEmployeeId")).getAttribute("value");
		System.out.println("Employee Id Created is :"+empId);
		String fName = driver.findElement(By.id("personal_txtEmpFirstName")).getAttribute("value");
		System.out.println("First Name is: "+fName);
		String lName = driver.findElement(By.id("personal_txtEmpLastName")).getAttribute("value");
		System.out.println("Last Name is: "+lName);
		Assert.assertEquals(firstName, fName);
		Assert.assertEquals(lastName, lName);
		driver.findElement(By.linkText("Employee List")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("empsearch_id")).sendKeys(empId);
		driver.findElement(By.id("searchBtn")).click();
		String empIdSearch = driver.findElement(By.xpath("//table/tbody/tr/td[2]")).getText();
		System.out.println("Employee ID from Search is: "+empIdSearch);
		Assert.assertEquals(empId, empIdSearch);
	}
	
	@And("^Fill details \"(.*)\" and \"(.*)\" and \"(.*)\" to Add Job Vacancy$")
	public void clickAddButtonAndFillDetails(String jobDetails, String hiringManager, String description) throws Throwable {
		//Thread.sleep(5000);
		//driver.findElement(By.id("btnAdd")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//select"));
		Select jobTitle = new Select(driver.findElement(By.xpath("//select")));
		jobTitle.selectByVisibleText(jobDetails);
		driver.findElement(By.xpath("//input[@name='addJobVacancy[name]']")).sendKeys(logid);
		driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys(hiringManager);
		driver.findElement(By.id("addJobVacancy_description")).sendKeys(description);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("btnSave")).click();
		
	}
	
	@And("^Close the OrangeHRM Browser$")
	public void closeOrangeBrowser() {
		driver.close();
	}
	
	
	
	public OrangeHRM() {
		// TODO Auto-generated constructor stub
	}

}
