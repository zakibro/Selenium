package przyklad1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoAUTTest {

    private WebDriver driver;


    @Before
    public void setUp() {
        // Uruchom nowy egzemplarz przeglądarki Chrome
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();

        // Zmaksymalizuj okno przeglądarki
        driver.manage().window().maximize();
        // Przejdź do strony
        driver.get("https://katalon-test.s3.amazonaws.com/demo-aut/dist/html/form.html");
    }

    @Test
    public void testSendingForm() {

        //Znajdź elementy
        WebElement firstName = driver.findElement(By.id("first-name"));
        WebElement lastName = driver.findElement(By.id("last-name"));
        WebElement gender = driver.findElement(By.xpath("//label[contains(text(),'Male')]"));
        WebElement dateOfBirth = driver.findElement(By.id("dob"));
        WebElement address = driver.findElement(By.id("address"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement company = driver.findElement(By.id("company"));
        WebElement comment = driver.findElement(By.id("comment"));
        WebElement submitBtn = driver.findElement(By.id("submit"));

        //Wprowadź dane
        firstName.clear();
        firstName.sendKeys("Karol");

        lastName.clear();
        lastName.sendKeys("Kowalski");

        gender.click();

        dateOfBirth.sendKeys("05/22/2010");

        address.clear();
        address.sendKeys("Prosta 51");

        email.clear();
        email.sendKeys("karol.kowalski@mailinator.com");

        password.clear();
        password.sendKeys("Pass123");

        company.clear();
        company.sendKeys("Coders Lab");

        comment.clear();
        comment.sendKeys("To jest mój pierwszy automat testowy");

        //Wyslij formularz
        submitBtn.click();
    }

    @After
    public void tearDown() throws Exception {
        // Zamknij przeglądarkę
        driver.quit();
    }
}
