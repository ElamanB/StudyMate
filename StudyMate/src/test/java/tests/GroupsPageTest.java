package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.GroupsPage;
import pages.LoginPage;
import utilities.Driver;

import java.time.Duration;

public class GroupsPageTest {

    WebDriver driver;
    LoginPage loginPage = new LoginPage();
    GroupsPage groupsPage = new GroupsPage();

    @BeforeEach
    public void startPoint() {
        driver = Driver.getDriver();
        driver.get("https://codewise.studymate.us/login");
        loginPage.logIn("Admin@codewise.com", "codewise123");

    }

    @Test
    public void testSuccessfulCreateGroup() {

        Assertions.assertTrue(groupsPage.groupsTab.isDisplayed());

        groupsPage.createGroupButton.click();

        Assertions.assertTrue(groupsPage.popUpGroupWindow.isDisplayed());

        groupsPage.groupNameInput.sendKeys("Group one!");
        groupsPage.creationDateInput.sendKeys("12042024");
        groupsPage.descriptionInput.sendKeys("Testing group creation!");

        int numOfGroupsBefore = groupsPage.listOfGroups.size();

        groupsPage.createButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath
                ("//div[@class='css-1qf1rpk']/div"), numOfGroupsBefore + 1));

        Assertions.assertTrue(groupsPage.newCreatedGroup.isDisplayed());

        int numOfGroupsAfter = groupsPage.listOfGroups.size();

        Assertions.assertTrue(groupsPage.listOfGroups.get(0).getText().contains("Group one!"));

        Assertions.assertEquals(numOfGroupsBefore, numOfGroupsAfter - 1);

    }

    @AfterEach
    public void endPoint() {
        driver.manage().deleteAllCookies();
    }
}
