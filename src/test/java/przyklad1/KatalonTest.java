package przyklad1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class KatalonTest {

    private WebDriver driver;
    private final static String FIRST_NAME = "Karol";
    private final static String LAST_NAME = "Kowalski";
    private final static String BIRTH_DATE = "05/22/2010";
    private final static String ADDRESS = "Prosta 51";
    private final static String EMAIL = "karol.kowalski@mailinator.com";
    private final static String PASSWORD = "Pass123";
    private final static String COMPANY = "Coders Lab";
    private final static String COMMENT = "To jest mój pierwszy automat testowy";

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
        WebElement firstNameLabel = driver.findElement(By.xpath("//label[contains(text(),'First name')]"));

        WebElement lastName = driver.findElement(By.id("last-name"));
        WebElement lastNameLabel = driver.findElement(By.xpath("//label[contains(text(),'Last name')]"));

        WebElement gender = driver.findElement(By.xpath("//label[contains(text(),'Male')]"));
        WebElement genderLabel = driver.findElement(By.xpath("//label[contains(text(),'Gender')]"));

        WebElement dateOfBirth = driver.findElement(By.id("dob"));
        WebElement dateOfBirthLabel = driver.findElement(By.xpath("//label[contains(text(),'Date of birth')]"));

        WebElement address = driver.findElement(By.id("address"));
        WebElement addressLabel = driver.findElement(By.xpath("//label[contains(text(),'Address')]"));

        WebElement email = driver.findElement(By.id("email"));
        WebElement emailLabel = driver.findElement(By.xpath("//label[contains(text(),'Email')]"));

        WebElement password = driver.findElement(By.id("password"));
        WebElement passwordLabel = driver.findElement(By.xpath("//label[contains(text(),'Password')]"));

        WebElement company = driver.findElement(By.id("company"));
        WebElement companyLabel = driver.findElement(By.xpath("//label[contains(text(),'Company')]"));


        WebElement comment = driver.findElement(By.id("comment"));
        WebElement commentLabel = driver.findElement(By.xpath("//label[contains(text(),'Comment')]"));


        WebElement submitBtn = driver.findElement(By.id("submit"));


        //Sprawdź czy element jest dostępny/widoczny, jeśli tak to wprowadź dane

        enterDataIfElementIsDisplayed(firstName,firstNameLabel,FIRST_NAME);
        enterDataIfElementIsDisplayed(lastName,lastNameLabel,LAST_NAME);

        if (gender.isEnabled()){
            gender.click();
            printTheLabel(genderLabel, gender.getText());
        }
        else Assert.fail();

        enterDataIfElementIsDisplayed(dateOfBirth,dateOfBirthLabel,BIRTH_DATE);
        enterDataIfElementIsDisplayed(address,addressLabel,ADDRESS);
        enterDataIfElementIsDisplayed(email,emailLabel,EMAIL);
        enterDataIfElementIsDisplayed(password,passwordLabel,PASSWORD);
        enterDataIfElementIsDisplayed(company,companyLabel,COMPANY);
        enterDataIfElementIsDisplayed(comment,commentLabel,COMMENT);


        //Wyslij formularz
        if (submitBtn.isEnabled()){
            submitBtn.click();
        }
        else Assert.fail();
    }

    @After
    public void tearDown() throws Exception {
        // Zamknij przeglądarkę
        driver.quit();
    }

    void printTheLabel(WebElement labelElement, String value){
        System.out.println(labelElement.getText() + " : " + value);
    }

    void enterDataIfElementIsDisplayed(WebElement element, WebElement elementLabel, String value){
        if (element.isDisplayed()){
            element.clear();
            element.sendKeys(value);
            printTheLabel(elementLabel, value);
        }
        else Assert.fail();
    }
}
