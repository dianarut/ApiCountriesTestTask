package org.example.verifications;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.example.ApiClient;
import org.example.dto.Country;
import org.junit.jupiter.api.Assertions;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;

public class ApiVerifications {

    ApiClient client = new ApiClient();

    public void verifyCountriesContainsBorder(List<String> countryCodes, String expectedBorder) {
        Response response = client.getCountriesRequest(countryCodes);
        Assertions.assertEquals(SC_OK, response.getStatusCode(), "Get Countries API status code is not correct.");
        List<Country> countries = response.getBody().as(new TypeRef<>() {
        });
        SoftAssert softAssert = new SoftAssert();
        for (Country country : countries) {
            List<String> borders = country.getBorders();
            softAssert.assertTrue(borders.contains(expectedBorder), String.format("Country %s does not contain %s border.", country.getName(), expectedBorder));
        }
        softAssert.assertAll();
    }
}
