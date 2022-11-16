package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driver {
    public WebDriver driver;
    public WebDriverWait wait;
    public Driver(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }
}
