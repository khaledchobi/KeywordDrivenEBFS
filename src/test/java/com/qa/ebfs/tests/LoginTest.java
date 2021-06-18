package com.qa.ebfs.tests;

import com.qa.ebfs.keyword.engine.KeyWordEngine;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    public KeyWordEngine keyWordEngine;

    @Test
    public void loginTest(){
        keyWordEngine = new KeyWordEngine();
        keyWordEngine.startExecution("login");

    }

    @Test
    public void signUpTest(){
        keyWordEngine = new KeyWordEngine();
        keyWordEngine.startExecution("signup");

    }

    @Test
    public void signUpWrongTest(){
        keyWordEngine = new KeyWordEngine();
        keyWordEngine.startExecution("signupwe");
        //keyWordEngine.driver.quit();

    }

}
