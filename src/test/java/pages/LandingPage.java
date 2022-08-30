package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;
import static support.TestContext.getWait;

public class LandingPage extends Header {

    public LandingPage() {
        setUrl("https://skryabin-careers.herokuapp.com/");
        setHeaderText("Careers");
    }

    public String applyToNewPosition() {
        List<WebElement> listOfPositions = getDriver().findElements(By.xpath("//ul//li[not(contains(@class, 'li-selected'))]"));

        List<WebElement> listOfIcons = getDriver().findElements(By.xpath("//i[@class='fa fa-check']"));

        int size = listOfPositions.size();
        int num = new Random().nextInt(size);

        WebElement position = listOfPositions.get(num);
        String id = position.getAttribute("id");

        WebElement clickIcon = listOfIcons.get(num);

        new Actions(getDriver()).moveToElement(position).perform();
        click(clickIcon);

        return id;
    }

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement login;

    public void clickLogin() {
        click(login);
    }

    @FindBy(xpath = "//*[@class='logout-box']/a")
    private WebElement loggedUser;

    public String getLoggedUser() {
        return loggedUser.getText();
    }

    public void isPositionSelected(String positionId) {
        waitForVisible(getDriver().findElement(By.xpath("//li[@id = '" + positionId + "']")));

        WebElement element = getDriver().findElement(By.xpath("//li[@id = '" + positionId + "']"));
        new Actions(getDriver()).moveToElement(element).perform();
        String positionClass = element.getAttribute("class");

        assertThat(positionClass.contains("li-selected")).isTrue();
    }

    @FindBy(xpath = "//*[@href='/my_jobs']/button")
    private WebElement myJobs;


    public void clickMyJobs() {
        click(myJobs);
    }
}
