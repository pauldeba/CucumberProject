package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test {
	
	public static final WebDriver driver = new FirefoxDriver();
	
		
	public Test() {
		
	}
	
	public static void m1() {
		System.out.println("m1 print from Test method - from diff project");
		driver.get("https://www.google.com");
	}

}
