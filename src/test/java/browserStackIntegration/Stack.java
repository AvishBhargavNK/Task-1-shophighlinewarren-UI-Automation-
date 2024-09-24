package browserStackIntegration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.shophighlinewarren.HomePage;

import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Stack {
    public static final String USERNAME = "avishbhargav_Yagn31";
    public static final String AUTOMATE_KEY = "QUHJaHz6YrQBiCJL3WR9";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static void main(String[] args) throws Exception {
    	  // browsers and resolutions 
        String[][] browsers = {
            
            {"Chrome", "latest", "1280x1024"},
            {"Firefox", "latest", "1920x1080"},
           
        };

        // Loop through the browser configurations
        for (String[] browser : browsers) {
            // Get browser name, version, and resolution
            String browserName = browser[0];
            String browserVersion = browser[1];
            String resolution = browser[2];

            // Set up desired capabilities for the browser
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", browserName);
            caps.setCapability("browserVersion", browserVersion);
            caps.setCapability("platformName", "Windows");

            // BrowserStack specific options
            Map<String, Object> browserStackOptions = new HashMap<>();
            browserStackOptions.put("osVersion", "11");
            browserStackOptions.put("resolution", resolution); // Set screen resolution
            browserStackOptions.put("local", "false");
            browserStackOptions.put("seleniumVersion", "4.0.0");
            browserStackOptions.put("userName", USERNAME);
            browserStackOptions.put("accessKey", AUTOMATE_KEY);
            browserStackOptions.put("buildName", "BrowserStack-Shophighlinewarren Automation");
            browserStackOptions.put("sessionName", browserName + " Test with resolution " + resolution);

            // Add BrowserStack options under 'bstack:options'
            caps.setCapability("bstack:options", browserStackOptions);

            // Initialize the Remote WebDriver with the BrowserStack URL
            WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
         
           
            
            runTest(driver);
            
         
            driver.quit();
    }
}
    public static void runTest(WebDriver driver) {
        try {
            //Navigate to shophighlinewarren  website 
            driver.get("https://www.shophighlinewarren.com/");
            System.out.println("Title of the page is: " + driver.getTitle());

            HomePage home = new HomePage(driver);
    		home.homepageverification();
    		home.browsebycategory();
    		home.productdetails();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



