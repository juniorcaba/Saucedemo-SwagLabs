package basetest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import java.time.Duration;

public class BaseTest {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    // Método para obtener el driver del hilo actual
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    // Método para establecer el driver en el hilo actual
    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser) {
        try {
            WebDriver driver = createDriver(browser);
            setDriver(driver);

            // Configuraciones generales del driver
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

            createStep("Navegador " + browser + " iniciado correctamente", true, false);
        } catch (Exception e) {
            logger.error("Error al inicializar el driver: " + e.getMessage());
            throw new RuntimeException("Fallo en la configuración del driver", e);
        }
    }

    private WebDriver createDriver(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Navegador no soportado: " + browser);
        }

        return driver;
    }

    @AfterMethod
    public void tearDown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            createStep("Cerrando navegador", true, false);
            driver.quit();
            driverThreadLocal.remove();
        }
    }

    // Método para crear steps en los reportes
    public static void createStep(String description, boolean capture, boolean highlight) {
        logger.info("[STEP] " + description);
        // Aquí puedes integrar tu lógica de reporte (ExtentReports, Allure, etc.)
    }
}
