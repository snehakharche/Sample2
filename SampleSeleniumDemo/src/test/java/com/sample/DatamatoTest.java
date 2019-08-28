package com.sample;



import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DatamatoTest {
	String browser;
	WebDriverWait wait;
	public static WebDriver driver;
	public String url;
	public String searchString;
	
	@CacheLookup
	@FindBy(xpath="//div[@id='res']//a")
	List<WebElement> allLinks;
	
	@CacheLookup
	@FindBy(xpath="/html/body/div[1]/div[4]/form/div[2]/div/div[3]/center/input[1]")
	WebElement googleBtn;
	

	@CacheLookup
	@FindBy(name="q")
	WebElement searchTextbox;
	
	@CacheLookup 
	@FindBy(linkText="Services")
	WebElement tabService;
	
	public void setBrowser(String browser)
	{
		this.browser = browser;
	}
	
	public void setUrl(String url)
	{
		this.url = url;
	}
	
	public void setSearchString(String s)
	{
		this.searchString = s;
	}
	
	
	//Test cases
	public String openBrowser()
	{
		//Check which browser 
		if(browser.equalsIgnoreCase("chrome"))
		{
			
			System.setProperty("webdriver.chrome.driver","E:\\Sneha\\New Workspace\\SampleSeleniumDemo\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "E:\\Sneha\\New Workspace\\SampleSeleniumDemo\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		//wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		
		//Check if browser is opened or not
		if(driver!=null)
		{
			return "Browser opened";
		}
		else
		{
			return "Browser doesnot opened";
		}
	}
	
	public boolean navigateToURL()
	{
		driver.get(url);
		
		if(driver.getTitle().equals("Google"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean searchDatamato()
	{
		searchTextbox.sendKeys(searchString);
		googleBtn.click();
		
		System.out.println("title"+driver.getTitle());
		
		//Check if expected page is loaded or not
		if(driver.getTitle().equals("datamato - Google Search"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String clickOnFirstLinkAndPrint()
	{
		System.out.println("In clickonfirstlink");
		
		System.out.println("Size of list : "+allLinks.size());
		
		
		System.out.println("first link "+allLinks.get(0).getText());
		
		//Click on First link on page
		allLinks.get(0).click();
		
		System.out.println(driver.getTitle()+ " Title of page");
		
		return "Datamato";
		
		
	}
	
	public boolean checkServiceTabPresent()
	{
		
		System.out.println("website loaded successfully "+tabService.getText());
		
		if(tabService.getText().equals("Services"))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
}
