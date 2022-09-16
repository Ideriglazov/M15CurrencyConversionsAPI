import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class VariousCurrenciesSelectionTest {
    //Link to the corresponding test case in Jira
    //https://legionqa.atlassian.net/browse/MICAT-3
    private static Response response;
    @Test
    public void VariousCurrenciesSelectionTest() {
        response = given().get(Consts.URL + Consts.LIVE_ENDPOINT + "?apikey=" + Consts.TOKEN +"&source=USD&currencies=CAD,EUR,ILS,RUB");
        System.out.println(response.asString());
        //Conversion rates from US dollar to Canadian dollar, Euro, Israel Shekel and Russian Ruble are expected
        response.then().statusCode(200);
        response.then().body("quotes.USDCAD", notNullValue());
        response.then().body("quotes.USDEUR", notNullValue());
        response.then().body("quotes.USDILS", notNullValue());
        response.then().body("quotes.USDRUB", notNullValue());
    }
}
