package helpers;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
//import helpers.FileHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import helpers.Utils;


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

        // CSS personalizado para mejorar la apariencia
        htmlReporter.config().setCss(
                ".navbar-brand { color: #28a745 !important; font-weight: bold; } " +
                        ".card-header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; } " +
                        ".test-pass { color: #28a745 !important; } " +
                        ".test-fail { color: #dc3545 !important; }"
        );

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
//public class ExtentManager {
//    static Logger logger = LoggerFactory.getLogger(ExtentManager.class);
//    private static ExtentReports extent;
//    private  ExtentManager(){}
//
//    public static ExtentReports getInstance(){
//        if(extent == null) {
//            createInstance();
//        }
//
//        return extent;
//
//    }
//
//    private static String reportRoute="";
//    private static String reportName="";
//
//    /** @implNote  Metodo para inicializar extent report
//     * @return ExtentReports
//     * @author FERNANDO PINEDA
//     * @since 03/08/2022
//     */
//    public static ExtentReports createInstance() {
//        try {
//            extent = new ExtentReports();
//            FileHelper.getInstance().createReportFolder();
//            reportName=FileHelper.getInstance().createFileName(Route.getInstance().getProjectName(), ".html");
//            reportRoute=Route.getInstance().getRouteReportFolder() +reportName;
//            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportRoute);
//            sparkReporter.config().setEncoding(String.valueOf(StandardCharsets.UTF_8));
//            sparkReporter.config().setDocumentTitle("Reportes de Automatizcaion");
//            sparkReporter.config().setReportName(Route.getInstance().getProjectName());
//            sparkReporter.config().setTheme(Theme.STANDARD);
//            sparkReporter.config().setCss(FileHelper.getInstance().readPlaneTextInsideJar(Route.getInstance().getRouteCssReport()));
//            sparkReporter.config().setJs(FileHelper.getInstance().readPlaneTextInsideJar(Route.getInstance().getRouteJavaScriptReport()).replace("projectName", Route.getInstance().getProjectName()));
//            String xml=FileHelper.getInstance().readPlaneTextInsideJar(Route.getInstance().getRouteXmlReport());
//            File xmlFile=FileHelper.getInstance().createFile("ExtentReport",
//                    xml,
//                    ".xml"
//            );
//            extent.setSystemInfo("USERNAME", System.getProperty("user.name"));
//            extent.setSystemInfo("OS", System.getProperty("os.name"));
//            sparkReporter.loadXMLConfig(xmlFile);
//
//            extent.attachReporter(sparkReporter);
//            extent.setAnalysisStrategy(AnalysisStrategy.SUITE);
//
//        } catch (IOException e) {
//            logger.error("Error Class ExtentManager in method createInstance", e);
//        }
//        return extent;
//    }
//
//    /** @implNote  Metodo para obtener html del dashboard del reporte creado
//     * @return String
//     * @author FERNANDO PINEDA
//     * @since 10/04/2022
//     */
//    public static String getDashboardHtmlFromReport(){
//        String resultDashboard="";
//
//        try {
//            File file = new File(reportRoute);
//
//            Document document = Jsoup.parse(file, "UTF-8");
//
//            Elements elements = document.getElementsByTag("script");
//            String scriptSrc = elements.get(3).toString();
//
//            String scriptResult = elements.get(2).toString();
//
//            String dashboard = document.getElementsByAttributeValue("class", "container-fluid p-4 view dashboard-view").toString();
//
//            String head = document.getElementsByTag("head").toString();
//
//            resultDashboard=head+dashboard+scriptResult+scriptSrc;
//        } catch (IOException e) {
//            logger.error("Error Class ExtentManager in method getDashboardHtmlFromReport", e);
//        }
//        return resultDashboard;
//
//    }
//
//    public static String getReportRoute() {
//        return reportRoute;
//    }
//
//    public static String getReportName() {
//        return reportName;
//    }
//
//}
