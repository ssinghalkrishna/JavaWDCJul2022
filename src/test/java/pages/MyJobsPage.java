package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static support.TestContext.getDriver;

public class MyJobsPage extends Header {

    public MyJobsPage() {
        setHeaderText("My Jobs");
    }

    @FindBy(xpath = "//a[contains(@href, '/candidates/')]")
    private WebElement name;

    public String getName() {
        waitForVisible(name);
        return name.getText();
    }

    public void isPositionAdded(String positionId) {

        List<WebElement> positions = getDriver().findElements(By.xpath("//ul//li"));

        for (WebElement position : positions) {
            String id = position.getAttribute("id");
            if (id.equals(positionId)) {
                System.out.println("Position is added!");
            }
        }
    }

}
