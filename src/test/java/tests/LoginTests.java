package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import utilities.Driver;

public class LoginTests {

    WebDriver driver;
    LoginPage loginPage;

    @Test
    public void testSuccessfulLogin() {
        driver = Driver.getDriver();
        driver.get("https://codewise.studymate.us/login");
        loginPage = new LoginPage();
        loginPage.logIn("Admin@codewise.com", "codewise123");
    }
}
