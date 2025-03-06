package webTests.myWorks;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import webTests.PageElements.*;
import webTests.Pages.DroppablePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HWDragAndDropTest {
    private final String url = "https://jqueryui.com/droppable/";

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
    public void dragAndDropDefaultTest() {
        //Assert
        DroppablePage droppablePage = new DroppablePage();
        droppablePage.isDroppablePageLoaded();
        SelenideElement defaultFunctionalityButton = droppablePage.getMenuTable().$(Selectors.byTagAndText("a", "Default functionality"));
        defaultFunctionalityButton.click();
        defaultFunctionalityButton.parent().shouldHave(Condition.cssClass("active"));

        droppablePage.switchToDemoFrame();
        DefaultFrameElements defaultFrameElements = new DefaultFrameElements();

        defaultFrameElements.getTargetSourceElement().$(By.cssSelector("p")).shouldHave(text("Drop here"));

        //Act
        actions().clickAndHold(defaultFrameElements.getSourceElement()).moveByOffset(145, 10).release().build().perform();
        sleep(3000);

        //Assert
        SelenideElement targetElement = defaultFrameElements.getTargetSourceElement();
        targetElement.shouldHave(Condition.cssClass("ui-state-highlight"));
        targetElement.$(By.cssSelector("p")).shouldHave(text("Dropped!"));
    }

    @Test
    public void acceptTest() {
        //Arrange
        switchExamplesMenuTo("Accept");

        AcceptFrameElements acceptFrameElements = new AcceptFrameElements();
        acceptFrameElements.getTargetSourceElement().$(By.cssSelector("p")).shouldHave(text("accept: '#draggable'"));

        //Act
        actions().clickAndHold(acceptFrameElements.getNotDroppedSourceElement()).moveByOffset(290, 50).release().build().perform();

        //Assert
        acceptFrameElements.getNotDroppedSourceElement().shouldHave(attribute("style", "position: relative; left: 290px; top: 50px;"));
        acceptFrameElements.getTargetSourceElement().$(By.cssSelector("p")).shouldHave(text("accept: '#draggable'"));

        //Act
        actions().clickAndHold(acceptFrameElements.getDroppedSourceElement()).moveToElement(acceptFrameElements.getTargetSourceElement()).release().build().perform();
        sleep(3000);

        //Assert
        SelenideElement targetElement = acceptFrameElements.getTargetSourceElement();
        targetElement.shouldHave(cssClass("ui-state-highlight"));
        targetElement.$(By.cssSelector("p")).shouldHave(text("Dropped!"));
    }

    @Test
    public void preventPropagationFirstOuterTest() {
        //Arrange
        switchExamplesMenuTo("Prevent propagation");

        PreventPropFrameElements preventPropFrameElements = new PreventPropFrameElements();
        preventPropFrameElements.getFirstOuterTargetSourceElement().$(By.cssSelector("p")).shouldHave(text("Outer droppable"));
        preventPropFrameElements.getFirstInnerNGTargetSourceElement().$(By.cssSelector("p")).shouldHave(text("Inner droppable (not greedy)"));

        //Act
        actions().clickAndHold(preventPropFrameElements.getSourceElement()).moveByOffset(195, 5).release().build().perform();

        //Assert
        preventPropFrameElements.getSourceElement().shouldHave(attribute("style", "position: relative; left: 195px; top: 5px;"));
        preventPropFrameElements.getFirstOuterTargetSourceElement().$(By.cssSelector("p")).shouldHave(text("Dropped!"));
        preventPropFrameElements.getFirstOuterTargetSourceElement().shouldHave(Condition.cssClass("ui-state-highlight"));
        preventPropFrameElements.getFirstInnerNGTargetSourceElement().$(By.cssSelector("p")).shouldNotHave(text("Dropped!"));
        preventPropFrameElements.getFirstInnerNGTargetSourceElement().shouldNotHave(cssClass("ui-state-highlight"));
    }

    @Test
    public void preventPropagationFirstInnerTest() {
        //Arrange
        switchExamplesMenuTo("Prevent propagation");

        PreventPropFrameElements preventPropFrameElements = new PreventPropFrameElements();
        preventPropFrameElements.getFirstOuterTargetSourceElement().$(By.cssSelector("p")).shouldHave(text("Outer droppable"));
        preventPropFrameElements.getFirstInnerNGTargetSourceElement().$(By.cssSelector("p")).shouldHave(text("Inner droppable (not greedy)"));

        //Act
        actions().clickAndHold(preventPropFrameElements.getSourceElement()).moveToElement(preventPropFrameElements.getFirstInnerNGTargetSourceElement()).release().build().perform();

        //Assert
        preventPropFrameElements.getFirstOuterTargetSourceElement().$(By.cssSelector("p")).shouldHave(text("Dropped!"));
        preventPropFrameElements.getFirstOuterTargetSourceElement().shouldHave(Condition.cssClass("ui-state-highlight"));
        preventPropFrameElements.getFirstInnerNGTargetSourceElement().$(By.cssSelector("p")).shouldHave(text("Dropped!"));
        preventPropFrameElements.getFirstInnerNGTargetSourceElement().shouldHave(cssClass("ui-state-highlight"));
    }

    @Test
    public void preventPropagationSecondOuterTest() {
        //Arrange
        switchExamplesMenuTo("Prevent propagation");

        PreventPropFrameElements preventPropFrameElements = new PreventPropFrameElements();
        preventPropFrameElements.getSecondOuterTargetSourceElement().$(By.cssSelector("p")).shouldHave(text("Outer droppable"));
        preventPropFrameElements.getSecondInnerTargetSourceElement().$(By.cssSelector("p")).shouldHave(text("Inner droppable (greedy)"));

        //Act
        actions().clickAndHold(preventPropFrameElements.getSourceElement()).moveByOffset(65, 165).release().build().perform();

        //Assert
        preventPropFrameElements.getSourceElement().shouldHave(attribute("style", "position: relative; left: 65px; top: 165px;"));
        preventPropFrameElements.getSecondOuterTargetSourceElement().$(By.cssSelector("p")).shouldHave(text("Dropped!"));
        preventPropFrameElements.getSecondOuterTargetSourceElement().shouldHave(Condition.cssClass("ui-state-highlight"));
        preventPropFrameElements.getSecondInnerTargetSourceElement().$(By.cssSelector("p")).shouldNotHave(text("Dropped!"));
        preventPropFrameElements.getSecondInnerTargetSourceElement().shouldNotHave(cssClass("ui-state-highlight"));
    }

    @Test
    public void preventPropagationSecondInnerTest() {
        //Arrange
        switchExamplesMenuTo("Prevent propagation");

        PreventPropFrameElements preventPropFrameElements = new PreventPropFrameElements();
        preventPropFrameElements.getSecondOuterTargetSourceElement().$(By.cssSelector("p")).shouldHave(text("Outer droppable"));
        preventPropFrameElements.getSecondInnerTargetSourceElement().$(By.cssSelector("p")).shouldHave(text("Inner droppable (greedy)"));

        //Act
        actions().clickAndHold(preventPropFrameElements.getSourceElement()).moveToElement(preventPropFrameElements.getSecondInnerTargetSourceElement()).release().build().perform();

        //Assert
        preventPropFrameElements.getSecondOuterTargetSourceElement().$(By.cssSelector("p")).shouldNotHave(text("Dropped!"));
        preventPropFrameElements.getSecondOuterTargetSourceElement().shouldNotHave(Condition.cssClass("ui-state-highlight"));
        preventPropFrameElements.getSecondInnerTargetSourceElement().$(By.cssSelector("p")).shouldHave(text("Dropped!"));
        preventPropFrameElements.getSecondInnerTargetSourceElement().shouldHave(cssClass("ui-state-highlight"));
    }

    @Test
    public void revertWhenDroppedTest() {
        //Arrange
        switchExamplesMenuTo("Revert draggable position");

        RevertDraggableFrameElements revertDraggableFrameElements = new RevertDraggableFrameElements();
        revertDraggableFrameElements.getRevertWhenDroppedSourceElement().$(By.cssSelector("p")).shouldHave(text("I revert when I'm dropped"));
        revertDraggableFrameElements.getTargetSourceElement().$(By.cssSelector("p")).shouldHave(text("Drop me here"));
        revertDraggableFrameElements.getTargetSourceElement().shouldNotHave(cssClass("ui-state-highlight"));

        //Act
        actions().clickAndHold(revertDraggableFrameElements.getRevertWhenDroppedSourceElement()).moveToElement(revertDraggableFrameElements.getTargetSourceElement()).release().build().perform();

        //Assert
        revertDraggableFrameElements.getRevertWhenDroppedSourceElement().shouldHave(attribute("style", "position: relative; left: 0px; top: 0px;"));
        revertDraggableFrameElements.getTargetSourceElement().$(By.cssSelector("p")).shouldHave(text("Dropped!"));
        revertDraggableFrameElements.getTargetSourceElement().shouldHave(cssClass("ui-state-highlight"));
    }

    @Test
    public void revertWhenNotDropped() {
        //Arrange
        switchExamplesMenuTo("Revert draggable position");

        RevertDraggableFrameElements revertDraggableFrameElements = new RevertDraggableFrameElements();
        revertDraggableFrameElements.getRevertWhenNotDroppedSourceElement().$(By.cssSelector("p")).shouldHave(text("I revert when I'm not dropped"));
        revertDraggableFrameElements.getTargetSourceElement().$(By.cssSelector("p")).shouldHave(text("Drop me here"));
        revertDraggableFrameElements.getTargetSourceElement().shouldNotHave(cssClass("ui-state-highlight"));

        //Act
        actions().clickAndHold(revertDraggableFrameElements.getRevertWhenNotDroppedSourceElement()).moveByOffset(165, 45).release().build().perform();

        //Assert
        revertDraggableFrameElements.getTargetSourceElement().$(By.cssSelector("p")).shouldHave(text("Dropped!"));
        revertDraggableFrameElements.getTargetSourceElement().shouldHave(cssClass("ui-state-highlight"));
        revertDraggableFrameElements.getRevertWhenNotDroppedSourceElement().shouldHave(attribute("style", "position: relative; left: 165px; top: 45px;"));

        //Act
        actions().clickAndHold(revertDraggableFrameElements
                .getRevertWhenNotDroppedSourceElement())
                .moveByOffset(-165, -45)
                .release()
                .build()
                .perform();
        sleep(2000);

        //Assert
        revertDraggableFrameElements
                .getRevertWhenNotDroppedSourceElement()
                .shouldHave(attribute("style", "position: relative; left: 165px; top: 45px;"));
    }

    @Test
    public void simplePhotoDragAndDropTest() {
        //Arrange
        switchExamplesMenuTo("Simple photo manager");

        SimplePhotoFrameElements simplePhotoFrameElements = new SimplePhotoFrameElements();
        simplePhotoFrameElements.isFrameLoaded();

        int gallerySize = simplePhotoFrameElements.getGalleryElements().size();
        assertTrue(gallerySize > 0);

        //Act
        actions().clickAndHold(simplePhotoFrameElements.getPhotoByTitle("High Tatras"))
                .moveToElement(simplePhotoFrameElements.getTrashElement())
                .release()
                .build()
                .perform();

        sleep(2000);

        //Assert
        assertEquals(1, simplePhotoFrameElements.getTrashGalleryElements().size());
        assertEquals(gallerySize - 1, simplePhotoFrameElements.getGalleryElements().size());
    }

    private void switchExamplesMenuTo(String menuButton) {
        DroppablePage droppablePage = new DroppablePage();
        droppablePage.isDroppablePageLoaded();
        SelenideElement desiredMenuButton = droppablePage.getMenuTable().$(Selectors.byTagAndText("a", menuButton));
        desiredMenuButton.click();
        desiredMenuButton.parent().shouldHave(cssClass("active"));

        droppablePage.switchToDemoFrame();
    }
}
