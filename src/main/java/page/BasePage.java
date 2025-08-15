package page;

import baseTest.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;


public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

//    // Metodo para navegar a una URL específica
//    public void goTo(String url) {
//        driver.get(url);
//        BaseTest.createStep("Navegando a la URL: " + url, true, true);
//    }

    // Métodos de interacción básica
    public void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            BaseTest.createStep("Click realizado en el elemento", true, true);
        } catch (Exception e) {
            logger.error("Error al hacer click en el elemento: " + e.getMessage());
            throw e;
        }
    }

    public void clickByLocator(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            BaseTest.createStep("Click realizado en: " + locator.toString(), true, true);
        } catch (Exception e) {
            logger.error("Error al hacer click en el localizador: " + locator.toString());
            throw e;
        }
    }

    public void type(WebElement element, String text) {
        try {
            WebElement el = wait.until(ExpectedConditions.visibilityOf(element));
            el.clear();
            el.sendKeys(text);
            BaseTest.createStep("Texto ingresado: " + text, true, true);
        } catch (Exception e) {
            logger.error("Error al escribir texto: " + e.getMessage());
            throw e;
        }
    }

    public void typeByLocator(By locator, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(text);
            BaseTest.createStep("Texto '" + text + "' ingresado en: " + locator.toString(), true, true);
        } catch (Exception e) {
            logger.error("Error al escribir en el localizador: " + locator.toString());
            throw e;
        }
    }

    public String getText(WebElement element) {
        try {
            String text = wait.until(ExpectedConditions.visibilityOf(element)).getText();
            BaseTest.createStep("Texto obtenido: " + text, true, false);
            return text;
        } catch (Exception e) {
            logger.error("Error al obtener texto del elemento: " + e.getMessage());
            throw e;
        }
    }

    public String getTextByLocator(By locator) {
        try {
            String text = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
            BaseTest.createStep("Texto obtenido de " + locator.toString() + ": " + text, true, false);
            return text;
        } catch (Exception e) {
            logger.error("Error al obtener texto del localizador: " + locator.toString());
            throw e;
        }
    }

    // Métodos de verificación
    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        BaseTest.createStep("Esperando que el elemento sea visible: " + locator.toString(), true, false);
    }

    public void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        BaseTest.createStep("Esperando que el elemento sea clickeable: " + locator.toString(), true, false);
    }

    // Métodos para dropdown/select
    public void selectFromDropdown(By locator, String visibleText) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
        BaseTest.createStep("Seleccionado del dropdown: " + visibleText, true, true);
    }

    // Método para scroll
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        BaseTest.createStep("Scroll realizado hacia el elemento", true, false);
    }

    // Getters
    public WebDriver getDriver() {
        return driver;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void clickOn(By element){
        try{
            wait.until(ExpectedConditions.elementToBeClickable(element));
            driver.findElement(element).click();
        }catch (NoSuchContextException e){
            logger.error("Error Class BasePage in method clickOn", e);
        }
    }

    public static void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restablece el estado de interrupción
        }
    }
}