import org.example.Pigu;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.example.Pigu.SEARCH_KEYWORD;
import static org.example.Pigu.browser;

public class PiguTest {

    @BeforeClass
    public static void setup() {
        Pigu.setup();
        Pigu.closeCookies();
    }

    @AfterClass
    public static void close() {
        Pigu.closeBrowser();
    }

    @Test (priority = 1)
    public void isEnabledDisplayed() {
        boolean resultActual = Pigu.isEnabledDisplayed(browser.findElement(By.className("sn-suggest-input")));
        Assert.assertTrue(resultActual);
    }

    @Test (priority = 2)
    public void isEnabledDisplayedNegative() {
        boolean resultActual =! Pigu.isEnabledDisplayed(browser.findElement(By.className("sn-suggest-input")));
        Assert.assertFalse(resultActual);
    }

    @Test (priority = 3)
    public void findKeywordUrl() {
        Pigu.searchByKeyword(SEARCH_KEYWORD);
        String resultActual = Pigu.findKeywordUrl(SEARCH_KEYWORD);
        Assert.assertEquals("Paieškos kriterijus '" + SEARCH_KEYWORD + "' YRA atvaizduojamas url adrese.", resultActual);
    }

    @Test (priority = 4)
    public void findKeywordUrlNegative() {
        String resultActual = Pigu.findKeywordUrl(SEARCH_KEYWORD);
//        System.out.println(browser.getCurrentUrl());
        Assert.assertNotEquals("Paieškos kriterijus '" + SEARCH_KEYWORD + "' NĖRA atvaizduojamas url adrese.", resultActual);
    }

    @Test  (priority = 5)
    public void findKeywordInTitle() {
        Pigu.waitElement(By.cssSelector("div[id^='_0productBlock']"));
        Pigu.hoverElement(browser.findElement(By.cssSelector("div[id^='_0productBlock']")));
        Pigu.waitElement(By.cssSelector(".product-name a"));
        Pigu.clickOnElementWithExecutor(By.cssSelector(".product-name a"));
        String resultActual = Pigu.checkTitle(SEARCH_KEYWORD);
        Assert.assertEquals("Paieškos kriterijus '" + SEARCH_KEYWORD + "' YRA atvaizduojamas pavadinime.", resultActual);
    }

    @Test  (priority = 6)
    public void findKeywordInTitleNegative() {
        String resultActual = Pigu.checkTitle(SEARCH_KEYWORD);
        Assert.assertNotEquals("Paieškos kriterijus '" + SEARCH_KEYWORD + "' NĖRA atvaizduojamas pavadinime.", resultActual);
    }

    @Test (priority = 7)
    public void isModalDisplayed() {
        Pigu.checkItemName(browser.findElement(By.tagName("h1")), SEARCH_KEYWORD);
        Pigu.clickOnElement(By.cssSelector(".c-product__controls .c-btn--primary"));
        Pigu.waitElement(By.id("modal"));
        boolean resultActual = Pigu.isModalContains(browser.findElement(By.id("modal")),browser.findElement(By.className("add-to-cart-modal-title")),"prekė įtraukta");
        Assert.assertTrue(resultActual);
    }

    @Test (priority = 8)
    public void isModalDisplayedNegative() {
        boolean resultActual =! Pigu.isModalContains(browser.findElement(By.id("modal")),browser.findElement(By.className("add-to-cart-modal-title")),"prekė įtraukta");
        Assert.assertFalse(resultActual);
    }
}
