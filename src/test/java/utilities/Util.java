package utilities;

import net.serenitybdd.rest.SerenityRest;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.Ensure;
import static org.hamcrest.Matchers.*;

public class Util {

    public static void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void verifyBitsMatchFields(String[] fieldNames, boolean[] bits) {
        for (int i = 0; i < fieldNames.length; i++) {
            JsonPath responsePath = SerenityRest.lastResponse().jsonPath();
            if (responsePath != null) {
                Object fieldValueObject = responsePath.get("parsed." + fieldNames[i]);
                if (fieldValueObject != null) {
                    if (fieldValueObject instanceof String) {
                        String fieldValue = (String) fieldValueObject;
                        final int index = i;
                        String trimmedFieldValue = fieldValue.trim(); // Remove leading and trailing whitespace
                        if (!trimmedFieldValue.isEmpty()) { // Check if the trimmed field value is not empty
                            String message = "Bit " + i + " matches " + fieldNames[index];
                            System.out.println(message);
                            Ensure.that(message, thenPart ->
                                    SerenityRest.lastResponse().then().body(fieldNames[index], equalTo(bits[index])));
                        }
                    } else {
                        System.out.println("Field " + fieldNames[i] + " is not a String: " + fieldValueObject.toString());
                    }
                } else {
                    System.out.println("Field " + fieldNames[i] + " not found in JSON response");
                }
            } else {
                System.out.println("Response path is null");
            }
        }
    }
}
