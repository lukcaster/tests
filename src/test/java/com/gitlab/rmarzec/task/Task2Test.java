package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.framework.utils.Task2TestUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class Task2Test {

    private Task2TestUtils task2TestUtils;

    protected WebDriver webDriver;

    @BeforeMethod
    public void setUp() {
        DriverFactory driverFactory = new DriverFactory();
        WebDriver webDriver = driverFactory.initDriver();

        task2TestUtils = new Task2TestUtils(webDriver);
        task2TestUtils.goToWikiPage();
        task2TestUtils.waitForDataToLoad();
    }

    @Test
    public void Task2Test(){
        //When
        task2TestUtils.allLanguages();

        //Then
        webDriver.quit();
    }
}
