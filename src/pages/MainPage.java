package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.testng.annotations.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    WebDriver driver;
    private WebElement staySignedIn_No = null;
    public MainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//input[@name='passwd']")
    public WebElement enterPasswordField;
    
    //@FindBy(xpath = "//input[@type='email']")
    private WebElement loginField;
 
    //@FindBy(xpath = "//input[@type='submit']")
    public WebElement submitButton;

	
    @FindBy(id = "idSIButton9")
    public WebElement signInButton;
    
    
    public void setLogin(String word) throws InterruptedException{

    	//Thread.sleep(1000);
    	
    	loginField = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='email']"))); 
    	loginField.sendKeys(word); 
    	submitButton = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='submit']")));
    	submitButton.click();
    	
    }
   
    public String PageTitle(){
        String title = driver.getTitle();
        return title;
    }
}