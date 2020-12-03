package localhost;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class registrationScript extends Settings {


    @Test
    public void mainTest() throws InterruptedException {
        setUp();
        driver.get("http://localhost/litecart/en/");

        String email = "rufanko@pyahoo.com";
        String password = "bestpassword";

        createAccount(email, password);
        Thread.sleep(1000);

        logout();
        Thread.sleep(1000);

        login(email, password);
        Thread.sleep(1000);

        logout();
        Thread.sleep(1000);
    }

    private void createAccount(String email, String password) throws InterruptedException {
        driver.findElement(By.cssSelector("form[name='login_form'] table tr:last-child")).click();
        Thread.sleep(1000);
        driver.findElement(By.name("firstname")).sendKeys("Lavrentiy");
        driver.findElement(By.name("lastname")).sendKeys("Beriya");
        driver.findElement(By.name("address1")).sendKeys("Vodniy stadion");
        driver.findElement(By.name("postcode")).sendKeys("32242");
        driver.findElement(By.name("city")).sendKeys("Boston");
        Select country = new Select(driver.findElement(By.name("country_code")));
        country.selectByVisibleText("United States");
        Thread.sleep(1000);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("+18452245869");
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);
        driver.findElement(By.name("create_account")).click();
        Select zone = new Select(driver.findElement(By.cssSelector("select[name='zone_code']")));
        zone.selectByVisibleText("Massachusetts");
        driver.findElement(By.name("create_account")).click();
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);
        driver.findElement(By.name("create_account")).click();


    }

    private void login(String email, String password) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }

    private void logout() {
        driver.findElement(By.cssSelector("div#box-account div.content li:last-child a")).click();
    }

}
