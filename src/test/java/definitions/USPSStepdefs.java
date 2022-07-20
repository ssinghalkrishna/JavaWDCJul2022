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
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;
import static support.TestContext.getWait;

public class USPSStepdefs {
    @Given("^I open \"([^\"]*)\" page$")
    public void iOpenPage(String page) throws Throwable {
        if (page.equalsIgnoreCase("usps")) {
            getDriver().get("https://www.usps.com/");
            getDriver().manage().window().maximize();
        }
    }

    @And("^I go to Lookup ZIP page by address$")
    public void iGoToLookupZIPPageByAddress() {
        getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='mail-ship-width']"))).click();
       // getDriver().findElement(By.xpath("//a[@id='mail-ship-width']")).click();
        getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'https://tools.usps.com/go/ZipLookupAction')]"))).click();
        getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@class, 'zip-code-address')]"))).click();

    }

    @And("^I fill out \"([^\"]*)\" street, \"([^\"]*)\" city, \"([^\"]*)\" state$")
    public void iFillOutStreetCityState(String street, String city, String state) throws Throwable {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tAddress']"))).sendKeys(street);

        getDriver().findElement(By.xpath("//*[@id='tCity']")).sendKeys(city);

        WebElement select = getDriver().findElement(By.xpath("//select[@id='tState']"));
        new Select(select).selectByValue(state);
    }

    @And("^I search for result$")
    public void iSearchForResult() {
        getDriver().findElement(By.xpath("//*[@id='zip-by-address']")).click();
    }

    @Then("^result must contain \"([^\"]*)\" zip code$")
    public void resultMustContainZipCode(String zip) throws Throwable {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@id='zipByAddressDiv']//li)[1]")));
        List<WebElement> list = getDriver().findElements(By.xpath("//*[@id='zipByAddressDiv']//li"));

        for (WebElement element : list) {
            assertThat(element.getText().contains(zip)).isTrue();
        }
    }

    @When("^I mouse over and go to Lookup Zip page by address$")
    public void iMouseOverAndGoToLookupZipPageByAddress() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='mail-ship-width']")));

        Actions actions = new Actions(getDriver());
        WebElement element = getDriver().findElement(By.xpath("//*[@id='mail-ship-width']"));
        actions.moveToElement(element).perform();

        getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='tool-zip']/a"))).click();

        //getDriver().findElement(By.xpath("//li[@class='tool-zip']/a")).click();

        getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Find by Address']"))).click();
       // getDriver().findElement(By.xpath("//*[text()='Find by Address']")).click();
    }

    @When("^I go to Calculate price page$")
    public void iGoToCalculatePricePage() {
        getDriver().findElement(By.xpath("//*[@id='mail-ship-width']")).click();
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@href='/calculateretailpostage/welcome.htm']"))).click();
    }

    @And("^I put country, shape, quantity$")
    public void iPutCountryShapeQuantity() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='CountryID']")));

        WebElement country = getDriver().findElement(By.xpath("//*[@id='CountryID']"));
        new Select(country).selectByValue("10150");

        getDriver().findElement(By.xpath("//*[@value='Postcard']")).click();

        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='quantity-0']"))).sendKeys("2");
    }

    @And("^I submit for result$")
    public void iSubmitForResult() {
        getDriver().findElement(By.xpath("//*[@value='Calculate']")).click();
    }

    @Then("^result must have price \"([^\"]*)\"$")
    public void resultMustHavePrice(String total) throws Throwable {
        String actualPrice = getDriver().findElement(By.xpath("//*[@id='total']")).getText();
        assertThat(actualPrice.equals(total)).isTrue();
    }
}
