package Test.test;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import junit.framework.TestCase;

public class seleniumTest extends TestCase {
	String chromeDriverPath = "./resources/drivers/chrome/chromedriver.exe";
	
	WebDriver driver = null;
	static ChromeDriverService cds = null;

	protected void setUp() throws Exception {
		super.setUp();
		System.setProperty("webdriver.chrome.driver",  chromeDriverPath );
		
	     cds =  ChromeDriverService.createDefaultService();
	     cds = new ChromeDriverService.Builder()
	    		    .usingDriverExecutable(new File( chromeDriverPath ))
	    		    .usingPort(6504).build(); //14252
	     cds.start();

	     
	   	 ChromeOptions chromeOptions = new ChromeOptions();
	    	//chromeOptions.addArguments("window-size=1980,960");
	   	 chromeOptions.addArguments("--headless");
	   	 chromeOptions.addArguments("--no-sandbox");
	   	 chromeOptions.addArguments("--disable-dev-shm-usage");
	   	 chromeOptions.addArguments("--test-type");
	   	 chromeOptions.addArguments("--disable-popup-blocking");	
	   	 chromeOptions.addArguments("--allow-insecure-localhost");	
	   	 chromeOptions.addArguments("--disable-gpu");	
	   	 chromeOptions.addArguments("--ignore-certificate-errors");
	   	 chromeOptions.addArguments("--start-maximized");
	   	 chromeOptions.addArguments("--kiosk");
	   	//chromeOptions.addArguments("--start-fullscreen");
	   	
	   	
	     DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	     capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
	     capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));				        
	     capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions );
	     driver = new ChromeDriver( cds,capabilities );	
	   	 driver.manage().deleteAllCookies();


	}
	@Test
   public void testAb()
   {
	   driver.get("https://www.google.co.in/");
	   System.out.println("Hi I have landed on : " + driver.getTitle() + " Page.");
   }
	protected void tearDown() throws Exception {
		super.tearDown();
		driver.close();
		driver.quit();
	}

}
