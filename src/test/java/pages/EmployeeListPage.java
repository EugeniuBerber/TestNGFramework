package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class EmployeeListPage extends CommonMethods {
    public EmployeeListPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "empsearch_employee_name_empName")
    public WebElement empNameSearchField;

    @FindBy(id = "empsearch_id")
    public WebElement empIdSearchField;

    @FindBy(id = "searchBtn")
    public WebElement searchBtn;

    @FindBy(id = "ohrmList_chkSelectAll")
    public WebElement selectAll_chkBox;

    @FindBy(id = "btnDelete")
    public WebElement deleteBtn;

    @FindBy(id = "dialogDeleteBtn")
    public WebElement dialogDeleteBtn;

    @FindBy(id="resultTable")
    public WebElement resultTable;

    @FindBy(xpath = "//table[@id='resultTable']/tbody/tr")
    public WebElement tableRow;
}
