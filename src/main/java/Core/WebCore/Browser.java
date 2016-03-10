package Core.WebCore;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Core.Global;
import Core.Log;

public class Browser {

	private static Browser browser;
	private static WebDriver driver;
	
	public static Browser getBrowser() {
		return browser;
	}
	public static void setBrowser(Browser browser) {
		Browser.browser = browser;
	}
	public void setDriver(WebDriver driver) {
		Browser.driver = driver;
	}

	public static WebDriver getDriver() {
		if (driver == null) {
			setBrowser(new Browser("Firefox"));
			driver.manage().window().maximize();
		}
		return driver;
	}

	Browser(String browserType) {
		Log.info("Creating an instance of a "+browserType+" browser.");
		switch (browserType) {
		case "Firefox":
			FirefoxProfile fp = new FirefoxProfile();
			fp.setAcceptUntrustedCertificates(true);
			this.setDriver(new FirefoxDriver(fp));
			break;
		case "IntenetExplorer":
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);      
			System.setProperty("webdriver.ie.driver", Global.ieDriverPath);
			this.setDriver(new InternetExplorerDriver(capabilities));
			driver.navigate().to("javascript:document.getElementById('overridelink').click()");
			break;
		case "Opera":
			driver = new OperaDriver();
			break;
		default:
			System.setProperty("webdriver.chrome.driver", Global.chromeDriverPath);
			this.setDriver(new ChromeDriver());
			break;
		}
	}
	
	public static boolean textExists(By by, int seconds){
		Log.info("'"+by.toString()+"' text existance verification");
		WebElement textItem = (new WebDriverWait(getDriver(), seconds)).until(ExpectedConditions.presenceOfElementLocated(by));
		return textItem!=null;
	}
	
	public static boolean textExists(String text, int seconds){
		return textExists(By.xpath("//*[contains(text(),'" + text + "')]"), seconds);
	}
	
	
	public static void waitForPageToLoad(int timeToWait) {
		Log.info("Waiting for the page to be loaded. Time = "+timeToWait);
		Browser.getDriver().manage().timeouts().pageLoadTimeout(timeToWait, TimeUnit.SECONDS);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public  static void sleep(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
