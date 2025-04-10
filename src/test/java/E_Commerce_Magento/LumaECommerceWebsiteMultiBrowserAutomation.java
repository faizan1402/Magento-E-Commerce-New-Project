package E_Commerce_Magento;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class LumaECommerceWebsiteMultiBrowserAutomation {
	WebDriver driver;
	WebDriverWait wait;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void setupExtentReport() {
		// Setup Extent Report
		String reportPath = System.getProperty("user.dir") + "/test-output/E-Commerce-Magento.html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
		sparkReporter.config().setDocumentTitle("Test Automation Report");
		sparkReporter.config().setReportName("E-Commerce Magento Automation Testing");
		sparkReporter.config().setTheme(Theme.DARK); // Can be STANDARD

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		// Add System Info
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.setSystemInfo("Tester", "Faizan Ahmad");
		extent.setSystemInfo("Execution Start Time", new Date().toString());
	}

	@Parameters("browser")
	@BeforeMethod
	public void setup(@Optional("chrome") String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\FAYEEM\\eclipse-workspace\\chromedriver_win32\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			this.driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\FAYEEM\\eclipse-workspace\\geckodriver-v0.36.0-win32\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			this.driver = new FirefoxDriver(options);
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\FAYEEM\\Downloads\\edgedriver_win32\\msedgedriver.exe");
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--remote-allow-origins=*");

			this.driver = new EdgeDriver(options);
		} else {
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}

		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://magento.softwaretestingboard.com/"); // E-Commmerce Side

//		// Get Browser Info
//		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
//		String browserName = cap.getBrowserName();
//		String browserVersion = cap.getBrowserVersion();
//		extent.setSystemInfo("Browser", browserName);
//		extent.setSystemInfo("Browser Version", browserVersion);
	}

	// 1
	// Whats New Page
	@Test
	public void WhatsNew() throws InterruptedException {
		// driver.findElement(By.xpath("//span[contains(text(),\"What's
		// New\")]")).click();

		ExtentTest test = extent.createTest("What's New").assignAuthor("Faizan Ahmad").assignCategory("Regression")
				.assignDevice("Chrome 134");// create a test node in the report
		test.log(Status.INFO, "Executing test case");
		driver.findElement(By.linkText("What's New")).click();
		test.pass("Clicked on What's New");
		// driver.findElement(By.xpath("//main[@id='maincontent']//strong[1]//span[1]"));

		driver.findElement(By.linkText("Hoodies & Sweatshirts")).click();
		test.pass("Clicked on Hoodies & Sweatshirts");
		driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[2]/div[1]/strong[2]")).click();
		test.info("Clicked on the item");
		// open the item
		driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[1]/div/a/span/span/img"))
				.click();

		test.pass("Clicked on the item image");

		// select hoddies size -S
		driver.findElement(By.xpath("//*[@id=\"option-label-size-143-item-167\"]")).click();
		test.pass("Clicked on size S");
		// color -gray
		driver.findElement(By.xpath("//*[@id=\"option-label-color-93-item-52\"]")).click();
		test.pass("Clicked color gray");
		// quantity of items
		driver.findElement(By.xpath("//*[@id=\"qty\"]")).sendKeys("1");
		test.pass("Checked quantity of items");

		// Add to Cart
		driver.findElement(By.xpath("//*[@id=\"product-addtocart-button\"]/span")).click();
		test.pass("Clicked on Add to Cart");

		// Add to Wishlist
		// driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[1]/div[5]/div/a[1]/span")).click();

	}

	// 2
	@Test
	public void Gear() {
		ExtentTest test = extent.createTest("Gear").assignAuthor("Faizan Ahmad").assignCategory("Smoke")
				.assignDevice("Chrome 134");// create a test node in the report
		test.log(Status.INFO, "Executing test case");
		driver.findElement(By.linkText("Gear")).click();
		test.pass("Clicked on Gear");
	}

	// 3
//	@Test
//	public void hackerNoon() {
//
//		test = extent.createTest("Hacker Noon").assignAuthor("Faizan Ahmad").assignCategory("Regression")
//				.assignDevice("Chrome 134");// create a test node in the report
//		test.log(Status.INFO, "Executing test case");
//		WebElement element = driver.findElement(By.linkText("READ"));
//		test.pass("Clicked on Read");
//		Actions actions = new Actions(driver);
//
//		actions.moveToElement(element).build().perform();
//
//		driver.findElement(By.xpath("//*[@id=\"__next\"]/div/header/div[2]/ul/li[1]/div/a")).click();
//		test.pass("Moved to element and clicked on it");
//
//	}

	// 4
	@Test
	public void hoodiesColor() throws InterruptedException {
		test = extent.createTest("Hoodies Color").assignAuthor("Faizan Ahmad").assignCategory("Regression")
				.assignDevice("Chrome 134");// create a test node in the report
		test.log(Status.INFO, "Executing test case");
		driver.findElement(By.linkText("What's New")).click();
		test.pass("Clicked on What's New");

		driver.findElement(By.linkText("Hoodies & Sweatshirts")).click();
		test.pass("Clicked on Hoodies & Sweartshirts");

		// open color option
		driver.findElement(By.xpath("//div[contains(text(),'Color')]")).click();
		test.pass("Clicked on Color");

		// choose the color
		driver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div[4]/div[2]/div/div/a[2]/div")).click();
		test.pass("Clicked on color option");

	}

	// 5
	@Test
	public void hoodiesMaterial() throws InterruptedException {
		test = extent.createTest("Hoodies Material").assignAuthor("Faizan Ahmad").assignCategory("Smoke")
				.assignDevice("Chrome 134");// create a test node in the report
		test.log(Status.INFO, "Executing test case");
		driver.findElement(By.linkText("What's New")).click();
		test.pass("Clicked on What's New");
		driver.findElement(By.linkText("Hoodies & Sweatshirts")).click();
		test.pass("Clicked on Hoodies & Sweartshirts");
		driver.findElement(By.xpath("//div[contains(text(),'Material')]")).click();
		test.pass("Clicked on Material");
		// open cotton page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// driver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div[7]/div[2]/ol/li[1]/a")).click();
		// OR Above or below path both are correct
		driver.findElement(By.cssSelector(
				"a[href='https://magento.softwaretestingboard.com/women/tops-women/hoodies-and-sweatshirts-women.html?material=33']"))
				.click();
		test.pass("Open Cotton Page");

		// select the heelena hooded fleece
		driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[1]/div/a/span/span/img"))
				.click();
		test.pass("Select the heelena hooded fleece hoodies");

		// slect the medium size of hoodies
		driver.findElement(By.xpath("//div[@id='option-label-size-143-item-168']")).click();
		test.pass("Select the size of hoodies Medium size");

		// add to cart hoodies
		driver.findElement(By.xpath("//span[normalize-space()='Add to Cart']")).click();
		test.pass("Add to cart hoodies");

	}

	// 6
	@Test
	public void hoodiesPrice() throws InterruptedException {
		test = extent.createTest("Hoodies Price").assignAuthor("Faizan Ahmad").assignCategory("Regression")
				.assignDevice("Chrome 134");// create a test node in the report
		test.log(Status.INFO, "Executing test case");

		driver.findElement(By.linkText("What's New")).click();
		test.pass("Clicked on What's New");
		driver.findElement(By.linkText("Hoodies & Sweatshirts")).click();
		test.pass("Clicked on Hoodies & Sweartshirts");

		// hoodies price page open
		driver.findElement(By.xpath("//div[normalize-space()='Price']")).click();
		test.pass("Clicked on Hoodies Price Page Open");

		// Hoodies price
		driver.findElement(By.xpath("//span[contains(text(),'$39.99')]")).click();
		test.pass("Selected on Hoodies Price");

		// Select price the item
		driver.findElement(By.xpath(
				"//img[@class='product-image-photo' or @src='https://magento.softwaretestingboard.com/pub/media/catalog/product/cache/7c4c1ed835fbbf2269f24539582c6d44/w/h/wh12-gray_main_1.jpg']"))
				.click();

		test.pass("Selected price the item");

		// select the S size of sweatshirt
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='option-label-size-143-item-167']")).click();
		test.pass("Selected the S size of sweatshirt");

		// select the color of sweatshirt
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='option-label-color-93-item-53']")).click();
		test.pass("Selected color of sweatshirt");

		// select the sweatshirt on addcart
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[contains(text(),'Add to Cart')]")).click();
		test.pass("Add to cart sweartshirt");

		// Sweatshirt Add to wishlist
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[1]/div[5]/div/a[1]/span")).click();
		test.pass("Add to wishlist sweatshirt");
//		// Add to compare the list
//		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[1]/div[5]/div/a[2]")).click();

	}

	// 7
	// Open Hoodies Size
	@Test
	public void hoodiesSize() throws InterruptedException {
		test = extent.createTest("Hoodies Size").assignAuthor("Faizan Ahmad").assignCategory("Smoke")
				.assignDevice("Chrome 134");// create a test node in the report
		test.log(Status.INFO, "Executing test case");

		driver.findElement(By.linkText("What's New")).click();
		test.pass("Clicked on What's New");
		driver.findElement(By.linkText("Hoodies & Sweatshirts")).click();
		test.pass("Clicked on Hoodies & Sweatshirt");
		// open size
		driver.findElement(By.xpath("//div[contains(text(),'Size')]")).click();
		test.pass("Clicked on Size");
		// select the size value
		driver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div[2]/div[2]/div/div/a[2]/div")).click();
		test.pass("Clicked on size value");

	}

	// 8
	@Test
	public void hoodiesStyle() throws InterruptedException {

		test = extent.createTest("Hoodies Style").assignAuthor("Faizan Ahmad").assignCategory("Smoke")
				.assignDevice("Chrome 134");// create a test node in the report
		test.log(Status.INFO, "Executing test case");
		driver.findElement(By.linkText("What's New")).click();
		test.pass("Clicked on What's New");
		driver.findElement(By.linkText("Hoodies & Sweatshirts")).click();
		test.pass("Clicked on Hoodies & Sweatshirt");

		// open style page
		driver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div[1]/div[1]")).click();
		test.pass("Open Stype Page");

		//
		// hoodies Sweat Shirt
		driver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div[1]/div[2]/ol/li[2]/a")).click();
		test.pass("Clicked on hoodies Sweat Shirt");

	}

	// 9
	@Test
	public void Men() {
		test = extent.createTest("Men").assignAuthor("Faizan Ahmad").assignCategory("Smoke").assignDevice("Chrome 134");// create
																														// a
																														// test
																														// node
																														// in
																														// the
																														// report
		test.log(Status.INFO, "Executing test case");
		driver.findElement(By.linkText("Men")).click();
		test.pass("Clicked on Men section");

	}

	// 10
	@Test
	public void performanceFabric() throws InterruptedException {
		test = extent.createTest("Performance Fabric").assignAuthor("Faizan Ahmad").assignCategory("Smoke")
				.assignDevice("Chrome 134");// create a test node in the report
		test.log(Status.INFO, "Executing test case");
		driver.findElement(By.linkText("What's New")).click();
		test.pass("Clicked on What's New");

		driver.findElement(By.linkText("Hoodies & Sweatshirts")).click();
		test.pass("Clicked on Hoodies & Sweatshirt");
		driver.findElement(By.xpath("//div[contains(text(),'Performance Fabric')]")).click();
		test.pass("Clicked on Performance Fabrice");

		driver.findElement(By.xpath("//div[contains(text(),'Erin Recommends')]")).click();
		test.pass("Clicked on Erin Recommends");

	}

	// 11
	@Test
	public void SignIn() throws InterruptedException {
		test = extent.createTest("SignIn").assignAuthor("Faizan Ahmad").assignCategory("Smoke")
				.assignDevice("Chrome 134");// create a test node in the report
		test.log(Status.INFO, "Executing test case");
		driver.findElement(By.xpath("//html/body/div[2]/header/div[1]/div/ul/li[2]/a")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("officialfaizanahmad@gmail.com");
		test.pass("Verified email");

		driver.findElement(By.xpath("//input[@name='login[password]' and @id='pass']")).sendKeys("Faizan@12345");
		test.pass("Verified Password");
		driver.findElement(By.xpath("//button[@type='submit' and @class='action login primary']")).click();
		test.pass("Submitted SignIn");

	}

	// 12
	@Test
	public void Training() {
		test = extent.createTest("Training").assignAuthor("Faizan Ahmad").assignCategory("Smoke")
				.assignDevice("Chrome 134");// create a test node in the report
		driver.findElement(By.linkText("Training")).click();
		test.pass("Clicked on Training");
	}

	// 13
	@Test
	public void InsideWhatsNewJackets() throws InterruptedException {
		test = extent.createTest("Inside Whats New Jackets").assignAuthor("Faizan Ahmad").assignCategory("Smoke")
				.assignDevice("Chrome 134");// create a test node in the report
		driver.findElement(By.linkText("Training")).click();
		test.pass("Clicked on Training");
		driver.findElement(By.linkText("What's New")).click();
		test.pass("Clicked on What's New");
		driver.findElement(By.linkText("Jackets")).click();
		test.pass("Clicked on Jackets");
		// driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[1]/div/a/span/span/img")).click();

		driver.findElement(By.xpath(
				"//img[@class='product-image-photo' and @src='https://magento.softwaretestingboard.com/pub/media/catalog/product/cache/7c4c1ed835fbbf2269f24539582c6d44/w/j/wj12-blue_main_1.jpg']"))
				.click();
		test.info("Clicked next page");

		// size of jackets
		driver.findElement(By.xpath("//*[@id=\"option-label-size-143-item-167\"]")).click();
		test.pass("Clicked on size of jackets");

		// choose the color of jackets
		driver.findElement(By.xpath("//*[@id=\"option-label-color-93-item-50\"]")).click();
		test.pass("Clicked on color of jackets");

		// quantity of item
		driver.findElement(By.xpath("//*[@id=\"qty\"]")).sendKeys("1");
		test.pass("Clicked on quantity of jackets");

		// add to cart item

		driver.findElement(By.xpath("//*[@id=\"product-addtocart-button\"]")).click();
		test.pass("Clicked on add to cart jackets");

		// add to wishlist item
		driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[1]/div[5]/div/a[1]/span")).click();
		test.pass("Clcked on add to wishlist jackets");

	}

	// 14
	@Test
	public void Women() {

		test = extent.createTest("Women").assignAuthor("Faizan Ahmad").assignCategory("Smoke")
				.assignDevice("Chrome 134");// create a test node in the report
		driver.findElement(By.xpath("//span[contains(text(),'Women')]")).click();
		test.pass("Clicked on Women");

	}

	// 15
	@Test
	public void Jackets() throws InterruptedException {
		test = extent.createTest("Jackets").assignAuthor("Faizan Ahmad").assignCategory("Smoke")
				.assignDevice("Chrome 134");// create a test node in the report

		// WebElement elementToHover =
		// driver.findElement(By.xpath("//span[contains(text(),'Women')]"));
		WebElement elementToHover = driver.findElement(By.linkText("Women"));
		test.pass("Clicked on Women");

		// Create an instance of Actions class
		Actions actions = new Actions(driver);
		// Perform the hover action
		actions.moveToElement(elementToHover).build().perform();

	}

	// 16
	@Test
	public void Women1() throws InterruptedException {

		// driver.findElement(By.xpath("//span[contains(text(),'Women')]")).click();
		test = extent.createTest("Women1").assignAuthor("Faizan Ahmad").assignCategory("Smoke")
				.assignDevice("Chrome 134");// create a test node in the report
		driver.findElement(By.linkText("Women")).click();
		test.pass("Clicked Women");

	}

	// 17
	@Test
	public void testPass() {
		test = extent.createTest("Test Case 1 - Pass Scenario");
		test.log(Status.INFO, "Executing Test Case 1");
		Assert.assertTrue(true);
	}

	// 18
	@Test
	public void testSkip() {
		test = extent.createTest("Test Case 3 - Skipped Scenario");
		test.log(Status.INFO, "Executing Test Case 3");
		throw new SkipException("Skipping this test case");
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@AfterSuite
	public void closeExtentReport() throws IOException {
		extent.flush();
		try {
			Desktop.getDesktop()
					.browse(new File(System.getProperty("user.dir") + "/test-output/E-Commerce-Magento.html").toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
