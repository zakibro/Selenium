package przyklad1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyStoreFilteringTest {

    private final static double LOW_PRICE = 11.00;
    private final static double MAX_PRICE = 14.00;

    private WebDriver driver;
    private final static String WEBPAGE  = "https://prod-kurs.coderslab.pl/index.php?id_category=2&controller=category";
    private final static String[] Accessories = {"Mug", "Cushion", "Poster", "Notebook"};

    @Before
    public void setUp() {
        // Start a new Chrome Browser
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();

        // Maximize the window
        driver.manage().window().maximize();
        // Go to the website
        driver.get(WEBPAGE);
        // set up implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void MyStoreFilteringTest() throws InterruptedException {

        //finding elements
        WebElement category = driver.findElement(By.
                xpath("//a[@class='_gray-darker search-link js-search-link'][contains(text(),'Accessories')]"));

        //entering data/clicking filters
        if (category.isEnabled()){
            category.click();
        }
        else Assert.fail();

        //without the below wait the script takes all the products before the filtering is applied
        Thread.sleep(1500);


        WebElement priceFilter = driver.findElement(By.xpath("//a[contains(text(),'11.00')]"));
        priceFilter.click();

        Thread.sleep(1000);


        List<WebElement> pricesOfFoundProducts = driver.findElements(By.
                className("price"));


        for (int i = 0; i < pricesOfFoundProducts.size(); i++) {
            String priceWith$ = pricesOfFoundProducts.get(i).getText();
            String priceString = priceWith$.replaceAll("[€]", "");
            double priceDouble = Double.parseDouble(priceString);
            if (priceDouble >= LOW_PRICE || priceDouble <= MAX_PRICE)
                System.out.println(priceDouble);
        }

    }
}
