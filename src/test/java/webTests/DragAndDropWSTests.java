package webTests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;

public class DragAndDropWSTests {
    private final String url = "https://www.globalsqa.com/demo-site/draganddrop/";

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1366x768";
        Configuration.headless = false;
        Configuration.timeout = 10000;
    }

    @BeforeEach
    public void openDragAndDropPage() {
        open(url);

        SelenideElement settingsButton = $x("//p[@class='fc-button-label' and normalize-space(text())='Настройки']");
        if (settingsButton.isDisplayed()) {
            settingsButton.click();
            SelenideElement confirmButton = $x("//p[@class='fc-button-label' and normalize-space(text())='Подтвердить']");
            confirmButton.click();
            confirmButton.should(disappear);
        }

        //executeJavaScript("document.body.style.zoom='50%'");
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
    public void dragAndDropTest() {
        SelenideElement tabPhotoManager = $(By.id("Photo Manager"));
        tabPhotoManager.shouldBe(visible).click();

        SelenideElement pictureFrame = $(".demo-frame.lazyloaded");
        switchTo().frame(pictureFrame);

        SelenideElement sourceElement = $x("//h5[normalize-space()='High Tatras']");
        sourceElement.shouldBe(visible);

        SelenideElement targetElement = $(By.id("trash"));
        targetElement.shouldBe(visible);

        actions().clickAndHold(sourceElement).moveToElement(targetElement).release().build().perform();
        //actions().dragAndDrop(sourceElement, targetElement);
        sleep(1000);

        ElementsCollection galleryElements = targetElement.$$(".gallery.ui-helper-reset");
        galleryElements.shouldHave(sizeGreaterThan(0));



    }
}
