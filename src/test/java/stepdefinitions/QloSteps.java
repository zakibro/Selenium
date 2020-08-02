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

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class QloSteps {

    private WebDriver driver;

    private static final int RANDOM_MAX_RANGE = 100;
    private static final String SUCCESS_ACCOUNT_CREATION_MSG = "Your account has been created.";
    Random random = new Random();
    Date date = new Date();
    private static final String FIRST_NAME = "Pawel";
    private static final String LAST_NAME = "Mazur";
    private static final String PASSWORD = "password123";

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

    @When("^user enters valid email address which hasn't been used$")
    public void userEntersValidEmailAddressWhichHasnTBeenUsed(){
        //finding elements
        WebElement email = driver.findElement(By.id("email_create"));
        email.clear();
        //entering random email and submitting
        email.sendKeys(generateRandomEmail());
        email.submit();
    }

    @And("^user enters the valid registration details$")
    public void userEntersTheValidRegistrationDetails() {
        //finding registration form elements
        WebElement gender = driver.findElement(By.xpath("//div[@id='uniform-id_gender1']"));
        WebElement firstName = driver.findElement(By.id("customer_firstname"));
        WebElement lastName = driver.findElement(By.id("customer_lastname"));
        WebElement password = driver.findElement(By.id("passwd"));
        WebElement submitBtn = driver.findElement(By.id("submitAccount"));

        //entering data
        gender.click();

        firstName.clear();
        firstName.sendKeys(FIRST_NAME);

        lastName.clear();
        lastName.sendKeys(LAST_NAME);

        password.clear();
        password.sendKeys(PASSWORD);

        submitBtn.click();
    }

    @Then("^the account has been created$")
    public void theAccountHasBeenCreated() {
        //asserting that account was sucessfully created
        WebElement successAlert = driver.findElement(By.xpath("//p[@class='alert alert-success']"));
        Assert.assertTrue(successAlert.getText().contains(SUCCESS_ACCOUNT_CREATION_MSG));
    }

    @And("^user is logged in$")
    public void userIsLoggedIn() {
        //aserting that user is correctly logged in
        WebElement isUserLoggedIn = driver.findElement(By.xpath("//span[@class='account_user_name hide_xs']"));
        Assert.assertEquals(FIRST_NAME, isUserLoggedIn.getText());
    }

    public String generateRandomEmail() {
        long time = date.getTime();
        return "testowy" + time + random.nextInt(RANDOM_MAX_RANGE) +
                "@gmail.com";
    }
}
