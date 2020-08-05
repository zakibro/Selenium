package przyklad1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyStorePurchaseTest {

    private WebDriver driver;
    private static final String EMAIL = "paweltestuje@gmail.com";
    private static final String PASSWORD = "password123";
    private static final int RANDOM_MAX_RANGE = 100;
    Random random = new Random();
    private boolean isFirstProductAdded = false;
    private boolean isSecondProductAdded = false;
    private static final int NUMBER_OF_PRODUCTS_ORDERED = 2;
    private static final String ORDER_CREATED_MESSAGE = "YOUR ORDER IS CONFIRMED";

    @Before
    public void setUp() {
        // Initialize new Chrome browser
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Maximize the window
        driver.manage().window().maximize();

        // go to login page
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&back=my-account");

        //find login elements
        WebElement emailInput = driver.findElement(By.name("email"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement loginBtn = driver.findElement(By.id("submit-login"));

        //enter credentials
        emailInput.clear();
        emailInput.sendKeys(EMAIL);

        passwordInput.clear();
        passwordInput.sendKeys(PASSWORD);

        if (loginBtn.isEnabled()) {
            loginBtn.click();
        } else Assert.fail();

    }

    @Test
    public void myStorePurchaseTest() throws InterruptedException {

        while (!isFirstProductAdded) {

            //Go to first category page 'Accessories'
            driver.get("https://prod-kurs.coderslab.pl/index.php?id_category=2&controller=category&q=Categories-Accessories");

            //finding elements of 'Accessories' products in category and pick random
            findAndPickRandom();

            //try adding first product to the cart, continue if it is out of stock
            WebElement addToCartBtn = driver.findElement(By.xpath("//button[@class='btn btn-primary add-to-cart']"));
            if (addToCartBtn.isEnabled()) {
                addToCartBtn.click();
                isFirstProductAdded = true;
                WebElement continueShoppingBtn = driver.findElement(By.xpath("//button[@class='btn btn-secondary']"));
                continueShoppingBtn.click();
                break;
            }
        }

        while (!isSecondProductAdded) {

            //Go to the second category page 'Art'
            driver.get("https://prod-kurs.coderslab.pl/index.php?id_category=9&controller=category");

            //finding elements of 'Art' products in category and pick random
            findAndPickRandom();

            //try adding second product to the cart, continue if it is out of stock
            WebElement addToCartBtn = driver.findElement(By.xpath("//button[@class='btn btn-primary add-to-cart']"));
            if (addToCartBtn.isEnabled()) {
                addToCartBtn.click();
                isSecondProductAdded = true;
                WebElement proceedToCheckoutBtn = driver.findElement(By.xpath("//a[@class='btn btn-primary']"));
                Thread.sleep(1000);
                proceedToCheckoutBtn.click();
                break;
            }
        }
        //checking if number of products is correct
        WebElement numberOfproducts = driver.findElement(By.xpath("//span[@class='label js-subtotal']"));
        Assert.assertEquals(Character.getNumericValue(numberOfproducts.getText().charAt(0)), NUMBER_OF_PRODUCTS_ORDERED);

        //proceed to checkout
        WebElement checkoutBtn = driver.findElement(By.xpath("//a[@class='btn btn-primary']"));
        if (checkoutBtn.isEnabled()) {
            checkoutBtn.click();
        } else Assert.fail();

        //confirm address
        WebElement continueBtn = driver.findElement(By.name("confirm-addresses"));
        if (continueBtn.isEnabled()) {
            continueBtn.click();
        } else Assert.fail();

        //pick delivery option
        WebElement deliveryoption1 = driver.findElement(By.id("delivery_option_1"));
        if (!deliveryoption1.isSelected()) {
            deliveryoption1.click();
        }
        //confirm delivery method
        WebElement confirmDeliveryBtn = driver.findElement(By.name("confirmDeliveryOption"));
        if (confirmDeliveryBtn.isEnabled()) {
            confirmDeliveryBtn.click();
        } else Assert.fail();

        //pick payment
        WebElement paywithWireOption = driver.findElement(By.id("payment-option-2"));
        if (!paywithWireOption.isSelected()) {
            paywithWireOption.click();
        }

        //confirm conditions
        WebElement conditionsCheckbox = driver.findElement(By.id("conditions_to_approve[terms-and-conditions]"));
        if (!conditionsCheckbox.isSelected()) {
            conditionsCheckbox.click();
        }

        //finalize the order
        WebElement orderBtn = driver.findElement(By.xpath("//button[@class='btn btn-primary center-block']"));
        if (orderBtn.isEnabled()) {
            orderBtn.click();
        } else Assert.fail();

        //assert order has been created
        String successMessage = driver.findElement(By.id("content-hook_order_confirmation")).getText();
        Assert.assertTrue(successMessage.contains(ORDER_CREATED_MESSAGE));
    }

    @After
    public void tearDown() throws Exception {
        // Close the browser
        driver.quit();
    }

    /**
     * finds all the products
     * finds all the products after category has been selected and pick ups random
     * @throws InterruptedException
     */
    public void findAndPickRandom() throws InterruptedException {
        Thread.sleep(1000);
        List<WebElement> products = driver.findElements(By.cssSelector("a.thumbnail.product-thumbnail"));
        products.get(random.nextInt(RANDOM_MAX_RANGE) % products.size()).click();
    }
}
