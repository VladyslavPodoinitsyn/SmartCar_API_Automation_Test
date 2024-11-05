package utilities;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

import net.serenitybdd.rest.Ensure;
import org.hamcrest.Matchers;



public class Base {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we don't need to type each http method.
        baseURI = ConfigReader.getProperty("base.url");
    }

    @AfterAll
    public static void close(){
        //reset the info we set above, method comes from restassured
        reset();
    }

    public String signInAndGetIdToken(String role) {

        String username, password;

        switch (role) {

            case "user-primary":
                username = ConfigReader.getProperty("user_primary_username");
                password = ConfigReader.getProperty("user_primary_password");
                break;

            case "user-shared":
                username = ConfigReader.getProperty("user_shared_username");
                password = ConfigReader.getProperty("user_shared_password");
                break;

            case "unauthenticated":
                username = ConfigReader.getProperty("unauthenticated_username");
                password = ConfigReader.getProperty("unauthenticated_password");
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + role);
        }

        String URI = ConfigReader.getProperty("cognito_uri");
        String AWS_CLIENT_ID = ConfigReader.getProperty("aws_client_id");

        // Set request headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-amz-json-1.1");
        headers.put("X-Amz-Target", "AWSCognitoIdentityProviderService.InitiateAuth");

        // Set request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("AuthFlow", "USER_PASSWORD_AUTH");
        requestBody.put("ClientId", AWS_CLIENT_ID);

        Map<String, String> authParameters = new HashMap<>();
        authParameters.put("USERNAME", username);
        authParameters.put("PASSWORD", password);

        requestBody.put("AuthParameters", authParameters);

        EncoderConfig encoderConfig = new EncoderConfig().encodeContentTypeAs("application/x-amz-json-1.1", ContentType.TEXT);

        // Send the POST request
        Response response = SerenityRest.given()
                .baseUri(URI)
                .headers(headers)
                .config(RestAssured.config().encoderConfig(encoderConfig))
                .body(requestBody)
                .post("/?setToken=true&whichToken=usertoken");

        String token = response.path("AuthenticationResult.IdToken");
        String idToken ="Bearer "+ token;

        // Return the IdToken
        return idToken;
    }
    protected String bearerTokenAdmin = ConfigReader.getProperty("admin_token");
    protected String idTokenUserPrimary = signInAndGetIdToken("user-primary");
    protected String idTokenUserShared = signInAndGetIdToken("user-shared");
    protected String idTokenUnauthenticated = signInAndGetIdToken("unauthenticated");


    public static boolean[] bitsFromHex(String hexNumber) {
        int hexValue = Integer.parseInt(hexNumber, 16);
        String binaryString = String.format("%8s", Integer.toBinaryString(hexValue)).replace(' ', '0');
        boolean[] bits = new boolean[8];
        for (int i = 0; i < bits.length; i++) {
            bits[i] = binaryString.charAt(7 - i) == '1';
        }
        return bits;
    }

    public static void verifyBitsMatchFields(String[] fieldNames, boolean[] bits) {
        for (int i = 0; i < fieldNames.length; i++) {
            String fieldName = fieldNames[i];
            boolean expectedBit = bits[i];
            String message = "Verify Bit " + i + " matches " + fieldName;
            Ensure.that(message, response -> {
                response.body("parsed." + fieldName, Matchers.equalTo(expectedBit));
            });
        }
    }
}

