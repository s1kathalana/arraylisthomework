package ArrayListHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.sun.jmx.snmp.ThreadContext.contains;


public class AutomationProgramUsingArrayList extends Utils {
    LoadProps props = new LoadProps();

    @BeforeMethod
    public void commonstep() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\Resources\\BrowserDriver\\chromedriver.exe");
        //open the browser
        driver = new ChromeDriver();
        //maximize the browser screen
        driver.manage().window().fullscreen();
        //set implicity wait for driver object
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //driver.get("https://demo.nopcommerce.com/");
        driver.get(props.getProperty("url"));
    }
    @AfterMethod
    public void closebrowser()
    {driver.quit();}

    @Test
    public void usershouldbeabletocomparetwoproductsandclearlist() {
        //click on 1st product for add to comparision
        clickOnElement(By.xpath(props.getProperty("item1")));
        //checking 1st product added for comparision
        Assert.assertEquals(getTextFromElement(By.partialLinkText("product comparison")), "product comparison");
        //delay for 5 sec
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //click on 2nd product for add to comparision
        clickOnElement(By.xpath(props.getProperty("item2")));
        //checking for 2nd product added for comparision
        Assert.assertEquals(getTextFromElement(By.partialLinkText("product comparison")), "product comparison");

        //click on product comparision
        clickOnElement(By.linkText("product comparison"));

        //checking added products for comparision are on webpage
        Assert.assertEquals(getTextFromElement(By.partialLinkText("Apple MacBook Pro 13-inch")), "Apple MacBook Pro 13-inch");
        Assert.assertEquals(getTextFromElement(By.partialLinkText("Build your own computer")), "Build your own computer");
        //click on clear list
        clickOnElement(By.xpath("//a[@class='clear-list']"));
        Assert.assertEquals(getTextFromElement(By.xpath("//div[@class='no-data']")), "You have no items to compare.");
    }

    @Test

    public void usershouldbeabletoaddcommentsinnewssection()
    {
        //click on news details button
        clickOnElement(By.xpath("//a[@href='/new-online-store-is-open']"));
        //enter news title
        enterText((By.xpath("//input[@class='enter-comment-title']")), props.getProperty("NewsTitle"));
        //enter news contents
        enterText((By.xpath("//textarea[@class='enter-comment-text']")), props.getProperty("Newscontents"));
        //click on submit button
        clickOnElement(By.xpath("//input[@name='add-comment']"));
        //stores all news contents in array list
        List<WebElement> al = driver.findElements(By.xpath("//div[@class='comment news-comment']"));
        //for loop will check whether written news contents are present in array list boxes or not
        for (WebElement e:al)
        {
           if (e.getAttribute("outerHTML").contains(props.getProperty("Newscontents")))
            {
                System.out.println("news content is displayed");
            }
        }
      Assert.assertEquals(getTextFromElement(By.xpath("//div[@class='result']")), "News comment is successfully added.");
    }


    @Test
    public void usershouldentersearchnikeandcanseedisplayedproductwiththatname()
    {
        //entering text in search box
        enterText((By.xpath("//input[@class='search-box-text ui-autocomplete-input']")),props.getProperty("productname"));
        clickOnElement(By.xpath("//input[@class='button-1 search-box-button']"));

        //storing elements in array list
        List<WebElement> al = driver.findElements(By.xpath("//div[@class='item-grid']"));
        //if al.size is zero that means there is no product in search results
        if (al.size() == 0)
        {
            System.out.println("no such products");
        } else
        {
            System.out.println("product contains specific word "+props.getProperty("productname"));
        }

        for (WebElement e : al)
        {
            if (e.getAttribute("outerHTML").contains(props.getProperty("productname")))
            {
                System.out.println("displayed product containes "+props.getProperty("productname"));
            } else
            {
                System.out.println("displayed products are not containing nike");
            }

        }

    }
}









