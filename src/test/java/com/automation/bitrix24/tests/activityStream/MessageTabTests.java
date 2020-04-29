package com.automation.bitrix24.tests.activityStream;

import com.automation.bitrix24.pages.LoginPage;
import com.automation.bitrix24.pages.activityStream.MessageTabPage;
import com.automation.bitrix24.tests.AbstractTestBase;
import com.automation.bitrix24.utilities.BrowserUtils;
import com.automation.bitrix24.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MessageTabTests extends AbstractTestBase {

    //peer review :
    //Nice and clean codes, proud of you.
    //Just FYI >> When you have more than one test in same class, if you have any issue
    // you may need to create page class object in every test method

    MessageTabPage messageTabPage = new MessageTabPage();
    LoginPage loginPage = new LoginPage();

    @Test
    public void createQuote() {
        loginPage.loginAsHelpDesk();
        messageTabPage.clickMessage();
        messageTabPage.clickQuoteIcon();
        String expected = "new quote";
        String actual = messageTabPage.getQuoteInputText("new quote");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void attachLink() {
        loginPage.loginAsHelpDesk();
        messageTabPage.clickMessage();
        messageTabPage.clickLinkIcon();
        messageTabPage.enterLinkTextInput("Google");
        messageTabPage.enterLinkURLInput("www.google.com");
        messageTabPage.clickLinkSaveBtn();
        Assert.assertEquals(messageTabPage.getLinkInBodyText(), "Google");
    }

    @Test
    public void addUsers() {
        loginPage.loginAsHr();
        messageTabPage.clickMessage();
        messageTabPage.clickToInputAddMore();
        messageTabPage.clickOnPeopleWithNum(1);
        Assert.assertEquals(messageTabPage.getAddedToInputText(), messageTabPage.getPeopleWithNumText(1));
    }

    @Test
    public void addVideo() {
        String videoUrlYouTube = "https://www.youtube.com/watch?v=ad7L2g6HmQs&list=UUMi0CoZlu1_2ItKY25to5eQ&index=2";
        String videoUrlVimeo = "https://vimeo.com/384129480";
        loginPage.loginAsHr();
        //go to youtube and search for storytime
        //click the first video
        //copy url and save it as a string

        messageTabPage.clickMessage();
        messageTabPage.clickVideoIcon();
        messageTabPage.enterVideoSourceInput(videoUrlVimeo);
        BrowserUtils.wait(5);
        Assert.assertTrue(messageTabPage.videoTitle.isDisplayed());
    }

}
