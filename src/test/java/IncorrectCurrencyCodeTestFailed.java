import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;


public class IncorrectCurrencyCodeTestFailed {
    private static Response response;
    @Test
    public void incorrectCurrencyCodeTest() {
        response = given().get(Consts.URL + Consts.LIVE_ENDPOINT + "?apikey=" + Consts.TOKEN +"&source=USD&currencies=CAD,EUR,FakeCurrencyCode");
        System.out.println(response.asString());
        //Error code 202 is expected. User entered one or more invalid currency codes.
        response.then().statusCode(202);
    }
}
