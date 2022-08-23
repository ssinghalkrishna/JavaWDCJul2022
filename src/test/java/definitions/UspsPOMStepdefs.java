package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import pages.Usps;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UspsPOMStepdefs {
    @Given("^I open \"([^\"]*)\" page, using POM$")
    public void iOpenPageUsingPOM(String page) throws Throwable {
        if (page.equalsIgnoreCase("usps")) {
            new Usps().open();
        }
    }

    @When("^I go to Lookup ZIP page by address, using POM$")
    public void iGoToLookupZIPPageByAddressUsingPOM() {
        new Usps().lookupZipByAddress();
    }

    @And("^I fill out \"([^\"]*)\" street, \"([^\"]*)\" city, \"([^\"]*)\" state, using POM$")
    public void iFillOutStreetCityStateUsingPOM(String street, String city, String state) throws Throwable {
        new Usps().fillAddress(street, city, state);
    }

    @Then("^I validate \"([^\"]*)\" zip code exists in the result, using POM$")
    public void iValidateZipCodeExistsInTheResultUsingPOM(String expectedZip) throws Throwable {
        List<WebElement> resultZip = new Usps().getZipList(expectedZip);

        for (WebElement item : resultZip) {
            assertThat(item.getText().contains(expectedZip)).isTrue();
        }
    }

    @Given("^I go to usps page object$")
    public void iGoToUspsPageObject() {
        new Usps().open();
    }

    @When("^I go to Calculate Price page object$")
    public void iGoToCalculatePricePageObject() {
        new Usps().calculatePrice();
    }

    @And("^I select \"([^\"]*)\" with \"([^\"]*)\" shape page object$")
    public void iSelectWithShapePageObject(String country, String shape) throws Throwable {
        new Usps().selectCountryShape();
    }

    @And("^I define \"([^\"]*)\" quantity page object$")
    public void iDefineQuantityPageObject(String count) throws Throwable {
        new Usps().selectQuantity(count);
    }

    @Then("^I calculate the price and validate cost is \"([^\"]*)\" page object$")
    public void iCalculateThePriceAndValidateCostIsPageObject(String cost) throws Throwable {
        String actual = new Usps().calculate();
        assertThat(actual.equals(cost)).isTrue();
    }

    @When("^I go to Postal Store tab$")
    public void iGoToPostalStoreTab() {
        new Usps().postalStoreTab();
    }

    @And("^I enter \"([^\"]*)\" into store search page object$")
    public void iEnterIntoStoreSearchPageObject(String store) throws Throwable {
        new Usps().storeSearch(store);
    }

    @Then("^I search and validate no products found page object$")
    public void iSearchAndValidateNoProductsFoundPageObject() {
        Usps storeSearch = new Usps();
        storeSearch.storeSearchBtn();
        String message = storeSearch.noProductMessage();
        assertThat(message.contains("Your search did not match any products")).isTrue();
    }
}
