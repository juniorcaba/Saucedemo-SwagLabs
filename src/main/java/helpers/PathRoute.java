package helpers;

public class PathRoute {

    private static PathRoute instancePath = null;
    private String ruta = "";
    public static PathRoute getInstancePath(){
        if (instancePath == null){
            instancePath = new PathRoute();
        }
        return instancePath;
    }
    private static final String JSON_ROUTE = "\\src\\main\\resources\\json\\";

    public String getJsonRoute() {
        return JSON_ROUTE;
    }

    public String takeCompletePathExternalProject() {
        if (ruta.isEmpty()) {
            ruta = System.getProperty("user.dir");

            if (ruta.isEmpty()) {
                Object rutaObjeto = null;

                rutaObjeto = this.getClass().getClassLoader().getResource("Keywords//utileria//").toString().replaceAll("file:|%20|Keywords//utileria"," ").trim();

                if (rutaObjeto != null) {
                    ruta = rutaObjeto.toString().replace("//", "\\\\");
                }
            }
        }

        return ruta;
    }
    public String takePathDataPool() {

        return PathRoute.getInstancePath()
                .takeCompletePathExternalProject() + "/src/main/resources/dataPool/";
    }


}


