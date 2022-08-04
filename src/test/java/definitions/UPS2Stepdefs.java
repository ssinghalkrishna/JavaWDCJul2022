package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import support.TestContext;

import java.io.FileNotFoundException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.*;

public class UPS2Stepdefs {
    @Then("^I verify origin shipment fields submitted$")
    public void iVerifyOriginShipmentFieldsSubmitted() throws FileNotFoundException {
        HashMap<String, String> sender = TestContext.getSender();
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='origin_showSummaryAddress']")));

        String origin = getDriver().findElement(By.xpath("//*[@id='origin_showSummaryAddress']")).getText();

        assertThat(origin).containsIgnoringCase(sender.get("name"));
        assertThat(origin).containsIgnoringCase(sender.get("address"));
        assertThat(origin).containsIgnoringCase(sender.get("zip"));
        assertThat(origin).containsIgnoringCase(sender.get("email"));
        assertThat(origin).containsIgnoringCase(sender.get("phone"));
        assertThat(origin).containsIgnoringCase(sender.get("city"));
        assertThat(origin).containsIgnoringCase(sender.get("state"));
    }

    @When("^I fill out destination shipment info$")
    public void iFillOutDestinationShipmentInfo() throws FileNotFoundException {
        HashMap<String, String> receiver = TestContext.getReceiver();
        getDriver().findElement(By.xpath("//*[@id='destination-singleLineAddressEditButton']")).click();

        getDriver().findElement(By.xpath("//*[@id='destination-cac_companyOrName']")).sendKeys(receiver.get("name"));
        getDriver().findElement(By.xpath("//*[@id='destination-cac_addressLine1']")).sendKeys(receiver.get("address"));
        getDriver().findElement(By.xpath("//*[@id='destination-cac_postalCode']")).sendKeys(receiver.get("zip"));

        getWait().until(ExpectedConditions.attributeToBe(
                By.xpath("//*[@id='destination-cac_city']"), "value", receiver.get("city").toUpperCase()));
        getWait().until(ExpectedConditions.elementToBeSelected(
                By.xpath("//*[@id='destination-cac_state']/option[@value='" + receiver.get("state") + "']")));

        getDriver().findElement(By.xpath("//*[@id='destination-cac_email']")).sendKeys(receiver.get("email"));
        getDriver().findElement(By.xpath("//*[@id='destination-cac_phone']")).sendKeys(receiver.get("phone"));
    }

    @And("^I set the packaging type$")
    public void iSetThePackagingType() throws InterruptedException {
        WebElement selectDropDown = getDriver().findElement(By.xpath("//*[@id='nbsPackagePackagingTypeDropdown0']"));
        Select select = new Select(selectDropDown);
        select.selectByVisibleText("UPS Express Box - Small");
        //  getDriver().findElement(By.xpath("//*[@id='nbsPackagePackagingTypeDropdown0']/option[text()= 'UPS Express Box - Small']")).click();

        getDriver().findElement(By.xpath("//*[@id='nbsPackagePackageWeightField0']")).sendKeys("12");
        // getWait().until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id='nbsPackagePackageWeightField0']"), "12"));
        getWait().until(ExpectedConditions.attributeToBe(By.xpath("//*[@id='nbsPackagePackageWeightField0']"), "value", "12"));
        getDriver().findElement(By.xpath("//*[@id='nbsPackageDeclaredValueField0']")).click();
    }

    @Then("^I verify total charges display$")
    public void iVerifyTotalChargesDisplay() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='total-charges-spinner']")));
        String total = getDriver().findElement(By.xpath("//*[@id='total-charges-spinner']")).getText();
        assertThat(total).isNotEmpty();
    }

    @And("^I select the cheapest delivery option$")
    public void iSelectTheCheapestDeliveryOption() {
        getDriver().findElement(By.xpath("(//*[@id='Cheapest'])[1]"));
    }

    @Then("^I verify that total charges changed$")
    public void iVerifyThatTotalChargesChanged() {
        WebElement total = getDriver().findElement(By.xpath("//*[@id='total-charges-spinner']"));
        String totalOld = total.getText();

        getWait().until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(total, totalOld)));
    }

    @And("^I enter Contents$")
    public void iEnterContents() {
        getDriver().findElement(By.xpath("//*[@id='nbsShipmentDescription']")).sendKeys("clothes");
    }

    @And("^I select Paypal payment type$")
    public void iSelectPaypalPaymentType() {
        getDriver().findElement(By.xpath("//*[@for='other-ways-to-pay-tile']")).click();
    }

    @Then("^I review all recorded details on the review page$")
    public void iReviewAllRecordedDetailsOnTheReviewPage() throws FileNotFoundException {
        HashMap<String, String> sender = getSender();

        String origin = getDriver().findElement(By.xpath("//*[@id='origin_showSummaryAddress']")).getText();
        assertThat(origin).containsIgnoringCase(sender.get("name"));
        assertThat(origin).containsIgnoringCase(sender.get("address"));
        assertThat(origin).containsIgnoringCase(sender.get("zip"));
        assertThat(origin).containsIgnoringCase(sender.get("city"));
        assertThat(origin).containsIgnoringCase(sender.get("state"));

        HashMap<String, String> receiver = getReceiver();

        String destination = getDriver().findElement(By.xpath("//*[@id='destination_showSummaryAddress']")).getText();
        assertThat(destination).containsIgnoringCase(receiver.get("name"));
        assertThat(destination).containsIgnoringCase(receiver.get("address"));
        assertThat(destination).containsIgnoringCase(receiver.get("zip"));
        assertThat(destination).containsIgnoringCase(receiver.get("city"));
        assertThat(destination).containsIgnoringCase(receiver.get("state"));
    }

//    @And("^I cancel the shipment form$")
//    public void iCancelTheShipmentForm() {
//        getDriver().findElement(By.xpath("//*[@id='nbsBackForwardNavigationCancelShipmentButton']")).click();
//    }


}
