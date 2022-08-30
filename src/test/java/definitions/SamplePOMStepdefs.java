package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.SampleForm;
import pages.SampleResult;
import support.TestContext;

import java.io.FileNotFoundException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class SamplePOMStepdefs {
    @Given("^I open sample page$")
    public void iOpenSamplePage() {
        new SampleForm().open();
    }

    @When("^I fill out sample fields$")
    public void iFillOutSampleFields() throws FileNotFoundException {
        HashMap<String, String> sample = TestContext.getSample();
        SampleForm form = new SampleForm();

        form.fillUsername(sample.get("username"));
        form.fillEmail(sample.get("email"));
        form.fillPassword(sample.get("password"));
        form.fillConfirmPassword(sample.get("password"));

        form.fillName(sample.get("fname"), sample.get("mname"), sample.get("lname"));

        form.fillPhone(sample.get("phone"));

        form.selectDateOfBirth();

        form.selectMonth(sample.get("month"));

        form.selectYear(sample.get("year"));

        form.selectDay();

        form.selectCountryOfOrigin(sample.get("origin"));
        form.selectGender();
        form.selectAllowedToContact();

        form.fillAddress(sample.get("address"));

        form.selectCarMake(sample.get("car"));

        form.fillContactNamePhone(sample.get("contactName"), sample.get("contactPhone"));
        form.selectAgreedToPrivacyPolicy();
    }

    @And("^I submit sample form$")
    public void iSubmitSampleForm() {
        new SampleForm().clickSubmit();
    }

    @Then("^I verify all fields$")
    public void iVerifyAllFields() throws FileNotFoundException {
        HashMap<String, String> data = TestContext.getSample();

        SampleResult result = new SampleResult();

        String resultText = result.getResult();

        assertThat(resultText).containsIgnoringCase(data.get("username"));
        assertThat(resultText).containsIgnoringCase(data.get("email"));
        assertThat(resultText).containsIgnoringCase(data.get("fname"));
        assertThat(resultText).containsIgnoringCase(data.get("mname"));
        assertThat(resultText).containsIgnoringCase(data.get("lname"));
        assertThat(resultText).containsIgnoringCase("04");
        assertThat(resultText).containsIgnoringCase(data.get("year"));

        assertThat(resultText).containsIgnoringCase(data.get("origin"));
        assertThat(resultText).containsIgnoringCase(data.get("gender"));
        assertThat(resultText).containsIgnoringCase(data.get("address"));
        assertThat(resultText).containsIgnoringCase(data.get("phone"));
        assertThat(resultText).containsIgnoringCase(data.get("car"));
        assertThat(resultText).containsIgnoringCase(data.get("contactName"));
        assertThat(resultText).containsIgnoringCase(data.get("contactPhone"));

        assertThat(result.getPassword()).doesNotContain(data.get("password"));
        assertThat(result.getPrivacyPolicy()).isEqualToIgnoringCase("true");
    }
}
