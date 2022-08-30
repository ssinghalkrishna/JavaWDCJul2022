package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class Header extends Page {

    private String headerText;

    @FindBy(xpath = "//span[contains(@class,'position-center')]")
    private WebElement headerTitle;

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public void assertHeader() throws InterruptedException {
        waitForTextToBePresentInElement(headerTitle, headerText);

        assertThat(headerTitle.getText().equals(headerText)).isTrue();
    }

}
