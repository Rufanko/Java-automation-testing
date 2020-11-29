package localhost;

import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;

public class Settings {
    public ChromeDriver driver;


    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Maven/chromedriver.exe");
        driver = new ChromeDriver();

    }
}


