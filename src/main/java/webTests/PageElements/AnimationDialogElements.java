package webTests.PageElements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AnimationDialogElements {

    public SelenideElement getOpenDialogButton() {
        return $(By.cssSelector("button#opener"));
    }

    public String getDialogTitle() {
        SelenideElement dialogTitle = $(By.id("ui-id-1"));
        dialogTitle.shouldBe(visible);
        return dialogTitle.getOwnText();
    }

    public SelenideElement getDialogBody() {
        return $(By.id("dialog"));
    }

    public void closeModalDialog() {
        SelenideElement dialogBody = $(By.id("dialog"));
        $("button.ui-dialog-titlebar-close").shouldBe(visible).click();
        dialogBody.should(disappear);
    }
}
