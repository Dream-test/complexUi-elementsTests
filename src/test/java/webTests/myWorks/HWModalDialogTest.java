package webTests.myWorks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import webTests.PageElements.AnimationDialogElements;
import webTests.PageElements.DefaultFuncDialogElements;
import webTests.PageElements.ModalFormDialogElements;
import webTests.Pages.ModalDialogPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HWModalDialogTest {
    private final String url = "https://jqueryui.com/dialog/";

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
    public void defaultModalDialogTest() {
        //Arrange, Act
        switchExamplesMenuTo("Default functionality");
        DefaultFuncDialogElements defaultFuncDialogElements = new DefaultFuncDialogElements();

        //Assert
        assertTrue(defaultFuncDialogElements.getDialogTitle().contains("Basic dialog"));
        defaultFuncDialogElements.getDialogBody()
                .$(By.tagName("p"))
                .shouldHave(text("This is the default dialog which is useful for displaying information."));

        //Act
        defaultFuncDialogElements.closeModalDialog();

        //Assert
        defaultFuncDialogElements.getDialogBody().shouldNotBe(visible);
    }

    @Test
    public void animationModalDialogTest() {
        //Arrange
        switchExamplesMenuTo("Animation");
        AnimationDialogElements animationDialogElements = new AnimationDialogElements();

        //Act
        animationDialogElements.getOpenDialogButton().click();

        //Assert
        assertTrue(animationDialogElements.getDialogTitle().contains("Basic dialog"));
        animationDialogElements.getDialogBody()
                .$(By.tagName("p"))
                .shouldHave(text("This is an animated dialog which is useful for displaying information. The dialog window can be moved, resized and closed with the 'x' icon."));

        //Act
        animationDialogElements.closeModalDialog();

        //Assert
        animationDialogElements.getDialogBody().shouldNotBe(visible);
    }

    @Test
    public void modalFormTest() {
        //Arrange
        switchExamplesMenuTo("Modal form");
        Faker faker = new Faker();
        String userName = faker.name().firstName();
        String userEmail = faker.internet().emailAddress();
        String userPassword = faker.internet().password();
        ModalFormDialogElements modalFormDialogElements = new ModalFormDialogElements();

        //Act
        modalFormDialogElements.openFormDialog();
        modalFormDialogElements.inputUserName(userName);
        modalFormDialogElements.inputUserEmail(userEmail);
        modalFormDialogElements.inputUserPassword(userPassword);
        modalFormDialogElements.closeFormDialog();

        //Assert
        boolean userExist = modalFormDialogElements.isUserPresent(userName, userEmail, userPassword);
        assertTrue(userExist);
    }

    private void switchExamplesMenuTo(String menuButton) {
        ModalDialogPage modalDialogPage = new ModalDialogPage();
        modalDialogPage.isModalDialogPageLoaded();
        SelenideElement desiredMenuButton = modalDialogPage.getMenuTable().$(Selectors.byTagAndText("a", menuButton));
        desiredMenuButton.click();
        desiredMenuButton.parent().shouldHave(cssClass("active"));

        modalDialogPage.switchToDemoFrame();
    }
}
