package com.gitlab.rmarzec.framework.utils;

import lombok.AllArgsConstructor;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@AllArgsConstructor
public class Waiter {

    private final WebDriver webDriver;
    private final By locator;
    public static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(2);

    public void waitForElementToDisappear() { waitForElementToDisappear(DEFAULT_TIMEOUT);}

    public void waitForElementToDisappear(Duration timeout) {
        var fluentWait = new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(1))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);

        try {
            var element = fluentWait.until(x -> x.findElement(locator));

            if (element != null) {
                var wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeout.toSeconds()));
                wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            }
        } catch (TimeoutException e) {
            //no operation
        }

   }
}