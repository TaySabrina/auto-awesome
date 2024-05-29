package selenium.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pages.Home;
import java.time.Duration;


public class TestAutomation {
    private WebDriver driver;
    Home home = new Home();

    @Before
    public void setup(){
        // Setting ChromeDriver
        System.setProperty("webdriver.chrome.driver", "c:/windows/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testWebPage(){
        //Open WebPage
        driver.get("https://plexusworldwide.com/");
        String expectedTitle = "Founded in gut health. Experts in microbiome. | Plexus Worldwide®";
        String pageTitle = driver.getTitle();
        System.out.println(">>Opening Plexus Webpage and Getting title text");

        //Getting image element
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> d.findElement(home.logoHome));
        var imgHome =  driver.findElement(home.logoHome);
        imgHome.isDisplayed();

        //Title and image assertion
        Assert.assertEquals(expectedTitle, pageTitle);
        System.out.println(">>Assertion: Comparison between expected title and page title");
        Assert.assertTrue(imgHome.isDisplayed());
        System.out.println(">>Assertion: Checking image display");

        //Navigate to another page
        driver.navigate().to("https://plexusworldwide.com/about");
        String currentUrl = driver.getCurrentUrl();
        System.out.println(">>Navigating to About page");

        //Url assertion
        Assert.assertTrue(currentUrl.contains("/about"));
        System.out.println(">>Assertion: Checking currentUrl address");
    }

    @After
    public void tearDown(){
        //Close Driver
        if (driver != null) {
            driver.quit();
            System.out.println(">>Ending session.")
        }
    }
}

