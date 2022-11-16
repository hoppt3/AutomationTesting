package pageobjects;

import common.UI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homepage extends BasePage{
    public String statisticsTabXpath = "//div[@class='navbar']//ul[@class='nav-menu__items']//a[text()[contains(.,'Stati')]]";
    public  Homepage(UI commonUI){
        super(commonUI);
    }
    public void NavigateToStatistics(WebDriver driver){
        WebElement statisticsTabEle = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(statisticsTabXpath)));
        statisticsTabEle.click();
    }
}
