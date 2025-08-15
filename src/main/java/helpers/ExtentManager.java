package helpers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static String reportFileName = "Test-Automaton-Report.html";
    private static String reportFilepath = System.getProperty("user.dir") + "/reports/";

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    public static ExtentReports createInstance() {
        // Crear directorio de reportes si no existe
        File reportsDir = new File(reportFilepath);
        if (!reportsDir.exists()) {
            reportsDir.mkdirs();
            System.out.println("✅ Directorio de reportes creado: " + reportFilepath);
        }

        String fileName = getReportName();
        String fullPath = reportFilepath + fileName;

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fullPath);

        // Configuración del reporte
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("🚀 Automation Test Report - SauceDemo");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("📊 SauceDemo Test Results");

        // CSS personalizado - solo fondo con gradiente y texto blanco simple
        String customCSS =
                ".navbar-brand { " +
                        "color: #28a745 !important; " +
                        "font-weight: bold !important; " +
                        "} " +

                        "/* Solo el contenedor del header tiene el gradiente */ " +
                        ".card-header { " +
                        "background: linear-gradient(135deg, #667eea 0%, #764ba2 100%) !important; " +
                        "border: none !important; " +
                        "} " +

                        "/* El texto del header es blanco simple, sin fondo */ " +
                        ".card-header h1, " +
                        ".card-header h2, " +
                        ".card-header h3, " +
                        ".card-header h4, " +
                        ".card-header h5, " +
                        ".card-header h6, " +
                        ".card-header p, " +
                        ".card-header span, " +
                        ".card-header div, " +
                        ".card-header a, " +
                        ".card-header .card-title, " +
                        ".card-header .test-name { " +
                        "color: white !important; " +
                        "background: transparent !important; " +
                        "text-shadow: none !important; " +
                        "text-decoration: none !important; " +
                        "} " +

                        "/* Estilos para otros headers específicos */ " +
                        ".test-content .card-header, " +
                        ".collapsible-header, " +
                        ".test-header { " +
                        "background: linear-gradient(135deg, #667eea 0%, #764ba2 100%) !important; " +
                        "border: none !important; " +
                        "} " +

                        "/* Texto blanco para otros headers */ " +
                        ".test-content .card-header *, " +
                        ".collapsible-header *, " +
                        ".test-header * { " +
                        "color: white !important; " +
                        "background: transparent !important; " +
                        "text-shadow: none !important; " +
                        "} " +

                        ".test-pass { " +
                        "color: #28a745 !important; " +
                        "} " +

                        ".test-fail { " +
                        "color: #dc3545 !important; " +
                        "}";

        htmlReporter.config().setCss(customCSS);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // Información del sistema
        extent.setSystemInfo("👤 Usuario", System.getProperty("user.name"));
        extent.setSystemInfo("💻 OS", System.getProperty("os.name") + " " + System.getProperty("os.version"));
        extent.setSystemInfo("☕ Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("🌐 Browser", "Chrome"); // Puedes hacerlo dinámico
        extent.setSystemInfo("🔧 Environment", "QA");
        extent.setSystemInfo("📅 Fecha Ejecución", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        System.out.println("✅ ExtentReports inicializado correctamente");
        System.out.println("📊 Reporte se generará en: " + fullPath);

        return extent;
    }

    public static String getReportName() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String formattedTime = dateTimeFormatter.format(localDateTime);
        return "SauceDemo_TestReport_" + formattedTime + ".html";
    }

    public static String getReportPath() {
        return reportFilepath;
    }
}

