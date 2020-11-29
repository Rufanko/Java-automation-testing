import localhost.Settings;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;



public class loginTest extends Settings {
    



    @Test
    public void TestLogin() throws InterruptedException {
        setUp();
        driver.get("http://localhost/litecart/admin/login.php?redirect_url=%2Flitecart%2Fadmin%2F");
        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("admin");
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("admin");
        WebElement loginButton = driver.findElement(By.name("login"));
        loginButton.click();
        clickOnMenu();



    }


    public void clickOnMenu() throws InterruptedException {
        List<WebElement> elementList = driver.findElements(By.cssSelector("#app-"));
        int numberOfListElements = elementList.size();


        for (int i = 0; i < numberOfListElements; i++) {

            elementList = driver.findElements(By.cssSelector("#app-"));
            elementList.get(i).click();
            Thread.sleep(200);
            List<WebElement> elementList2 = driver.findElements((By.cssSelector("#app- li")));

            int numberOfUl = elementList2.size();
            System.out.println(numberOfUl);

            for (int k = 0; k < numberOfUl; k++) {
                elementList2 = driver.findElements((By.cssSelector("#app- li")));

                elementList2.get(k).click();
                Thread.sleep(200);


            }

        }

    }


}
