package webTests.PageElements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class ModalFormDialogElements {

    public void openFormDialog() {
        $(By.cssSelector("button#create-user")).click();
        $(By.id("ui-id-1")).shouldBe(visible).shouldHave(text("Create new user"));
    }

    public void inputUserName(String userName) {
        SelenideElement nameInputField = $(By.cssSelector("input#name"));
        nameInputField.clear();
        nameInputField.setValue(userName);
    }

    public void inputUserEmail(String userEmail) {
        SelenideElement emailInputField = $(By.cssSelector("input#email"));
        emailInputField.clear();
        emailInputField.setValue(userEmail);
    }

    public void inputUserPassword(String userPassword) {
        SelenideElement passwordInputField = $(By.cssSelector("input#password"));
        passwordInputField.clear();
        passwordInputField.setValue(userPassword);
    }

    public void closeFormDialog() {
        SelenideElement formDialogTitle = $(By.id("ui-id-1"));
        formDialogTitle.shouldBe(visible).shouldHave(text("Create new user"));
        $(Selectors.byTagAndText("button", "Create an account")).click();
        formDialogTitle.should(disappear);
    }

    public boolean isUserPresent(String userName, String userEmail, String userPassword) {
        ElementsCollection usersData = $(By.cssSelector("table#users")).$$(By.tagName("tr"));
        return usersData.stream()
                .anyMatch(row -> {
                    ElementsCollection cells = row.$$(By.tagName("td"));
                    boolean nameFound = cells.stream().anyMatch(cell -> cell.getText().equals(userName));
                    boolean emailFound = cells.stream().anyMatch(cell -> cell.getText().equals(userEmail));
                    boolean passwordFound = cells.stream().anyMatch(cell -> cell.getText().equals(userPassword));
                    return nameFound && emailFound && passwordFound;
                });
    }
}



