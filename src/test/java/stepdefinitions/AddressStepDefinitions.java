package stepdefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AddressPage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class AddressStepDefinitions {

    private WebDriver driver;
    private LoginPage loginPage;
    private AddressPage addressPage;
    private static final String LOGGED_USER = "Paweł Mazur";
    private static final String EMAIL = "paweltestuje@gmail.com";
    private static final String PASSWORD = "password123";

    @Given("^user is logged in my store$")
    public void userIsLoggedInMyStore() {

        //initiate new Chrome Browser and setup global wait
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Maximize the browser window
        driver.manage().window().maximize();

        //login correctly to my store
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account");
        loginPage = new LoginPage(driver);
        loginPage.loginAs(EMAIL, PASSWORD);
        Assert.assertEquals(LOGGED_USER, loginPage.getLoggedUsername());

    }

    @When("^user goes to AddressPage$")
    public void userGoesToAddressPage() {
        //go to Add new address page
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=address");
        addressPage = new AddressPage(driver);
    }

    @And("^user enters required fields with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userEntersRequiredFieldsWithData(String company, String address, String postcode, String city) {
        //enter the required fields
        addressPage.enterRequiredFields(company, address, postcode, city);
    }

    @And("^user saves information$")
    public void userSavesInformation() {
        //save the form
        addressPage.saveNewAddress();
    }

    @Then("^User sees \"([^\"]*)\"$")
    public void userSeesTheConfirmation(String successMessage) {
        //assert the address has been correctly added
        WebElement successAlert = driver.findElement(By.xpath("//article[@class='alert alert-success']"));
        successAlert.getText().equals(successMessage);
        Assert.assertEquals(successMessage, successAlert.getText());
    }
}
