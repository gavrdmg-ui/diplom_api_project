package tests;

import api.ApiClient;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import testdata.CreatePetTestData;

public class TestBase {
    ApiClient api = new ApiClient();

    CreatePetTestData createTestData= new CreatePetTestData();
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl","https://petstore.swagger.io");
    }
}
