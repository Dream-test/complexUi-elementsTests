package webTests.customs;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static webTests.utils.CastomActions.*;


public class CustomActionsTest {private final String url = "https://www.booking.com/";

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1366x768";
        Configuration.headless = false;
        Configuration.timeout = 10000;
    }

    @BeforeEach
    public void openModalPage() {
        open(url);
        sleep(3000);
        handleCookieConsent();
    }

    @AfterEach
    public void cleanBrowser() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @AfterAll
    public static void tearDownAll() {
        closeWebDriver();
    }

    @Test
    public void hotelBookingTest() {

        enterText($(By.id(":rh:")), "New York");

        selectDate($(Selectors.byAttribute("data-testid", "searchbox-dates-container")), "2025-03-10", "2025-03-15");

        waitForVisibilityAndClick($(By.cssSelector("button[type='submit']")));

        sleep(3000);

        closeSignPopup();

        waitForVisibilityAndClick($(By.cssSelector("div[data-filters-item='class:class=4']")));

        $(By.cssSelector("label[data-testid='filter:class=4']")).should(appear);

        waitForVisibilityAndClick($(By.cssSelector("div[data-testid='title']")));

        switchTo().window(1); //switch to new tab (number start from "0")

        SelenideElement bookNowButton = $(By.id("hp_book_now_button"));
        bookNowButton.$(By.cssSelector(".bui-button__text")).shouldBe(visible).shouldHave(Condition.text("Забронировать"));
        waitForVisibilityAndClick(bookNowButton);

        $(By.cssSelector(".hp-dates-summary__header")).should(appear);

    }

    @Test
    public void scrollToElementTest() {

        enterText($(By.id(":rh:")), "New York");

        selectDate($(Selectors.byAttribute("data-testid", "searchbox-dates-container")), "2025-03-10", "2025-03-15");

        waitForVisibilityAndClick($(By.cssSelector("button[type='submit']")));

        sleep(3000);

        closeSignPopup();

        waitForVisibilityAndClick($(By.cssSelector("div[data-filters-item='class:class=4']")));

        $(By.cssSelector("label[data-testid='filter:class=4']")).should(appear);

        waitForVisibilityAndClick($(By.cssSelector("div[data-testid='title']")));

        switchTo().window(1); //switch to new tab (number start from "0")

       SelenideElement conditionBox = $(By.id("hp_policies_box"));

       scrollToElement(conditionBox);

    }

    @Test
    public void tooltipAppearanceTest() {

        enterText($(By.id(":rh:")), "New York");

        selectDate($(Selectors.byAttribute("data-testid", "searchbox-dates-container")), "2025-03-10", "2025-03-15");

        waitForVisibilityAndClick($(By.cssSelector("button[type='submit']")));

        sleep(3000);

        closeSignPopup();

        SelenideElement taxiProposition = $(By.cssSelector("span[data-testid='property-card-deal']"));
        SelenideElement tooltip = $(By.cssSelector(".f998a7c837.ec5d8a4630"));

        hoverAndWaitForTooltip(taxiProposition, tooltip);



    }



}
