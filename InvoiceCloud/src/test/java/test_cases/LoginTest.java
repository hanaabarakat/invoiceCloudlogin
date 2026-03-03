package test_cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.selenium.Driver_Factory;


import java.time.Duration;

import static utilities.selenium.Driver_Factory.getDriver;
public class LoginTest extends BaseTest {

    LoginPage login;

    @Test
    public void loginWithValidDataAsAdmin() {
        login = new LoginPage();
        login.loginAsAdmin();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(login.getDashboardElement()));
        String currentUrl = getDriver().getCurrentUrl();
        System.out.println("Current URL after login: " + currentUrl);
        WebElement dashboard = login.getDashboardElement();
        Assert.assertTrue(dashboard.isDisplayed(), "Home");
    }

    @Test
    public void loginWithValidDataAsTenantAdmin() {
        login = new LoginPage();
        login.loginAsTenantAdmin();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(login.getDashboardElement()));
        String currentUrl = getDriver().getCurrentUrl();
        //Assert.assertTrue(currentUrl.contains("/dashboard"), "Unexpected URL after login");
        WebElement dashboard = login.getDashboardElement();
        Assert.assertTrue(dashboard.isDisplayed(), "Dashboard not displayed after login");

    }
}




