package webTests.utils;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class CastomActions {

    public static void waitForVisibilityAndClick(SelenideElement element) {
        element.should(exist);
        element.shouldBe(visible).click();
    }

    public static void enterText(SelenideElement element, String text) {
        element.shouldBe(visible).setValue(text);
    }

    public static void selectDate(SelenideElement calendarElement, String startDate, String endDate) {
        calendarElement.click();

        SelenideElement startDateElement = $(By.cssSelector((String.format("[data-date='%s']", startDate))));
        waitForVisibilityAndClick(startDateElement);

         SelenideElement endDateElement = $(By.cssSelector((String.format("[data-date='%s']", endDate))));
         waitForVisibilityAndClick(endDateElement);
    }

    public static void handleCookieConsent() {
        SelenideElement cookieConsentButton = $(By.id("onetrust-accept-btn-handler"));
        if (cookieConsentButton.isDisplayed()) {
            waitForVisibilityAndClick(cookieConsentButton);
            cookieConsentButton.should(disappear);
        }
    }

    public static void closeSignPopup() {
        SelenideElement closeSignUpButton = $(By.cssSelector("button[aria-label='Скрыть меню входа в аккаунт.']"));
        if (closeSignUpButton.isDisplayed()) {
            waitForVisibilityAndClick(closeSignUpButton);
            closeSignUpButton.should(disappear);
        }
    }

    public static void scrollToElement(SelenideElement element) {
        element.scrollIntoView(false).shouldBe(visible);
    }

    public static void verifyHoverEffect(SelenideElement element, String expectedColor) {
        String originalBackground = element.getCssValue("background-color");

        element.hover();

        sleep(2000);
        element.shouldHave(cssValue("background-color", expectedColor));
        element.shouldNotHave(cssValue("background-color", originalBackground));
    }

    public static void hoverAndWaitForTooltip(SelenideElement element, SelenideElement tooltip) {
        element.hover();
        sleep(2000);
        tooltip.shouldBe(visible);
    }

    public static void waitForVisibilityAndDoubleClick(SelenideElement element) {
        element.should(exist);
        element.shouldBe(visible).doubleClick();
    }

    public static void waitForVisibilityAndRightClick(SelenideElement element) {
        element.should(exist);
        element.shouldBe(visible).contextClick();
    }



}
