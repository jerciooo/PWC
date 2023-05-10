import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class RegisterTest {
    private WebDriver driver;
    private RegisterPage registerPage;
    private HomePage homePage;
    private MyAccountPage myAccountPage;
    
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com/");
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @Test
    public void testRegistration() {
        // Navigate to the registration page
        registerPage = homePage.clickCreateAccountButton();

        // Fill in the registration form
        registerPage.enterFirstName("John");
        registerPage.enterLastName("Doe");
        registerPage.enterEmail("johndoe@example.com");
        registerPage.enterPassword("password123.");
        registerPage.confirmPassword("password123.");
        myAccountPage = registerPage.clickRegisterButton();

        // Verify that the registration was successful
        Assert.assertTrue(myAccountPage.isWelcomeMessageDisplayed());
        Assert.assertEquals("John Doe", myAccountPage.getFullName());
        Assert.assertEquals("johndoe@example.com", myAccountPage.getEmail());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}