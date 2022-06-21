package in.reqres.requresinfo;

import in.reqres.constant.EndPoints;
import in.reqres.testbase.TestBase;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;

public class FirstTest extends TestBase {
    @Test
    public void getAllProduct(){
        SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_USER)
                .then()
                .log().all()
                .statusCode(200);
    }

    }

