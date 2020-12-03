package localhost;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;



public class Settings {
    public ChromeDriver driver;





    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Maven/chromedriver.exe");
        //driver = new FirefoxDriver();
        //driver = new InternetExplorerDriver();
        driver = new ChromeDriver();

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


}


