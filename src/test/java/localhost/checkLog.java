package localhost;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class checkLog extends Settings {
    private WebDriverWait wait;

    @Test
    public void mainTest() throws InterruptedException {

        setUp();
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        Thread.sleep(1000);
        searchAndClick(By.cssSelector("ul#box-apps-menu li#app-"), "Catalog");
        Thread.sleep(1000);
        driver.findElement(By.xpath(".//table[@class='dataTable']//td[3]/a")).click();
        Thread.sleep(1000);
        workWithPage(By.xpath(".//table[@class='dataTable']//td[3]/img"));
    }

    private  void workWithPage(By locator) {
        int countItem = driver.findElements(locator).size();
        WebElement firstImg = driver.findElement(locator);

        String first = firstImg.findElement(By.xpath("../..")).getAttribute("rowIndex");
        int firstItem = Integer.parseInt(first) + 1;
        clearBrowserLog();
        consistentClickItem(firstItem, countItem);
    }

    private void consistentClickItem(int firstItem, int countItem) {
        WebElement currentItem;
        for (int i=firstItem; i<countItem + firstItem; i++) {
            currentItem = driver.findElement(By.xpath(".//table[@class='dataTable']//tr[" + i +"]/td[3]/a"));
            System.out.println("************* Item " + currentItem.getAttribute("text"));
            currentItem.click();
            implicitlyWaitOff();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
            implicitlyWaitOn();
            driver.findElement(By.name("cancel")).click();
        }
    }


    private void clearBrowserLog () {
        driver.manage().logs().get("browser").getAll();
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
}

