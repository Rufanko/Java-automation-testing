package localhost;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ZonesAndCountries extends Settings {



    @Test
    public void mainTest() {
        setUp();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        checkCountries();
        checkZones();
    }


    private void checkZones() {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        String  zone;
        String root = "td#content table.dataTable tbody";
        String table = ".//tr/td[3]//option[@selected='selected']";
        int count = driver.findElements(By.cssSelector("td#content table.dataTable tr.row")).size();
        for (int i=1; i<=count; i++) {
            zone = ".//tr[@class='row'][" + i + "]/td[3]/a";
            currentElement(root, zone).click();
            checkSortInList(root, table, true, true);
            CancelClick();
            driver.findElement(By.cssSelector(root));
        }
    }

    private void checkCountries() {
        String root = "td#content table.dataTable tbody";
        String table;

        table = "./tr[@class='row']/td[6]";
        List<WebElement> listWE= fillListWE(root, table);
        List<String> listS = fillList(listWE, true, true);
        int i = 1;
        for (String zone:listS){
            if (!zone.equals("0")) {
                table = "./tr[@class='row'][" + i +"]/td[5]/a";
                currentElement(root, table).click();
                table = "./tr/td[3]";
                checkSortInList(root, table, false, false);
                CancelClick();
                driver.findElement(By.cssSelector(root));
            }
            i++;
        }
    }
    private void CancelClick() {
        driver.findElement(By.cssSelector("td#content p button[name=cancel]")).click();
    }

    private WebElement currentElement(String rootLoc, String element) {
        WebElement root = driver.findElement(By.cssSelector(rootLoc));
        return root.findElement(By.xpath(element));
    }

    private void checkSortInList(String rootLoc, String tableLoc, boolean first, boolean last){
        List<WebElement> listWE = fillListWE(rootLoc, tableLoc);
        List<String> list = fillList(listWE, first, last);
        isListSorted(list);
    }

    private List<WebElement> fillListWE(String rootLoc, String tableLoc){
        WebElement root = driver.findElement(By.cssSelector(rootLoc));
        List<WebElement> listWE = root.findElements(By.xpath(tableLoc));
        return listWE;
    }

    private List<String> fillList(List<WebElement> listWE, boolean first, boolean last) {
        List<String> resultList = new ArrayList<>();
        for (WebElement we:listWE)
            resultList.add(we.getAttribute("textContent"));
        if (!first) resultList.remove(0);
        if (!last) resultList.remove(resultList.size()-1);
        return resultList;
    }

    private boolean isListSorted(List<String> list) {
        String previous = list.get(0);
        String current;
        boolean result = true;
        for (int i=1; i<list.size(); i++) {
            current = list.get(i);
            if (current.compareTo(previous) < 0) {
                result = false;
                break;
            }
            previous = current;
        }
        return result;
    }



}

