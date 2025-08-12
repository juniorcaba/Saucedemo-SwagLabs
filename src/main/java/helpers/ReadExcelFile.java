package helpers;

import java.util.List;
import java.util.Map;

public class ReadExcelFile {

    private static ReadExcelFile intanceOpcionesProyecto = null;

    public static ReadExcelFile getOpcionesProyecto(){
        if(intanceOpcionesProyecto == null){
            intanceOpcionesProyecto = new ReadExcelFile();
        }
        return intanceOpcionesProyecto;
    }
    public Map<String, String>obtenerDatosDePrueba(List<Map<String, String>> list, String test){
        Map<String, String> dataSet = null;

        String estado = "true";
        for (int i =0; i <list.size(); i++){
            if(list.get(i).get("escenarioDePrueba").equalsIgnoreCase(test) && list.get(i).get("estado").equalsIgnoreCase(estado)){
                dataSet = list.get(i);
            }
        }
        return dataSet;
    }
}
