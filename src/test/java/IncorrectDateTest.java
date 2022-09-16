import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class IncorrectDateTest {
    //Link to the corresponding test case in Jira
    //https://legionqa.atlassian.net/jira/software/c/projects/MICAT/issues/MICAT-6
    private static Response response;
    @Test
    public void IncorrectDateTest() {
        response = given().contentType("application/json").get(Consts.URL + Consts.HISTORICAL_ENDPOINT + "?apikey=" + Consts.TOKEN + "&source=USD&currencies=CAD,EUR,ILS,RUB&date=Wrong date");
        System.out.println(response.asString());
        //Expected to return the status code 302
        //Line below does not work. Code 200 is returned
        //response.then().statusCode(302);
        response.then().body("error.code", equalTo(302));
    }
}