package Helpers;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class BaseApi {


    public static Response response;

    public void doGetMethod(String apiUrl){
        response = given().contentType("application/json")
                .when()
                .get(apiUrl);
    }

    public void validateStatusCode(int statusCode){
        Assert.assertEquals(statusCode,response.getStatusCode());
    }

    public void extractAttribute(String attributeName){
        String res = response.toString();
        JsonPath.with(res).get(attributeName);
    }

    public void doPostRequest(String apiUrl){
        response = given().contentType("application/json")
                .body(Payload.postUser())
                .when()
                .post(apiUrl);
       int statusCode = response.statusCode();
        System.out.println(statusCode);
    }

    public void doPutRequest(String apiUrl){
        response = given().header("Content-Type","application/json")
                .when()
                .post(apiUrl);
    }

    public void doDeleteRequest(String apiUrl){
        response = given().contentType(ContentType.JSON)
                .when()
                .delete(apiUrl);
    }

    public void getReponseValueFromResponse(String propertyKey, String propertyValue){
        response.then().body(propertyKey, containsString(propertyValue));
    }

    public String getResponseKeyFromResponse(String propertyKey){
        JsonPath jpath = new JsonPath(response.toString());
        return jpath.getString(propertyKey);
    }

}
