package webTests.Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DroppablePage {
    private final SelenideElement pageContent = $(By.id("content"));
    private final SelenideElement pageDemoFrame = $(".demo-frame");
    private final SelenideElement menuTable = $(".demo-list");

    public void isDroppablePageLoaded() {
        pageContent.shouldBe(visible);
        pageContent.$("h1.entry-title").shouldHave(text("Droppable"));
    }

    public void switchToDemoFrame() {
        switchTo().frame(pageDemoFrame);
    }

    public void switchToDefault() {
        switchTo().defaultContent();
    }

    public SelenideElement getMenuTable() {
        return menuTable;
    }
}
