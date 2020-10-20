package stepDefinitions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SuiteCRM {
	WebDriver driver = new FirefoxDriver();
	WebDriverWait wait = new WebDriverWait(driver,15);
	
	@Given("^Open the browser to the Alchemy CRM site and login with \"(.*)\" and \"(.*)\"$")
	public void openCRMandLogIn(String loginId, String password) throws Throwable {
		driver.get("https://alchemy.hguy.co/crm/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("user_name")).sendKeys(loginId);
		driver.findElement(By.id("username_password")).sendKeys(password);
		driver.findElement(By.id("bigbutton")).click();
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='modulelinks']")));
		Thread.sleep(5000);
	}
	
	@Then("^Count the number of Dashlets on the homepage and print$")
	public void countDashletsOnCRMPage() {
		List<WebElement> dashLet = driver.findElements(By.xpath("//h3"));
		dashLet.size();
		System.out.println("Number of DashLet are: "+dashLet.size());
	}
	
	@Then("^Navigate to Sales -> Leads -> Create Lead$")
	public void navigateToSalesLeadCreateLeads() throws Throwable {
		Thread.sleep(5000);
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.linkText("SALES"))).build().perform();
		a.moveToElement(driver.findElement(By.linkText("Leads"))).click().perform();
		Thread.sleep(3000);
		a.moveToElement(driver.findElement(By.linkText("Create Lead"))).click().perform();
	}
	
	@Then("^Fill deatils with \"(.*)\" as FirstName \"(.*)\" as LastName \"(.*)\" as Department \"(.*)\" as AccountName$")
	public void fillDetailsforCreateLead(String fName, String lName, String department, String accName) throws Throwable {
		Thread.sleep(3000);
		Select title = new Select(driver.findElement(By.id("salutation")));
		title.selectByVisibleText("Mr.");
		driver.findElement(By.id("first_name")).sendKeys(fName);
		driver.findElement(By.id("last_name")).sendKeys(lName);
		driver.findElement(By.id("department")).sendKeys(department);
		driver.findElement(By.id("EditView_account_name")).sendKeys(accName);
	}
	
	@Then("^Navigate to Activities -> Meetings -> Schedule a Meeting$")
	public void navigateToActivitiesMeetings() throws Throwable {
		Thread.sleep(5000);
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.linkText("ACTIVITIES"))).build().perform();
		a.moveToElement(driver.findElement(By.linkText("Meetings"))).click().perform();
		Thread.sleep(3000);
		a.moveToElement(driver.findElement(By.linkText("Schedule Meeting"))).click().perform();
	}
	
	@Then("^Enter the details of the meeting$")
	public void enterMeetingDetails() throws Throwable {
		Thread.sleep(3000);
		driver.findElement(By.id("name")).sendKeys("Testing And Development");
		driver.findElement(By.id("description")).sendKeys("Please Attend the Meeting");
	}
	
	@Then("^Navigate to All -> Products-> Create Product$")
	public void navigatetoAllProductCreateProduct() throws Throwable {
		Thread.sleep(5000);
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.linkText("ALL"))).build().perform();
		driver.findElement(By.xpath("//div[@class='desktop-toolbar']/ul/li[7]/span[2]/ul/li[25]")).click();
		//a.moveToElement(driver.findElement(By.linkText("Products"))).click().perform();
		Thread.sleep(3000);
		a.moveToElement(driver.findElement(By.linkText("Create Product"))).click().perform();
	}
	
	@Then("^Enter \"(.*)\" as ProductName, \"(.*)\" as PartNumber, \"(.*)\" with Price and \"(.*)\"$")
	public void enterDetailsForProducts(String prodName, String partNumber, String price, String description) throws Throwable {
		Thread.sleep(5000);
		driver.findElement(By.id("name")).sendKeys(prodName);
		driver.findElement(By.id("part_number")).sendKeys(partNumber);
		driver.findElement(By.id("price")).sendKeys(price);
		driver.findElement(By.id("description")).sendKeys(description);
	}
	
	@And("^Print the title of each Dashlet into the console$")
	public void printTitleOfDashlet() {
		List<WebElement> dashLet = driver.findElements(By.xpath("//h3"));
		for(int i=0; i<dashLet.size(); i++) {
			System.out.println(dashLet.get(i).getText());
		}
	}
	
	@And("^Click Save to Finish and Navigate to the View Leads page to see results$")
	public void saveAndNavigateToViewLeadPage() throws Throwable {
		driver.findElement(By.id("SAVE")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("View Leads")).click();
		Thread.sleep(3000);
		String name = driver.findElement(By.xpath("//table[@class='list view table-responsive']/tbody/tr/td[3]/b")).getText();
		System.out.println("Name of Lead Created: " +name);
	}
	
	@And("^Search for member \"(.*)\" and \"(.*)\" and add them to the meeting$")
	public void searchMembersAddToMeeting(String attendy1, String attendy2) throws Throwable {
		Thread.sleep(3000);
		driver.findElement(By.id("invitees_search")).click();
		Assert.assertEquals("Chris Olliver", driver.findElement(By.xpath("//div[@id]/table/tbody/tr[3]/td[2]")).getText());
		//driver.findElement(By.id("invitees_add_1")).click();
		//driver.findElement(By.id("invitees_add_2")).click();
		//driver.findElement(By.id("invitees_add_3")).click();
		List<WebElement> attendies = driver.findElements(By.xpath("//div[@id]/table/tbody/tr/td[2]"));
		System.out.println("Number of Element is :"+attendies.size());
		System.out.println("Attendy: "+attendy1);
		System.out.println("Attendy: "+attendy2);
		for(int i=0; i<attendies.size(); i++) {
			String sAttendies = attendies.get(i).getText();
			//System.out.println(sAttendies);
			//Thread.sleep(1000);
			if(sAttendies.contentEquals(attendy1) | sAttendies.contentEquals(attendy2)) {
				System.out.println(sAttendies);
				String id = "invitees_add_"+(i+1);
				//System.out.println(id);
				driver.findElement(By.id(id)).click();
			}
		}
		Thread.sleep(5000);
	}
	
	@And("^Click Save and Navigate to View Meetings page and confirm creation of the meeting$")
	public void saveAndNavigateToConfirmMeetingPage() throws Throwable {
		driver.findElement(By.id("SAVE_HEADER")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("View Meetings")).click();
		System.out.println(driver.findElement(By.xpath("//table[@class='list view table-responsive']/tbody/tr/td[4]")).getText());
	}
	
	@And("^Click Save and Go to the View Products page to see all products listed$")
	public void clickSaveAndViewProductsPageLists() throws Throwable {
		driver.findElement(By.id("SAVE")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("View Products")).click();
	}
	
		
	@And("^Close the SuiteCRM browser$")
	public void closeSuiteCRMBrower() {
		driver.close();
	}

}
