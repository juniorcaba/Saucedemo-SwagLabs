package tests;

import helpers.GenerateData;
import page.POLogin;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TELogin extends BaseTest {



    //@Test(priority = 1, description = "Verificar que la página de login se carga correctamente")
    public void testLoginPageLoads() {
        POLogin loginPage = new POLogin(getDriver());

        loginPage.goTo();

    }


    @Test(description = "Login exitoso con credenciales válidas")
    public void testSuccessfulLogin() {

        String name = new Object() {
        }.getClass().getEnclosingMethod().getName();
        GenerateData generateData = new GenerateData(name);
        POLogin loginPage = new POLogin(getDriver());

        loginPage.goTo();
        loginPage.setUserName(generateData.usersaucedemo());
        loginPage.setPassword(generateData.passsaucedemo());

    }


//    }    @Test(priority = 2, description = "Login exitoso con credenciales válidas")
//    public void testSuccessfulLogin() {
//        POLogin loginPage = new POLogin(getDriver());
//
//        loginPage.goTo();
//        loginPage.login("standard_user", "secret_sauce");
//
//        // Verificar que el login fue exitoso (cambio de URL)
//        Assert.assertTrue(loginPage.getCurrentUrl().contains("inventory.html"),
//                "El login no fue exitoso - URL incorrecta");
//    }

//    @Test(priority = 3, description = "Login fallido con credenciales inválidas")
//    public void testFailedLogin() {
//        POLogin loginPage = new POLogin(getDriver());
//
//        loginPage.openLoginPage();
//        loginPage.login("invalid_user", "invalid_password");
//
//        // Verificar que se muestra mensaje de error
//        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
//                "No se mostró mensaje de error para credenciales inválidas");
//
//        String errorMsg = loginPage.getErrorMessage();
//        Assert.assertTrue(errorMsg.contains("Username and password do not match"),
//                "El mensaje de error no es el esperado: " + errorMsg);
//    }
//
//    @Test(priority = 4, description = "Login con usuario bloqueado")
//    public void testLockedUserLogin() {
//        POLogin loginPage = new POLogin(getDriver());
//
//        loginPage.openLoginPage();
//        loginPage.login("locked_out_user", "secret_sauce");
//
//        // Verificar mensaje de error para usuario bloqueado
//        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
//                "No se mostró mensaje de error para usuario bloqueado");
//
//        String errorMsg = loginPage.getErrorMessage();
//        Assert.assertTrue(errorMsg.contains("locked out"),
//                "El mensaje de error para usuario bloqueado no es correcto: " + errorMsg);
//    }
//
//    @Test(priority = 5, description = "Login con campos vacíos")
//    public void testEmptyFieldsLogin() {
//        POLogin loginPage = new POLogin(getDriver());
//
//        loginPage.openLoginPage();
//        loginPage.clickLoginButton(); // Click sin llenar campos
//
//        // Verificar mensaje de error
//        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
//                "No se mostró mensaje de error para campos vacíos");
//
//        String errorMsg = loginPage.getErrorMessage();
//        Assert.assertTrue(errorMsg.contains("Username is required"),
//                "El mensaje de error para campos vacíos no es correcto: " + errorMsg);
//    }
}