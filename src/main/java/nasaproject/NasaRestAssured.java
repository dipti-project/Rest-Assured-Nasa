package nasaproject;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.List;

public class NasaRestAssured {
	String baseUrl = "https://images-api.nasa.gov/search";

	@Test
	public void validateSearch() {

		given().param("q", "apollo 11").param("center", "JPL").param("nasa_id", "PIA12909").when()
				.get("https://images-api.nasa.gov/Search").then().statusCode(200).log().all();

	}

	@Test
	public void validateTotalHits() {

		String json = given().param("q", "apollo 11").param("center", "JPL").param("nasa_id", "PIA12909").when()
				.get("https://images-api.nasa.gov/search").then().extract().asString();

		JsonPath jp = new JsonPath(json);
		System.out.println(jp.get("collection.metadata.total_hits"));
		Assert.assertEquals(jp.get("collection.metadata.total_hits"), 2, "both are not equal");
	
	}

	@Test
	public void validateCountOfItems() {

		String json = given().param("keywords", "ranger,ranger 9,launch, Moon").param("nasa_id", "PIA12909").when()
				.get(baseUrl).then().extract().asString();

		JsonPath jp = new JsonPath(json);
		List<String> li = jp.get("collection.items");
		System.out.println(li.size());
		Assert.assertEquals(li.size(), 100, "both are not equal");

	}

	@Test
	public void validateKeywordsPresent() {
		int i;
		String json = given().param("keywords", "ranger,ranger 9,launch, Moon").when().get(baseUrl).then().extract()
				.asString();

		JsonPath jp = new JsonPath(json);
		List<String> li1 = jp.get("collection.items");

		List<String> str = jp.get("collection.items[0].data[0].keywords");
		Assert.assertTrue(str.contains("ranger,ranger 9,launch, Moon"));

	}

	@Test
	public void validateNextPageResult() {

		String json = given().param("q", "apollo 11").param("description", "moon landing").when()
				.get("https://images-api.nasa.gov/search").then().extract().asString();
		JsonPath jp = new JsonPath(json);
		System.out.println(jp.get("collection.links[0].href"));
		String nextPageUrl = jp.get("collection.links[0].href");
		given().when().get(nextPageUrl).then().statusCode(200).log().all();

	}

	@Test
	public void verifyingContentType() {

		given().param("nasa_id", "PIA12909").when().get(baseUrl).then().assertThat().statusCode(200)
				.contentType(ContentType.JSON).header("Content-Length", "970");
	}

	@Test
	public void verifyingInvalidCharactersText() {

		String json = given().when().get(baseUrl).then().statusCode(400).extract().asString();
		JsonPath jp = new JsonPath(json);
		System.out.println(jp.get("reason"));
		Assert.assertEquals(jp.get("reason"), "'q' text search parameter or other keywords.");
	}
	

}
