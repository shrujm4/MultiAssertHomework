package MultiAssertHomeWork;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserActionOnNopCommerceWebsite extends Utils {

  SoftAssert softAssert = new SoftAssert();

  @BeforeMethod
  public void openBrowser() {

    System.setProperty("webdriver.chrome.driver", "src\\main\\Resources\\BrowserDriver\\chromedriver.exe");

    driver = new ChromeDriver();
    //Maximise the browser window screen
    driver.manage().window().fullscreen();
    //implicity wait for driver object
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    //open website
    driver.get("https://demo.nopcommerce.com/");
  }

  @AfterMethod()
  public void closeBrowser() {
    //browser close
    driver.quit();
  }


  @Test
  public void userShouldBeAbleToCompareTheProductSuccessfully() throws InterruptedException {

    clickElement(By.xpath("(//input[@class=\"button-2 add-to-compare-list-button\"])[1]"));


//waitForElementToBeInvisible(By.xpath("//*[@id=\"bar-notification\"]/div/p"), 5);

    //waitForAlertPresent(5);

    //product comparison Text for Bar Comparison

    Thread.sleep(600);

    String actual = getTextFromElement(By.partialLinkText("product comparison"));
    Assert.assertEquals(actual, "product comparison");

    //waitForTextToVisible(By.xpath("//*[@id=\"bar-notification\"]/div/p/text()"),7);
    //Second product comparison
    clickElement(By.xpath("(//input[@class=\"button-2 add-to-compare-list-button\"])[2]"));


    //Thread used due to other waits were not working
    Thread.sleep(600);

    String actual1 = getTextFromElement(By.partialLinkText("product comparison"));
    Assert.assertEquals(actual1, "product comparison");
    // Thread used due to other waits were not working.
    Thread.sleep(300);

    clickElement(By.partialLinkText("product comparison"));

    Assert.assertEquals(getTextFromElement(By.xpath("//*[text()='Compare products']")), "Compare products");

    clickElement(By.className("clear-list"));

    Assert.assertEquals(getTextFromElement(By.xpath("//div[text()=\"You have no items to compare.\"]")), "You have no items to compare.");

    /*
    issue with xpath so softAssert didnt work
    //Checking first condition
    String actual = getTextFromElement(By.xpath("//*[@id=\"bar-notification\"]/div/p/a"));
    System.out.println(actual);
    softAssert.assertEquals(actual, "product comparison");
    //Checking second condition
    String actual1 = getTextFromElement(By.xpath("//*[@id=\"bar-notification\"]/div/p/a"));
    System.out.println(actual1);
    softAssert.assertEquals(actual1, "product comparison");
    //Checking Third condition
    softAssert.assertEquals(getTextFromElement(By.xpath("//*[text()='Compare products']")), "Compare products");
    //Checking forth condition
    softAssert.assertEquals(getTextFromElement(By.xpath("//div[text()=\"You have no items to compare.\"]")), "You have no items to compare.");

    softAssert.assertAll(); */
  }

  @Test
  public void addCommentsToNewStoreOpen() {

    //Click on details at bottom of the page
    clickElement(By.xpath("(//a[@class='read-more'])[1]"));
    //To check if new online store is visible or not
    // Assert.assertEquals(getTextFromElement(By.xpath("//font[text()=\"New online store is open!\"]")), "New online store is open!");
    //Enter the title of for your comment
    enterText(By.className("enter-comment-title"), "New Store is nice6");
    //Message for your comment
    enterText(By.className("enter-comment-text"), "Visited the new store and found good product stock and verity");


    waitForElementToBeDisplayed(By.xpath("//*[@id=\"AddNewComment_CommentTitle\"]"), 5);


    WebElement element = driver.findElement(By.xpath("//input[@name=\"AddNewComment.CommentTitle\"]"));
    String text = element.getAttribute("value");
    //  String Actual = getTextFromElement(By.xpath("//input[@name=\"AddNewComment.CommentTitle\"]"));
    System.out.println(text);


    //Click submit button
    clickElement(By.xpath("//input[@value=\"New comment\"]"));


    // Assert to check if message submitted successfully or not
    String actual1 = getTextFromElement(By.xpath("//div[@class=\"result\"]"));
    System.out.println(actual1);

    Assert.assertEquals(actual1, "News comment is successfully added.");

    //List<WebElement> list = driver.findElements(By.xpath("//strong[@class=\"comment-text\"]"));



     }

    //for(int i=0; i< list.size(); i++){

     // Assert.assertTrue(list.get(i).getAttribute("value").contains(text));
      // Assert.assertTrue(list.get(i).getText().contains(text),"Search result validation failed at instance" + i );
    //}





  @Test
  public void userSearchResultContainsSearchedKeyword() throws InterruptedException {

    //Enter the search keywords of the product you with to search.
    String searchTerm = "Nike";

    WebElement search = driver.findElement(By.xpath("//*[@id=\"small-searchterms\"]"));

    search.sendKeys(searchTerm);
    //enterText(By.xpath("//*[@id=\"small-searchterms\"]"),"Nike");

    //Click search button
    clickElement(By.xpath("//input[@type=\"submit\"]"));

    Thread.sleep(900);

    List<WebElement> result = driver.findElements(By.xpath("//h2[@class=\"product-title\"]"));

    for (int i = 0; i < result.size(); i++) {

      Assert.assertTrue(result.get(i).getText().contains(searchTerm), "Search result validation failed at instance [ + i + ].");
      System.out.println(result.get(i).getText().contains(searchTerm));

    }




  }
}
