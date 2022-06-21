package in.reqres.requressteps;


import in.reqres.constant.EndPoints;
import in.reqres.model.RequresPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UserSteps {

    @Step
    public ValidatableResponse createUser(String name, String job, String email, String password) {

        RequresPojo requresPojo = new RequresPojo();
        requresPojo.setName(name);
        requresPojo.setJob(job);
        requresPojo.setEmail(email);
        requresPojo.setPassword(password);


        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(requresPojo)
                .when()
                .post(EndPoints.CREATE_USER_BY_ID)
                .then();
    }

    @Step("Getting the product information with name: {0}")
    public HashMap<String, Object> getUserInfoByFirstname(String userID) {

        HashMap<String, Object> productMap = SerenityRest.given().log().all()
                .when()
                .pathParam("userID", userID)
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
        return productMap;
    }
    @Step("Deleting product information with UserId: {0}")
    public ValidatableResponse deleteUser(String userID){
        return SerenityRest.given().log().all()
                .pathParam("userID", userID)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }

    @Step("Getting product information with UserId: {0}")
    public ValidatableResponse getUserById(String userID){
        return SerenityRest.given().log().all()
                .pathParam("userID", userID)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then();
    }
    @Step("Getting all users")
    public ValidatableResponse getAllUsers() {
        return SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_USER)
                .then();
    }
}