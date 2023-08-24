package marathon.assignment1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.sukgu.Shadow;

public class ArchitectCertification {

	public static void main(String[] args) throws InterruptedException, IOException {
		EdgeOptions options = new EdgeOptions();
		options.addArguments("--disable-notifications");
		
		EdgeDriver driver = new EdgeDriver(options);
		driver.get(" https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("hari.radhakrishnan@qeagle.com");
		driver.findElement(By.id("password")).sendKeys("Leaf$1234");
		driver.findElement(By.id("Login")).click();
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		String handle1 =driver.getWindowHandle();
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		Set<String> handle2 =driver.getWindowHandles();
		List<String> handlewin = new ArrayList<String>(handle2);
	    for (int i = 0; i < handlewin.size(); i++) {
	    	driver.switchTo().window(handlewin.get(1));
			
		}
			driver.findElement(By.xpath("//button[text()='Confirm']")).click();
			Shadow shadow = new Shadow(driver);
			shadow.setImplicitWait(20);
			WebElement learn = shadow.findElementByXPath("//span[text()='Learning']");
			learn.click();
			Actions ac = new Actions(driver);
			WebElement learntrial = shadow.findElementByXPath("//span[text()='Learning on Trailhead']");
			Thread.sleep(1000);
			ac.moveToElement(learntrial).perform();
			
			WebElement sales = shadow.findElementByXPath("//a[text()='Salesforce Certification']");
			JavascriptExecutor js =(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", sales);
			
			WebElement role = shadow.findElementByXPath("//div[@class='roleMenu-item ']//div[contains(text(),'Salesforce')]");
			
			js.executeScript("arguments[0].click()", role);
		
			String title = driver.findElement(By.xpath("//h1[text()='Salesforce Architect']/following-sibling ::div")).getText();
			System.out.println(title);
			List<WebElement> listofcert = driver.findElements(By.xpath("//div[@class='credentials-card_title']//a"));
			for (WebElement certications : listofcert) {
			String cert =	certications.getText();
			System.out.println(cert);
			
			}
			
			driver.findElement(By.xpath("(//div[@class='credentials-card_title']//a)[1]")).click();
			System.out.println("the list of certifications available in Apllication Architect :");
			List<WebElement> listofcertArt = driver.findElements(By.xpath("//div[@class='credentials-card ']//div[@class='credentials-card_title']"));
			for (WebElement list : listofcertArt) {
			String values=	list.getText();
			
			System.out.println(values);
			File source = driver.getScreenshotAs(OutputType.FILE);
			File file = new File("./salesForce/ApplicantArchitect.png");
			FileUtils.copyFile(source, file);
			}
	

}
}
