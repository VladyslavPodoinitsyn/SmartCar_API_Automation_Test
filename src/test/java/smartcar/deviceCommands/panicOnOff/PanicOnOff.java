package smartcar.deviceCommands.panicOnOff;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.junit.jupiter.api.*;
import utilities.Base;

import java.util.LinkedHashMap;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.hamcrest.Matchers.is;
import static utilities.Util.waitFor;

@Disabled
@SerenityTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PanicOnOff extends Base {

    @DisplayName("POST request Panic On Admin")
    @Test
    @Order(1)
    public void postPanicOnAdmin() throws InterruptedException {

        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("device_key", "123456789");
        requestJsonMap.put("device_type", "A");
        requestJsonMap.put("command", "panic_on");

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
                thenPart -> thenPart.body("device_key",is("123456789")));
        Ensure.that("command name = Panic On",
                thenPart -> thenPart.body("parsed.command_name",is("Panic On")));
        Ensure.that("command sent= panic_on",
                thenPart -> thenPart.body("parsed.command_sent",is("panic_on")));
        Ensure.that("success command = true",
                thenPart -> thenPart.body("parsed.command_success",is(true)));
        waitFor(10000);
    }

    @DisplayName("POST request Panic Off Admin")
    @Test
    @Order(2)
    public void postPanicOffAdmin() {

        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("device_key", "123456789");
        requestJsonMap.put("device_type", "A");
        requestJsonMap.put("command", "panic_off");

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
                thenPart -> thenPart.body("device_key",is("123456789")));
        Ensure.that("command name = Panic Off",
                thenPart -> thenPart.body("parsed.command_name",is("Panic Off")));
        Ensure.that("command sent= panic_off",
                thenPart -> thenPart.body("parsed.command_sent",is("panic_off")));
        Ensure.that("success command = true",
                thenPart -> thenPart.body("parsed.command_success",is(true)));
    }

    @DisplayName("POST request Panic On User-Primary")
    @Test
    @Order(3)
    public void postPanicOnUserPrimary() throws InterruptedException {

        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("device_key", "123456789");
        requestJsonMap.put("device_type", "A");
        requestJsonMap.put("command", "panic_on");

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
                thenPart -> thenPart.body("device_key",is("123456789")));
        Ensure.that("command name = Panic On",
                thenPart -> thenPart.body("parsed.command_name",is("Panic On")));
        Ensure.that("command sent= panic_on",
                thenPart -> thenPart.body("parsed.command_sent",is("panic_on")));
        Ensure.that("success command = true",
                thenPart -> thenPart.body("parsed.command_success",is(true)));
        waitFor(10000);
    }

    @DisplayName("POST request Panic Off User-Primary")
    @Test
    @Order(4)
    public void postPanicOffUserPrimary()  {

        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("device_key", "123456789");
        requestJsonMap.put("device_type", "A");
        requestJsonMap.put("command", "panic_off");

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
                thenPart -> thenPart.body("device_key",is("123456789")));
        Ensure.that("command name = Panic Off",
                thenPart -> thenPart.body("parsed.command_name",is("Panic Off")));
        Ensure.that("command sent= panic_off",
                thenPart -> thenPart.body("parsed.command_sent",is("panic_off")));
        Ensure.that("success command = true",
                thenPart -> thenPart.body("parsed.command_success",is(true)));
    }

    @DisplayName("POST request Panic On User-Shared")
    @Test
    @Order(5)
    public void postPanicOnUserShared() throws InterruptedException {

        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("device_key", "123456789");
        requestJsonMap.put("device_type", "A");
        requestJsonMap.put("command", "panic_on");

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
                thenPart -> thenPart.body("device_key",is("123456789")));
        Ensure.that("command name = Panic On",
                thenPart -> thenPart.body("parsed.command_name",is("Panic On")));
        Ensure.that("command sent= panic_on",
                thenPart -> thenPart.body("parsed.command_sent",is("panic_on")));
        Ensure.that("success command = true",
                thenPart -> thenPart.body("parsed.command_success",is(true)));
        waitFor(10000);
    }

    @DisplayName("POST request Panic Off (A02,0) User-Shared, Device State (Installed)")
    @Test
    @Order(6)
    public void postPanicOffUserShared() {

        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("device_key", "123456789");
        requestJsonMap.put("device_type", "A");
        requestJsonMap.put("command", "panic_off");

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
                thenPart -> thenPart.body("device_key",is("123456789")));
        Ensure.that("command name = Panic Off",
                thenPart -> thenPart.body("parsed.command_name",is("Panic Off")));
        Ensure.that("command sent= panic_off",
                thenPart -> thenPart.body("parsed.command_sent",is("panic_off")));
        Ensure.that("success command = true",
                thenPart -> thenPart.body("parsed.command_success",is(true)));
    }

    @DisplayName("POST request Panic On status Unauthenticated")
    @Test
    @Order(7)
    public void postPanicOnUnauthenticated() {

        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("device_key", "123456789");
        requestJsonMap.put("device_type", "A");
        requestJsonMap.put("command", "panic_on");

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
                thenPart -> thenPart.body("success",is(false)));
        Ensure.that("detail = You do not have permission to perform this action.",
                thenPart -> thenPart.body("detail",is("You do not have permission to perform this action.")));
    }

    @DisplayName("POST request Panic Off status Unauthenticated")
    @Test
    @Order(8)
    public void postPanicOffUnauthenticated() {

        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("device_key", "123456789");
        requestJsonMap.put("device_type", "A");
        requestJsonMap.put("command", "panic_on");

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
                thenPart -> thenPart.body("success",is(false)));
        Ensure.that("detail = You do not have permission to perform this action.",
                thenPart -> thenPart.body("detail",is("You do not have permission to perform this action.")));
    }

}
