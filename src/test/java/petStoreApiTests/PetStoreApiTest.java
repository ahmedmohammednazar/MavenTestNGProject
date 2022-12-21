package petStoreApiTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetStoreApiTest {
  @Test
  public void postPet() {
	  
//	  Perform a POST request to create a pet with id 3465xxx (change the xxx to numbers). 
//	  Give the pet a name booboo in the request body.
//	  Status is available. 
//	  Then
//	  Verify the status code is 200 
//	  Verify the content type is application.json
	  
	  String requestBody = "{\n"
	  		+ "  \"id\": 3465674,\n"
	  		+ "  \"category\": {\n"
	  		+ "    \"id\": 0,\n"
	  		+ "    \"name\": \"string\"\n"
	  		+ "  },\n"
	  		+ "  \"name\": \"booboo\",\n"
	  		+ "  \"photoUrls\": [\n"
	  		+ "    \"string\"\n"
	  		+ "  ],\n"
	  		+ "  \"tags\": [\n"
	  		+ "    {\n"
	  		+ "      \"id\": 0,\n"
	  		+ "      \"name\": \"string\"\n"
	  		+ "    }\n"
	  		+ "  ],\n"
	  		+ "  \"status\": \"available\"\n"
	  		+ "}";
	  
	  Response myResponse = RestAssured.given().accept(ContentType.JSON).contentType("application/json").body(requestBody)
			  .when().post("https://petstore.swagger.io/v2/pet");
	  myResponse.then().statusCode(200).and().contentType("application/json");
	  myResponse.prettyPrint();
  }
  
  @Test
  public void getPetById() {

//	  Test Case 2:
//		  Perform a GET request to find a pet with id 3465xxx (xxx your number from above post request)
//		  Verify the status code is 200 
//		  Verify the content type is application.json
//		  Verify the pet name is booboo status is available 
	  
	  Response MyResponse = RestAssured
			  .given().accept(ContentType.JSON).when().get("https://petstore.swagger.io/v2/pet/3465674");
	MyResponse.then().statusCode(200).contentType("application/json");
	  
	 String actualcontentType = MyResponse.getContentType();
	 System.out.println(actualcontentType);
	 Assert.assertEquals(actualcontentType, "application.json");
	 
	 String petName = MyResponse.path("name");
	 
	 System.out.println(petName);
	 Assert.assertEquals(petName, "booboo");
  }
  
 @Test 
 public void getRequestById() {
	 
//	 Test Case 3: 
//		 Perform a GET request to find a pet with ID 7867864
//		 Verify the status code is 404 and content type is application.json
//		 Verify message is Pet not found
	 
	Response myResponse = RestAssured.given().accept(ContentType.JSON).when().get("https://petstore.swagger.io/v2/pet/7867864");
	myResponse.then().statusCode(404);
	
	int statusCode = myResponse.getStatusCode();
	
	Assert.assertEquals(statusCode, 404);
	 
	 
 }
}
