package Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SampleElements {
	@FindBy(how = How.XPATH,using = "(//*[text()=' Sign In'])")
	public WebElement Sign_in;
	
	@FindBy(how = How.XPATH,using = "//*[text()='Continue with Password']")
	public WebElement Login_With_Password;
	
	
	
	
	@FindBy(how = How.XPATH,using = "//*[@id='logOutBtn']")
	public WebElement Logout_btn;

	@FindBy(how = How.XPATH,using = "//*[@id='form1Example1']")
	public WebElement Email;

	@FindBy(how = How.XPATH,using = "//*[@id='form1Example2']")
	public WebElement Password;
	
	@FindBy(how = How.XPATH,using = "(//*[text()='Login'])[2]")
	public WebElement Login;
	
	@FindBy(how = How.XPATH,using = "//*[text()='My Account']")
	public WebElement MyAccount;
	
	@FindBy(how = How.XPATH,using = "//*[text()='emailRequired ']")
	public WebElement Email_Msg;
	
	@FindBy(how = How.XPATH,using = "//*[text()='passwordRequired ']")
	public WebElement Password_Msg;
	
	@FindBy(how = How.XPATH,using = "//*[text()=' invalidEmail ']")
	public WebElement Invalid_Email;
	
	@FindBy(how = How.XPATH,using = "//*[@class='error-msg invalid-feedback']")
	public WebElement Error_Message;
	
	@FindBy(how = How.XPATH,using = "(//*[text()=' passwordInvalid '])")
	public WebElement Invalid_Username_Password;
	
	@FindBy(how = How.XPATH,using = "//*[@class='notify-success']")
	public WebElement Success_Message;
	
	

	public SampleElements(WebDriver	driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	
	

}
