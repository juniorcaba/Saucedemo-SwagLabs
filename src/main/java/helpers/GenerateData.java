package helpers;

import java.util.List;
import java.util.Map;

public class GenerateData {



    Map<String, String> dataTest;
    List<Map<String, String>> dataSet;

    private  String urlAmbiente ="";

    private String users = "";
    //private String pass = "";
    private String newPass = "";
    private String refNumber = "";
    private String identificacions = "";
    private String tipoIdentificacion = "";
    private String lugarNacimiento = "";
    private String nombres = "";
    private String primerApellido ="";
    private String segundoApellido = "";
    private String tipoReferencia = "";
    private String nivelAcadamico = "";
    private String ocupacion = "";
    private String ingresoMensual = "";
    private String calle = "";
    private String numero = "";
    private String antiguedad = "";
    private String empresa = "";
    private String cargo = "";
    private String provincia = "";
    private String municipio = "";
    private String sector = "";
    private String testParaje = "";
    private String vivienda = "";
    private String provinciatc = "";
    private String fechaVencimiento = "";
    private String fechaExpedicion = "";
    private  String userpega = "";
    private  String paswordpega = "";
    private String tipoingreo = "";
    private String usuarioHector = "";

    private String usersaucedemo = "";
    private String passsaucedemo = "";
    private String methodtest = "";


    public GenerateData(String dato) {

        dataSet = RouteData.consultaDatosExcel("DataR.xlsx", "Hoja1");
        dataTest = RouteData.obtenerDatosPrueba(dataSet, dato);

        users = dataTest.get(GetDataUser.enumElementsTest.USERS.getDato());
        //pass = dataTest.get(GetDataUser.enumElementsTest.PASSWORD.getDato());
        refNumber = dataTest.get(GetDataUser.enumElementsTest.NUMEROREFERENCIA.getDato());
        identificacions =dataTest.get(GetDataUser.enumElementsTest.IDENTIFICACION.getDato());
        tipoIdentificacion=dataTest.get(GetDataUser.enumElementsTest.TIPOIDENTIFICACION.getDato());
        lugarNacimiento = dataTest.get(GetDataUser.enumElementsTest.LUGARNACIMIENTO.getDato());
        newPass = dataTest.get(GetDataUser.enumElementsTest.NEWPASSWORD.getDato());


        nombres = dataTest.get(GetDataUser.enumElementsTest.NOMBRES.getDato());
        primerApellido = dataTest.get(GetDataUser.enumElementsTest.PRIMERAPELLIDO.getDato());
        segundoApellido = dataTest.get(GetDataUser.enumElementsTest.SEGUNDOAPELLIDO.getDato());

        tipoReferencia = dataTest.get(GetDataUser.enumElementsTest.TIPOREFERENCIA.getDato());
        nivelAcadamico = dataTest.get(GetDataUser.enumElementsTest.NIVELACADEMICO.getDato());
        ocupacion = dataTest.get(GetDataUser.enumElementsTest.OCUPACION.getDato());
        ingresoMensual = dataTest.get(GetDataUser.enumElementsTest.INGRESOMENSUAL.getDato());
        calle = dataTest.get(GetDataUser.enumElementsTest.CALLE.getDato());
        numero = dataTest.get(GetDataUser.enumElementsTest.NUMERO.getDato());
        vivienda = dataTest.get(GetDataUser.enumElementsTest.VIVIENDA.getDato());
        antiguedad = dataTest.get(GetDataUser.enumElementsTest.ANTIGUEDAD.getDato());
        empresa = dataTest.get(GetDataUser.enumElementsTest.EMPRESA.getDato());
        cargo = dataTest.get(GetDataUser.enumElementsTest.CARGO.getDato());
        provincia = dataTest.get(GetDataUser.enumElementsTest.PROVINCIA.getDato());
        municipio = dataTest.get(GetDataUser.enumElementsTest.MUNICIPIO.getDato());
        sector = dataTest.get(GetDataUser.enumElementsTest.SECTOR.getDato());
        testParaje = dataTest.get(GetDataUser.enumElementsTest.PARAJE.getDato());
        provinciatc = dataTest.get(GetDataUser.enumElementsTest.PROVINCIATC.getDato());
        fechaVencimiento = dataTest.get(GetDataUser.enumElementsTest.FECHAVENCIMIENTO.getDato());
        fechaExpedicion = dataTest.get(GetDataUser.enumElementsTest.FECHAEXPEDICION.getDato());
        tipoingreo = dataTest.get(GetDataUser.enumElementsTest.TIPODEINGRESO.getDato());

        usersaucedemo = dataTest.get(GetDataUser.enumElementsTest.USERSAUCEDEMO.getDato());
        passsaucedemo = dataTest.get(GetDataUser.enumElementsTest.PASSSAUCEDEMO.getDato());
        methodtest = dataTest.get((GetDataUser.enumElementsTest.METHODTEST.getDato()));

    }

    public String url(){return urlAmbiente;}
    public String user(){return users;}
    //public String password(){return pass;}
    public String newPassword(){return newPass;}
    public String refNumber(){return refNumber;}
    public String identificacion(){return identificacions;}
    public String lugarNacimiento(){return lugarNacimiento;}
    public String tipoIdentification(){return tipoIdentificacion;}
    public String nombres(){return nombres;}
    public String primerApellido(){return primerApellido;}
    public String segundoApellido(){return segundoApellido;}
    public String tipoReferencia(){return tipoReferencia;}
    public String levelAcademic(){return nivelAcadamico;}
    public String occupation(){return ocupacion;}
    public String ingresoMensual(){return ingresoMensual;}
    public String street(){return calle;}
    public String number(){return numero;}
    public String livingPlace(){return vivienda;}
    public String antiquity(){return antiguedad;}
    public String business(){return empresa;}
    public String position(){return cargo;}
    public String typeingreso(){return tipoingreo;}

    public String province(){return provincia;}
    public String municipality(){return municipio;}
    public String sector(){return sector;}
    public String paraje(){return testParaje;}
    public String provinceTC(){return provinciatc;}
    public String fechaVencimiento(){return fechaVencimiento;}
    public String fechaExpedicion(){return fechaExpedicion;}

    public String userPega(){return userpega;}
    public String passwordPega(){return paswordpega;}
    public String usuarioHector(){return usuarioHector;}


    public String usersaucedemo(){return usersaucedemo;}
    public String passsaucedemo(){return passsaucedemo;}
<<<<<<< Updated upstream
    public String methodtest(){return methodtest;}


}
=======
    public String methodtest(){return methodtest;}
>>>>>>> Stashed changes
