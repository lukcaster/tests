package com.gitlab.rmarzec.framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Set;

public class BrowserHandler {

    private final WebDriver webDriver;

    public BrowserHandler(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void switchTabTo(int tabNumber) {
        ArrayList<String> tabs = new ArrayList<String> (webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(tabNumber));
    }

    public void switchToFrame(String frameName) {
        webDriver.switchTo().frame(webDriver.findElement(By.cssSelector(frameName)));
    }
}
