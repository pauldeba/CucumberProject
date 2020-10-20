package stepDefinitions;

import java.time.LocalTime;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import junit.framework.Assert;

public class AlchemyJobSearch {
	
	WebDriver driver;
	WebDriverWait wait;
	
	LocalTime myObj = LocalTime.now();
	String test = "Test_"+myObj;
	String testnew = test.replace(":", "_");
	//String logid = testnew.replace(".", "_");
	String email = testnew.replace(".", "_")+"@gmail.com";
	
	@Given("^Open Browser with Alchemy Jobs Site$")
	public void openBrowser() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver,15);
		driver.get("https://alchemy.hguy.co/jobs/");
		driver.manage().window().maximize();
	}
	
	@Given("^Navigate to Job Search Page$")
	public void navigateToJobSearchPage() {
		driver.findElement(By.linkText("Jobs")).click();
	}
	
	@Given("^Go to Post a Job page$")
	public void navigateToPostJobPage() {
		driver.findElement(By.linkText("Post a Job")).click();
	}
	
	@Then("^Enter \"(.*)\" as JobTitle and Description as \"(.*)\" and company as \"(.*)\"$")
	public void postJobDetails(String jobTitle, String description, String company) throws Throwable {
		driver.findElement(By.id("create_account_email")).sendKeys(email);
		driver.findElement(By.id("job_title")).sendKeys(jobTitle);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//iframe")).sendKeys(description);
		driver.findElement(By.id("application")).sendKeys(email);
		driver.findElement(By.id("company_name")).sendKeys(company);
		//driver.findElement(By.xpath("//input[@class='button secondary save_draft']")).click();
		driver.findElement(By.xpath("//input[@name='submit_job']")).click();
	}
	
	@Then("^Click submit and Go to Jobs Page$")
	public void submitGoToJobsPage() throws InterruptedException {
		driver.findElement(By.id("job_preview_submit_button")).click();
		Thread.sleep(3000);
		String text = driver.findElement(By.xpath("//div[@class='entry-content clear']")).getText();
		System.out.println("Confirmation Message: "+text);
		Assert.assertEquals(text, "Job listed successfully. To view your listing click here.");
		driver.findElement(By.linkText("click here")).click();
	}
	
	@Then("^Type in keywords to search for jobs and change the Job type$")
	public void searchJob() {
		driver.findElement(By.id("search_keywords")).sendKeys("Tester");
		List<WebElement> elements = driver.findElements(By.xpath("//form[@class]/ul/li"));
		for (int i=0; i<elements.size();i++){
		      //System.out.println("Button text:" + elements.get(i).getAttribute("value"));
		      System.out.println(elements.get(i).getText());
		    }
		driver.findElement(By.xpath("//form[@class]/ul/li[1]")).click();
		//driver.findElement(By.xpath("//form[@class]/ul/li[3]")).click();
	}
	
	@Then("^Find the filter using XPath and filter job type to show only Full Time jobs$")
	public void filterJob() {
		driver.findElement(By.xpath("//form[@class]/ul/li[3]")).click();
		driver.findElement(By.xpath("//form[@class]/ul/li[4]")).click();
		driver.findElement(By.xpath("//form[@class]/ul/li[5]")).click();
	}
	
	@And("^Find a job listing using XPath and it to see job details$")
	public void findJobDeatils() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.xpath("//ul[@class='job_listings']/li[1]")).click();
		//driver.findElement(By.xpath("//h3")).click();
		String text = driver.findElement(By.xpath("//div[@class='job_description']/p")).getText();
		System.out.println("Job Description: "+text);
	}
	
	@And("^Find the title of the job listing using XPath and print it to the console$")
	public void jobTitle() {
		String jobTitle = driver.findElement(By.xpath("//h1")).getText();
		System.out.println("Job Title: "+jobTitle);
	}
	
	@And("^Find and Click on the Apply for job button$")
	public void apply() {
		driver.findElement(By.xpath("//input[@class=\"application_button button\"]")).click();
	}
	
	@And("^Confirm job listing is shown on page as \"(.*)\"$")
	public void confirmJobListing(String jobtitle) {
		String jobTitle = driver.findElement(By.xpath("//h1")).getText();
		System.out.println("Job Title : "+jobTitle);
		Assert.assertEquals(jobTitle, jobtitle);
	}
	@And("^Close the site$")
	public void close() {
		driver.close();
	}

	public AlchemyJobSearch() {
		// TODO Auto-generated constructor stub
	}

}
