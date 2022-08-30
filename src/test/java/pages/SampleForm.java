package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static support.TestContext.getDriver;

public class SampleForm extends Page {
    @FindBy(xpath = "//*[@name='username']")
    private WebElement username;

    @FindBy(xpath = "//*[@name='email']")
    private WebElement email;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//*[@id='confirmPassword']")
    private WebElement confirmPassword;

    //enter name
    @FindBy(xpath = "//*[@id='name']")
    private WebElement name;

    @FindBy(xpath = "//*[@id='firstName']")
    private WebElement firstName;

    @FindBy(xpath = "//*[@id='middleName']")
    private WebElement middleName;

    @FindBy(xpath = "//*[@id='lastName']")
    private WebElement lastName;

    @FindBy(xpath = "//div[@aria-describedby='nameDialog']//span[text()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "//*[@name='phone']")
    private WebElement phone;
    //end of enter name

    //add for DOB
    @FindBy(xpath = "//*[@id='dateOfBirth']")
    private WebElement dateOfBirth;

    @FindBy(xpath = "//select[@class='ui-datepicker-month']")
    private WebElement month;

    @FindBy(xpath = "//select[@class='ui-datepicker-year']")
    private WebElement year;

    @FindBy(xpath = "//*[@data-handler='selectDay']/*[text()='11']")
    private WebElement day;

    @FindBy(xpath = "//select[@name='countryOfOrigin']")
    private WebElement countryOfOrigin;
    //end of DOB

    @FindBy(xpath = "//*[@name='gender'][@value='female']")
    private WebElement gender;
    //need to add sleep

    @FindBy(xpath = "//*[@type='checkbox'][@name='allowedToContact']")
    private WebElement allowedToContact;

    @FindBy(xpath = "//*[@id='address']")
    private WebElement address;

    @FindBy(xpath = "//*[@name='carMake']")
    private WebElement carMake;

    //add for additional info
    @FindBy(xpath = "//iframe[@name='additionalInfo']")
    private WebElement iFrame;

    @FindBy(xpath = "//input[@name='contactPersonName']")
    private WebElement contactPersonName;

    @FindBy(xpath = "//input[@id='contactPersonPhone']")
    private WebElement contactPersonPhone;

    @FindBy(xpath = "//*[@type='checkbox'][@name='agreedToPrivacyPolicy']")
    private WebElement agreedToPrivacyPolicy;

    @FindBy(xpath = "//*[@id='formSubmit']")
    private WebElement submitButton;

    public SampleForm() {
        setUrl("https://skryabin.com/webdriver/html/sample.html");
    }

    public void fillContactNamePhone(String name, String phone) {
        getDriver().switchTo().frame(iFrame);
        sendKeys(contactPersonName, name);
        sendKeys(contactPersonPhone, phone);
        getDriver().switchTo().defaultContent();
    }

    public void fillUsername(String value) {
        sendKeys(username, value);
    }

    public void fillEmail(String value) {
        sendKeys(email, value);
    }

    public void fillPassword(String value) {
        sendKeys(password, value);
    }

    public void fillConfirmPassword(String value) {
        sendKeys(confirmPassword, value);
    }

    public void fillName(String firstName, String middleName, String lastName) {
        click(name);
        sendKeys(this.firstName, firstName);
        sendKeys(this.middleName, middleName);
        sendKeys(this.lastName, lastName);
        click(saveButton);
    }

    public void fillPhone(String value) {
        sendKeys(phone, value);
    }

    public void selectDateOfBirth() {
        click(dateOfBirth);
    }

    public void selectMonth(String value) {
        new Select(month).selectByVisibleText(value);
    }

    public void selectYear(String value) {
        new Select(year).selectByValue(value);
    }

    public void selectDay() {
        click(day);
    }

    public void selectCountryOfOrigin(String value) {
        new Select(countryOfOrigin).selectByValue(value);
    }

    public void selectGender() {
        click(gender);
    }

    public void selectAllowedToContact() {
        click(allowedToContact);
    }

    public void fillAddress(String value) {
        sendKeys(address, value);
    }

    public void selectCarMake(String value) {
        new Select(carMake).selectByValue(value);
    }

    public void selectAgreedToPrivacyPolicy() {
        click(agreedToPrivacyPolicy);
    }

    public void clickSubmit() {
        click(submitButton);

    }

}
