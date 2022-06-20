package com.gitlab.rmarzec.framework.utils;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class Task2TestUtils {

    @FindBy(css = "#mw-panel")
    protected WebElement leftPanel;

    protected WebDriver webDriver;

    private final Waiter waiter;

    protected String wikiUrl = "https://pl.wikipedia.org/wiki/Wiki";

    public Task2TestUtils(WebDriver webDriver) {
        this.webDriver = webDriver;

        PageFactory.initElements(webDriver, this);

        waiter = new Waiter(webDriver, By.className("h2"));
    }

    public void goToWikiPage() {
        webDriver.get(wikiUrl);
    }

    public void waitForDataToLoad() {
        waiter.waitForElementToDisappear();
    }

    public void allLanguages() {
        var wholeList = leftPanel
                .findElements(By.cssSelector(".interlanguage-link a"));

        for(WebElement webElement : wholeList) {
            if (webElement.getAttribute("lang").equals("en")) {
                String name = webElement.getText() + " " + webElement.getAttribute("href");
                System.out.println(name);
            }
            if (!webElement.getAttribute("lang").equals("en")) {
                String restOfTheNames = webElement.getText();
                System.out.println(restOfTheNames);
            }
        }
    }
}
