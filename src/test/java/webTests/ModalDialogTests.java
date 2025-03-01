package webTests;

import com.codeborne.selenide.*;
import com.codeborne.selenide.selector.ByTagAndText;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ModalDialogTests {
    private final String url = "https://www.lambdatest.com/selenium-playground/bootstrap-modal-demo";

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
    public void verifyModalDialogBox() {
        SelenideElement singleModalButton =$x("//button[@data-target='#myModal']");
        singleModalButton.shouldBe(visible).click();

        SelenideElement modalDialog = $(By.className("modal-dialog"));
        modalDialog.shouldBe(visible);
        modalDialog.$(By.className("modal-body")).shouldHave(Condition.text("This is the place where the content for the modal dialog displays"));

        sleep(1000);

        modalDialog.$(Selectors.byTagAndText("button", "Save Changes")).click();
        modalDialog.should(disappear);
    }

    @Test
    public void multiModalDialogBox() {
        SelenideElement multiModalButton = $x("//button[@data-target='#myMultiModal']");
        multiModalButton.shouldBe(visible).click();

        SelenideElement firstModalDialog = $(By.id("myMultiModal"));
        firstModalDialog.shouldBe(visible);
        firstModalDialog.$(By.className("modal-body")).shouldHave(Condition.text("This is the place where the content for the modal dialog displays."));

        sleep(2000);

        firstModalDialog.$(By.xpath(".//button[@data-target='#mySecondModal']")).shouldBe(visible).click();

        SelenideElement secondModalDialog = $(By.id("mySecondModal"));
        secondModalDialog.$(By.className("modal-body")).shouldBe(visible).shouldHave(Condition.text("This is the place where the content for the modal dialog displays."));

        secondModalDialog.$(Selectors.byTagAndText("button", "Save Changes")).click();
        secondModalDialog.should(disappear);
        firstModalDialog.shouldBe(visible);

        actions().sendKeys(Keys.ESCAPE).perform();
        firstModalDialog.shouldNotBe(visible);
    }



}
