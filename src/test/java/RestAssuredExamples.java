import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class RestAssuredExamples extends BaseApi{

    public BaseApi baseApi = new BaseApi();
    static final RestLogger rlog = RestLogger.getLogger(RestAssuredExamples.class);


    @Test
    public void testGetRestBooking(){
        String apiUrl = Constants.bookingURI+Constants.bookingPath;
        rlog.info(apiUrl);
        baseApi.doGetMethod(apiUrl);
        baseApi.validateStatusCode(200);
    }

    @Test
    public void testPostBooking(){

        String postBody = "{\"firstname\":\"Jim\",\"lastname\":\"Brown\",\"totalprice\":111,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2018-01-01\",\"checkout\":\"2019-01-01\"},\"additionalneeds\":\"Breakfast\"}";

        Response response = (Response) given().contentType(ContentType.JSON)
                .when()
                .body(postBody)
                .post("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200);
        System.out.println(response.asString());

    }

    @Test
    public void testPostHashBooking() {

        Map<String, Object> postBody = new HashMap<>();
        postBody.put("firstname", "QT");
        postBody.put("lastname", "Batch3");
        postBody.put("depositpaid", "true");
        postBody.put("totalprice", "112");
        postBody.put("additionalneeds", "breakfast");
        Map<String, Object> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2018-04-04");
        postBody.put("bookingdates", bookingdates);

        System.out.println(postBody);
          given().contentType(ContentType.JSON)
                .when()
                .body(postBody)
                .post("https://restful-booker.herokuapp.com/booking")
                .then().log().all()
                .statusCode(200);

    }

    @Test
    public void testPostBookingUsingJsonFile() {

        File postFile = new File("src/test/resources/testData/sample.json");
        given().contentType(ContentType.JSON)
                .when()
                .body(postFile)
                .post("https://restful-booker.herokuapp.com/booking")
                .then().log().all()
                .statusCode(200);

    }

    @Test
    public void testPostBookingPojoFile(){

        CreateUser user = new CreateUser();
        user.setName("morpheus");
        user.setJob("leader");
        given().contentType(ContentType.JSON)
                .when()
                .body(user)
                .post("https://reqres.in/api/users")
                .then().log().all()
                .statusCode(201);
    }

    @Test
    public void testPostBookingAsArray() throws IOException {
        //byte[] postBody = Files.readAllBytes(Paths.get("src/test/resources/testData/sample.json"));
        given().contentType(ContentType.JSON)
                .when()
               // .body(postBody)
                .post("https://restful-booker.herokuapp.com/booking")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void testPostCustomerUsingJSONObject(){

        JSONObject user = new JSONObject();
        user.put("name","morpheus");
        user.put("job","leader");
        given().contentType(ContentType.JSON)
                .when()
                .body(user)
                .post("https://reqres.in/api/users")
                .then().log().all()
                .statusCode(201);
    }


    @Test
    public void testPUTMethod(){
        Map<String, Object> putBody = new HashMap<>();
        putBody.put("name", "morpheus");
        putBody.put("job", "zion resident");
        given().contentType(ContentType.JSON)
                .when()
                .body(putBody)
                .put("https://reqres.in/api/users/2")
                .then().log().all()
                .statusCode(200);

    }

    @Test
    public void testDeleteMethod(){
        given().contentType(ContentType.JSON)
                .when()
                .delete("https://reqres.in/api/users/2")
                .then().log().all()
                .statusCode(204);
    }

    @Test(dataProvider = "xlData")
    public void testPostUsingExcel(String name, String job){

        JSONObject testData = new JSONObject();
        testData.put("name",name);
        testData.put("job",job);
        given().contentType(ContentType.JSON)
                .when()
                .body(testData)
                .post(Constants.baseURI+Constants.basePath+Constants.endPoint)
                .then().log().all()
                .statusCode(201);

    }

    @DataProvider(name="xlData")
    public Object[][] getXlData() throws IOException {
        String excelPath="./src/test/resources/testData/testData.xlsx";
        Object[][] data = excelData(excelPath,"Data");
        return data;
    }

    public Object[][] excelData(String excelPath, String sheetName) throws IOException {
        ExcelUtils excelUtils = new ExcelUtils(excelPath,sheetName);
        int rowCount = excelUtils.getRowCount();
        int columnCount = excelUtils.getColumnCount();

        Object[][] data = new Object[rowCount][columnCount];
        for(int i=1; i<rowCount; i++){
            for(int j=0; j<columnCount; j++){
                String cellData = excelUtils.getCellData(i,j);
                data[i-1][j]=cellData;
            }
        }
        return data;
    }

}
