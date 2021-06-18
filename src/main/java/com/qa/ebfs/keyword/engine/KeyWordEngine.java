package com.qa.ebfs.keyword.engine;

import com.qa.ebfs.keyword.base.Base;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.*;
import java.util.Properties;


public class KeyWordEngine {

    public WebDriver driver;
    public Properties prop;

    public static Workbook book;
    public static Sheet sheet;

    public Base base;
    public WebElement element;

    public static String path = System.getProperty("user.dir");

    public final String SCENARIO_SHEET_PATH = (path + "/src/main/java/com/qa/ebfs/keyword/testdata/ebfs.scenarios.xlsx");

    //String Path_Object = (path + "/src/main/java/com/qa/ebfs/keyword/properties/Object Repository.properties");



    public void startExecution(String sheetName){

        FileInputStream file = null;
        try {
            file = new FileInputStream(SCENARIO_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            book = WorkbookFactory.create(file);
        } catch (InvalidFormatException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = book.getSheet(sheetName);

        int k = 0;
        for(int i=0; i<sheet.getLastRowNum(); i++){
            try{
            String locatorType = sheet.getRow(i+1).getCell(k+1).toString().trim();
            String locatorValue = sheet.getRow(i+1).getCell(k+2).toString().trim();

            String action = sheet.getRow(i+1).getCell(k+3).toString().trim();
            String value = sheet.getRow(i+1).getCell(k+4).toString().trim();

                switch (action){
                    case "open browser":
                        base = new Base();
                        prop = base.init_properties();
                        if(value.isEmpty() || value.equals("NA")){
                            driver = base.init_driver(prop.getProperty("browser"));
                        }else{
                            driver = base.init_driver(value);
                        }
                        break;

                    case "enter url":
                        if(value.isEmpty() || value.equals("NA")){
                            driver.get(prop.getProperty("url"));
                        }else{
                            driver.get(value);
                        }
                        break;

                    case "quit":
                        driver.quit();
                        break;

                    default:
                        break;
                }

            switch (locatorType){
                case "id":
                    element = driver.findElement(By.id(locatorValue));
                        if(action.equalsIgnoreCase("sendkeys")){
                            element.clear();
                            element.sendKeys(value);
                        }else if(action.equalsIgnoreCase("click")){
                            element.click();
                        }else if(action.equalsIgnoreCase("isDisplayed")){
                            element.isDisplayed();
                        }else if(action.equalsIgnoreCase("isEnabled")){
                            element.isEnabled();
                        }else if(action.equalsIgnoreCase("getText")){
                            String elementText = element.getText();
                            System.out.println("text from element : " + elementText);
                        }
                    locatorType = null;
                    break;

                case "xpath":
                    base = new Base();
                    prop = base.init_properties();
                    element = driver.findElement(By.xpath(prop.getProperty(locatorValue)));
                    if(action.equalsIgnoreCase("sendkeys")){
                        element.clear();
                        element.sendKeys(value);
                    }else if(action.equalsIgnoreCase("click")){
                        element.click();
                    }else if(action.equalsIgnoreCase("isDisplayed")){
                        element.isDisplayed();
                    }else if(action.equalsIgnoreCase("isEnabled")){
                        element.isEnabled();
                    }else if(action.equalsIgnoreCase("getText")){
                        String elementText = element.getText();
                        System.out.println("text from element : " + elementText);
                    }else if(action.equalsIgnoreCase("select")){
                        Select days = new Select(driver.findElement(By.xpath(prop.getProperty(locatorValue))));
                        days.selectByValue(value);
                    }
                    locatorType = null;
                    break;

                case "name":
                    element = driver.findElement(By.name(locatorValue));
                    if(action.equalsIgnoreCase("sendkeys")){
                        element.clear();
                        element.sendKeys(value);
                    }else if(action.equalsIgnoreCase("click")){
                        element.click();
                    }else if(action.equalsIgnoreCase("isDisplayed")){
                        element.isDisplayed();
                    }else if(action.equalsIgnoreCase("isEnabled")){
                        element.isEnabled();
                    }else if(action.equalsIgnoreCase("getText")){
                        String elementText = element.getText();
                        System.out.println("text from element : " + elementText);
                    }
                    locatorType = null;
                    break;

                case "className":
                    element = driver.findElement(By.className(locatorValue));
                    if(action.equalsIgnoreCase("sendkeys")){
                        element.clear();
                        element.sendKeys(value);
                    }else if(action.equalsIgnoreCase("click")){
                        element.click();
                    }else if(action.equalsIgnoreCase("isDisplayed")){
                        element.isDisplayed();
                    }else if(action.equalsIgnoreCase("isEnabled")){
                        element.isEnabled();
                    }else if(action.equalsIgnoreCase("getText")){
                        String elementText = element.getText();
                        System.out.println("text from element : " + elementText);
                    }
                    locatorType = null;
                    break;

                case "cssSelector":
                    element = driver.findElement(By.cssSelector(locatorValue));
                    if(action.equalsIgnoreCase("sendkeys")){
                        element.clear();
                        element.sendKeys(value);
                    }else if(action.equalsIgnoreCase("click")){
                        element.click();
                    }else if(action.equalsIgnoreCase("isDisplayed")){
                        element.isDisplayed();
                    }else if(action.equalsIgnoreCase("isEnabled")){
                        element.isEnabled();
                    }else if(action.equalsIgnoreCase("getText")){
                        String elementText = element.getText();
                        System.out.println("text from element : " + elementText);
                    }
                    locatorType = null;
                    break;

                case "linkText":
                    element = driver.findElement(By.linkText(locatorValue));
                    element.click();
                    locatorType = null;
                    break;

                case "partialLinkText":
                    element = driver.findElement(By.partialLinkText(locatorValue));
                    element.click();
                    locatorType = null;
                    break;

                default:
                    break;
            }
        }
            catch (Exception e){
            }
        }



    }

    public static void waitFor(String object) throws Exception{
        Thread.sleep(5000);
    }













    /*public WebDriver driver;
    public Properties prop;

    public static Workbook book;
    public static Sheet sheet;

    public Base base;
    public WebElement element;

    //String path = System.getProperty("user.dir");

    public final String SCENARIO_SHEET_PATH = "/Users/khaledhasan/Desktop/Java_Github/Frame_Work/KeywordDrivenEBFS/src/main/java/com/qa/ebfs/keyword/testdata/ebfs.scenarios.xlsx";

    //File file = new File(path+"/src/main/java/com/qa/ebfs/keyword/testdata/Object Repository.properties");





    public void startExecution(String sheetName){

        String locatorName = null;
        String locatorValue = null;

        FileInputStream file = null;
        try {
            file = new FileInputStream(SCENARIO_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            book = WorkbookFactory.create(file);
        } catch (InvalidFormatException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = book.getSheet(sheetName);

        int k = 0;
        for(int i=0; i<sheet.getLastRowNum(); i++){
            try{
                String locatorColValue = sheet.getRow(i+1).getCell(k+1).toString().trim(); // id=email
                if(!locatorColValue.equalsIgnoreCase("NA")){
                    locatorName = locatorColValue.split("=")[0].trim(); //id
                    locatorValue = locatorColValue.split("=")[1].trim(); //email
                }
                String action = sheet.getRow(i+1).getCell(k+2).toString().trim();
                String value = sheet.getRow(i+1).getCell(k+3).toString().trim();

                switch (action){
                    case "open browser":
                        base = new Base();
                        prop = base.init_properties();
                        if(value.isEmpty() || value.equals("NA")){
                            driver = base.init_driver(prop.getProperty("browser"));
                        }else{
                            driver = base.init_driver(value);
                        }
                        break;

                    case "enter url":
                        if(value.isEmpty() || value.equals("NA")){
                            driver.get(prop.getProperty("url"));
                        }else{
                            driver.get(value);
                        }
                        break;

                    case "quit":
                        driver.quit();
                        break;
                    default:
                        break;
                }

                switch (locatorName){
                    case "id":
                        element = driver.findElement(By.id(locatorValue));
                        if(action.equalsIgnoreCase("sendkeys")){
                            element.clear();
                            element.sendKeys(value);
                        }else if(action.equalsIgnoreCase("click")){
                            element.click();
                        }
                        locatorName = null;
                        break;

                    case "xpath":
                        element = driver.findElement(By.xpath(locatorValue));
                        if(action.equalsIgnoreCase("sendkeys")){
                            element.clear();
                            element.sendKeys(value);
                        }else if(action.equalsIgnoreCase("click")){
                            element.click();
                        }
                        locatorName = null;
                        break;

                    case "linkText":
                        element = driver.findElement(By.linkText(locatorValue));
                        element.click();
                        locatorName = null;
                        break;

                    default:
                        break;
                }
            }
            catch (Exception e){
            }
        }

    }*/



}
