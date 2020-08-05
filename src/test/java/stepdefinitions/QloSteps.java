package stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class QloSteps {

    private WebDriver driver;

    private static final String SUCCESS_ACCOUNT_CREATION_MSG = "Your account has been created.";


    @Given("^the user is on Qlo register page$")
    public void theUserIsOnQloRegisterPage() {

        // Start a Chrome Browser
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Maximize the browser window
        driver.manage().window().maximize();

        // Go to Qlo registration page
        driver.get("https://qloapps.coderslab.pl/en/login");
    }

    @When("^user enters valid  \"([^\"]*)\" address which hasn't been used$")
    public void userEntersValidAddressWhichHasnTBeenUsed(String email) throws Throwable {

        //finding elements
        WebElement emailInput = driver.findElement(By.id("email_create"));
        emailInput.clear();
        //entering email and submit
        emailInput.sendKeys(email);
        emailInput.submit();
    }

    @And("^user enters the valid : \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userEntersTheValidAnd(String firstName, String lastName, String password) throws Throwable {

        //finding registration form elements
        WebElement gender = driver.findElement(By.xpath("//div[@id='uniform-id_gender1']"));
        WebElement firstNameInput = driver.findElement(By.id("customer_firstname"));
        WebElement lastNameInput = driver.findElement(By.id("customer_lastname"));
        WebElement passwordInput = driver.findElement(By.id("passwd"));
        WebElement submitBtn = driver.findElement(By.id("submitAccount"));

        //entering data
        gender.click();

        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);

        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);

        passwordInput.clear();
        passwordInput.sendKeys(password);

        submitBtn.click();
    }

    @Then("^the account has been created$")
    public void theAccountHasBeenCreated() {
        //asserting that account was sucessfully created
        WebElement successAlert = driver.findElement(By.xpath("//p[@class='alert alert-success']"));
        Assert.assertTrue(successAlert.getText().contains(SUCCESS_ACCOUNT_CREATION_MSG));
    }

    @And("^user is logged in and sees name \"([^\"]*)\"$")
    public void userIsLoggedInAndSeesName(String firstName) {
        WebElement isUserLoggedIn = driver.findElement(By.xpath("//span[@class='account_user_name hide_xs']"));
        Assert.assertEquals(firstName, isUserLoggedIn.getText());
    }

}
