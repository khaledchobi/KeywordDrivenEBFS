package com.qa.ebfs.keyword.base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    public WebDriver driver;
    public Properties prop;
    public static String path = System.getProperty("user.dir");
    String Path_Object = (path + "/src/main/java/com/qa/ebfs/keyword/properties/Object Repository.properties");

    public WebDriver init_driver(String browserName) throws InterruptedException {
        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "/Users/khaledhasan/Desktop/Java_Github/Frame_Work/KeywordDrivenEBFS/src/main/resources/drivers/chromedriver");
            if (prop.getProperty("headless").equals("yes")) {
                // headless mode
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
            } else {
                driver = new ChromeDriver();
            }
        }else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "/Users/khaledhasan/Desktop/Java_Github/Frame_Work/KeywordDrivenEBFS/src/main/resources/drivers/geckodriver");
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return driver;
    }


    public Properties init_properties() {
        prop = new Properties();

        try {
            FileInputStream ip = new FileInputStream("/Users/khaledhasan/Desktop/Java_Github/Frame_Work/KeywordDrivenEBFS/src/main/java/com/qa/ebfs/keyword/config/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream or = new FileInputStream(Path_Object);
            prop.load(or);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }

    /*public static void waitFor(String object) throws Exception{
        Thread.sleep(5000);
    }*/
}