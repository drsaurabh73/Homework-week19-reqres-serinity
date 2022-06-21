package in.reqres.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.reqres.requressteps.UserSteps;
import in.reqres.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;

public class MyStepdefs {

    static ValidatableResponse response;
    static String name = "User" + TestUtils.getRandomValue();
    static String job = "User" + TestUtils.getRandomValue();
    static String email = "User" + TestUtils.getRandomValue();
    static String password = "User" + TestUtils.getRandomValue();
    static String userID;

    @Steps
    UserSteps userSteps;

    @Given("^I am on Homepage of application reqres$")
    public void iAmOnHomepageOfApplicationReqres() {
    }

    @Then("^User can get back a valid status code (\\d+) of users$")
    public void userCanGetBackAValidStatusCodeOfUsers(int code) {
        response.statusCode(code);
        response.assertThat().statusCode(code);
    }


    @When("^User send a GET Request to list endpoint users$")
    public void userSendAGETRequestToListEndpointUsers() {
        response = userSteps.getAllUsers();
    }

    @When("^User can create new user using POST method in products application$")
    public void userCanCreateNewUserUsingPOSTMethodInProductsApplication() {
        HashMap<Object, Object> user = new HashMap<>();
        user.put("Marks", "8");
        user.put("Gentleman", "10");
        ValidatableResponse response = userSteps.createUser(name,job,email,password);
        response.log().all().statusCode(201);
        userID = response.log().all().extract().path("id");
        System.out.println(userID);
    }


    @When("^User can Delete new product using DELETE method in products application$")
    public void userCanDeleteNewProductUsingDELETEMethodInProductsApplication() {
        response =  userSteps.deleteUser(userID);
        response.assertThat().statusCode(204);
    }

    @And("^User verify that the product is deleted successfully from product$")
    public void userVerifyThatTheProductIsDeletedSuccessfullyFromProduct() {
        response =  userSteps.deleteUser(userID);
        response.assertThat().statusCode(404);
    }

    @And("^I validate page \"([^\"]*)\"$")
    public void iValidatePage(int expected)  {
        response = userSteps.getAllUsers();
        int Actual = response.extract().path("page");
        Assert.assertEquals(expected,Actual);
    }

    @And("^I validate per_page \"([^\"]*)\"$")
    public void iValidatePer_page(int expected)  {
        response = userSteps.getAllUsers();
        int Actual = response.extract().path("per_page");
        Assert.assertEquals(expected,Actual);
    }

    @And("^I validate name of ID \"([^\"]*)\"$")
    public void iValidateNameOfID(String expected)  {
        response = userSteps.getAllUsers();
        String Actual = response.extract().path("data[3].first_name");
        Assert.assertEquals(expected,Actual);
    }

    @And("^I validate totalID \"([^\"]*)\"$")
    public void iValidateTotalID(int expected)  {
        response = userSteps.getAllUsers();
        int Actual = response.extract().path("data[1].id");
        Assert.assertEquals(expected,Actual);
    }

    @And("^I validate list of data 6$")
    public void iValidateListOfData() {
        response = userSteps.getAllUsers();
        List<Object> Actual = response.extract().path("data");
        Assert.assertEquals(6,Actual.size());
    }

    @And("^I validate avatar \"([^\"]*)\"$")
    public void iValidateAvatar(String expected)  {
        response = userSteps.getAllUsers();
        String Actual = response.extract().path("data[5].avatar");
        Assert.assertEquals(expected,Actual);
    }

    @And("^I validate support url  \"([^\"]*)\"$")
    public void iValidateSupportUrl(String expected)  {
        response = userSteps.getAllUsers();
        String Actual = response.extract().path("support.url");
        Assert.assertEquals(expected,Actual);
    }

    @And("^I validate support text \"([^\"]*)\"$")
    public void iValidateSupportText(String expected) {
        response = userSteps.getAllUsers();
        String Actual = response.extract().path("support.text");
        Assert.assertEquals(expected,Actual);
    }
}
