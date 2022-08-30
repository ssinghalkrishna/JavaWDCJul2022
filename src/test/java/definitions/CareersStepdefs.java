package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.ApplyPage;
import pages.LandingPage;
import pages.LoginPage;
import pages.MyJobsPage;
import support.TestContext;

import java.io.FileNotFoundException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getData;

public class CareersStepdefs {
    @Given("^I open \"([^\"]*)\" page object$")
    public void iOpenPageObject(String page) throws Throwable {
        switch (page) {
            case "careers":
                new LandingPage().open();
                break;
            default:
                throw new Exception("Page " + page + " not recognized!");
        }
    }

    @And("^I apply to a new position$")
    public void iApplyToANewPosition() throws FileNotFoundException, InterruptedException {
        String positionId = new LandingPage().applyToNewPosition();

        TestContext.setTestData("positionId", positionId);

        ApplyPage applyPage = new ApplyPage();
        applyPage.assertHeader();

        HashMap<String, String> candidate = TestContext.getCandidate();
        applyPage.fillProfileDetails(candidate);
        applyPage.submit();
    }

    @Then("^I verify profile is created$")
    public void iVerifyProfileIsCreated() throws FileNotFoundException, InterruptedException {
        MyJobsPage myJobsPage = new MyJobsPage();

        HashMap<String, String> candidate = TestContext.getCandidate();
        String actualName = myJobsPage.getName();

        myJobsPage.assertHeader();

        assertThat(actualName).isEqualTo(candidate.get("firstName") + " " + candidate.get("lastName"));
    }

    @And("^I see position in my jobs$")
    public void iSeePositionInMyJobs() {
        String positionId = TestContext.getStringTestData("positionId");

        MyJobsPage myJobsPage = new MyJobsPage();
        myJobsPage.isPositionAdded(positionId);
    }

    @And("^I login as \"([^\"]*)\"$")
    public void iLoginAs(String role) throws Throwable {
        HashMap<String, String> user = getData(role);

        LandingPage landingPage = new LandingPage();
        landingPage.assertHeader();
        landingPage.clickLogin();

        LoginPage loginPage = new LoginPage();
        loginPage.assertHeader();
        loginPage.submitCredentials(user.get("email"), user.get("password"));
    }

    @Then("^I verify \"([^\"]*)\" login$")
    public void iVerifyLogin(String role) throws Throwable {
        HashMap<String, String> user = getData(role);

        LandingPage landingPage = new LandingPage();
        landingPage.assertHeader();

        String actualUser = landingPage.getLoggedUser();

        assertThat(actualUser).isEqualToIgnoringCase(user.get("firstName") + " " + user.get("lastName"));
    }

    @When("^I apply for a new job$")
    public void iApplyForANewJob() {
        String positionId = new LandingPage().applyToNewPosition();
        TestContext.setTestData("positionId", positionId);
    }

    @Then("^I see position marked as applied$")
    public void iSeePositionMarkedAsApplied() throws InterruptedException {
        String positionId = TestContext.getStringTestData("positionId");
        //   Thread.sleep(9000);
        new LandingPage().isPositionSelected(positionId);
    }

    @And("^I see position added in my jobs$")
    public void iSeePositionAddedInMyJobs() {
        LandingPage landingPage = new LandingPage();
        landingPage.clickMyJobs();

        String positionId = TestContext.getStringTestData("positionId");
        new MyJobsPage().isPositionAdded(positionId);
    }
}
