package baseTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
//import helpers.ExtentManagers;
import helpers.ExtentManager;
//mport helpers.ExtentManagers;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import helpers.Utils;

public class BaseTest {
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    private static WebDriver driver;

    // ExtentReports integration
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @BeforeSuite
    public void beforeSuite() {
        log.info("=== INICIANDO SUITE DE PRUEBAS ===");
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        // Inicializar WebDriver
        setupDriver();

        // Crear test en ExtentReports
        String testDescription = "";
        if (method.isAnnotationPresent(Test.class)) {
            Test testAnnotation = method.getAnnotation(Test.class);
            testDescription = testAnnotation.description();
        }

        ExtentTest test = extent.createTest(method.getName(), testDescription);
        extentTest.set(test);

        log.info("Iniciando test: " + method.getName());
        createStep("=== INICIANDO TEST: " + method.getName() + " ===", true, false);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        String methodName = result.getMethod().getMethodName();

        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.get().log(Status.FAIL, "❌ Test Fallido: " + result.getThrowable());
            createStep("❌ TEST FALLIDO: " + methodName, false, true);

            // Capturar screenshot en caso de fallo
            String screenshotPath = captureScreenshot(methodName + "_FAILED");
            if (screenshotPath != null) {
                try {
                    extentTest.get().addScreenCaptureFromPath(screenshotPath);
                } catch (Exception e) {
                    log.error("Error al adjuntar screenshot: " + e.getMessage());
                }
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.get().log(Status.PASS, "✅ Test Exitoso");
            createStep("✅ TEST EXITOSO: " + methodName, true, false);
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.get().log(Status.SKIP, "⏭️ Test Omitido: " + result.getThrowable());
            createStep("⏭️ TEST OMITIDO: " + methodName, true, false);
        }

        // Cerrar navegador
        if (driver != null) {
            driver.quit();
            log.info("Cerrando navegador");
            createStep("Cerrando navegador", true, false);
        }
    }

    @AfterSuite
    public void afterSuite() {
        extent.flush(); // Genera el reporte final
        log.info("=== SUITE DE PRUEBAS COMPLETADA ===");
        log.info("Reporte generado en: " + System.getProperty("user.dir") + "/reports/");

        // Crear carpeta reports si no existe
        File reportsDir = new File(System.getProperty("user.dir") + "/reports");
        if (!reportsDir.exists()) {
            reportsDir.mkdirs();
        }
    }

    public void setupDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            log.info("Driver inicializado correctamente");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    // Método mejorado createStep para integrar con ExtentReports
    public static void createStep(String stepDescription, boolean passed, boolean takeScreenshot) {
        ExtentTest currentTest = extentTest.get();

        if (currentTest != null) {
            if (passed) {
                currentTest.log(Status.PASS, "✅ " + stepDescription);
            } else {
                currentTest.log(Status.FAIL, "❌ " + stepDescription);
            }
        }

        // Screenshot si es necesario
        if (takeScreenshot && driver != null) {
            String screenshotPath = captureScreenshot(stepDescription.replaceAll("\\s", "_"));
            if (screenshotPath != null && currentTest != null) {
                try {
                    currentTest.addScreenCaptureFromPath(screenshotPath);
                } catch (Exception e) {
                    log.error("Error al adjuntar screenshot: " + e.getMessage());
                }
            }
        }

        // Logging en consola
        if (passed) {
            log.info("[STEP] " + stepDescription);
        } else {
            log.error("[STEP FAILED] " + stepDescription);
        }
    }

    // Método para capturar screenshots
    public static String captureScreenshot(String testName) {
        if (driver == null) return null;

        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

            // Crear directorio de screenshots si no existe
            File screenshotDir = new File(System.getProperty("user.dir") + "/screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String fileName = testName + "_" + timestamp + ".png";
            File destFile = new File(screenshotDir, fileName);

            FileUtils.copyFile(sourceFile, destFile);

            log.info("Screenshot capturado: " + destFile.getAbsolutePath());
            return destFile.getAbsolutePath();

        } catch (IOException e) {
            log.error("Error al capturar screenshot: " + e.getMessage());
            return null;
        }
    }
}








//package basetest;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Parameters;
//import java.time.Duration;
//
//public class BaseTest {
//    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
//    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
//
//    // Método para obtener el driver del hilo actual
//    public static WebDriver getDriver() {
//        return driverThreadLocal.get();
//    }
//
//    // Método para establecer el driver en el hilo actual
//    public static void setDriver(WebDriver driver) {
//        driverThreadLocal.set(driver);
//    }
//
//    @BeforeMethod
//    @Parameters({"browser"})
//    public void setUp(String browser) {
//        try {
//            WebDriver driver = createDriver(browser);
//            setDriver(driver);
//
//            // Configuraciones generales del driver
//            driver.manage().window().maximize();
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
//
//            createStep("Navegador " + browser + " iniciado correctamente", true, false);
//        } catch (Exception e) {
//            logger.error("Error al inicializar el driver: " + e.getMessage());
//            throw new RuntimeException("Fallo en la configuración del driver", e);
//        }
//    }
//
//    private WebDriver createDriver(String browser) {
//        WebDriver driver;
//
//        switch (browser.toLowerCase()) {
//            case "chrome":
//                WebDriverManager.chromedriver().setup();
//                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.addArguments("--disable-notifications");
//                chromeOptions.addArguments("--disable-popup-blocking");
//                driver = new ChromeDriver(chromeOptions);
//                break;
//
//            case "firefox":
//                WebDriverManager.firefoxdriver().setup();
//                driver = new FirefoxDriver();
//                break;
//
//            case "edge":
//                WebDriverManager.edgedriver().setup();
//                driver = new EdgeDriver();
//                break;
//
//            default:
//                throw new IllegalArgumentException("Navegador no soportado: " + browser);
//        }
//
//        return driver;
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        WebDriver driver = getDriver();
//        if (driver != null) {
//            createStep("Cerrando navegador", true, false);
//            driver.quit();
//            driverThreadLocal.remove();
//        }
//    }
//
//    // Método para crear steps en los reportes
//    public static void createStep(String description, boolean capture, boolean highlight) {
//        logger.info("[STEP] " + description);
//        // Aquí puedes integrar tu lógica de reporte (ExtentReports, Allure, etc.)
//    }
//}
