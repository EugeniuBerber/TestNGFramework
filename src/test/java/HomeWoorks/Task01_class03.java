package HomeWoorks;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.CommonMethods;
import utils.ConfigReader;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Task01_class03 extends CommonMethods {
    public int counter = 1, counter2 = 1;

    @DataProvider
    public Object[][] empList() {
        Object[][] data = {
                {"Vanessa", "Matei", "vanesa993", "Zgfk3j62!!"},
                {"Eliot", "Slayer", "eliotslay", "hHhsdhs28o.1!"},
                {"Monique", "Verano", "monivera", "Pagin!!m0r"},
                {"Johnny", "Sunhe", "johnnysunhe", "Mamal0rdeturc!"},
                {"Kirk", "Hammett", "kirkhamm", "SaCurgas!nge21"}
        };
        return data;
    }

    @Test(groups = "Smoke", dataProvider = "empList")
    public void addEmployee(String firstName, String lastName, String username, String password) throws InterruptedException {
        LoginPage lp = new LoginPage();
        lp.login(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));

        DashboardPage dashboardPage = new DashboardPage();
        click(dashboardPage.pimOption);
        click(dashboardPage.addEmployeeBtn);

        AddEmployeePage addEmployeePage = new AddEmployeePage();
        sendText(addEmployeePage.firstName, firstName);
        sendText(addEmployeePage.lastName, lastName);
        click(addEmployeePage.createLoginDetails);
        sendText(addEmployeePage.userNameField, username);
        sendText(addEmployeePage.passwordField, password);
        sendText(addEmployeePage.rePasswordField, password);
        Thread.sleep(2000);
        click(addEmployeePage.saveBtn);
        PersonalDetailPage pdp = new PersonalDetailPage();
        Assert.assertTrue(pdp.personalDetailContainer.isDisplayed(), "ERROR: Employee has not been Added");
        System.out.println("Employee added successfully");
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile, new File("C:\\Users\\BloOdSugar\\Desktop\\ScreenShots\\empAdded" + counter + ".png"));
            counter++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = "Smoke", dataProvider = "empList")
    public void verifyEmployeeWasAdded(String firstName, String lastName, String username, String password) throws InterruptedException {
        LoginPage lp = new LoginPage();
        lp.login(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));

        DashboardPage dbp = new DashboardPage();
        click(dbp.pimOption);
        click(dbp.employeeList);
        Thread.sleep(6000);
        EmployeeListPage elp = new EmployeeListPage();
        sendText(elp.empNameSearchField, firstName+" "+lastName);
        Thread.sleep(1000);
        click(elp.searchBtn);
        Thread.sleep(4000);
        List<WebElement> employeeList = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
        Iterator<WebElement> iterator = employeeList.iterator();
        while (iterator.hasNext()) {
            String target = iterator.next().getText();
            if (target.contains(firstName)) {
                System.out.println("Employee: '" + target + "' was added successfully");
                TakesScreenshot ts = (TakesScreenshot) driver;
                File sourceFile = ts.getScreenshotAs(OutputType.FILE);
                try {
                    FileUtils.copyFile(sourceFile, new File("C:\\Users\\BloOdSugar\\Desktop\\ScreenShots\\searchFromEmpList" + counter2 + ".png"));
                    counter2++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            } else
                System.out.println("Employee: '" + target + "' was not found");
        }
    }

}
