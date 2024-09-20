package JATTask16.task17;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Program1 {
	
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		
	       
	       // Initialize ChromeDriver with options
	     WebDriver driver = new ChromeDriver();
     
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Open Snapdeal
            driver.get("https://www.snapdeal.com");

            // Move to "Sign In" and hold
            Actions actions = new Actions(driver);
            WebElement signInElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']")));
            actions.moveToElement(signInElement).perform();

            // Click on the Sign In button
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='accountBtn btn rippleWhite']/a")));
            loginButton.click();
            
            
            WebElement iframe=driver.findElement(By.id("loginIframe"));
            driver.switchTo().frame(iframe);
           

           // Enter email and password
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName")));
            emailField.sendKeys("test@gmail.com"); //dummy email/password
            //emailField.sendKeys("9999999999"); 
            
            Thread.sleep(2000);

            WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='checkUser']")));
            continueButton.click();
            
            driver.findElement(By.xpath("//form[@id='loginOtpUC']/div[1]/input"));
			Thread.sleep(25000);
			driver.findElement(By.xpath("//*[@id='loginUsingOtp']")).click();
			driver.switchTo().defaultContent();
			

            // Verify successful login
            try {
            	
            	
            	 WebElement accountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='myAccountTab accountHeaderClass col-xs-13 reset-padding']")));
                 actions.moveToElement(accountElement).perform();
            	System.out.println("User logged in successfully: " + accountElement.isDisplayed());
            } catch (Exception e) {
                System.out.println("Login failed: " + e.getMessage());
            }
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            // Close the browser
            driver.quit();
        }
    }

}
