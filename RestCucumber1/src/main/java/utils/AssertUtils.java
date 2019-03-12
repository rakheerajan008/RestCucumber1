package utils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cucumber.api.DataTable;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;

public class AssertUtils {
	RestUtils restUtils = new RestUtils();
	JsonPath jPath;
	XmlPath xPath;

	public void checkStatusCode(String statusCode) {
		int code = Integer.parseInt(statusCode);
		assertThat("The status code is not as expected", RestUtils.response.statusCode(), is(equalTo(code)));
	}

	public void checkKeyJson(String key, String expValue) {
		String json = RestUtils.response.getBody().asString();
		jPath = new JsonPath(json);
		assertThat("The values did not match", expValue, is(jPath.getString(key)));
	}

	public void checkMultipleKeysFromJson(DataTable dataKeys) {
		String json = RestUtils.response.getBody().asString();
		jPath = new JsonPath(json);

		// Asserting the values of multiple keys from the response
		List<Map<String, String>> keyList = dataKeys.asMaps(String.class, String.class);
		Iterator<Map<String, String>> keyCounter = keyList.iterator();
		while (keyCounter.hasNext()) {
			Map<String, String> mapObj = keyCounter.next();
			System.out.println("Actual Key ***** :" + mapObj.get("actualKey"));
			System.out.println("Expected Value ***** :" + mapObj.get("expectedValue"));
			System.out.println("real value from json **** :" + jPath.getString(mapObj.get("actualKey")));
			assertThat("The values did not match", mapObj.get("expectedValue"),
					is(jPath.getString(mapObj.get("actualKey"))));
		}

	}

	public void checkMultipleKeysFromXml(DataTable dataKeys) {
		List<Map<String, String>> keyList = dataKeys.asMaps(String.class, String.class);
		Iterator<Map<String, String>> keyCounter = keyList.iterator();
		String strXml = RestUtils.response.getBody().asString();
		xPath = new XmlPath(strXml);
		while (keyCounter.hasNext()) {
			Map<String, String> mapObj = keyCounter.next();
			String key = mapObj.get("actualKey");
			List<String> res = xPath.getList(
					"Envelope.Body.GetHolidaysAvailableResponse.GetHolidaysAvailableResult.HolidayCode." + key);
			for (String str : res) {
				System.out.println(str + "\n");
			}

		}

	}

}
