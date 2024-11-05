package smartcar.queryComands;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.*;
import utilities.ConfigReader;
import utilities.Base;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.Matchers.*;


//@Disabled
@SerenityTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QueryCommandsTest extends Base {

    @DisplayName("POST request Device Information")
    @Test
    @Order(1)
    public void postDeviceInfo() {

        Map<String, Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("device_key", ConfigReader.getProperty("device_key"));
        requestJsonMap.put("device_type", "B");
        requestJsonMap.put("command", "query_device_info");

        given()
                .header("Authorization", bearerTokenAdmin)
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestJsonMap).log().all()
                .when()
                .post("/s/iot/command/")
                .then().log().all();

        Ensure.that("Status code is 200", x -> x.statusCode(200));
        Ensure.that("Content type is JSON", vR -> vR.contentType(ContentType.JSON));
        Ensure.that("device key = 123456789",
                thenPart -> thenPart.body("device_key", is("123456789")));
        Ensure.that("command name = Device Information",
                thenPart -> thenPart.body("parsed.command_name", is("Device Information")));
        Ensure.that("success command = true",
                thenPart -> thenPart.body("parsed.command_success", is(true)));
    }

    @DisplayName("POST request Dash Camera Status")
    @Test
    @Order(2)
    public void postDashCameraStatus() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Map<String, Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("device_key", ConfigReader.getProperty("device_key"));
        requestJsonMap.put("device_type", "B");
        requestJsonMap.put("command", "dashcam_status");
        given()
                .header("Authorization", bearerTokenAdmin)
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestJsonMap).log().all()
                .when()
                .post("/s/iot/command/")
                .then().log().all();
        String payload = SerenityRest.lastResponse().path("payload");
        System.out.println("payload = " + payload);
        String hexNumber = payload.substring(payload.indexOf("dashcam_status") + 4, payload.indexOf("dashcam_status") + 6);
        System.out.println("hex number = " + hexNumber);
        boolean[] bits = Base.bitsFromHex(hexNumber);
        System.out.println("bits = " + Arrays.toString(bits));
        String[] fieldNames = {"is_sd_card_inserted", "is_recording", "is_speaker_enabled", "is_microphone_enabled", "is_wifi_enabled", "is_wifi_streaming_enabled", "is_parking_mode_enabled"};

        Ensure.that("Status code is 200", x -> x.statusCode(200));
        Ensure.that("Content type is JSON", vR -> vR.contentType(ContentType.JSON));
        Ensure.that("device key = 123456789",
                thenPart -> thenPart.body("device_key",is("123456789")));
        Ensure.that("command name = Dash Camera Status",
                thenPart -> thenPart.body("parsed.command_name",is("Dash Camera Status")));
        Ensure.that("command send = dashcam_status",
                thenPart -> thenPart.body("parsed.command_sent",is("dashcam_status")));
        Ensure.that("success command = true",
                thenPart -> thenPart.body("parsed.command_success",is(true)));

        //"Verify Bits Match Fields"
        //"each field corresponds to the corresponding bit index and their values correspond to"
        Ensure.that("hex values match the decoded JSON values", ensure -> Base.verifyBitsMatchFields(fieldNames, bits));


    }
}
