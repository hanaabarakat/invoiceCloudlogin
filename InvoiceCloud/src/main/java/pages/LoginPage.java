package pages;

import dev.failsafe.internal.util.Assert;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.selenium.Driver_Factory;
import utilities.shared_Component.SharedMethods;
import utilities.test_data.Constant;

import java.time.Duration;

public class LoginPage {

    protected WebDriver driver;

    public LoginPage() {
        driver = Driver_Factory.getDriver();
    }

    // Locators
    private WebElement change() {
        WebElement change = driver.findElement(By.linkText("Change"));
        return SharedMethods.waitForVisible(driver, change);
    }

    private WebElement tenancyInput() {
        WebElement input = driver.findElement(By.name("TenancyName"));
        return SharedMethods.waitForVisible(driver, input);
    }

    private WebElement saveBtn() {
        WebElement save = driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
        return SharedMethods.waitForVisible(driver, save);
    }

    private WebElement username() {
        WebElement username = driver.findElement(By.name("UsernameOrEmailAddress"));
        return SharedMethods.waitForVisible(driver, username);
    }

    private WebElement password() {
        WebElement password = driver.findElement(By.name("Password"));
        return SharedMethods.waitForVisible(driver, password);
    }

    private WebElement loginBtn() {
        WebElement loginBtn = driver.findElement(By.id("LoginButton"));
        return SharedMethods.waitForVisible(driver, loginBtn);
    }
    private final By dashboardTitle = By.xpath("//h2[text()='DASHBOARD']");


    // Actions
    public void clickOnChange() {
        change().click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("TenantChangeModal")));
        wait.until(ExpectedConditions.elementToBeClickable(By.name("TenancyName")));
    }

    public void enterTenancyName(String nameValue) {
        WebElement input = tenancyInput();
        input.clear();
        input.sendKeys(nameValue);
    }
        public void clickOnSaveBtn () {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveBtn()));
            save.click();
            wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(
                            By.id("TenantChangeModal")
                    )
            );
        }


    public void enterUserName(String userName) {
        WebElement input = username();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(input));
        input.sendKeys(userName);
    }

    public void enterPassword(String passwordValue) {
        WebElement input = password();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(input));
        input.sendKeys(passwordValue);
    }

    public void clickOnLoginBtn() {
        WebElement btn = loginBtn();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(btn));
        btn.click();
    }

        public boolean isDashboardDisplayed() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(dashboardTitle)
            ).isDisplayed();
        }
    public WebElement getDashboardElement() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='DASHBOARD']")));
    }

    // Tests
    @Step("Login As Admin")
    public void loginAsAdmin() {
        enterUserName(Constant.ADMIN_USERNAME);
        enterPassword(Constant.ADMIN_PASSWORD);
        clickOnLoginBtn();

    }
        @Step("loginAsTenantAdmin")
        public void loginAsTenantAdmin() {
            clickOnChange();
            enterTenancyName(Constant.Tenant_Name);
            clickOnSaveBtn();
            enterUserName(Constant.Tenant_Admin_Username);
            enterPassword(Constant.Tenant_Admin_Password);
            clickOnLoginBtn();
        }
}
