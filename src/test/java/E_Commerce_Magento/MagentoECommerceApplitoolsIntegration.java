package E_Commerce_Magento;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MagentoECommerceApplitoolsIntegration {

    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;
    Eyes eyes; // Applitools object

    @BeforeSuite
    public void setupExtentReport() {
        String reportPath = System.getProperty("user.dir") + "/test-output/E-Commerce-Magento.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Test Automation Report");
        sparkReporter.config().setReportName("E-Commerce Magento Automation Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Tester", "Faizan Ahmad");
        extent.setSystemInfo("Execution Start Time", new java.util.Date().toString()); // Fixed here
    }
 
    
    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
    	System.setProperty("webdriver.chrome.driver","C:\\Users\\FAYEEM\\eclipse-workspace\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://magento.softwaretestingboard.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        eyes = new Eyes();
        eyes.setApiKey("rK0tMVbnKcBN109k8JJLaZ6KxVrqCN0L70E8ARbgbm5cs110"); // Replace with your Applitools API key
        eyes.setBatch(new BatchInfo("Magento E-Commerce Tests"));

        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        extent.setSystemInfo("Browser", cap.getBrowserName());
        extent.setSystemInfo("Browser Version", cap.getBrowserVersion());
    }

    @Test
    public void WhatsNew() throws InterruptedException {
        test = extent.createTest("What's New with Applitools").assignAuthor("Faizan Ahmad")
                .assignCategory("Regression").assignDevice("Chrome 134");
        test.log(Status.INFO, "Executing test case");

        eyes.open(driver, "Magento E-Commerce", "Whats New Visual Test");

        driver.findElement(By.linkText("What's New")).click();
        test.pass("Clicked on What's New");
        eyes.check("What's New Page", Target.window());

        driver.findElement(By.linkText("Hoodies & Sweatshirts")).click();
        test.pass("Clicked on Hoodies & Sweatshirts");
        eyes.check("Hoodies Page", Target.window());

        driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[2]/div[1]/strong[2]")).getText();
        driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[1]/div/a/span/span/img"))
                .click();
        test.pass("Clicked on the item image");

        driver.findElement(By.xpath("//*[@id=\"option-label-size-143-item-167\"]")).click();
        test.pass("Clicked on size S");
        driver.findElement(By.xpath("//*[@id=\"option-label-color-93-item-52\"]")).click();
        test.pass("Clicked color gray");
        driver.findElement(By.xpath("//*[@id=\"qty\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"qty\"]")).sendKeys("1");
        test.pass("Checked quantity of items");

        driver.findElement(By.xpath("//*[@id=\"product-addtocart-button\"]/span")).click();
        test.pass("Clciked Take Screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());
        test.pass("Clicked on Add to Cart");

        eyes.closeAsync();
    }

    
    

   

    public String getBase64() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
    
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (eyes != null) {
            eyes.abortIfNotClosed();
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

//    @Test
//    public void testPass() {
//        test = extent.createTest("Test Case 1 - Pass Scenario");
//        test.log(Status.INFO, "Executing Test Case 1");
//        Assert.assertTrue(true);
//    }
//
//    @Test
//    public void testSkip() {
//        test = extent.createTest("Test Case 3 - Skipped Scenario");
//        test.log(Status.INFO, "Executing Test Case 3");
//        throw new SkipException("Skipping this test case");
//    }
}
