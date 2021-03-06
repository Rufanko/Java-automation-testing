package com.dotabuff;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {
    @Test
    public void firstTest() {
        System.setProperty("webdriver.chrome.driver", "/Maven/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.dotabuff.com/");
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("DOTABUFF - Dota 2 Statistics"));
        driver.quit();
    }
}
