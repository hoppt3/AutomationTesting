package resources.com.fundingsocieties;

import common.UI;
import dataProvider.ConfigFileReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pageobjects.BasePage;
import pageobjects.Homepage;
import pageobjects.Statistics;

public class TestCases {
    WebDriver driver ;
    WebDriverWait wait;
    Homepage homepage ;
    Statistics statisticsPage;
    BasePage basePage;
    UI commonUI ;
    JavascriptExecutor js;
    Actions actions;
    ConfigFileReader configFileReader = new ConfigFileReader();

    @BeforeMethod
    public void setBaseUrl() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        commonUI = new UI(driver, wait);
        basePage = new BasePage(commonUI);
        homepage = new Homepage(commonUI);
        statisticsPage = new Statistics(commonUI);
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        driver.get(configFileReader.getApplicationUrl() );
        driver.manage().window().maximize();
    }

    @Test
    public void FullFlow() {
        try {
            homepage.NavigateToStatistics(driver);
            statisticsPage.WaitUntilLoadingDisppeared();
            statisticsPage.VerifyGeneralTabIsDisplayed();
            statisticsPage.VerifyTextComponentsOnStatistics();
            statisticsPage.VerifyTabsOnStatistics();
            statisticsPage.ScrollToChart(statisticsPage.generalChartXpath);
            statisticsPage.GetTotalApprovedChart();
            statisticsPage.GetTotalAmountDisbursedChart();
            statisticsPage.GetDefaultRateChart();
            commonUI.ClickOnTab(statisticsPage.repaymentTabXpath, statisticsPage.itemOnRepaymentChartXpath);
            commonUI.waitUntilElementVisible(statisticsPage.repaymentChartXpath, 30, 10);
            statisticsPage.GetRepaymentChart();
            commonUI.ClickOnTab(statisticsPage.disbursementTabXpath, statisticsPage.disbursementChartXpath);
            commonUI.waitUntilElementVisible(statisticsPage.disbursementChartXpath, 30, 10);
            statisticsPage.GetDisbursementChart();
        } catch (
                Exception e) {
            System.out.println("Something's at checking full flow");
            throw (e);
        }
    }

    @Test
    public void CheckGeneralTab() {
        try {
            homepage.NavigateToStatistics(driver);
            statisticsPage.WaitUntilLoadingDisppeared();
            statisticsPage.VerifyGeneralTabIsDisplayed();
            statisticsPage.VerifyTextComponentsOnStatistics();
            statisticsPage.VerifyTabsOnStatistics();
            statisticsPage.ScrollToChart(statisticsPage.generalChartXpath);
            statisticsPage.GetTotalApprovedChart();
            statisticsPage.GetTotalAmountDisbursedChart();
            statisticsPage.GetDefaultRateChart();
        } catch (
                Exception e) {
            System.out.println("Something's at checking general tab");
            throw (e);
        }
    }

    @Test
    public void CheckRepaymentTab() {
        try {
            homepage.NavigateToStatistics(driver);
            statisticsPage.WaitUntilLoadingDisppeared();
            commonUI.ClickOnTab(statisticsPage.repaymentTabXpath, statisticsPage.itemOnRepaymentChartXpath);
            statisticsPage.ScrollToChart(statisticsPage.repaymentChartXpath);
            statisticsPage.GetRepaymentChart();
        } catch (
                Exception e) {
            System.out.println("Something's wrong at checking repayment tab");
            throw (e);
        }
    }

    @Test
    public void CheckDisbursementTab() {
        try {
            homepage.NavigateToStatistics(driver);
            statisticsPage.WaitUntilLoadingDisppeared();
            commonUI.ClickOnTab(statisticsPage.disbursementTabXpath, statisticsPage.disbursementChartXpath);
            statisticsPage.ScrollToChart(statisticsPage.disbursementChartXpath);
            statisticsPage.GetDisbursementChart();
        } catch (
                Exception e) {
            System.out.println("Something's wrong at checking disbursement tab");
            throw (e);
        }
    }

    @AfterMethod
    public void endSession() {
        driver.quit();
        System.out.println("Finished test case");
    }
}