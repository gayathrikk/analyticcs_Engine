package Lovya.triple;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class analyticsengine {
	
		private RemoteWebDriver driver;
		private WebDriverWait wait;


		@BeforeTest

		public void setup() throws Exception
		{
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		       URL url = new URL("http://172.22.0.5:4444/wd/hub");
		       driver = new RemoteWebDriver(url, dc);
		       wait = new WebDriverWait(driver, 10);
		}

		  @Test(priority = 1)
			public void login() throws InterruptedException {
			driver.get("https://apollo2.humanbrain.in/viewer/annotation/portal");
			driver.manage().window().maximize();
			String currentURL = driver.getCurrentUrl();
			System.out.println("Current URL: " + currentURL);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			driver.switchTo().defaultContent(); // Switch back to default content
			// WebElement viewerElement = wait
			// .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Viewer']")));
			// if (viewerElement.isEnabled() && viewerElement.isDisplayed()) {
			// viewerElement.click();
			// System.out.println("Viewer icon is clicked");
			// } else {
			// System.out.println("Viewer icon is not clickable");
			// }

			// String parentWindow = driver.getWindowHandle();
			WebElement loginButton = wait
			.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='auth-button']")));
			if (loginButton != null) {
			loginButton.click();
			System.out.println("Login button clicked successfully.");
			} else {
			System.out.println("Login button is not clicked.");
			}

			// wait.until(ExpectedConditions.numberOfWindowsToBe(2));
			// Set<String> allWindows = driver.getWindowHandles();
			// for (String window : allWindows) {
			// if (!window.equals(parentWindow)) {
			// driver.switchTo().window(window);
			// break;
			// }
			// }
			WebElement emailInput = wait
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
			if (emailInput != null && emailInput.isDisplayed()) {
			emailInput.sendKeys("teamsoftware457@gmail.com");
			System.out.println("Email was entered successfully.");
			} else {
			System.out.println("Email was not entered.");
			}

			WebElement nextButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));
			if (nextButton1 != null) {
			nextButton1.click();
			System.out.println("Next button 1 is clicked.");
			} else {
			System.out.println("Next button 1 is not clicked.");
			}

			WebElement passwordInput = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label='Enter your password']")));
			passwordInput.sendKeys("Health#123");
			if (passwordInput.getAttribute("value").equals("Health#123")) {
			System.out.println("Password was entered successfully.");
			} else {
			System.out.println("Password was not entered.");
			}

			WebElement nextButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));
			if (nextButton2 != null) {
			nextButton2.click();
			System.out.println("Next button 2 is clicked.");
			} else {
			System.out.println("Next button 2 is not clicked.");
			}

			WebElement continuebutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Continue']")));
            if (continuebutton != null) {
            continuebutton.click();
            System.out.println("continuebutton is clicked.");
            } else {
            System.out.println("continuebutton is not clicked.");
        }

			//driver.switchTo().window(parentWindow);
			System.out.println("Login successfully");

			System.out.println("************************Login validation done***********************");
			Thread.sleep(5000);
			}

		  @Test(priority = 2)
		    public void analyticsEngine() throws InterruptedException {
		    	try {
		    	    WebDriverWait wait = new WebDriverWait(driver, 30);

		    	    // Click Analytics Engine
		    	    WebElement analyticsIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/viewer/assets/images/colorsvg/analytics_engine.svg']")));
		    	    analyticsIcon.click();
		    	    System.out.println("Analytics Engine icon clicked successfully.");

		    	    // Call saveSearchHistory API
		    	    System.out.println("Calling saveSearchHistory API...");
		    	    Response saveSearchResponse = RestAssured.given()
		    	        .post("https://apollo2.humanbrain.in/analytics/saveSearchHistory");
		    	    System.out.println("saveSearchHistory API Response: " + saveSearchResponse.getStatusCode() + " - " + saveSearchResponse.getBody().asString());

		    	    // Enter Query
		    	    WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text']")));
		    	    searchBox.sendKeys("greater than 10\n");
		    	    Thread.sleep(5000);
		    	    System.out.println("The Query is entered successfully");

		    	    // Call db_query API
		    	    System.out.println("Calling db_query API...");
		    	    Response dbQueryResponse = RestAssured.given()
		    	        .queryParam("query", "greater than 10")
		    	        .get("https://apollo2.humanbrain.in/analyticsengine/db_query");
		    	    System.out.println("db_query API Response: " + dbQueryResponse.getStatusCode() + " - " + dbQueryResponse.getBody().asString());

		    	    // Validate Text 'Fetal brain 34'
		    	    WebElement resultTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='MTB3']")));
		    	    String actualText = resultTextElement.getText();
		    	    String expectedText = "MTB3";

		    	    if (!actualText.equals(expectedText)) {
		    	        throw new AssertionError("Validation failed! Expected text: '" + expectedText + "', but found: '" + actualText + "'");
		    	    }
		    	    System.out.println("Validation passed: 'MTB3' text is displayed correctly.");

		    	    // Download Button
		    	    WebDriverWait wait3 = new WebDriverWait(driver, 60);
		    	    WebElement download = wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@src='/viewer/assets/images/colorsvg/download.svg'])[1]")));
		    	    download.click();
		    	    System.out.println("Download button clicked successfully.");

		    	    // Call getSearchHistory API
		    	    System.out.println("Calling getSearchHistory API...");
		    	    Response searchHistoryResponse = RestAssured.given()
		    	        .queryParam("user_id", "193")
		    	        .get("https://apollo2.humanbrain.in/analytics/getSearchHistory");
		    	    System.out.println("getSearchHistory API Response: " + searchHistoryResponse.getStatusCode() + " - " + searchHistoryResponse.getBody().asString());

		    	} catch (AssertionError e) {
		    	    System.err.println("Test case failed: " + e.getMessage());
		    	    throw e; // Rethrow the assertion error to mark the test as failed
		    	} catch (Exception e) {
		    	    System.err.println("An error occurred in AnalyticsEngine test: " + e.getMessage());
		    	}}
		    @AfterTest
		    public void tearDown() {
		        if (driver != null) {
		            driver.quit();
		            System.out.println("Browser closed successfully");
		        }
		    }
		}
