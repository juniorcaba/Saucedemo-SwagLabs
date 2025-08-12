package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page.BasePage;
import basetest.BaseTest;

public class POLogin extends BasePage {

    // Localizadores de elementos
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");
    private final By loginLogo = By.className("login_logo");

    // Constructor
    public POLogin(WebDriver driver) {
        super(driver);
    }

//    // Métodos de página
//    public void openLoginPage() {
//        goTo("https://www.saucedemo.com/v1/");
//    }

    public void goTo(){
       BaseTest.getDriver().get("https://www.saucedemo.com/v1/");
       BaseTest.createStep("Ingresa a la pagina de Saucedemo", true, true);
    }

    public void enterUsername(String username) {
        typeByLocator(usernameField, username);
    }

    public void enterPassword(String password) {
        typeByLocator(passwordField, password);
    }

    public void clickLoginButton() {
        clickByLocator(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isLoginPageDisplayed() {
        return isElementVisible(loginLogo);
    }

    public String getErrorMessage() {
        if (isElementVisible(errorMessage)) {
            return getTextByLocator(errorMessage);
        }
        return "";
    }

    public boolean isErrorMessageDisplayed() {
        return isElementVisible(errorMessage);
    }
}