package webTests.Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class ModalDialogPage {
    private final SelenideElement pageContent = $(By.id("content"));
    private final SelenideElement pageDemoFrame = $(".demo-frame");
    private final SelenideElement menuTable = $(".demo-list");

    public void isModalDialogPageLoaded() {
        pageContent.shouldBe(visible);
        pageContent.$("h1.entry-title").shouldHave(text("Dialog"));
    }

    public SelenideElement getMenuTable() {
        return menuTable;
    }

    public void switchToDemoFrame() {
        switchTo().frame(pageDemoFrame);
    }

    public void switchToDefault() {
        switchTo().defaultContent();
    }
}
