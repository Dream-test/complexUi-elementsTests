package webTests.PageElements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RevertDraggableFrameElements {

    public SelenideElement getRevertWhenDroppedSourceElement() {
        SelenideElement sourceElement =$(By.id("draggable"));
        sourceElement.$(By.tagName("p")).shouldBe(visible).shouldHave(text("I revert when I'm dropped"));
        return sourceElement;
    }

    public SelenideElement getRevertWhenNotDroppedSourceElement() {
        SelenideElement sourceElement =$(By.id("draggable2"));
        sourceElement.$(By.tagName("p")).shouldBe(visible).shouldHave(text("I revert when I'm not dropped"));
        return sourceElement;
    }

    public SelenideElement getTargetSourceElement() {
        return $(By.id("droppable"));
    }
}
