package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import support.TestContext;

import java.util.List;

public class Usps extends Page {
    @FindBy(xpath = "//a[@id='mail-ship-width']")
    private WebElement send;

    @FindBy(xpath = "//*[contains(@href, 'https://tools.usps.com/go/ZipLookup')]")
    private WebElement lookupAZip;

    @FindBy(xpath = "//*[@href='/zip-code-lookup.htm?byaddress'][contains(@class,'zip-code-address')]")
    private WebElement byAddress;

    @FindBy(xpath = "//*[@id='tAddress']")
    private WebElement street;

    @FindBy(xpath = "//*[@id='tCity']")
    private WebElement city;

    @FindBy(xpath = "//*[@id='tState']")
    private WebElement state;

    @FindBy(xpath = "//*[@id='zip-by-address']")
    private WebElement findButton;

    @FindBy(xpath = "//div[@id='zipByAddressDiv']")
    private WebElement zipSearchResultElement;

    @FindBy(xpath = "(//div[@id='zipByAddressDiv']//li)[1]")
    private WebElement firstResultItem;

    @FindBy(xpath = "//div[@id='zipByAddressDiv']//li")
    private List<WebElement> items;

    @FindBy(xpath = "//*[@href='/calculateretailpostage/welcome.htm']")
    private WebElement calculatePrice;

    @FindBy(xpath = "//*[@id='CountryID']")
    private WebElement country;

    @FindBy(xpath = "//*[@id='option_1']")
    private WebElement postcard;

    @FindBy(xpath = "//*[@id='quantity-0']")
    private WebElement quantity;

    @FindBy(xpath = "//*[@value='Calculate']")
    private WebElement calculateButton;

    @FindBy(xpath = "//*[@id='total']")
    private WebElement total;

    @FindBy(xpath = "//a[@id='navpostalstore']/../a[@class='menuitem']")
    private WebElement postalStore;

    @FindBy(xpath = "//*[@id='store-search']")
    private WebElement storeSearch;

    @FindBy(xpath = "//*[@id='store-search-btn']")
    private WebElement storeSearchBtn;

    @FindBy(xpath = "//*[@class='no-product']/p")
    private WebElement noProductMessage;

    public Usps() {
        setUrl("https://www.usps.com/");
    }

    public void lookupZipByAddress() {
        click(send);
        waitForVisible(lookupAZip);
        click(lookupAZip);
        waitForVisible(byAddress);
        click(byAddress);
    }

    public void fillAddress(String aStreet, String aCity, String aState) {
        waitForVisible(street);
        sendKeys(street, aStreet);
        sendKeys(city, aCity);
        new Select(state).selectByValue(aState);
        click(findButton);
    }

    public List<WebElement> getZipList(String aZip) {
        TestContext.getWait().until(ExpectedConditions.textToBePresentInElement(zipSearchResultElement, aZip));

        TestContext.getWait().until(ExpectedConditions.textToBePresentInElement(firstResultItem, aZip));

        return items;
    }

    public void calculatePrice() {
        click(send);
        waitForVisible(calculatePrice);
        click(calculatePrice);
    }

    public void selectCountryShape() {
        waitForVisible(country);
        new Select(country).selectByValue("10150");
        click(postcard);
    }

    public void selectQuantity(String count) {
        waitForVisible(quantity);
        sendKeys(quantity, count);
    }

    public String calculate() {
        String originalPrice = total.getText();
        click(calculateButton);
        TestContext.getWait().until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(total, originalPrice)));
        return total.getText();
    }

    public void postalStoreTab() {
        click(postalStore);
    }

    public void storeSearch(String store) {
        sendKeys(storeSearch, store);
    }

    public void storeSearchBtn() {
        click(storeSearchBtn);
    }

    public String noProductMessage() {
        waitForVisible(noProductMessage);
        return noProductMessage.getText();
    }

}
