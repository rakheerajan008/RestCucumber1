package steps;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utils.AssertUtils;
import utils.RestUtils;
public class CommonSteps {
	RestUtils restUtils = new RestUtils();
	AssertUtils assertUtils = new AssertUtils();
	
	@Given("^I have the \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_have_the_and(String base, String endPoint) throws Throwable {
	   
		restUtils.setBaseUri(base, endPoint);
	}

	@When("^I make a call to the REST API GET request$")
	public void i_make_a_call_to_the_REST_API_GET_request() throws Throwable {
		restUtils.getUri();
 	}

	@Then("^the HTTP status code from the response should be \"([^\"]*)\"$")
	public void the_HTTP_status_code_from_the_response_should_be(String StatusCode) throws Throwable {
		assertUtils.checkStatusCode(StatusCode);
	}

	@Then("^the \"([^\"]*)\" is equal to \"([^\"]*)\"$")
	public void the_is_equal_to(String key, String expValue) throws Throwable {
		assertUtils.checkKeyJson(key, expValue);
	}
	@Then("^the multiple keys are validated as below from Json$")
	public void the_multiple_keys_are_validated_as_below_from_Json(DataTable dataKeys) throws Throwable {
		assertUtils.checkMultipleKeysFromJson(dataKeys);
	  
	}
	@When("^I make a call to the REST API POST request with (.*)$")
	public void i_make_a_call_to_the_REST_API_POST_request_with_postData_json(String jsonFileName) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		restUtils.postUri(jsonFileName);
	}



}
