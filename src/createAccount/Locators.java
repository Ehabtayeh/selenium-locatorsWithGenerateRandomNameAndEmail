package createAccount;

import java.time.Duration;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

public class Locators {
	WebDriver driver ;
	By cssSelectorsignup = By.cssSelector("a[href='/login']");
	By nameSignupName = By.name("name");
	By xpathSignupEmail = By.xpath("//input[@data-qa='signup-email']");
	By cssSelectorsignupButton = By.cssSelector("#form > div > div > div:nth-child(3) > div > form > button");
	By checkboxTitle = By.xpath("//input[@value='Mr']");
	By idPassword = By.id("password");
	By dateOfBirth_dayes = By.id("days");
	By dateOfBirth_months = By.id("months");
	By dateOfBirth_years = By.id("years");
	By firstName = By.id("first_name");
	By lastName = By.id("last_name");
	By company = By.id("company");
	By address1 = By.id("address1");
	By country = By.id("country");
	By state = By.id("state");
	By city = By.id("city");
	By zipcode = By.id("zipcode");
	By mobileNumber = By.id("mobile_number");
	By createAccountButton = By.xpath("//*[@id=\"form\"]/div[1]/div/div/div/form/button");	
	By continueButton = By.xpath("//*[@id=\"form\"]/div/div/div/div/a");
		
	public WebElement findElements(By by) {
		return driver.findElement(by);
	}
	
	public void click(By locator) {
        driver.findElement(locator).click();
    }
	 public void type(By locator, String text) {
	        driver.findElement(locator).clear();
	        driver.findElement(locator).sendKeys(text);
	    }
	 public WebElement fluentWait(By locator) {

		    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		            .withTimeout(Duration.ofSeconds(5))
		            .pollingEvery(Duration.ofSeconds(1))
		            .ignoring(NoSuchElementException.class)
		            .ignoring(StaleElementReferenceException.class);

		    return wait.until(driver -> driver.findElement(locator));
		}
	 // Generate unique email
	    public static String generateEmail() {
	        return "user_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
	    }

	    // Generate random name
	    public static String generateName() {
	        return "User" + UUID.randomUUID().toString().substring(0, 5);
	    }
	    public static String[] doSignup(WebDriver driver) {

	        String name = generateName();
	        String email = generateEmail();
	        return new String[]{name, email};

	    }

	@Test
	public void locatorsDemo()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
		click(cssSelectorsignup);
		String[] userData = Locators.doSignup(driver);

	    String generatedName = userData[0];
	    String generatedEmail = userData[1];
		type(nameSignupName, generatedName);
		type(xpathSignupEmail, generatedEmail);
		click(cssSelectorsignupButton);
		click(checkboxTitle);
		type(idPassword, "Ehab12345@");
		
		Select selectDay = new Select(findElements(dateOfBirth_dayes));
		selectDay.selectByValue("15");
		Select selectMonth = new Select(findElements(dateOfBirth_months));
		selectMonth.selectByValue("3");
		Select selectYear = new Select(findElements(dateOfBirth_years));
		selectYear.selectByValue("1992");
		type(firstName, "Ehab");
		type(lastName, "Tayeh");
		type(company, "IT Company");
		type(address1, "123 IT Street");
		fluentWait(country);
		Select selectCountry = new Select(findElements(country));
		selectCountry.selectByIndex(1);
		type(state, "California");
		type(city, "Los Angeles");
		type(zipcode, "90001");
		type(mobileNumber, "(213) 555-0187");
		click(createAccountButton);
		
		
	}

}
