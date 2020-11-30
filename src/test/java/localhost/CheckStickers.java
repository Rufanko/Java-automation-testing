package localhost;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class CheckStickers extends Settings {
    @Test
    public void findStickers() {
        setUp();
        driver.get("http://localhost/litecart/");
        List<WebElement> elementList =  driver.findElements(By.cssSelector(".product"));
        int numberOfListElements = elementList.size();

        for (int i = 0; i < numberOfListElements; i++){
            elementList = driver.findElements(By.cssSelector(".product"));
            WebElement product = elementList.get(i);
            int sticker = product.findElements(By.cssSelector(".sticker")).size();
            if (sticker != 1){
                throw  new AssertionError();
            }

        }

    }

}
