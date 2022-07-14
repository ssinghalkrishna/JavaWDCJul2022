package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;
import static support.TestContext.getWait;

public class WorkdayStepdefs {
    @Given("^I go to \"([^\"]*)\" page \\(upload\\)$")
    public void iGoToPageUpload(String page) throws Throwable {
        if (page.equalsIgnoreCase("workday")) {
            getDriver().get("https://workday.wd5.myworkdayjobs.com/Workday");
        }
        else if (page.equalsIgnoreCase("calculator")) {
            getDriver().get("https://www.calculator.net/");
        }
    }

    @When("^I select any position$")
    public void iSelectAnyPosition() {
        Random rand = new Random();
        int num = rand.nextInt(20);
        System.out.println(num);

        getWait().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@data-automation-id='jobFoundText']")));

        List<WebElement> jobs = getDriver().findElements(By.xpath("//ul[@role='list']/li"));
        WebElement pickedJob = jobs.get(num);

        new Actions(getDriver()).moveToElement(pickedJob).perform();

        getDriver().findElement(By.xpath("(//li//*[@data-automation-id='jobTitle'])[" + num + "]")).click();
    }

    @And("^I Apply with LinkedIn$")
    public void iApplyWithLinkedIn() throws InterruptedException {
        getDriver().findElement(By.xpath("//*[@data-automation-id='adventureButton']")).click();

        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-automation-id='applyWithLinkedIn']")));

        WebElement iframe = getDriver().findElement(By.xpath("//iframe"));
        getDriver().switchTo().frame(iframe);
        WebElement iframe1 = getDriver().findElement(By.xpath("//iframe[contains(@src, 'https://')]"));
        getDriver().switchTo().frame(iframe1);

        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Sign in with LinkedIn']"))).click();
    }

    @Then("^I verify that valid OAuth LinkedIn login page opens$")
    public void iVerifyThatValidOAuthLinkedInLoginPageOpens() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='linkedin-logo']")));
        assertThat(getDriver().getTitle()).containsIgnoringCase("LinkedIn");
    }
}
