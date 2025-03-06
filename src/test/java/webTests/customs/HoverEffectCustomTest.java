package webTests.customs;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static webTests.utils.CastomActions.*;

public class HoverEffectCustomTest {
    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1366x768";
        Configuration.headless = false;
        Configuration.timeout = 10000;
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
    public void heckHoverTest() {
        open("https://productstar.ru/");

        SelenideElement selectCourseButton = $x("//a[contains(normalize-space(),'Каталог курсов')]");
        verifyHoverEffect(selectCourseButton, "rgba(255, 255, 0, 1)");
        sleep(2000);
    }

    @Test
    public void tooltipAppearanceTest() {
        open("https://jqueryui.com/tooltip/");
        switchTo().frame($(".demo-frame"));

        SelenideElement ageField = $(By.id("age"));
        SelenideElement tooltip = $(By.className("ui-tooltip"));

        hoverAndWaitForTooltip(ageField, tooltip);

        SelenideElement tooltipLink = $x("//a[normalize-space()='Tooltips']");
        hoverAndWaitForTooltip(tooltipLink, tooltip);
    }
}
