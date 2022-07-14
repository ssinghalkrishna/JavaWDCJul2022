package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;

public class ConverterStepdefs {
    @When("^I click on \"([^\"]*)\"$")
    public void iClickOn(String tab) throws Throwable {
        getDriver().findElement(By.xpath("//a[contains(@href, '" + tab + "')]")).click();
    }

    @And("^I set \"([^\"]*)\" to \"([^\"]*)\"$")
    public void iSetTo(String from, String to) throws Throwable {
        WebElement selectFrom = getDriver().findElement(By.xpath("//select[@id='calFrom']"));
        new Select(selectFrom).selectByVisibleText(from);

        WebElement selectTo = getDriver().findElement(By.xpath("//select[@id='calTo']"));
        new Select(selectTo).selectByVisibleText(to);
    }

    @Then("^I enter into From field \"([^\"]*)\" and verify \"([^\"]*)\" result$")
    public void iEnterIntoFromFieldAndVerifyResult(String from, String to) throws Throwable {
        getDriver().findElement(By.xpath("//*[@id='fromVal']")).sendKeys(from);
       // String actualTo = getDriver().findElement(By.xpath("//*[@name='toVal']")).getText();
        String actualTo = getDriver().findElement(By.xpath("//*[@name='toVal']")).getAttribute("value");

        assertThat(actualTo.contains(to)).isTrue();
    }
}
