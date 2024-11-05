package smartcar.deviceCommands.trunk;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.junit.jupiter.api.*;
import utilities.Base;

import java.util.LinkedHashMap;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.hamcrest.Matchers.is;

@Disabled
@SerenityTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TrunkOpen extends Base {

    @DisplayName("POST request Trunk Open Admin")
    @Test
    @Order(1)
    public void postTrunkAdmin() {

        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("device_key", "123456789");
        requestJsonMap.put("device_type", "A");
        requestJsonMap.put("command", "trunk_open");

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
        Ensure.that("command name = Trunk",
                thenPart -> thenPart.body("parsed.command_name",is("Trunk")));
        Ensure.that("command sent= trunk_open",
                thenPart -> thenPart.body("parsed.command_sent",is("trunk_open")));
        Ensure.that("success command = true",
                thenPart -> thenPart.body("parsed.command_success",is(true)));
    }

    @DisplayName("POST request Trunk Open User-Primary")
    @Test
    @Order(2)
    public void postTrunkUserPrimary() {

        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("device_key", "123456789");
        requestJsonMap.put("device_type", "A");
        requestJsonMap.put("command", "trunk_open");

        given()
                .header("Authorization", idTokenUserPrimary)
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
        Ensure.that("command name = Trunk",
                thenPart -> thenPart.body("parsed.command_name",is("Trunk")));
        Ensure.that("command sent= trunk_open",
                thenPart -> thenPart.body("parsed.command_sent",is("trunk_open")));
        Ensure.that("success command = true",
                thenPart -> thenPart.body("parsed.command_success",is(true)));
    }

    @DisplayName("POST request Trunk Open User-Shared")
    @Test
    @Order(3)
    public void postTrunkUserShared() {

        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("device_key", "123456789");
        requestJsonMap.put("device_type", "A");
        requestJsonMap.put("command", "trunk_open");

        given()
                .header("Authorization", idTokenUserPrimary)
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
        Ensure.that("command name = Trunk",
                thenPart -> thenPart.body("parsed.command_name",is("Trunk")));
        Ensure.that("command sent= trunk_open",
                thenPart -> thenPart.body("parsed.command_sent",is("trunk_open")));
        Ensure.that("success command = true",
                thenPart -> thenPart.body("parsed.command_success",is(true)));
    }

    @DisplayName("POST request Trunk Open Unauthenticated")
    @Test
    @Order(4)
    public void postTrunkUnauthenticated() {

        Map<String,Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("device_key", "123456789");
        requestJsonMap.put("device_type", "A");
        requestJsonMap.put("command", "trunk_open");

        given()
                .header("Authorization", idTokenUnauthenticated)
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
