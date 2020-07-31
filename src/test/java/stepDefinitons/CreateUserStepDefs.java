package stepDefinitons;

import API_Helpers.RestAssuredExamples;
import Helpers.BaseApi;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;


public class CreateUserStepDefs {

    public RestAssuredExamples restExamples = new RestAssuredExamples();
    public BaseApi baseApi = new BaseApi();

    public CreateUserStepDefs() {

    }

    @When("i use POST request to create a bookingID")
    public void i_use_post_request_to_create_a_booking_id() {
        restExamples.testPostRequest();
    }

    @Then("the request should give me (\\d+) response")
    public void the_request_should_give_me_response(int statusCode) {
        baseApi.validateStatusCode(statusCode);
    }

    @When("i use GET request to get the list of bookings")
    public void i_use_get_request_to_get_the_list_of_bookings() {
        restExamples.testGetRestBooking();
    }


}
