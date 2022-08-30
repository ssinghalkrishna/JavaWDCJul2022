package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import java.util.Set;

import static org.junit.Assert.assertTrue;
import static support.TestContext.getDriver;

public class WebDriverStepdefs {
    @Given("^I go to \"([^\"]*)\" page$")
    public void iGoToPage(String page) throws Throwable {
        if (page.equalsIgnoreCase("sample")) {
            getDriver().get("https://skryabin.com/webdriver/html/sample.html");
        } else if (page.equalsIgnoreCase("google")) {
            getDriver().get("https://www.google.com");
        } else if (page.equalsIgnoreCase("documents")) {
            getDriver().get("https://skryabin.com/webdriver/html/new_window.html");
        }
    }

    @And("^I print page details$")
    public void iPrintPageDetails() {
        System.out.println("Page Title: " + getDriver().getTitle());
        System.out.println("Page Url: " + getDriver().getCurrentUrl());
        System.out.println("Window Handle: " + getDriver().getWindowHandle());
        //  System.out.println("Page Source: " + getDriver().getPageSource());
    }

    @And("^I go back and forward, then refresh the page$")
    public void iGoBackAndForwardThenRefreshThePage() {
        getDriver().navigate().back();
        getDriver().navigate().forward();
        getDriver().navigate().refresh();
    }

    @And("^I fill required fields$")
    public void iFillRequiredFields() {
        getDriver().findElement(By.xpath("//*[@name='username']")).sendKeys("sara");
        getDriver().findElement(By.xpath("//*[@name='email']")).sendKeys("sara@mail.com");
        getDriver().findElement(By.xpath("//*[@name='password']")).sendKeys("summer123");
        getDriver().findElement(By.xpath("//*[@name='confirmPassword']")).sendKeys("summer123");
        getDriver().findElement(By.xpath("//*[@id='name']")).click();
        getDriver().findElement(By.xpath("//*[@id='firstName']")).sendKeys("Sara");
        getDriver().findElement(By.xpath("//*[@id='middleName']")).sendKeys("M");
        getDriver().findElement(By.xpath("//*[@id='lastName']")).sendKeys("Romero");
        getDriver().findElement(By.xpath("//*[@aria-describedby='nameDialog']//*[text()='Save']")).click();
        getDriver().findElement(By.xpath("//*[@name='agreedToPrivacyPolicy']")).click();
    }

    @And("^I fill non required fields$")
    public void iFillNonRequiredFields() {
        getDriver().findElement(By.xpath("//*[@name='phone']")).sendKeys("111-888-1345");
        getDriver().findElement(By.xpath("//*[@id='dateOfBirth']")).click();
        getDriver().findElement(By.xpath("//select[@class='ui-datepicker-month']/*[text()='Feb']")).click();
        getDriver().findElement(By.xpath("//select[@class='ui-datepicker-year']/*[@value='1984']")).click();
        getDriver().findElement(By.xpath("//*[@class=' ui-datepicker-week-end ']/*[text()='5']")).click();

        getDriver().findElement(By.xpath("//select[@name='countryOfOrigin']/*[@value='Canada']")).click();
        getDriver().findElement(By.xpath("//*[@value='female']")).click();
        getDriver().findElement(By.xpath("//*[@name='allowedToContact']")).click();
        getDriver().findElement(By.xpath("//*[@id='address']")).sendKeys("123 mary lane, mtn view, ca 94043");
        getDriver().findElement(By.xpath("//*[@name='carMake']/*[@value='BMW']")).click();

        getDriver().switchTo().frame("additionalInfo");
        getDriver().findElement(By.xpath("//*[@id='contactPersonName']")).sendKeys("remi");
        getDriver().findElement(By.xpath("//*[@id='contactPersonPhone']")).sendKeys("678-878-3456");
        getDriver().switchTo().defaultContent();

        getDriver().findElement(By.xpath("//*[@id='thirdPartyButton']")).click();
        getDriver().switchTo().alert().accept();

        getDriver().findElement(By.xpath("//*[contains(@onclick,'window.open')]")).click();
    }

    @And("^I submit page$")
    public void iSubmitPage() {
        getDriver().findElement(By.xpath("//*[@id='formSubmit']")).click();
    }

    @And("^I add contact \"([^\"]*)\" with phone \"([^\"]*)\"$")
    public void iAddContactWithPhone(String name, String phone) throws Throwable {
        getDriver().switchTo().frame("additionalInfo");
        getDriver().findElement(By.xpath("//*[@id='contactPersonName']")).sendKeys(name);
        getDriver().findElement(By.xpath("//*[@id='contactPersonPhone']")).sendKeys(phone);
        getDriver().switchTo().defaultContent();
    }

    @And("^I verify \"([^\"]*)\" in related documents$")
    public void iVerifyInRelatedDocuments(String title) throws Throwable {
        String original = getDriver().getWindowHandle();

        getDriver().findElement(By.xpath("//*[contains(@onclick,'window.open')]")).click();

        Set<String> windows = getDriver().getWindowHandles();
        windows.remove(original);

        for (String window : windows) {
            getDriver().switchTo().window(window);
            String document2 = getDriver().findElement(By.xpath("(//body//li)[2]")).getText();
            assertTrue(document2.equalsIgnoreCase(title));
        }
        // getDriver().switchTo().window(original);
//or
//        Set<String> windowHandles = getDriver().getWindowHandles();
//        for (String windowHandle: windowHandles){
//            getDriver().switchTo().window(windowHandle);
//        }

    }

    @When("^I verify email field behavior$")
    public void iVerifyEmailFieldBehavior() {
        getDriver().findElement(By.xpath("//*[@name='email']")).clear();
        getDriver().findElement(By.xpath("//*[@name='email']")).sendKeys("sara");
        getDriver().findElement(By.xpath("//*[@name='password']")).click();

        String errorMsg = getDriver().findElement(By.xpath("//*[@id='email-error']")).getText();
        assertTrue(errorMsg.equalsIgnoreCase("Please enter a valid email address."));
    }

    @And("^I verify \"([^\"]*)\" page$")
    public void iVerifyPage(String arg0) throws Throwable {
        getDriver().findElement(By.xpath("//body/*[text()='Documents List:']")).isDisplayed();
    }

    @And("^I \"([^\"]*)\" third party agreement$")
    public void iThirdPartyAgreement(String choice) throws Throwable {
        getDriver().findElement(By.xpath("//*[@id='thirdPartyButton']")).click();

        if (choice.equalsIgnoreCase("accept")) {
            getDriver().switchTo().alert().accept();
        } else if (choice.equals("dismiss")) {
            getDriver().switchTo().alert().dismiss();
        } else {
            throw new Exception("Unsupported action: " + choice);
        }
    }
}
