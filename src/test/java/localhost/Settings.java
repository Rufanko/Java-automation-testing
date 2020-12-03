package localhost;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


public class Settings {
    public ChromeDriver driver;
    public int timeout = 3;






    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Maven/chromedriver.exe");
        //driver = new FirefoxDriver();
        //driver = new InternetExplorerDriver();
        DesiredCapabilities cap = new DesiredCapabilities();
        driver = new ChromeDriver(cap);
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);


        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    public boolean isElementPresent (By locator) {
        try {
            driver.findElement(locator);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public boolean areElementsPresent (By locator){
        return driver.findElements(locator).size() > 0;

    }
    public void implicitlyWaitOn() {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    public void implicitlyWaitOff() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }


}


