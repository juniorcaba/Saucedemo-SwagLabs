package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.BasePage;
import basetest.BaseTest;

import java.time.Duration;

public class POLogin extends BasePage {

    // Localizadores de elementos
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");
    private final By loginLogo = By.className("login_logo");


    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

    // Constructor
    public POLogin(WebDriver driver) {
        super(driver);
    }



    public void goTo(){
       BaseTest.getDriver().get("https://www.saucedemo.com/v1/");
       BaseTest.createStep("Ingresa a la pagina de Saucedemo", true, true);
    }

    public void setUserName(String usuario){
        wait.until(ExpectedConditions.elementToBeClickable(usernameField)).sendKeys(usuario);
        BaseTest.createStep("Ingreso el usuario", true, true);

    }

    public void setPassword(String password){
        wait.until(ExpectedConditions.elementToBeClickable(passwordField)).sendKeys(password);
        BaseTest.createStep("Ingreso contraseña", true, true);
    }

//    public void enterUsername(String username) {
//        typeByLocator(usernameField, username);
//    }
//
//    public void enterPassword(String password) {
//        typeByLocator(passwordField, password);
//    }
//
//    public void clickLoginButton() {
//        clickByLocator(loginButton);
//    }
//
//    public void login(String username, String password) {
//        enterUsername(username);
//        enterPassword(password);
//        clickLoginButton();
//    }
//
//    public boolean isLoginPageDisplayed() {
//        return isElementVisible(loginLogo);
//    }
//
//    public String getErrorMessage() {
//        if (isElementVisible(errorMessage)) {
//            return getTextByLocator(errorMessage);
//        }
//        return "";
//    }
//
//    public boolean isErrorMessageDisplayed() {
//        return isElementVisible(errorMessage);
//    }
}