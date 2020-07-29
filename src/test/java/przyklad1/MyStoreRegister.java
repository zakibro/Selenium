package przyklad1;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Date;
import java.util.Random;

public class MyStoreRegister {

    private WebDriver driver;
    private static final int NUMBER_OF_ACCOUNTS_TO_CREATE = 3;
    private static final int RANDOM_MAX_RANGE = 100;
    Random random = new Random();
    Date date= new Date();
    String webpage = "https://prod-kurs.coderslab.pl/index.php?controller=authentication&create_account=1";

    String [] firstNames = {"Paweł", "Karol", "Maciej", "Jakub", "Michał", "Zbigniew", "Tester"};
    String [] lastNames = {"Kowalski", "Mazur", "Lewandowski", "Aleksandrowicz", "Danielak", "Testowy"};
    String [] emails = {"@gmail.com", "@yahoo.com", "@interia.pl", "@onet.pl", "@wp.pl", "@o2.pl", "@gazeta.pl"};


    @Before
    public void setUp() {
        // Uruchom nowy egzemplarz przeglądarki Chrome
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();

        // Zmaksymalizuj okno przeglądarki
        driver.manage().window().maximize();
        // Przejdź do strony Sign in strony MyStore
        driver.get(webpage);
    }

    @Test
    public void MyStoreRegisterTest() throws InterruptedException {

        //Stwórz tyle kont użytkowników ile zdefiniowane w NUMBER_OF_ACCOUNTS_TO_CREATE
        for (int i = 0; i < NUMBER_OF_ACCOUNTS_TO_CREATE; i++) {

            //Znajdowanie elementów
            WebElement firstName = driver.findElement(By.name("firstname"));
            WebElement lastName = driver.findElement(By.name("lastname"));
            WebElement email = driver.findElement(By.name("email"));
            WebElement password = driver.findElement(By.name("password"));
            WebElement saveBtn = driver.findElement(By.xpath("//button[@data-link-action='save-customer']"));

            firstName.sendKeys(generateRandomFirstName());
            lastName.sendKeys(generateRandomLastName());
            email.sendKeys(generateRandomEmail());
            password.sendKeys(randomPasswordGenerator());
            saveBtn.click();

            //Proces wylogowania
            WebElement logOutBtn = driver.findElement(By.xpath("//a[@class='logout hidden-sm-down']"));
            logOutBtn.click();
            driver.get(webpage);
        }
    }

    //generates email with the use of timestamp
    public String generateRandomEmail(){
        long time = date.getTime();
        return "testowy" + time  + emails[random.nextInt(RANDOM_MAX_RANGE) % emails.length];
    }

    public String generateRandomFirstName(){
        return firstNames[random.nextInt(RANDOM_MAX_RANGE) % firstNames.length];
    }

    public String generateRandomLastName(){
        return lastNames[random.nextInt(RANDOM_MAX_RANGE) % lastNames.length];
    }

    public String randomPasswordGenerator(){
        String result;
        StringBuilder sb = new StringBuilder();
        int numberOfCharsInPwd = 5;
        String setOfCharacters = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";

        for (int i= 0; i < numberOfCharsInPwd; i++) {
            int k = random.nextInt(setOfCharacters.length()-1);
            sb.append(setOfCharacters.charAt(k));
        }
        return result = sb.toString();
    }
}
