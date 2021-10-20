package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginTest extends CommonMethods {
    @Test(groups = "Regression")
    public void adminLogin(){
        LoginPage loginPage = new LoginPage();
        sendText(loginPage.usernameBox, ConfigReader.getPropertyValue("username"));
        sendText(loginPage.passwordBox, ConfigReader.getPropertyValue("password"));
        click(loginPage.loginBtn);
        //assertion
        DashboardPage dashBoardPage = new DashboardPage();
        Assert.assertTrue(dashBoardPage.welcomeMessage.isDisplayed());
        System.out.println("The Dashboard page is Displayed");
    }
    @DataProvider
    public Object[][] invalidData(){
        Object[][] data = {
                {"James", "123!", "Invalid credentials"},
                {"Admin1", "Hum@nhrm123", "Invalid credentials"},
                {"Admin", "", "Password cannot be empty"},
                {"", "Hum@nhrm123", "Username cannot be empty"}

        };
        return data;
    }
    @Test(dataProvider = "invalidData")
    public void invalidLoginErrorMessageValidation(String username, String password, String message){
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);

        String actualError=loginPage.errorMessage.getText();
        Assert.assertEquals(actualError, message, "Error message doesn't match");
    }
}