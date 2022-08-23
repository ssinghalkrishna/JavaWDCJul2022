package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.Converter;

import static org.assertj.core.api.Assertions.assertThat;

public class ConverterPOMStepDefs {
    @Given("^I open converter page$")
    public void iOpenConverterPage() {
        new Converter().open();
    }

    @When("^I click on \"([^\"]*)\" on page$")
    public void iClickOnOnPage(String tab) throws Throwable {
        new Converter().clickTab(tab);
    }

    @And("^I set \"([^\"]*)\" to \"([^\"]*)\" on page$")
    public void iSetToOnPage(String fromUnit, String toUnit) throws Throwable {
        Converter converter = new Converter();
        converter.selectFromUnit(fromUnit);
        converter.selectToUnit(toUnit);
    }

    @Then("^I enter into From field \"([^\"]*)\" and verify \"([^\"]*)\" result on page$")
    public void iEnterIntoFromFieldAndVerifyResultOnPage(String from, String to) throws Throwable {
        String actualTo = new Converter().getActualTo(from);
        assertThat(actualTo).contains(to);
    }
}
