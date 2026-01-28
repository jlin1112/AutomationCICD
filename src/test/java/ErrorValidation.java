import TestComponent.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.CartPage;
import pageObject.CheckoutPage;
import pageObject.ProductList;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class ErrorValidation extends BaseTest {


    public static WebDriver driver;


    @Test
    public void submitOrder() throws IOException, InterruptedException {

        driver = launchApplication();
        landingPage.loginApplication("asdasd@sfsf.com", "Aa");


        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());

    }
}

