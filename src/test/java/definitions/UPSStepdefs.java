package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import support.TestContext;

import java.io.FileNotFoundException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;
import static support.TestContext.getWait;

public class UPSStepdefs {
    @And("^I select \"([^\"]*)\" and \"([^\"]*)\" on global page$")
    public void iSelectAndOnGlobalPage(String region, String country) throws Throwable {
//       boolean isUSEngDisplayed = getDriver().findElement(By.xpath("//*[@lang='en']/*[@data-code='en_us']")).isDisplayed();
//       if (!isUSEngDisplayed){
//           getDriver().findElement(By.xpath("//*[@data-map='ups-svg-namerica']")).click();
//       }
//        getDriver().findElement(By.xpath("//*[@lang='en']/*[@data-code='en_us']")).click();
//
//       getWait().until(ExpectedConditions.urlToBe("https://www.ups.com/us/en/Home.page"));

        Actions actions = new Actions(getDriver());
        WebElement heading = getDriver().findElement(By.xpath("//div[@class='page-title_cell']"));
        actions.moveToElement(heading).perform();

        By countrySelector = By.xpath("//*[@lang='en']/*[text() = '" + country + "']");

        if (getDriver().findElements(countrySelector).size() == 0) {
            getDriver().findElement(By.xpath("//*[@class='ups-acc-headcont']/*[text() = '" + region + "']")).click();
        }
        getDriver().findElement(countrySelector).click();
    }

    @And("^I open Shipping menu$")
    public void iOpenShippingMenu() {
        if (getDriver().findElement(By.xpath("//*[@class='close_btn_thick']")).isDisplayed()) {
            getDriver().findElement(By.xpath("//*[@class='close_btn_thick']")).click();
        }
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@role='tablist']//*[contains(text(), 'Ship')]"))).click();
    }

    @When("^I go to Create Shipment page$")
    public void iGoToCreateShipmentPage() {
        boolean heading = getDriver().findElement(By.xpath("//*[text() = 'Create a Shipment']")).isDisplayed();
        assertThat(heading).isTrue();
    }

    @When("^I fill out origin shipment fields$")
    public void iFillOutOriginShipmentFields() throws FileNotFoundException, InterruptedException {
        HashMap<String, String> sender = TestContext.getSender();

        getDriver().findElement(By.xpath("//*[@id='origin-cac_companyOrName']")).sendKeys(sender.get("name"));

        getDriver().findElement(By.xpath("//*[@id='origin-singleLineAddressEditButton']")).click();

        getDriver().findElement(By.xpath("//*[@id='origin-cac_addressLine1']")).sendKeys(sender.get("address"));
        getDriver().findElement(By.xpath("//*[@id='origin-cac_postalCode']")).sendKeys(sender.get("zip"));

        getWait().until(ExpectedConditions.attributeToBe(By.xpath("//*[@id='origin-cac_city']"), "value", sender.get("city").toUpperCase()));

        getWait().until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id='origin-cac_state']/option[@value = '" + sender.get("state") + "']")));

        getDriver().findElement(By.xpath("//*[@id='origin-cac_email']")).sendKeys(sender.get("email"));
        getDriver().findElement(By.xpath("//*[@id='origin-cac_phone']")).sendKeys(sender.get("phone"));
    }

    @And("^I Submit the form$")
    public void iSubmitTheForm() {
        String oldUrl = getDriver().getCurrentUrl();

        String nextButtonXpath;

        if (oldUrl.contains("payment")) {
            nextButtonXpath = "//*[@id='nbsBackForwardNavigationReviewPrimaryButton']";
        } else {
            nextButtonXpath = "//*[@id='nbsBackForwardNavigationContinueButton']";
        }
        WebElement button = getDriver().findElement(By.xpath(nextButtonXpath));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", button);

        getWait().until(ExpectedConditions.not(ExpectedConditions.urlToBe(oldUrl)));
    }

    @When("^I cancel the form$")
    public void iCancelTheForm() {
        WebElement button = getDriver().findElement(By.xpath("//*[@id='nbsBackForwardNavigationCancelShipmentButton']"));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", button);

        getDriver().findElement(By.xpath("//*[@id='nbsCancelShipmentWarningYes']")).click();
    }

    @Then("^I verify shipment form is reset$")
    public void iVerifyShipmentFormIsReset() {
        getDriver().findElement(By.xpath("//*[@id='origin-cac_companyOrName']")).getAttribute("value").isEmpty();
        getDriver().findElement(By.xpath("//*[@id='origin-cac_singleLineAddress']")).getAttribute("value").isEmpty();
        getDriver().findElement(By.xpath("//*[@id='origin-cac_email']")).getAttribute("value").isEmpty();
        getDriver().findElement(By.xpath("//*[@id='origin-cac_phone']")).getAttribute("value").isEmpty();
    }
}
