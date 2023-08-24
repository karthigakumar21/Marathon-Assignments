package marathon.assignment1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.sukgu.Shadow;

public class OrderingMobile {

	public static void main(String[] args) throws IOException {
		
		EdgeOptions options = new EdgeOptions();
		options.addArguments("--disable-notifications");
		EdgeDriver driver = new EdgeDriver(options);
		driver.get("https://dev146490.service-now.com/");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.findElement(By.id("user_name")).sendKeys("admin",Keys.TAB);
		driver.findElement(By.id("user_password")).sendKeys("Uouh$-9J2cIU",Keys.TAB);
		driver.findElement(By.id("sysverb_login")).sendKeys(Keys.ENTER);
		
		Shadow sh = new Shadow(driver);
		sh.setImplicitWait(20);
		WebElement all = sh.findElementByXPath("//div[text()='All']");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", all);
		
		Actions ac = new Actions(driver);
		WebElement filter= sh.findElementByXPath("//input[@id='filter']");
		js.executeScript("arguments[0].setAttribute('value','Service Catalog')",filter);
	
		WebElement service = sh.findElementByXPath("//span[text()='Service Catalog']");
		js.executeScript("arguments[0].click()", service);
		
		driver.switchTo().frame(sh.findElementByXPath("//iframe[@id='gsft_main']"));
		driver.findElement(By.xpath("//a[text()='Mobiles']")).click();
		
		driver.findElement(By.xpath("//strong[text()='Apple iPhone 13 pro']/ancestor::a")).click();
		
		driver.findElement(By.xpath("(//span[@class='input-group-radio']//label)[1]")).click();
		
		driver.findElement(By.xpath("//input[@class='cat_item_option sc-content-pad form-control']")).sendKeys("99");
		
		WebElement option = driver.findElement(By.xpath("//select[@class='form-control cat_item_option ']"));
		Select s = new Select(option);
		s.selectByValue("unlimited");
		driver.findElement(By.xpath("((//div[@class='row sc-row'])[5]//label)[3]")).click();
		
		driver.findElement(By.xpath("//button[@id='oi_order_now_button']")).click();
		
		String result =driver.findElement(By.xpath("//div[@class='notification notification-success']/span[text()='Thank you, your request has been submitted']")).getText();
		System.out.println(result);
		
		if(result.equals("Thank you, your request has been submitted")) 
		{
			System.out.println("order placed successfully");
		
		}
		else {
			System.out.println("not placed suucessfully");
		}
	
		File source =driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./orderingmob.png");
		FileUtils.copyFile(source, dest);

	}

}
