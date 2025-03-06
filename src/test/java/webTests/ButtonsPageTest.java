package webTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import webTests.Pages.ButtonsPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static webTests.utils.CastomActions.*;

public class ButtonsPageTest {
    private final String url = "https://demoqa.com/buttons";

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1366x768";
        Configuration.headless = false;
        Configuration.timeout = 10000;
    }

    @BeforeEach
    public void openModalPage() {
        open(url);
        executeJavaScript("document.body.style.zoom='50%'");
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
    public void doubleClickButtonTest() {
        ButtonsPage buttonsPage = new ButtonsPage();
        buttonsPage.isButtonPageLoaded();

        waitForVisibilityAndDoubleClick(buttonsPage.getDoubleClickButton());

        buttonsPage.getDoubleClickMessage()
                .shouldBe(visible)
                .shouldHave(Condition.text("You have done a double click"));
    }

    @Test
    public void rightClickButtonTest() {
        ButtonsPage buttonsPage = new ButtonsPage();
        buttonsPage.isButtonPageLoaded();

        waitForVisibilityAndRightClick(buttonsPage.getRightClickButton());

        buttonsPage.getRightClickMessage()
                .shouldBe(visible)
                .shouldHave(Condition.text("You have done a right click"));
    }

    @Test
    public void oneClickButtonTest() {
        ButtonsPage buttonsPage = new ButtonsPage();
        buttonsPage.isButtonPageLoaded();

        waitForVisibilityAndClick(buttonsPage.getOneClickButton());

        buttonsPage.getOneClickMessage()
                .shouldBe(visible)
                .shouldHave(Condition.text("You have done a dynamic click"));
    }
}
