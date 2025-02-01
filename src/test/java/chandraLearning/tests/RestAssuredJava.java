package chandraLearning.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestAssuredJava {

    public static final String PAYPAL_BASE_URL = "https://api.sandbox.paypal.com/v1/oauth2/token";
    public static final String PAYPAL_CLIENT_ID = "AUs-Nfh-846rA5w-p-MWEQaAUytP5O0oR9QNC9-11zofb-11phFxwTkSI6FOdq3vSdUXHy5VKIScsTi_";
    public static final String PAYPAL_CLIENT_SECRET = "EBHbp39z5xJx_H17g0I52O1r6ljGD09AUo2YuTJ6DC79SlIOz25TynfLo0MCN-F9Q9mtA0baCvUoUwYS";
    public static final String PAYPAL_TEST_URL = "https://api.sandbox.paypal.com/v2/invoicing/templates";
    public static String PAYPAL_ACCESS_TOKEN;
    public static final String BASIC_AUTH_URL = "https://postman-echo.com/basic-auth";
    public static final String BASIC_USERNAME = "postman";
    public static final String BASIC_PASSWORD = "password";
    RequestSpecification requestSpec;
    Response response;

    @Test
    public void restAssuredAuth20Way1() {
        RestAssured.baseURI = PAYPAL_BASE_URL;
        requestSpec = RestAssured.given();
        requestSpec.auth().preemptive().basic(PAYPAL_CLIENT_ID, PAYPAL_CLIENT_SECRET);
        requestSpec.param("grant_type", "client_credentials");
        response = requestSpec.post();
        System.out.println(response.body().prettyPrint());
        System.out.println("***********************************************");
        PAYPAL_ACCESS_TOKEN = response.jsonPath().get("access_token");
        RestAssured.baseURI = PAYPAL_TEST_URL;
        requestSpec = RestAssured.given();
        requestSpec.header("Authorization", "Bearer " + PAYPAL_ACCESS_TOKEN)
                .header("Content-Type", "application/json");
        response = requestSpec.get();
        System.out.println(response.body().prettyPrint());
    }

    @Test
    public void restAssuredAuth20Way2() {

        PAYPAL_ACCESS_TOKEN = RestAssured.given()
                .auth().preemptive().basic(PAYPAL_CLIENT_ID, PAYPAL_CLIENT_SECRET)
                .param("grant_type", "client_credentials")
                .when()
                .post(PAYPAL_BASE_URL)
                .jsonPath().get("access_token");
        RestAssured.given()
                .header("Authorization", "Bearer " + PAYPAL_ACCESS_TOKEN)
                .header("Content-Type", "application/json")
                .when()
                .get(PAYPAL_TEST_URL)
                .then().log().body();

    }

    @Test
    public void restAssuredBasiAuthenticationWay1() {
        RestAssured.baseURI = BASIC_AUTH_URL;
        requestSpec = RestAssured.given()
                .auth().basic(BASIC_USERNAME,BASIC_PASSWORD);
        response = requestSpec.get();
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("authenticated"),"true");

    }
    @Test
    public void restAssuredBasiAuthenticationWay2() {

        RestAssured.given().auth().basic(BASIC_USERNAME,BASIC_PASSWORD)
                .when()
                .get(BASIC_AUTH_URL)
                .then().statusCode(200)
                .body("authenticated", Matchers.equalTo(true));
    }
}
