package webTests.workshop;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MapTests {
    private final String url = "https://www.open-user-map.com/demo/";

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1366x768";
        Configuration.headless = false;
        Configuration.timeout = 10000;
    }

    @BeforeEach
    public void openModalPage() {
        open(url);
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
    public void zoomTest() {
        $(".leaflet-control-zoom-in").shouldBe(visible).click();
        SelenideElement controlImage = $x("//img[@title='Fakistra']");
        controlImage.shouldBe(visible);
        SelenideElement zoomOutButton = $(".leaflet-control-zoom-out");
        zoomOutButton.shouldHave(attribute("aria-disabled", "false"));
        zoomOutButton.click();
        controlImage.should(disappear);
        zoomOutButton.click();
        zoomOutButton.shouldHave(attribute("aria-disabled", "true"));

    }

    @Test
    public void movingMapTest() {
        SelenideElement zoomInButton = $(".leaflet-control-zoom-in");
        zoomInButton.click();
        zoomInButton.click();
        zoomInButton.click();
        SelenideElement controlImage = $x("//img[@title='Fakistra']");
        controlImage.shouldNotBe(visible);
        sleep(3000);

        SelenideElement mapBody = $(By.id("map-20210929"));
        actions().moveToElement(mapBody).clickAndHold().moveByOffset(-70, -70).release().build().perform();
        controlImage.shouldBe(visible);
        sleep(4000);
    }
}
