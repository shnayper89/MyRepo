import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Обморок on 14.05.2016.
 */
public class GoogleAuthTest {
        private WebDriver driver;
        private String baseUrl;
        private boolean acceptNextAlert = true;
        private StringBuffer verificationErrors = new StringBuffer();

        @BeforeClass(alwaysRun = true)
        public void setUp() throws Exception {
            driver = new FirefoxDriver();
            baseUrl = "https://accounts.google.com/ServiceLogin?service=mail&passive=true&rm=false";
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

        @Test
        public void testQatest1() throws Exception {
            driver.get(baseUrl);
            driver.findElement(By.name("login")).click();
            driver.findElement(By.name("login")).clear();
            driver.findElement(By.name("login")).sendKeys("qatestnew1989");
            driver.findElement(By.name("passwd")).clear();


            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);



            driver.findElement(By.name("passwd")).sendKeys("qwe123asd");
            driver.findElement(By.cssSelector("label.checkbox__label")).click();
            driver.findElement(By.name("twoweeks")).click();
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            driver.findElement(By.cssSelector("img.b-ico.b-ico_compose")).click();
            // ERROR: Caught exception [Error: unknown strategy [class] for locator [class=b-mail-input_yabbles]]
            // ERROR: Caught exception [Error: unknown strategy [class] for locator [class=b-mail-input_yabbles]]
            driver.findElement(By.id("compose-subj")).clear();
            driver.findElement(By.id("compose-subj")).sendKeys("qatest2");
            driver.findElement(By.id("compose-submit")).click();
            driver.findElement(By.linkText("Отправленные")).click();
            driver.findElement(By.xpath("//div[@id='js-page']/div/div[5]/div/div[3]/div/div[3]/div/div/div/div/div[3]/div[2]/div/div[2]/div[2]/div/span[2]/span/a/span/span")).click();
            driver.findElement(By.cssSelector("span.header-user-name.js-header-user-name")).click();
            driver.findElement(By.linkText("Выход")).click();
        }

        @AfterClass(alwaysRun = true)
        public void tearDown() throws Exception {
            driver.quit();
            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
        }

        private boolean isElementPresent(By by) {
            try {
                driver.findElement(by);
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        }

        private boolean isAlertPresent() {
            try {
                driver.switchTo().alert();
                return true;
            } catch (NoAlertPresentException e) {
                return false;
            }
        }

        private String closeAlertAndGetItsText() {
            try {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                if (acceptNextAlert) {
                    alert.accept();
                } else {
                    alert.dismiss();
                }
                return alertText;
            } finally {
                acceptNextAlert = true;
            }
        }
}
