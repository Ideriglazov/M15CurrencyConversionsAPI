import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class SecurityAuthorizationTest {
    //Link to the corresponding test case in Jira
    //https://legionqa.atlassian.net/browse/MICAT-1
    private static Response response;
    @Test
    public void SecurityAuthorizationTest() {
        response = given().contentType("application/json").get(Consts.URL + Consts.LIVE_ENDPOINT + "?apikey=" + Consts.TOKEN);
        System.out.println(response.asString());
        //A list of currency conversion rates is expected
        response.then().statusCode(200);
        response.then().body("quotes.USDAED", notNullValue());
        response.then().body("quotes.USDBGN", notNullValue());
        response.then().body("quotes.USDTOP", notNullValue());

        response = given().contentType("application/json").get(Consts.URL + Consts.LIVE_ENDPOINT + "?apikey=");
        //Error 401 is expected
        response.then().statusCode(401);

        response = given().contentType("application/json").get(Consts.URL + Consts.HISTORICAL_ENDPOINT +"?date=2018-01-01&apikey=" + Consts.TOKEN);
        System.out.println(response.asString());
        //A list of currency conversion rates for the specified date is expected
        response.then().statusCode(200);
        response.then().body("date", notNullValue());
        response.then().body("quotes.USDAED", notNullValue());
        response.then().body("quotes.USDBGN", notNullValue());
        response.then().body("quotes.USDTOP", notNullValue());

        response = given().contentType("application/json").get(Consts.URL + Consts.HISTORICAL_ENDPOINT +"?date=2018-01-01&apikey=");
        //Error 401 is expected
        response.then().statusCode(401);
    }
}