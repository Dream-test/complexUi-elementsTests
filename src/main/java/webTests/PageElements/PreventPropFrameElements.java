package webTests.PageElements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PreventPropFrameElements {

    public SelenideElement getSourceElement() {
        SelenideElement sourceElement =$(By.id("draggable"));
        sourceElement.$(By.tagName("p")).shouldBe(visible).shouldHave(text("Drag me to my target"));
        return sourceElement;
    }

    public SelenideElement getFirstOuterTargetSourceElement() {
        return $(By.id("droppable"));
    }

    public SelenideElement getFirstInnerNGTargetSourceElement() {
        return $(By.id("droppable-inner"));
    }

    public SelenideElement getSecondOuterTargetSourceElement() {
        return $(By.id("droppable2"));
    }

    public SelenideElement getSecondInnerTargetSourceElement() {
        return $(By.id("droppable2-inner"));
    }
}
