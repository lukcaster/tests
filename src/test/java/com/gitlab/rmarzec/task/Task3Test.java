package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.BrowserHandler;
import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.framework.utils.Task3TestUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.gitlab.rmarzec.framework.utils.Task3TestUtils.CookiesOptions.ACCEPT;

public class Task3Test {

    private Task3TestUtils task3TestUtils;

    private final String searchPhrase = "HTML select tag - W3Schools";

    private final String luckyShot = "Szczęśliwy traf";

    private static Logger logger = LoggerFactory.getLogger(Task3Test.class);

    private BrowserHandler browserHandler;

    @BeforeMethod
    public void setUp() {
        DriverFactory driverFactory = new DriverFactory();
        WebDriver webDriver = driverFactory.initDriver();

        browserHandler = new BrowserHandler(webDriver);

        task3TestUtils = new Task3TestUtils(webDriver);
        task3TestUtils.goToGoogle();
        task3TestUtils.waitForDataToLoad();
    }

    @Test
    public void Task3Test(){
        //When
        task3TestUtils.googleCookiesOperator(ACCEPT);

        task3TestUtils.waitForDataToLoad();

        task3TestUtils.searchFor(searchPhrase);
        task3TestUtils.waitForListToShow();

        task3TestUtils.selectTypeOfSearch(luckyShot);

        //Then
        task3TestUtils.checkIfUrlIsCorrect();
        task3TestUtils.waitForW3SchoolsCookies();

        task3TestUtils.w3CookiesOperator(ACCEPT);
        task3TestUtils.waitForDataToLoad();

        //When
        task3TestUtils.selectFirstExample();
        task3TestUtils.waitForDataToLoad();

        browserHandler.switchTabTo(1);
        task3TestUtils.waitForDataToLoad();
        browserHandler.switchToFrame("#iframeResult");
        //Then
        var header = task3TestUtils.getHeader();
        logger.info(header);
        var chosenCar = task3TestUtils.selectCarFromDropdown("Opel");

        var car = task3TestUtils.getChosenCarNameAndValue(chosenCar);
        logger.info(car);
    }
}
