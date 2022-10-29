package com.newtours.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.newtours.page.HomePage;
import com.newtours.page.LoginPage;
import org.apache.log4j.Logger;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public class RegressionTesting {

    //String driverPath = "C:\\Users\\Admin\\Documents\\Kingsley Automation Profile\\geckodriver.exe";
	String driverPath = "C:\\Users\\Admin\\Documents\\Kingsley Automation Profile\\chromedriver.exe";
    
    WebDriver driver;

    LoginPage objLogin;

    HomePage objHomePage; 

    
    static ExtentTest test;
    static ExtentReports report;
    
    @BeforeClass
    public static void startTest()
    {
    report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
    test = report.startTest("ExtentDemo");
    }
    
    @Test(priority=0)
    public void setup(){

        //System.setProperty("webdriver.gecko.driver", driverPath);
    	System.setProperty("webdriver.chrome.driver", driverPath);
        
        //driver = new FirefoxDriver()
        driver = new ChromeDriver();

        driver.get("http://demo.guru99.com/V4/");
        Logger log = Logger.getLogger("devpinoyLogger");
        log.debug("opening webiste");
        log.debug("closing webiste");


    }

    /**

     * This test go to http://demo.guru99.com/V4/

     * Verify login page title as guru99 bank

     * Login to application

     * Verify the home page using Dashboard message

     */

    @Test(priority=1)

    public void test_Home_Page_Appear_Correct(){

        //Create Login Page object

    objLogin = new LoginPage(driver);

    //Verify login page title

    String loginPageTitle = objLogin.getLoginTitle();

    Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

    //login to application

    objLogin.loginToGuru99("mgr123", "mgr!23");

    // go the next page

    objHomePage = new HomePage(driver);

    //Verify home page

    Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mgr123"));

    }
    
    @AfterClass
    public static void endTest()
    {
    report.endTest(test);
    report.flush();
    }

}