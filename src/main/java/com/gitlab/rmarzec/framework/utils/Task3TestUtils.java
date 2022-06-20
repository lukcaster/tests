package com.gitlab.rmarzec.framework.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Task3TestUtils {


    @FindBy(css = "input.gLFyf")
    protected WebElement searchInput;

    @FindBy(css = ".CqAVzb")
    protected WebElement listAfterInput;

    @FindBy(css = ".w3-example")
    protected WebElement examples;

    @FindBy(css = "#cars")
    protected WebElement carsMenu;

    protected WebDriver webDriver;

    private final Waiter waiter;

    private final String googleUrl = "https://www.google.com/";

    private final String desiredUrl = "https://www.w3schools.com/tags/tag_select.asp";

    private static Logger logger = LoggerFactory.getLogger(Task3TestUtils.class);

    public Task3TestUtils(WebDriver webDriver) {
        this.webDriver = webDriver;

        PageFactory.initElements(webDriver, this);

        waiter = new Waiter(webDriver, By.className(".lnXdpd"));
    }

    public void waitForDataToLoad() {
        waiter.waitForElementToDisappear();
    }

    public void waitForListToShow() {
        var listWaiter = new Waiter(webDriver, By.cssSelector("div"));
        listWaiter.waitForElementToDisappear();
    }

    public void waitForW3SchoolsCookies() {
        var w3Waiter = new Waiter(webDriver, By.cssSelector("#sn-back"));
        w3Waiter.waitForElementToDisappear();
    }

    public void goToGoogle() {webDriver.get(googleUrl);}

    @AllArgsConstructor
    @Getter
    public enum CookiesOptions {
        ACCEPT(  2),
        DECLINE(  1);


        private final int row;
    }

    public void googleCookiesOperator(CookiesOptions cookiesOptions) {
        var locator = String.format(".VDity button:nth-child(%d)", cookiesOptions.row);
        var locator2 = String.format(".GzLjMd button:nth-child(%d)", cookiesOptions.row);

        try {
            webDriver
                    .findElement(By.cssSelector(locator))
                    .click();
        } catch (NoSuchElementException e) {
            webDriver
                    .findElement(By.cssSelector(locator2))
                    .click();
        }
    }

    public void w3CookiesOperator(CookiesOptions cookiesOptions) {
        var w3locator = String.format(".sn-action-btn > div:nth-child(%d)", cookiesOptions.row);

        webDriver
                .findElement(By.cssSelector(w3locator))
                .click();
    }

    public void searchFor(String phrase) {
        searchInput.click();
        searchInput.sendKeys(phrase);
    }

    public void selectTypeOfSearch(String type) {
        listAfterInput
                .findElements(By.cssSelector("input"))
                .stream()
                .filter(x -> x.getAttribute("value").equals(type))
                .findAny()
                .ifPresent(WebElement::click);
    }

    public void checkIfUrlIsCorrect() {
        if (!webDriver.getCurrentUrl().equals(desiredUrl)) {
            webDriver.get(desiredUrl);
        }
    }

    public void selectFirstExample() {
        examples
                .findElements(By.cssSelector("a"))
                .stream()
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public String getHeader() {
        var text = webDriver
                .findElement(By.cssSelector("h1"))
                .getText()
                .trim();
        logger.warn(text);
        return text;
    }

    public WebElement selectCarFromDropdown(String car) {
        var element = carsMenu
                .findElements(By.cssSelector("option"))
                .stream()
                .filter(x -> x.getText().equals(car))
                .findFirst()
                .orElseThrow();
            element.click();
            return element;
    }

    public String getChosenCarNameAndValue(WebElement element) {
        var carName = element.getText().trim();
        var carValue = element.getAttribute("value").trim();
        return "( " + carName + ", " + carValue + ")";
    }
}
