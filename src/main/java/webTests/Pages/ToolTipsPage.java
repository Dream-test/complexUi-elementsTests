package webTests.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ToolTipsPage {

    public void isToolTipsPageLoaded() {
        SelenideElement toolTipContainer = $(By.id("toopTipContainer"));
        toolTipContainer.shouldBe(visible).$("h1").shouldHave(Condition.text("Tool Tips"));
    }

    public SelenideElement getHoverMeButton() {
        return $(By.id("toolTipButton"));
    }

    public SelenideElement getHoverMeField() {
        return $(By.id("toolTipTextField"));
    }

    public SelenideElement getContraryElement() {
        return $(Selectors.byTagAndText("a", "Contrary"));
    }

    public SelenideElement getNumberElement() {
        return $(Selectors.byTagAndText("a", "1.10.32"));
    }

    public SelenideElement getTooltipElement() {
        return $(".tooltip-inner");
    }




}
