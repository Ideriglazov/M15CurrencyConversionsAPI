import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class LiveEndpointTestFailed {
    //Link to the corresponding test bug in Jira
    //https://legionqa.atlassian.net/jira/software/c/projects/MICAT/issues/MICAT-7
    private static Response response;
    @Test
    public void LiveEndpointTest() {
        response = given().contentType("application/json").get(Consts.URL + Consts.LIVE_ENDPOINT + "?apikey=" + Consts.TOKEN);
        //Line below is commented out as this approach does not work for some reason
        //response = given().auth().oauth2(Consts.TOKEN).contentType("application/json").get(Consts.URL + Consts.LIVE_ENDPOINT);
        System.out.println(response.asString());
        //Expected to return success,terms,privacy,timestamp,source,quotes
        response.then().body("success",notNullValue());
        response.then().body("terms",notNullValue());
        response.then().body("privacy",notNullValue());
        response.then().body("timestamp",notNullValue());
        response.then().body("source",notNullValue());
        response.then().body("quotes",notNullValue());
    }
}
