package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static support.TestContext.getDriver;

public class Converter extends Page {
    public Converter() {
        setUrl("https://www.unitconverters.net/");
    }

    public void clickTab(String value) {
        getDriver().findElement(By.xpath("//a[contains(@href,'" + value + "')]")).click();
    }

    @FindBy(xpath = "//select[@id='calFrom']")
    private WebElement selectFrom;

    public void selectFromUnit(String value) {
        new Select(selectFrom).selectByVisibleText(value);
    }

    @FindBy(xpath = "//select[@id='calTo']")
    private WebElement selectTo;

    public void selectToUnit(String value) {
        new Select(selectTo).selectByVisibleText(value);
    }

    @FindBy(xpath = "//*[@name='fromVal']")
    private WebElement fromVal;

    @FindBy(xpath = "//*[@name='toVal']")
    private WebElement toVal;

    public String getActualTo(String from) {
        sendKeys(fromVal, from);
        return toVal.getAttribute("value");
    }

}
