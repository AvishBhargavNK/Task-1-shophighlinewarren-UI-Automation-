package com.shophighlinewarren;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.apache.hc.core5.util.Asserts;
import org.jspecify.annotations.Nullable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class HomePage extends BaseClass {
	
	final String URL = "https://www.shophighlinewarren.com/";
	final String categoryURL = "https://www.shophighlinewarren.com/c/oil-gear-oil";
	private String product = "PRIMPG80W90QT";
	private String initialDialog = "//div[@data-testid='FLYOUT']";
	private String closeButton = "//button[@aria-label = 'Close dialog']";
	private String browseCategory = "//a[@title='Browse By Category']";
	private String categoryleveldropdown ="//ul[@class='category-level1 dropdown-menu']";
	private String oilandlubricantlink = "//a[@data-testing-id='oilandlubricants-link']";
	private String oilandlubricantcategory = "//ul[@class='category-level2 active']//span[contains(text(),'Oil & Lubricants')]";
	private String gearoil = "//ul[@class='category-level2 active']//li//a[contains(text(),'Gear Oil')]";
    private String gearoilproduct = "//img[@src='/images/products/medium/"+product+"_1_medium.jpg']";
	private String suggestionfield = "//div[@class='suggest-details coveo-pdp-suggest']//h2";
	private String productid = "//div[@class='product-number']//span";
	private String productprice = "//div[@class='pdp-price']//a";
	
	
	@SuppressWarnings("deprecation")
	public void browsersetup() {
		try {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\BrowserDrivers\\chromedriver.exe");
	        driver = new ChromeDriver() ;
	        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        driver.manage().window().maximize();
	        driver.get(URL);
	        
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void homepageverification() {
		try {
			String title = driver.getTitle();
	           assertEquals("HIGHLINE WARREN", title); //Title Verification
	           Thread.sleep(2000);
	           WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(initialDialog)));
		       if( driver.findElement(By.xpath(closeButton)).isDisplayed())
		       driver.findElement(By.xpath(closeButton)).click();
		        
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Dialog Not Displayed");
		}

	} 
	
	
	public void browsebycategory() {
		try {
			Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(By.xpath(browseCategory))).perform();
            
            if(driver.findElement(By.xpath(categoryleveldropdown)).isDisplayed())
            System.out.println("Category listing is visible");
            else
                System.out.println("Category listing is not visible");
            
           actions.moveToElement(driver.findElement(By.xpath(oilandlubricantlink))).perform();
           
           if(driver.findElement(By.xpath(oilandlubricantcategory)).isDisplayed())
           {
               System.out.println("Category listing is visible");
           assertEquals("Oil & Lubricants", driver.findElement(By.xpath(oilandlubricantcategory)).getText());
           }
               else {
                   System.out.println("Category listing is not visible");
               }
           driver.findElement(By.xpath(gearoil)).click();
          WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
           wait.until(ExpectedConditions.urlToBe(categoryURL));
         
		String currentUrl = driver.getCurrentUrl();
		if(currentUrl.contains("gear-oil"))
			System.out.println("Gear oil category listed");
		else 
			System.out.println("Gear oil category not listed");


		}
		catch (Exception e) {
			System.out.println("Unable to click on the browse by category");
			System.out.println(e.getMessage());
					}
	}
	public void productdetails() {
		try {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(gearoilproduct)));
			driver.findElement(By.xpath(gearoilproduct)).click();
		   
			wait.until(ExpectedConditions.urlContains(product));		
			
			String suggestiontext = driver.findElement(By.xpath(suggestionfield)).getText();
			assertEquals("SUGGESTED FOR YOU", suggestiontext);
			
			String productidtext = driver.findElement(By.xpath(productid)).getText();
			assertEquals(product, productidtext);
			
			String price = driver.findElement(By.xpath(productprice)).getText();
			assertEquals("Log In", price); //Price is not revealed until user login
			
			
		} catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println(("Unable to verify the product details"));
		}
	}
	
	public void quitbrowser() {
		driver.quit();
	}
	
	public static void main(String[] args) {
		
		HomePage home = new HomePage();
		home.browsersetup();
		home.homepageverification();
		home.browsebycategory();
		home.productdetails();
		home.quitbrowser();
		
	}
	

}
