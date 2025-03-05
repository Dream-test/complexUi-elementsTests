package webTests.PageElements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SimplePhotoFrameElements {

    public void isFrameLoaded() {
        SelenideElement trashWidgetHeader = $x("//*[@id=\"trash\"]/h4");
        trashWidgetHeader.shouldBe(visible);
        trashWidgetHeader.$(By.tagName("span")).shouldBe(visible).shouldHave(text("Trash"));
    }

    public ElementsCollection getGalleryElements() {
        return  $(By.id("gallery")).$$(By.tagName("li"));
    }

    public SelenideElement getPhotoByTitle(String photoTitle ) {
        ElementsCollection photos = $(By.id("gallery")).$$(By.tagName("li"));

        return photos.stream()
                .filter(pic -> pic.$(By.cssSelector("h5")).getText().equals(photoTitle))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Picture with title '" + photoTitle + "' not found"));
    }

    public SelenideElement getTrashElement() {
        return $(By.id("trash"));
    }

    public ElementsCollection getTrashGalleryElements() {
        return $(By.id("trash")).$$(By.tagName("li"));
    }

}
