package com.gitlab.rmarzec.framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Task4TestUtils {

    @FindBy(css = "#content")
    protected WebElement videoDetails;

    protected WebDriver webDriver;

    private final Waiter waiter;


    List<YTTile> ytTileList = new ArrayList<>();


    public Task4TestUtils(WebDriver webDriver) {
        this.webDriver = webDriver;

        PageFactory.initElements(webDriver, this);

        waiter = new Waiter(webDriver, By.className("#logo-icon"));
    }

    public void goToYouTube() {webDriver.get("https://www.youtube.com/");}

    public void cookiesWaiter() {
        var cookieWaiter = new Waiter(webDriver, By.cssSelector("#topbar"));
        cookieWaiter.waitForElementToDisappear();
    }

    public void acceptCookies() {
        webDriver
                .findElement(By.cssSelector(".eom-buttons > .eom-button-row:nth-child(1) > ytd-button-renderer:nth-child(2)"))
                .click();
    }

    private Collection<? extends YTTile> getTitles() {
        List<String> titlesList = null;
        for (var i = 1; i <= 12; i++) {
            var oneVideo = videoDetails.findElement(By.xpath("//div[@id='contents]'[" + i + "]"));
            var videoTitle = oneVideo.findElement(By.cssSelector("#video-title")).getText().trim();
            titlesList.add(videoTitle);
        }
        return titlesList;
    }

    private Collection<? extends YTTile> getVideoLength() {
        List<String> videoLengthList = null;
        for (var i = 1; i <= 12; i++) {
            var oneVideo = videoDetails.findElement(By.xpath("//div[@id='contents]'[" + i + "]"));
            var videoLength = oneVideo.findElement(By.cssSelector("ytd-thumbnail-overlay-time-status-renderer span")).getText().trim();
            videoLengthList.add(videoLength);
        }
        return videoLengthList;
    }

    private Collection<? extends YTTile> getUserChannel() {
        List<String> userList = null;
        for (var i = 1; i <= 12; i++) {
            var oneVideo = videoDetails.findElement(By.xpath("//div[@id='contents]'[" + i + "]"));
            var userChannel = oneVideo.findElement(By.cssSelector(".ytd-channel-name a")).getText().trim();
            userList.add(userChannel);
        }
        return userList;
    }

    public YTTile fillYtList() {
        ytTileList.addAll(Objects.requireNonNull(getTitles()));
        ytTileList.addAll(Objects.requireNonNull(getUserChannel()));
        ytTileList.addAll(Objects.requireNonNull(getVideoLength()));
        return (YTTile) ytTileList;
    }
}
