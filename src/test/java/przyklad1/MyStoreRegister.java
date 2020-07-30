package przyklad1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Date;
import java.util.Random;

public class MyStoreRegister {

    private WebDriver driver;
    private static final int NUMBER_OF_ACCOUNTS_TO_CREATE = 10;
    private static final int RANDOM_MAX_RANGE = 100;
    Random random = new Random();
    Date date = new Date();
    String webpage = "https://prod-kurs.coderslab.pl/index.php?controller=authentication&create_account=1";

    String[] firstNames = {"Paweł", "Karol", "Maciej", "Jakub", "Michał", "Zbigniew", "Tester"};
    String[] lastNames = {"Kowalski", "Mazur", "Lewandowski", "Aleksandrowicz", "Danielak", "Testowy"};
    String[] emails = {"@gmail.com", "@yahoo.com", "@interia.pl", "@onet.pl", "@wp.pl", "@o2.pl", "@gazeta.pl"};
    String[] companies = {"Shopify", "Google", "Facebook", "Twitter", "Apple", "Netflix", "Amazon", "Tesla", "Microsoft"};
    String[] addresses = {"York Road", "Mill Lane", "Chester Road", "Park Lane", "Highfield Road", "Station Road",
            "Broadway", "York Road"};
    String[] cities = {"INVERNESS", "DURHAM", "READING", "BELFAST", "DARTFORD", "SWINDON", "DONCASTER", "NOTTINGHAM",
            "MANCHESTER", "CHESTER"};


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
    public void MyStoreRegisterAndAccountSetingsTest() throws InterruptedException {

        //Create accounts based on NUMBER_OF_ACCOUNTS_TO_CREATE
        for (int i = 0; i < NUMBER_OF_ACCOUNTS_TO_CREATE; i++) {

            //Finding registration form elements
            WebElement firstName = driver.findElement(By.name("firstname"));
            WebElement lastName = driver.findElement(By.name("lastname"));
            WebElement email = driver.findElement(By.name("email"));
            WebElement password = driver.findElement(By.name("password"));
            WebElement RegisterFormSaveBtn = driver.findElement(By.xpath("//button[@data-link-action='save-customer']"));

            //Enter data to registration form
            firstName.sendKeys(generateRandomFirstName());
            lastName.sendKeys(generateRandomLastName());
            email.sendKeys(generateRandomEmail());
            password.sendKeys(randomPasswordGenerator());
            RegisterFormSaveBtn.click();

            //Going to Your account / Addresses
            driver.get("https://prod-kurs.coderslab.pl/index.php?controller=address");

            //Finding elements on Address form
            WebElement company = driver.findElement(By.name("company"));
            WebElement addres = driver.findElement(By.name("address1"));
            WebElement postcode = driver.findElement(By.name("postcode"));
            WebElement city = driver.findElement(By.name("city"));
            Select countryDropdown = new Select(driver.findElement(By.name("id_country")));
            WebElement addressFormSaveBtn = driver.findElement(By.cssSelector("button.btn.btn-primary.float-xs-right"));

            //Entering data to Address form
            company.sendKeys(generateRandomCompany());
            addres.sendKeys(generateRandomAddress());
            postcode.sendKeys(generateRandomPostcode());
            city.sendKeys(generateRandomCity());
            countryDropdown.selectByIndex(1);
            addressFormSaveBtn.click();

            //Log out
            WebElement logOutBtn = driver.findElement(By.xpath("//a[@class='logout hidden-sm-down']"));
            logOutBtn.click();
            driver.get(webpage);
        }
    }

    @After
    public void tearDown() throws Exception {
        // Close the browser
        driver.quit();
    }

    /**
     * Generates the random email
     * Uses the emails table and timestamp to randomize the output
     *
     * @return random generate email
     */
    public String generateRandomEmail() {
        long time = date.getTime();
        return "testowy" + time + random.nextInt(RANDOM_MAX_RANGE) +
                emails[random.nextInt(RANDOM_MAX_RANGE) % emails.length];
    }

    /**
     * Generates random first name
     * Uses the firstNames table
     *
     * @return random firstName
     */
    public String generateRandomFirstName() {
        return firstNames[random.nextInt(RANDOM_MAX_RANGE) % firstNames.length];
    }

    /**
     * Generates random last name
     * Uses the lastNames table
     *
     * @return random lastName
     */
    public String generateRandomLastName() {
        return lastNames[random.nextInt(RANDOM_MAX_RANGE) % lastNames.length];
    }

    /**
     * Generates random company
     * Uses the companies table
     *
     * @return random company
     */
    public String generateRandomCompany() {
        return companies[random.nextInt(RANDOM_MAX_RANGE) % companies.length];
    }

    /**
     * Generates random address
     * Uses random number and addresses table
     *
     * @return random address
     */
    public String generateRandomAddress() {
        return random.nextInt(RANDOM_MAX_RANGE) + " " + addresses[random.nextInt(RANDOM_MAX_RANGE) % addresses.length];
    }

    /**
     * Generates random UK postal code
     * Creates the code with the following format '[A-Z][A-Z][0-9][0-9] [0-9][A-Z][A-Z]'
     *
     * @return random postcode
     */
    public String generateRandomPostcode() {
        int numberOfCharsinCode = 8;
        StringBuilder sb = new StringBuilder();
        String setOfCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String setOfNumbers = "0123456789";
        int k = 0;

        for (int i = 0; i < numberOfCharsinCode; i++) {

            if (i == 2 || i == 3 || i == 5) {
                k = random.nextInt(setOfNumbers.length() - 1);
                sb.append(setOfNumbers.charAt(k));
            } else if (i == 4) {
                sb.append(" ");
            } else {
                k = random.nextInt(setOfCharacters.length() - 1);
                sb.append(setOfCharacters.charAt(k));
            }
        }
        return sb.toString();
    }

    /**
     * Generates random city
     * Uses the cities table
     *
     * @return random city
     */
    public String generateRandomCity() {
        return cities[random.nextInt(RANDOM_MAX_RANGE) % cities.length];
    }

    /**
     * Generates random 5 character password
     * Uses lowercase characters, digital numbers and special characters
     *
     * @return random password
     */
    public String randomPasswordGenerator() {
        StringBuilder sb = new StringBuilder();
        int numberOfCharsInPwd = 5;
        String setOfCharacters = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";

        for (int i = 0; i < numberOfCharsInPwd; i++) {
            int k = random.nextInt(setOfCharacters.length() - 1);
            sb.append(setOfCharacters.charAt(k));
        }
        return sb.toString();
    }
}
