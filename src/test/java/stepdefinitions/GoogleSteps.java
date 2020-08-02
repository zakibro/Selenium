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


public class GoogleSteps {

    private WebDriver driver;

    @Given("^an open browser with google\\.com$")
    public void anOpenBrowserWithGoogleCom() {
        // Uruchom nowy egzemplarz przeglądarki Chrome
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();

        // Zmaksymalizuj okno przeglądarki
        driver.manage().window().maximize();
        // Przejdź do Google
        driver.get("http://www.google.com");
    }

    @When("^a keyword (.*) is enetered in input field$")
    public void aKeywordSeleniumIsEneteredInInputField(String keyword) {

        // Znajdź element wprowadzania tekstu na podstawie jego nazwy
        WebElement element = driver.findElement(By.name("q"));
        // Wyczyść teskst zapisany w elemencie
        element.clear();

        // Wpisz informacje do wyszukania
        element.sendKeys(keyword);

        // Prześlij formularz
        element.submit();
    }

    @Then("^the first one should contain (.*)$")
    public void theFirstOneShouldContainSelenium(String expectedText) {
        //wyszukanie elementu
        WebElement resultWebElement = driver.findElement(By.xpath("//h3[@class='LC20lb DKV0Md']"));
        //asercja sprawdzająca czy pierwszy wynik zawiera zdefiniowany keyword
        Assert.assertTrue(resultWebElement.getText().toLowerCase().contains(expectedText));
    }

    @And("^close browser$")
    public void closeBrowser() {
        // Zamknij przeglądarkę
        driver.quit();
    }
}
