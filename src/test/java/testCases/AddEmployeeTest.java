package testCases;

import org.testng.annotations.Test;
import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

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
}
