package in.reqres.requresinfo;

import in.reqres.constant.EndPoints;
import in.reqres.requressteps.UserSteps;
import in.reqres.testbase.TestBase;
import in.reqres.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ReqresCURDTestWithSteps extends TestBase {

    static String name = "User" + TestUtils.getRandomValue();
    static String job = "User" + TestUtils.getRandomValue();
    static String email = "User" + TestUtils.getRandomValue();
    static String password = "User" + TestUtils.getRandomValue();
    static String userID;

    @Steps
    UserSteps userSteps;

    @Title("This will create a new user")
    @Test
    public void test001() {
        HashMap<Object, Object> user = new HashMap<>();
        user.put("Marks", "8");
        user.put("Gentleman", "10");
        ValidatableResponse response = userSteps.createUser(name,job,email,password);
        response.log().all().statusCode(201);
        userID = response.log().all().extract().path("id");
        System.out.println(userID);
    }
    @Title("Verify if the user was added to the application")
    @Test
    public void test002 () {
        HashMap<String, Object> storeMap = userSteps.getUserInfoByFirstname(userID);
        Assert.assertThat(storeMap, hasValue(name));
        System.out.println(userID);
    }
    @Title("Delete the user and verify if the user is deleted!")
    @Test
    public void test004() {
        userSteps.deleteUser(userID).statusCode(204);
        userSteps.getUserById(userID).statusCode(404);
    }
    @Title("Getting all the users")
    @Test
            public void getAllUsers(){
            SerenityRest.given()
                    .when()
                    .get(EndPoints.GET_ALL_USER)
                    .then();
    }
}
