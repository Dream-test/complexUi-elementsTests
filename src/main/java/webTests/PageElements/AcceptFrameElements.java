package webTests.PageElements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AcceptFrameElements {

    public SelenideElement getNotDroppedSourceElement() {
        SelenideElement sourceElement =$(By.id("draggable-nonvalid"));
        sourceElement.$(By.tagName("p")).shouldBe(visible).shouldHave(text("I'm draggable but can't be dropped"));
        return sourceElement;
    }

    public SelenideElement getDroppedSourceElement() {
        SelenideElement sourceElement =$(By.id("draggable"));
        sourceElement.$(By.tagName("p")).shouldBe(visible).shouldHave(text("Drag me to my target"));
        return sourceElement;
    }

    public SelenideElement getTargetSourceElement() {
        return $(By.id("droppable"));
    }
}
