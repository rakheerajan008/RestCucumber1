package utils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;


import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.*;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestUtils {
	PropertiesUtils prop = new PropertiesUtils();
	public static String baseUri;
	public static Response response;

	// Creating an object request of type RequestSpecification
	// so that can be modified depending on the type of request
	public RequestSpecification request = RestAssured.given();

	// setup a base uri
	public void setBaseUri(String base, String endpoint) throws IOException {
		// Checking if the value exists in the configuration file
		assertThat(prop.getPropValue(base), is(not(nullValue())));
		assertThat(prop.getPropValue(endpoint), is(not(nullValue())));

		// Creating the uri using the base uri and endpoint
		baseUri = prop.getPropValue(base) + prop.getPropValue(endpoint);
	}

	// create a generic method to do the get request
	public void getUri() throws InterruptedException {
		// fetching the response from the get method for the given uri
		response = request.get(baseUri);

	}

	// create a generic method to do the post request
	public void postUri(String jsonFileName) throws IOException {
		String jsonBody = new String(Files
				.readAllBytes(Paths.get(System.getProperty("user.dir") + "\\src\\test\\resources\\" + jsonFileName)));
		response = request.header("Content-Type", "application/json").body(jsonBody).post(baseUri);
	}
	
	public void postSoapUri(String xmlFileName) throws IOException {
		String xmlBody = new String(Files
				.readAllBytes(Paths.get(System.getProperty("user.dir") + "\\src\\test\\resources\\" + xmlFileName)));
		response = request.header("Content-Type", "text/xml").body(xmlBody).post(baseUri);
		}

}
