package common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.*;

public class UI extends  Driver{
    public UI(WebDriver driver, WebDriverWait wait){
//        this.driver = driver;
//        this.wait = wait;
        super(driver, wait);
    }
    public void clickButton(String xpath, int timeWait){
        WebElement disbursementBtnEle = new WebDriverWait(driver, timeWait).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        disbursementBtnEle.click();
    }

    public void scrollToElement(JavascriptExecutor js, String xpath, int timeWait){
        WebElement element = new WebDriverWait(driver, timeWait).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        js.executeScript("arguments[0].scrollIntoView();",element);
    }

    public void waitUntilElementVisible(String xpath, int timeWait,int  pollingEvery){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeWait))
                .pollingEvery(Duration.ofSeconds(pollingEvery))
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
    public void waitUntilElementClickable(String xpath, int timeWait,int  pollingEvery){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeWait))
                .pollingEvery(Duration.ofSeconds(pollingEvery))
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    public void sortMapByValue(Map<String, Double> map){
        List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        for (String key : sortedMap.keySet())
            System.out.println(key );
    }

    public void ClickOnTab(String tabXpath, String waitingItemXpath){
        clickButton(tabXpath, 2);
        waitUntilElementVisible(waitingItemXpath, 30, 10);
    }

}
