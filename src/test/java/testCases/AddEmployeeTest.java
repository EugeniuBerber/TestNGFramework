package testCases;

import org.testng.annotations.Test;
import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;
import utils.ExcelReading;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeTest extends CommonMethods {
    @Test(groups = "Smoke")
    public void addEmployee(){
        //calling login method from LoginPage class
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getPropertyValue("username"),ConfigReader.getPropertyValue("password"));

        DashboardPage dashboardPage = new DashboardPage() ;
        click(dashboardPage.pimOption);
        click(dashboardPage.addEmployeeBtn);

        //add employee page
        AddEmployeePage addEmployeePage= new AddEmployeePage();
        sendText(addEmployeePage.firstName, "malaka001");
        sendText(addEmployeePage.middleName, "malaka001");
        sendText(addEmployeePage.lastName, "malaka001");
        click(addEmployeePage.saveBtn);

    }
    @Test
    public void addMultipleEmployee(){
        //login first to hrms
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getPropertyValue("username"),ConfigReader.getPropertyValue("password"));
        // nav to add Employee page
        DashboardPage dashboardPage = new DashboardPage() ;
        AddEmployeePage addEmployeePage = new AddEmployeePage();

        List<Map<String ,String >> newEmployee = ExcelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH, "Sheet1");
        Iterator<Map<String,String>> it = newEmployee.iterator();
        while (it.hasNext()){
            click(dashboardPage.pimOption);
            click(dashboardPage.addEmployeeBtn);
            Map<String,String> mapNewEmployee = it.next();
            sendText(addEmployeePage.firstName, mapNewEmployee.get("FirstName"));
            sendText(addEmployeePage.middleName, mapNewEmployee.get("MiddleName"));
            sendText(addEmployeePage.lastName, mapNewEmployee.get("LastName"));
            sendText(addEmployeePage.photograph, mapNewEmployee.get("Photograph"));
            //select check box
            if (!addEmployeePage.createLoginDetails.isSelected()){
                click(addEmployeePage.createLoginDetails);
            }
            // provide credentials for user
            sendText(addEmployeePage.userNameField, mapNewEmployee.get("Username"));
            sendText(addEmployeePage.passwordField, mapNewEmployee.get("Password"));
            sendText(addEmployeePage.rePasswordField, mapNewEmployee.get("Password"));
            click(addEmployeePage.saveBtn);
        }
    }
}
