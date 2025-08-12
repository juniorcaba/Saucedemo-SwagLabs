package helpers;



public class GetDataUser {


    public enum enumElementsTest {

        //LoginSinCapcha
        CEDULA("cedula"),
        USERS("usuario"),
        PASSWORD("password"),
        NEWPASSWORD("newPassword"),
        NUMEROREFERENCIA("numeroReferencias"),
        IDENTIFICACION("identificacion"),
        TIPOIDENTIFICACION("tipoIdentificacion"),
        LUGARNACIMIENTO("lugarNacimiento"),
        NOMBRES("nombres"),
        PRIMERAPELLIDO("primerApellido"),
        FECHAVENCIMIENTO("fechaVencimiento"),
        SEGUNDOAPELLIDO("segundoApellido"),
        TIPOREFERENCIA("tipoReferencia"),
        NIVELACADEMICO("NivelAcadamico"),
        OCUPACION("Ocupacion"),
        INGRESOMENSUAL("IngresoMensual"),
        CALLE("calle"),
        NUMERO("Numero"),
        ANTIGUEDAD("Antiguedad"),
        EMPRESA("Empresa"),
        CARGO("Cargo"),
        PROVINCIA("Provincia"),
        MUNICIPIO("Municipio"),
        SECTOR("Sector"),
        PARAJE("Paraje"),
        VIVIENDA("Vivienda"),
        PROVINCIATC("ProvinciaTC"),
        FECHAEXPEDICION("fechaExpedicion"),
        TIPODEINGRESO("tipoingreso");


        String dato;

        public String getDato() {
            return dato;
        }

        enumElementsTest(String string) {
            dato = string;
        }

    }


}
