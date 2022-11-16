package pageobjects;

import common.UI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Statistics extends  BasePage{
    public String loadingImageXpath = "(//*[@class='HYPE_element' and contains(@id, 'hype-obj')])[1]";
    public String statisticsDetailRowXpath = "//div[contains(@class, 'detailStatistic')]//div[@class='statisticDetailRow']";
    public String totalFundedTxtXpath="//div[text()='Total funded']";
    public String noOfFinancingTxtXpath="//div[text()='financing']";
    public String defaultRateTxtXpath="//div[@class='statisticDetailRow']/div[3]/div";
    public String financingFullfillmentRateTxtXpath="//div[@class='statisticDetailRow']/div[4]/div";
    public String generalTabXpath = "//button[contains(text(),'General')]";
    public String repaymentTabXpath = "//button[contains(text(),'Repayment')]";
    public String disbursementTabXpath = "//button[contains(text(),'Disbursement')]";
    public String generalChartXpath = "(//*[contains(@class,'highcharts-markers highcharts-serie')])[1]";
    public String pointOnChartXpath = "(//*[contains(@class,'highcharts-markers highcharts-serie')])[1]//*[name()='path']";
    public String eachPointOnChartXpath = "(//*[contains(@class,'highcharts-markers highcharts-serie')])[1]//*[name()='path'][%s]";
    public String valueXpath = "//*[contains(@class,'tooltip')]//*[name()='text']//*[4]";
    public String amountDisbursedBtnXpath = "//*[text()='Amount disbursed']";
    public String defaultRateBtnXpath = "//label[text()='Default rate']";
    public String repaymentChartXpath = "(//*[local-name()='svg'])[1]//*[name()='g' and @class='highcharts-series-group']";
    public String itemOnRepaymentChartXpath = "(//*[local-name()='svg'])[1]//*[name()='g' and @class='highcharts-series-group']//*[name()='rect']";
    public String repaymentInformationTextXpath = "(//*[local-name()='svg'])[1]//*[contains(@class,'highcharts-tooltip') and name()='g']//*[name()='text']";
    public String disbursementChartXpath = "(//*[local-name()='svg'])[1]//*[contains(@class,'highcharts-series-group')]//*[name()='path']";
    public String industryNameTextXpath = "(//*[local-name()='svg'])[1]//*[contains(@class,'tooltip') and name()='g']//*[name()='text']/*[name()='tspan'][1]";
    public String disbursementpercentageValueXpath = "(//*[local-name()='svg'])[1]//*[contains(@class,'tooltip') and name()='g']//*[name()='text']/*[name()='tspan'][4]";

    JavascriptExecutor js = (JavascriptExecutor) commonUI.driver;

    public Statistics(UI commonUI){
        super(commonUI);
        this.commonUI = commonUI;
    }

    public void WaitUntilLoadingDisppeared(){
        By loadingImageBy = By.xpath(loadingImageXpath);
        commonUI.wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(loadingImageBy)));
    }

    public boolean VerifyGeneralTabIsDisplayed() {
        commonUI.waitUntilElementClickable(amountDisbursedBtnXpath, 30, 10);
        boolean status = commonUI.driver.findElement(By.xpath(generalTabXpath)).isDisplayed();
        return status;
    }

    public void VerifyTextComponentsOnStatistics() {
        boolean totalFundedStatus = commonUI.driver.findElement(By.xpath(totalFundedTxtXpath)).isDisplayed();
        if (totalFundedStatus) {
            System.out.println("Total funded value is displayed");
        } else {
            System.out.println("Total funded value is not displayed");
        }
        boolean noOfFinancingStatus = commonUI.driver.findElement(By.xpath(noOfFinancingTxtXpath)).isDisplayed();
        if (noOfFinancingStatus) {
            System.out.println("No. of financing value is displayed");
        } else {
            System.out.println("No. of financing value is not displayed");
        }
        boolean defaultRateStatus = commonUI.driver.findElement(By.xpath(defaultRateTxtXpath)).isDisplayed();
        if (defaultRateStatus) {
            System.out.println("Default rate value is displayed");
        } else {
            System.out.println("Default rate value is not displayed");
        }
        boolean financingFulfillmentRateStatus = commonUI.driver.findElement(By.xpath(financingFullfillmentRateTxtXpath)).isDisplayed();
        if (financingFulfillmentRateStatus) {
            System.out.println("Financing fulfillment value is displayed");
        } else {
            System.out.println("Financing fulfillment value is not displayed");
        }
    }

    public void VerifyTabsOnStatistics(){
        boolean generalTabStatus = commonUI.driver.findElement(By.xpath(generalTabXpath)).isDisplayed();
        if (generalTabStatus) {
            System.out.println("General tab is displayed");
        } else {
            System.out.println("General tab is not displayed");
        }
        boolean repaymentTabStatus = commonUI.driver.findElement(By.xpath(repaymentTabXpath)).isDisplayed();
        if (repaymentTabStatus) {
            System.out.println("Repayment tab is displayed");
        } else {
            System.out.println("Repayment tab is not displayed");
        }
        boolean disbursementTabStatus = commonUI.driver.findElement(By.xpath(disbursementTabXpath)).isDisplayed();
        if (disbursementTabStatus) {
            System.out.println("Disbursement tab is displayed");
        } else {
            System.out.println("Disbursement tab is not displayed");
        }
    }
    public void ScrollToChart(String chartXpath){
        commonUI.scrollToElement(js, statisticsDetailRowXpath, 2);
        commonUI.waitUntilElementVisible(chartXpath, 30, 10);
    }

// Get total approved chart
    public void GetTotalApprovedChart() {
        Actions actions = new Actions(commonUI.driver);
        List<WebElement> PointOnGeneralChartList = commonUI.driver.findElements(By.xpath(pointOnChartXpath));
        int location = 1;
        String index = "";
        String formattedPointOnChartXpath = "";
        commonUI.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
        for (int i = 2; i <= PointOnGeneralChartList.size() ; i++) {
            location = i - 1;
            index = i + "";
            formattedPointOnChartXpath = String.format(eachPointOnChartXpath, index);
            WebElement point2OnChartElement = commonUI.driver.findElement(By.xpath(formattedPointOnChartXpath));
            actions.moveToElement(point2OnChartElement).perform();
            point2OnChartElement.click();
            String text = commonUI.driver.findElement(By.xpath(valueXpath)).getText();
            System.out.println("Total disbursed is  " + location + " : " + text);
        }
        commonUI.driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS) ;
    }

//  Get the total amount disbursed
    public void GetTotalAmountDisbursedChart(){
        Actions actions = new Actions(commonUI.driver);
        commonUI.clickButton(amountDisbursedBtnXpath, 2);
        commonUI.waitUntilElementVisible(generalChartXpath, 30, 10);
        List<WebElement> PointOnGeneralChartList = commonUI.driver.findElements(By.xpath(pointOnChartXpath));
        String formattedPointOnChartXpath = "";
        int location = 1;
        String index = "";
        commonUI.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
        for (int i = 2; i <= PointOnGeneralChartList.size() + 1; i++) {
            location = i - 1;
            index = i + "";
            formattedPointOnChartXpath = String.format(eachPointOnChartXpath, index);
            WebElement pointOnChartElement = commonUI.driver.findElement(By.xpath(formattedPointOnChartXpath));
            actions.click(pointOnChartElement).perform();
            String text = commonUI.driver.findElement(By.xpath(valueXpath)).getText();
            System.out.println("Amount disbursed is " + location + " : " + text);
        }
        commonUI.driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS) ;
    }

//   Get the default rate
    public void GetDefaultRateChart(){
        Actions actions = new Actions(commonUI.driver);
        commonUI.clickButton(defaultRateBtnXpath, 10);
        commonUI.waitUntilElementVisible(generalChartXpath, 30, 10);
        List<WebElement> PointOnGeneralChartList = commonUI.driver.findElements(By.xpath(pointOnChartXpath));
        String formattedPointOnChartXpath = "";
        int location = 1;
        String index = "";
        commonUI.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
        for (int i = 2; i <= PointOnGeneralChartList.size(); i++) {
            location = i - 1;
            index = i + "";
            formattedPointOnChartXpath = String.format(eachPointOnChartXpath, index);
            WebElement pointOnChartElement = commonUI.driver.findElement(By.xpath(formattedPointOnChartXpath));
            actions.click(pointOnChartElement).perform();
            String text = commonUI.driver.findElement(By.xpath(valueXpath)).getText();
            System.out.println("Default rate is " + location + " : " + text);
        }
        commonUI.driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS) ;
    }

//   Go to Repayment tab and get total repayment amount, principal amount and interest amount
    public void GetRepaymentChart(){
        Actions actions = new Actions(commonUI.driver);
        List<WebElement> repaymentChartItemList = commonUI.driver.findElements(By.xpath(itemOnRepaymentChartXpath));
        commonUI.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        for (int i = 0; i < repaymentChartItemList.size(); i++) {
            actions.moveToElement(repaymentChartItemList.get(i)).perform();
            String text = commonUI.driver.findElement(By.xpath(repaymentInformationTextXpath)).getText();
            text = text.replace("●", "");
            System.out.println(text);
        }
        commonUI.driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

//   Go to Disbursement tab and store all industry names according percentage  (increasing order)
    public void GetDisbursementChart(){
        Actions actions = new Actions(commonUI.driver);
        List<WebElement> disbursementChartItemList = commonUI.driver.findElements(By.xpath(disbursementChartXpath));
        Map<String, Double> map = new HashMap<String, Double>();
        commonUI.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        for (int i = 0; i < disbursementChartItemList.size(); i++) {
            actions.moveToElement(disbursementChartItemList.get(i)).perform();
            String industryNameTxt = commonUI.driver.findElement(By.xpath(industryNameTextXpath)).getText();
            Double percentageValue = Double.parseDouble(commonUI.driver.findElement(By.xpath(disbursementpercentageValueXpath)).getText());
            map.put(industryNameTxt, percentageValue);
        }
        commonUI.driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        commonUI.sortMapByValue(map);
    }

}