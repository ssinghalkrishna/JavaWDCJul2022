package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;
import static support.TestContext.getWait;

public class CalculatorStepdefs {
    @When("^I navigate to \"([^\"]*)\"$")
    public void iNavigateTo(String type) throws Throwable {
        WebElement autoLoanMenuItem = getDriver().findElement(By.xpath("//a[@href='/auto-loan-calculator.html']"));
        new Actions(getDriver()).moveToElement(autoLoanMenuItem).click().perform();
    }

    @And("^I clear all calculator fields$")
    public void iClearAllCalculatorFields() throws InterruptedException {
        getDriver().findElement(By.xpath("//*[@id='cloanamount']")).clear();
        getDriver().findElement(By.xpath("//*[@id='cloanterm']")).clear();
        getDriver().findElement(By.xpath("//*[@id='cinterestrate']")).clear();
        getDriver().findElement(By.xpath("//*[@id='cdownpayment']")).clear();
        getDriver().findElement(By.xpath("//*[@id='ctradeinvalue']")).clear();
        getDriver().findElement(By.xpath("//*[@id='csaletax']")).clear();
        getDriver().findElement(By.xpath("//*[@id='ctitlereg']")).clear();
    }

    @And("^I calculate$")
    public void iCalculate() {
        getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@value='Calculate']"))).click();
    }

    @Then("^I verify \"([^\"]*)\" calculator error$")
    public void iVerifyCalculatorError(String errorMessage) throws Throwable {
        String allErrorText = getDriver().findElement(By.xpath("//*[@name='autoloanresult']/..")).getText();
        assertThat(allErrorText).containsIgnoringCase(errorMessage);
    }

    @Then("^I verify monthly pay is \"([^\"]*)\"$")
    public void iVerifyMonthlyPayIs(String amount) throws Throwable {
        WebElement monthlyAmount = getDriver().findElement(By.xpath("//*[@class='h2result']"));

        getWait().until(ExpectedConditions.textToBePresentInElement(monthlyAmount, amount));
    }

    @And("^I enter \"([^\"]*)\" price, \"([^\"]*)\" months, \"([^\"]*)\" interest, \"([^\"]*)\" downpayment, \"([^\"]*)\" trade-in, \"([^\"]*)\" state,\"([^\"]*)\" percenttax, \"([^\"]*)\" fees$")
    public void iEnterPriceMonthsInterestDownpaymentTradeInStatePercenttaxFees(String price, String months, String interest, String downPayment, String tradeIn, String state, String percentTax, String fees) throws Throwable {
        getDriver().findElement(By.xpath("//*[@id='cloanamount']")).sendKeys(price);
        getDriver().findElement(By.xpath("//*[@id='cloanterm']")).sendKeys(months);
        getDriver().findElement(By.xpath("//*[@id='cinterestrate']")).sendKeys(interest);
        getDriver().findElement(By.xpath("//*[@id='cdownpayment']")).sendKeys(downPayment);
        getDriver().findElement(By.xpath("//*[@id='ctradeinvalue']")).sendKeys(tradeIn);
        getDriver().findElement(By.xpath("//select[@name='cstate']/*[@value='CA']")).click();
        getDriver().findElement(By.xpath("//*[@id='csaletax']")).sendKeys(percentTax);
        getDriver().findElement(By.xpath("//*[@id='ctitlereg']")).sendKeys(fees);
    }
}