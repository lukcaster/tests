package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.framework.utils.Task4TestUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Task4Test {

    private Task4TestUtils task4TestUtils;

    @BeforeMethod
    public void setUp() {
        DriverFactory driverFactory = new DriverFactory();
        WebDriver webDriver = driverFactory.initDriver();

        task4TestUtils = new Task4TestUtils(webDriver);
        task4TestUtils.goToYouTube();
    }

    @Test
    public void Task4Test(){
        //When
        task4TestUtils.cookiesWaiter();
        task4TestUtils.acceptCookies();


        //Then
        task4TestUtils.fillYtList();
    }
}
