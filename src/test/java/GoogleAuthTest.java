import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;

import static org.hamcrest.CoreMatchers.is;
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
            driver.manage().window().maximize();
        }

        @Test
        public void testQatest1() throws Exception {

            String topic = "First Text 1";
            String messageText = "Text Blah Blah";
            String name = "Иброгим";

            driver.get(baseUrl);    /* Navigate to HomePage */
            driver.findElement(By.id("Email")).sendKeys("qatestnew1989@gmail.com"); /* Fill e-mail */
            driver.findElement(By.id("next")).click();  /* Click "Next" button */
            driver.findElement(By.id("Passwd")).sendKeys("0637456058"); /* Fill password */
            driver.findElement(By.id("signIn")).click();    /* Click "SignIn" button */
            driver.get("https://mail.google.com/mail/#inbox");  /* Navigate to "My e-mail" */
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);   /* waiting for fully page loading */

            assertEquals(driver.findElement(By.cssSelector(".gb_P.gb_R")).getText(), (name));   /* login assertion */

            driver.findElement(By.cssSelector(".z0 div")).click();  /* Click "Write" button */
            driver.findElement(By.cssSelector(".wO.nr.l1 textarea")).sendKeys("shnayper_89@mail.ru");   /* Fill e-mail "send to" field */
            driver.findElement(By.cssSelector(".aoT")).sendKeys(topic); /* Fill topic field */
            driver.findElement(By.cssSelector(".Ar.Au div")).sendKeys(messageText); /* Fill text of message */
            driver.findElement(By.cssSelector(".T-I.J-J5-Ji.aoO.T-I-atl.L3")).click();  /* Click "Send" button */
            driver.findElement(By.cssSelector(".ag.a8k")).click();  /* Open sent messages  */

            assertEquals(driver.findElement(By.cssSelector(".hP")).getText(), (topic)); /* Comparison */

            driver.findElement(By.cssSelector(".gb_2a.gbii")).click();
            driver.findElement(By.cssSelector(".gb_Ea.gb_te.gb_Ae.gb_qb")).click(); /* Click "LogOut" button */

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
