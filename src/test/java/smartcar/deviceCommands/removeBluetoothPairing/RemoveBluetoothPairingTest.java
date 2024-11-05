package smartcar.deviceCommands.removeBluetoothPairing;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.junit.jupiter.api.*;
import utilities.Base;

import java.util.LinkedHashMap;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.given;


import static org.hamcrest.Matchers.*;
import static utilities.Util.waitFor;


@Disabled
@SerenityTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RemoveBluetoothPairingTest extends Base {

    @DisplayName("POST request Remove Bluetooth Pairing Admin")
    @Test
    @Order(1)
    public void postFormatSDCardAdmin() throws InterruptedException {

        Map<String, Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("device_key", "123456789");
        requestJsonMap.put("device_type", "B");
        requestJsonMap.put("command", "remove_ble_pairing");

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
        Ensure.that("command name = Remove Bluetooth Pairing",
                thenPart -> thenPart.body("parsed.command_name", is("Remove Bluetooth Pairing")));
        Ensure.that("command sent= remove_ble_pairing",
                thenPart -> thenPart.body("parsed.command_sent", is("remove_ble_pairing")));
        Ensure.that("success command = true",
                thenPart -> thenPart.body("parsed.command_success", is(true)));

        waitFor(10000);
    }

    @DisplayName("POST request Remove Bluetooth Pairing User-Primary")
    @Test
    @Order(2)
    public void postFormatSDCardUserPrimary() throws InterruptedException {

        Map<String, Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("device_key", "123456789");
        requestJsonMap.put("device_type", "B");
        requestJsonMap.put("command", "remove_ble_pairing");

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
        Ensure.that("command name = Remove Bluetooth Pairing",
                thenPart -> thenPart.body("parsed.command_name", is("Remove Bluetooth Pairing")));
        Ensure.that("command sent= remove_ble_pairing",
                thenPart -> thenPart.body("parsed.command_sent", is("remove_ble_pairing")));
        Ensure.that("success command = true",
                thenPart -> thenPart.body("parsed.command_success", is(true)));

        waitFor(10000);
    }

    @DisplayName("POST request Remove Bluetooth Pairing User-Shared")
    @Test
    @Order(3)
    public void postFormatSDCardUserShared() throws InterruptedException {

        Map<String, Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("device_key", "123456789");
        requestJsonMap.put("device_type", "B");
        requestJsonMap.put("command", "remove_ble_pairing");

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
        Ensure.that("command name = Remove Bluetooth Pairing",
                thenPart -> thenPart.body("parsed.command_name", is("Remove Bluetooth Pairing")));
        Ensure.that("command sent= remove_ble_pairing",
                thenPart -> thenPart.body("parsed.command_sent", is("remove_ble_pairing")));
        Ensure.that("success command = true",
                thenPart -> thenPart.body("parsed.command_success", is(true)));

        waitFor(10000);
    }

    @DisplayName("POST request Remove Bluetooth Pairing Unauthenticated")
    @Test
    @Order(4)
    public void postFormatSDCardUnauthenticated() {

        Map<String, Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("device_key", "123456789");
        requestJsonMap.put("device_type", "B");
        requestJsonMap.put("command", "remove_ble_pairing");

        given()
                .header("Authorization", bearerTokenAdmin)
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestJsonMap).log().all()
                .when()
                .post("/s/iot/command/")
                .then().log().all();

        Ensure.that("Status code is 403", x -> x.statusCode(403));
        Ensure.that("Content type is JSON", vR -> vR.contentType(ContentType.JSON));
        Ensure.that("success command = false",
                thenPart -> thenPart.body("success", is(false)));
        Ensure.that("detail = You do not have permission to perform this action.",
                thenPart -> thenPart.body("detail", is("You do not have permission to perform this action.")));
    }
}


