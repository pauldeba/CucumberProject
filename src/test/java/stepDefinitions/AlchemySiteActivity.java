package stepDefinitions;

import java.time.LocalTime;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AlchemySiteActivity {
	//WebDriver driver = new FirefoxDriver();
	WebDriver driver;
	WebDriverWait wait;
	
	
	LocalTime myObj = LocalTime.now();
	String test = "Test_"+myObj;
	String testnew = test.replace(":", "_");
	String logid = testnew.replace(".", "_");
	String email = logid+"@gmail.com";
	
	@Given("^Navigate to Alchemy Page$")
	public void navigateAlchemy() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver,15);
		driver.get("https://alchemy.hguy.co/jobs/wp-admin");
		driver.manage().window().maximize();
	}
	
		
	@Then("^Login into Alchemy with \"(.*)\" and \"(.*)\"$")
	public void loginAlchemy(String username, String password) throws InterruptedException {
		driver.findElement(By.id("user_login")).sendKeys(username);
		driver.findElement(By.id("user_pass")).sendKeys(password);
		driver.findElement(By.id("wp-submit")).click();
		Thread.sleep(3000);
	}
	
	@Then("^Locate the left hand menu and click on User$")
	public void clickUser() {
		//driver.findElement(By.linkText("Users")).click();
		driver.findElement(By.xpath("//div[@class=\"wp-menu-image dashicons-before dashicons-admin-users\"]")).click();
	}
	@Then("^Locate the Add New button and click it$")
	public void clickAddNewButton() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.linkText("Add New")).click();
	}
	
	@Then("^Fill deatils and click Add New User button$")
	public void fillDetails() throws InterruptedException {
		driver.findElement(By.id("user_login")).sendKeys(logid);
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("first_name")).sendKeys("FirstName");
		driver.findElement(By.id("last_name")).sendKeys("LastName");
		driver.findElement(By.xpath("//button[@class=\"button wp-generate-pw hide-if-no-js\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("createusersub")).click();
		//Thread.sleep(3000);
		//driver.findElement(By.xpath("//div[@class=\"error\"]/p/strong")).isDisplayed()
	//	if(driver.findElement(By.xpath("//div[@class=\"error\"]/p/strong")).isDisplayed()) {
	//		driver.findElement(By.xpath("//button[@class=\"button wp-generate-pw hide-if-no-js\"]")).click();
	//		driver.findElement(By.id("createusersub")).click();
	//	}
		Thread.sleep(3000);
	}
	
	@And("^Verify the user has created$")
	public void verifyUserCreation() {
		String text = driver.findElement(By.xpath("//div[@id=\"message\"]/p")).getText();
		System.out.println(text);
		Assert.assertEquals(text, "New user created. Edit user");
	}
	
	@And("^Close the Browser$")
	public void closeBrowser() {
		driver.close();
	}
	

	public AlchemySiteActivity() {
		// TODO Auto-generated constructor stub
	}

}
