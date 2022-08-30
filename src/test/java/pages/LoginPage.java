package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Header {

    public LoginPage() {
        setHeaderText("Login");
    }

    @FindBy(xpath = "//*[@for='loginUsername']/../input")
    private WebElement username;

    @FindBy(xpath = "//*[@for='loginPassword']/../input")
    private WebElement password;

@FindBy(xpath = "//*[@id='loginButton']")
private WebElement submit;

public void submitCredentials(String usernamea, String passworda){
    sendKeys(username, usernamea);
    sendKeys(password, passworda);
    click(submit);
}

}
