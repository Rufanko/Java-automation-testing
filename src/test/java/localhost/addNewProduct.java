package localhost;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class addNewProduct extends Settings {
    @Test
    public void addNewDuck() throws InterruptedException {
        setUp();
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        Thread.sleep(1000);

        searchAndClick("ul#box-apps-menu li#app-", "Catalog");
        Thread.sleep(1000);
        searchAndClick("td#content a.button", "Add New Product");
        Thread.sleep(1000);
        String newItem = "Duck Donald Trump";
        String relativePath = "./src/test/images/newItem.jpg";
        Path filePath = Paths.get(relativePath);
        String absolutePath = filePath.normalize().toAbsolutePath().toString();
        fillTabGeneral(newItem, absolutePath);
        searchAndClick("div.tabs li", "Information");
        Thread.sleep(1000);
        fillTabInformation();
        searchAndClick("div.tabs li", "Prices");
        Thread.sleep(1000);
        fillTabPrices();
        driver.findElement(By.cssSelector("button[name=save]")).click();
        Thread.sleep(1000);
        checkNewItem(newItem);

    }

    private void fillTabGeneral(String item, String path){
        driver.findElement(By.name("name[en]")).sendKeys(item);
        driver.findElement(By.cssSelector("input[name=status][value='1']")).click();
        driver.findElement(By.name("code")).sendKeys("rp001");
        driver.findElement(By.cssSelector("input[type=checkbox][value='0']")).click();
        driver.findElement(By.cssSelector("input[type=checkbox][value='1']")).click();
        driver.findElement(By.name("quantity")).sendKeys(Keys.CONTROL + "a" + Keys.DELETE );
        driver.findElement(By.name("quantity")).sendKeys("50");
        driver.findElement(By.name("new_images[]")). sendKeys(path);
    }

    private void fillTabInformation() {
        Select manufact = new Select(driver.findElement(By.name("manufacturer_id")));
        manufact.selectByVisibleText("ACME Corp.");
        driver.findElement(By.name("short_description[en]")).sendKeys("duck");
        driver.findElement(By.className("trumbowyg-editor")).sendKeys("and donald trump in one person");
    }

    private void fillTabPrices() {
        driver.findElement(By.name("purchase_price")).sendKeys(Keys.CONTROL + "a" + Keys.DELETE );
        driver.findElement(By.name("purchase_price")).sendKeys("11");
        Select curr_code = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        curr_code.selectByVisibleText("Euros");
        driver.findElement(By.name("prices[USD]")).sendKeys("17");
    }

    private void checkNewItem(String item) {
        String name;
        WebElement root = driver.findElement(By.cssSelector("table.dataTable tbody"));
        List<WebElement> list = root.findElements(By.xpath(".//tr/td[3]/a"));
        for (WebElement we : list) {
            name = we.getText();
            if (name.equals(item) ) {
                break;
            }
        }
    }

    private void searchAndClick(String linkList, String text) {
        List<WebElement> list = driver.findElements(By.cssSelector(linkList));
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
