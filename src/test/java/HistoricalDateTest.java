import Utilities.SharedDriver;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HistoricalDateTest {
    //Link to the corresponding test case in Jira
    //https://legionqa.atlassian.net/jira/software/c/projects/MICAT/issues/MICAT-5
    private static Response response;
   private static WebDriver driver;
    @Test
    public void HistoricalDateTest() {
        driver = SharedDriver.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        response = given().contentType("application/json").get(Consts.URL + Consts.HISTORICAL_ENDPOINT + "?apikey=" + Consts.TOKEN + "&source=USD&currencies=CAD,EUR,ILS,RUB&date=2020-07-01");
        System.out.println(response.asString());
        JsonPath jsonPathEvaluator = response.jsonPath();
        int timestampInt = jsonPathEvaluator.get("timestamp");
        String timestampString = String.valueOf(timestampInt);
        System.out.println(timestampString);

        driver.get(Consts.EPOCHCONVERTER_URL);
        WebElement searchBar = driver.findElement(By.xpath("//input[@id='ecinput']"));
        searchBar.clear();
        searchBar.sendKeys(timestampString);
        WebElement timeStampToHumanDateButton = driver.findElement(By.xpath("//input[@id='ecinput']//following-sibling::button[@type='submit']"));
        timeStampToHumanDateButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'среда, 1 июля 2020 г., 19:59:59')]")));
        WebElement date = driver.findElement(By.xpath("//*[contains(text(), 'среда, 1 июля 2020 г., 19:59:59')]"));
        assertNotNull(date);
        SharedDriver.closeDriver();
    }
}
