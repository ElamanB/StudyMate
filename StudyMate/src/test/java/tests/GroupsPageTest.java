package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    public void testSuccessfulCreateGroup() throws InterruptedException {

        // Verify user see groups tab on the left side
        Assertions.assertTrue(groupsPage.groupsTab.isDisplayed());

        groupsPage.createGroupButton.click();

        // Verify user see popUp window with fields
        Assertions.assertTrue(groupsPage.popUpGroupWindow.isDisplayed());

        groupsPage.groupNameInput.sendKeys("Group one!");
        groupsPage.creationDateInput.sendKeys("12042024");
        groupsPage.descriptionInput.sendKeys("Testing group creation!");

        int numOfGroupsBefore = groupsPage.listOfGroups.size();

        groupsPage.createButton.click();

        Thread.sleep(2000);

        int numOfGroupsAfter = groupsPage.listOfGroups.size();

        Thread.sleep(2000);

        // Verify user see the created group in the list of the groups
        Assertions.assertTrue(groupsPage.listOfGroups.get(0).getText().contains("Group one!"));

        // Verify that number of groups is increased by 1
        Assertions.assertEquals(numOfGroupsBefore, numOfGroupsAfter - 1);

    }

    @AfterEach
    public void endPoint() {
        driver.manage().deleteAllCookies();
    }
}
