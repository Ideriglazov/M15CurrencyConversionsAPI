import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class HistoricalDateTest {
    private static Response response;
    @Test
    public void HistoricalDateTest() {
        response = given().contentType("application/json").get(Consts.URL + Consts.HISTORICAL_ENDPOINT + "?apikey=" + Consts.TOKEN + "&source=USD&currencies=CAD,EUR,ILS,RUB&date=2020-07-01");
        System.out.println(response.asString());
        JsonPath jsonPathEvaluator = response.jsonPath();
        int timestamp = jsonPathEvaluator.get("timestamp");
        System.out.println(timestamp);
    }
}
