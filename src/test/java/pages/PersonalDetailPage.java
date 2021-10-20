package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class PersonalDetailPage extends CommonMethods {
    @FindBy(id = "pdMainContainer")
    public WebElement personalDetailContainer;


    public PersonalDetailPage(){
        PageFactory.initElements(driver, this);
    }
}
