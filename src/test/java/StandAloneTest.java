import TestComponent.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.CartPage;
import pageObject.CheckoutPage;
import pageObject.ProductList;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StandAloneTest extends BaseTest {


    public static WebDriver driver;


    @Test(dataProvider ="getData")
    public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException {

        driver = launchApplication();
        landingPage.loginApplication(email, password);

        ProductList productList = new ProductList(driver);

        List<WebElement> products = productList.getProductList();

//        String productName = "ZARA COAT 3";
        productList.addToCart(productName);
        productList.goToCartPage();
        CartPage cartPage = new CartPage(driver);
        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        cartPage.goToCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.selectCountry("China");
        checkoutPage.submitOrder();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
        Assert.assertTrue(confirmation.getText().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.quit();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        return new Object[][] {{"jl@email.com","Aa12345678!","ZARA COAT 3"},
//                {"123@email.com","asfggsf!","ZARA COAT 3"},
                {"jl@email.com","Aa12345678!","ADIDAS ORIGINAL"}};
//        ArrayList<HashMap<String,String>> data = getJsonDataToMap("src/test/java/data/purchaseOrder.json");
//        return new Object[][] {{data.get(0)},{data.get(1)}};
    }
}

