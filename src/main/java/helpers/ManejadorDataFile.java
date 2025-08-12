//package helpers;
//
//import com.google.common.base.Splitter;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.testng.Assert;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class ManejadorDataFile {
//
//    private ManejadorDataFile(){}
//
//
//
//    static ManejadorDataFile instanciaManejadorDataFile = null;
//
//    public static ManejadorDataFile getInstanciaManejadorDataFile(){
//
//        if(instanciaManejadorDataFile==null){
//            instanciaManejadorDataFile = new ManejadorDataFile();
//        }
//        return instanciaManejadorDataFile;
//
//    }
//
//
//
//    /**
//     * Metodo. Retorna un arreglo con la informacion de un archivo xlsx
//     * @params
//     * @return List<String>
//     * @author Nelson Mejia
//     * @since 03/05/2022
//     * @author_Modified
//     * @since_Modified
//     * @comment_Modified
//     */
////    public List<Map<String, String>> consultaDatosExcel(String ruta, String nombreHojaExcel) {
////        ArrayList<Map<String, String>> result = new ArrayList<>();
////        Map<String, String> consultaDatosExcel = new HashMap<>();
////        Map <String, String> funciones = new HashMap<>();
////
////        try{
////
////            File excel = new File(ruta);
////            FileInputStream inputStream = new FileInputStream(excel);
////            XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream);
////            XSSFSheet hojaExcel = newWorkbook.getSheet(nombreHojaExcel);
////            int rowCount = hojaExcel.getLastRowNum() - hojaExcel.getFirstRowNum();
////            List<String> titulos = titulosExcel(hojaExcel);
////
////            for (int i = 1; i <= rowCount; i++) {
////                XSSFRow row = hojaExcel.getRow(i);
////
////                for (int j = 0; j < row.getLastCellNum(); j++) {
////                    funciones.put(titulos.get(j), row.getCell(j).getStringCellValue());
////                }
////                consultaDatosExcel = convertWithGuava(funciones.toString().replace(", ", ",").replace("{", "").replace("}", ""));
////                result.add(consultaDatosExcel);
////                funciones.clear();
////            }
////
////            return result;
////        }
////        catch (IOException e){
////            Assert.fail(e.getMessage());
////            return result;
////        }
////    }
//
//    public List<Map<String, String>> consultaDatosExcel(String ruta, String nombreHojaExcel) {
//        List<Map<String, String>> result = new ArrayList<>();
//
//        try {
//            FileInputStream inputStream = new FileInputStream(new File(ruta));
//            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
//            XSSFSheet sheet = workbook.getSheet(nombreHojaExcel);
//            List<String> titulos = titulosExcel(sheet);
//
//            int rowCount = sheet.getLastRowNum();
//
//            for (int i = 1; i <= rowCount; i++) {
//                XSSFRow row = sheet.getRow(i);
//                if (row == null) continue; // salta filas vacías
//
//                Map<String, String> filaMap = new HashMap<>();
//
//                for (int j = 0; j < titulos.size(); j++) {
//                    String key = titulos.get(j);
//                    XSSFCell celda = row.getCell(j);
//
//                    String valor = "";
//                    if (celda != null) {
//                        celda.setCellType(CellType.STRING);  // convierte a texto para evitar errores
//                        valor = celda.getStringCellValue();
//                    }
//
//                    filaMap.put(key, valor);
//                }
//
//                result.add(filaMap);
//            }
//
//            workbook.close();
//            inputStream.close();
//
//        } catch (IOException e) {
//            Assert.fail("Error leyendo Excel: " + e.getMessage());
//        }
//
//        return result;
//    }
//
//    /** Metodo. Retorna una lista con los ebcabezados de un excel
//     * @params
//     * @return List<String>
//     * @author Kemil Bastardo
//     * @since 10/04/2021
//     * @author_Modified
//     * @since_Modified
//     * @comment_Modified
//     */
//    public static List<String> titulosExcel(XSSFSheet hojaExcel){
//        List<String> titulos = new ArrayList<>();
//        for(int i = 0; i<1; i++){
//            XSSFRow row = hojaExcel.getRow(i);
//            for(int j = 0; j< row.getLastCellNum(); j++) {
//                titulos.add(row.getCell(j).getStringCellValue());
//            }
//        }
//        return titulos;
//    }
//    public static Map<String, String> convertWithGuava(String mapAsString) {
//        return Splitter.on(',').withKeyValueSeparator('=').split(mapAsString);
//    }
//}

