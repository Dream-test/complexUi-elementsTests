package webTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import webTests.Pages.ToolTipsPage;

import static com.codeborne.selenide.Selenide.*;
import static webTests.utils.CastomActions.hoverAndWaitForTooltip;

public class ToolTipsPageTest {
    private final String url = "https://demoqa.com/tool-tips";

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
    public void hoverMeButtonTooltipTest() {
        ToolTipsPage toolTipsPage = new ToolTipsPage();

        hoverAndWaitForTooltip(toolTipsPage.getHoverMeButton(), toolTipsPage.getTooltipElement());
    }

    @Test
    public void hoverMeFieldTooltipTest() {
        ToolTipsPage toolTipsPage = new ToolTipsPage();

        hoverAndWaitForTooltip(toolTipsPage.getHoverMeField(), toolTipsPage.getTooltipElement());
    }

    @Test
    public void hoverContraryElementTest() {
        ToolTipsPage toolTipsPage = new ToolTipsPage();

        hoverAndWaitForTooltip(toolTipsPage.getContraryElement(), toolTipsPage.getTooltipElement());
    }

    @Test
    public void hoverNumberElementTest() {
        ToolTipsPage toolTipsPage = new ToolTipsPage();

        hoverAndWaitForTooltip(toolTipsPage.getNumberElement(), toolTipsPage.getTooltipElement());
    }
}
