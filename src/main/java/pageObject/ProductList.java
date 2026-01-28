package pageObject;

import Components.Components;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductList extends Components {

    WebDriver driver;

    public ProductList(WebDriver driver){
       super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(css=".mb-3")
     List<WebElement> products ;

    @FindBy(css=".ng-animating")
    WebElement spinner ;


    By productsBy = By.cssSelector(".mb-3");
    By addToCart =  By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");


    public List<WebElement> getProductList(){

        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement gerProductByName(String productName) {
        List<WebElement> products = getProductList();
        WebElement p = null;
        for (WebElement product : products) {
            String name = product.findElement(By.cssSelector("b")).getText();
            if (name.equals(productName)) {
//                product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
                p = product;
                break;
            }
        }
        return p;
    }

    public void addToCart(String productName) throws InterruptedException {
        WebElement prod = gerProductByName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }

}
