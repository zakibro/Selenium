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
    private static final int NUMBER_OF_ACCOUNTS_TO_CREATE = 10;
    private static final int RANDOM_MAX_RANGE = 1000;
    Random random = new Random();
    Date date= new Date();
    String webpage = "https://prod-kurs.coderslab.pl/index.php?controller=authentication&create_account=1";

    String [] firstNames = {"Paweł", "Karol", "Maciej", "Jakub", "Michał", "Zbigniew", "Tester"};
    String [] lastNames = {"Kowalski", "Mazur", "Lewandowski", "Aleksandrowicz", "Danielak", "Testowy"};
    String [] emails = {"@gmail.com", "@yahoo.com", "@interia.pl", "@onet.pl", "@wp.pl", "@o2.pl", "@gazeta.pl"};


    @Before
    public void setUp() {
        // Start a new Chrome Browser
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();

        // Maximize the window
        driver.manage().window().maximize();
        // Go to the website
        driver.get(webpage);
    }

    @Test
    public void MyStoreRegisterTest() throws InterruptedException {

        //Create accounts based on NUMBER_OF_ACCOUNTS_TO_CREATE
        for (int i = 0; i < NUMBER_OF_ACCOUNTS_TO_CREATE; i++) {

            //Finding elements
            WebElement firstName = driver.findElement(By.name("firstname"));
            WebElement lastName = driver.findElement(By.name("lastname"));
            WebElement email = driver.findElement(By.name("email"));
            WebElement password = driver.findElement(By.name("password"));
            WebElement saveBtn = driver.findElement(By.xpath("//button[@data-link-action='save-customer']"));

            //Enter data
            firstName.sendKeys(generateRandomFirstName());
            lastName.sendKeys(generateRandomLastName());
            email.sendKeys(generateRandomEmail());
            password.sendKeys(randomPasswordGenerator());
            saveBtn.click();

            //Log out
            WebElement logOutBtn = driver.findElement(By.xpath("//a[@class='logout hidden-sm-down']"));
            logOutBtn.click();
            driver.get(webpage);
        }
    }

    //generate random email with the use of timestamp
    public String generateRandomEmail(){
        long time = date.getTime();
        return "testowy" + time + random.nextInt(RANDOM_MAX_RANGE) +
                emails[random.nextInt(RANDOM_MAX_RANGE) % emails.length];
    }

    public String generateRandomFirstName(){
        return firstNames[random.nextInt(RANDOM_MAX_RANGE) % firstNames.length];
    }

    public String generateRandomLastName(){
        return lastNames[random.nextInt(RANDOM_MAX_RANGE) % lastNames.length];
    }

    //generate random 5 character password
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
