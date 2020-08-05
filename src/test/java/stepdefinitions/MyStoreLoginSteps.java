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

public class MyStoreLoginSteps {

    private WebDriver driver;

    @Given("^an open browser with my store login page$")
    public void anOpenBrowserWithMyStoreLoginPage() {

        // Start a new Chrome Browser
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();

        // Maximize the window
        driver.manage().window().maximize();
        // Go to the website
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account");

    }

    @When("^user enters valid \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userEntersValidCredentials(String email, String password) {

        insertTheCredentials(email, password);
    }

    @And("^submits the login form$")
    public void submitsTheLoginForm() {

        WebElement signInBtn = driver.findElement(By.id("submit-login"));
        if (signInBtn.isEnabled()) {
            signInBtn.click();
        } else Assert.fail();
    }

    @Then("^the user is logged on correct account with the \"([^\"]*)\"$")
    public void theUserIsLoggedOnCorrectAccount(String accountName) {

        WebElement isUserLoggedIn = driver.findElement(By.xpath("//span[@class='hidden-sm-down']"));
        Assert.assertEquals(isUserLoggedIn.getText(), accountName);

    }

    @When("^user enters invalid \"([^\"]*)\" or \"([^\"]*)\"$")
    public void userEntersInvalidCredentials(String email, String password){

        insertTheCredentials(email, password);
    }

    @Then("^the user is not logged in$")
    public void theUserIsNotLoggedIn() {
        WebElement isUserNotLoggedIn = driver.findElement(By.xpath("//span[contains(text(),'Sign in')]"));
        if (!isUserNotLoggedIn.isDisplayed()){
            Assert.fail();
        }
    }

    @And("^sees the \"([^\"]*)\"$")
    public void seesThe(String message){

        WebElement loginFailMessage = driver.findElement(By.xpath("//li[@class='alert alert-danger']"));
        Assert.assertEquals(loginFailMessage.getText(), message);

    }

    @And("^quit browser$")
    public void closeBrowser(){
        driver.quit();
    }

    private void insertTheCredentials(String email, String password) {
        //find login page elements
        WebElement emailInput = driver.findElement(By.name("email"));
        WebElement passwordInput = driver.findElement(By.name("password"));

        //enters data
        emailInput.clear();
        passwordInput.clear();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
    }

}
