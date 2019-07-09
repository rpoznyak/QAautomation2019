package ui.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;


import static org.slf4j.LoggerFactory.getLogger;

public class BrowserFactory {

    private Logger logger = LoggerFactory.getLogger(BrowserFactory.class);
    public static WebDriver driver;

    @BeforeSuite
    public  void setUpSuite() {
        ChromeOptions options = new ChromeOptions();
        //options.add("--disable-notifications");
        System.setProperty("webdriver.chrome.driver", "C:/Webdriver/chromedriver.exe");
        driver = new ChromeDriver(options);
    }

    @BeforeTest
    public void setUp(){
        driver = new ChromeDriver();
        logger.info("Browser Started");
    }

    //@AfterTest
    public void tearDown(){
        driver.quit();
        logger.info("Browser Closed");

    }

    public static WebDriver driver(){
        return driver;
    }


    public static void get(String url){
        driver().get(url);
    }

    public static WebDriverWait getWebDriverWait(long timeout){
        return new WebDriverWait(driver(), timeout);
    }
}
