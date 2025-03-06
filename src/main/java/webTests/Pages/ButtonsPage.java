package webTests.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ButtonsPage {

    public void isButtonPageLoaded() {
        SelenideElement buttonsContainer =$(".col-md-6");
        buttonsContainer.shouldBe(visible).$("h1").shouldHave(Condition.text("Buttons"));
    }

    public SelenideElement getDoubleClickButton() {
        return $(By.id("doubleClickBtn"));
    }

    public SelenideElement getRightClickButton() {
        return $(By.id("rightClickBtn"));
    }

    public SelenideElement getOneClickButton() {
        return $(Selectors.byTagAndText("button", "Click Me"));
    }

    public SelenideElement getDoubleClickMessage() {
        return $(By.id("doubleClickMessage"));
    }

    public SelenideElement getRightClickMessage() {
        return $(By.id("rightClickMessage"));
    }

    public SelenideElement getOneClickMessage() {
        return $(By.id("dynamicClickMessage"));
    }
}
