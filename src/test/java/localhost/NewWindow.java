package localhost;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class NewWindow extends Settings {
    private WebDriverWait wait;
    private int timeout = 3;



    @Test
    public void mainTest() throws InterruptedException {


        setUp();
        implicitlyWaitOn();
        wait = new WebDriverWait(driver, 10);
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();


        searchAndClick(By.cssSelector("ul#box-apps-menu li#app-"), "Countries");

        choiseFromListAndClick(By.xpath(".//tr[@class='row']//td[@style]"));

        String newWindow, mainWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();
        List<WebElement> list = driver.findElements(By.cssSelector("i.fa-external-link"));
        for (WebElement we:list) {
            we.click();
            implicitlyWaitOff();
            newWindow = wait.until(anyWindowOtherThan(oldWindows));
            driver.switchTo().window(newWindow);
            String title = driver.getTitle();
            System.out.println(title);
            if (title.contains("Wikipedia"))
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
            else
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#acct_btn")));
            implicitlyWaitOn();
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }

    public ExpectedCondition<String> anyWindowOtherThan (Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver d) {
                Set<String> handles = d.getWindowHandles();
                handles.removeAll(oldWindows);
                return  handles.size()>0 ? handles.iterator().next() : null;
            }
        };
    }

    private void choiseFromListAndClick (By locator) {
        List<WebElement> list = driver.findElements(locator);
        int index = (int)(Math.random()*list.size());
        list.get(index).click();
    }

    private void searchAndClick(By locator, String text) {
        List<WebElement> list = driver.findElements(locator);
        String name;
        for (WebElement we : list) {
            name = we.getText();
            if (name.equals(text) ) {
                we.click();
                break;
            }
        }
    }

    private void implicitlyWaitOn() {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    private void implicitlyWaitOff() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

}
