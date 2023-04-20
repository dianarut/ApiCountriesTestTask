package org.example.tests;

import io.restassured.response.Response;
import org.example.ApiClient;
import org.example.utils.ResponseParsers;
import org.example.verifications.ApiVerifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.example.utils.ReadPropertiesFile.readPropertiesFile;

public class GetCountriesTest {

    ApiClient client = new ApiClient();
    ApiVerifications apiVerifications = new ApiVerifications();
    ResponseParsers responseExtractor = new ResponseParsers();
    private final Properties properties = readPropertiesFile("src/test/resources/appData.properties");
    private final String rusCountryCode = properties.getProperty("rusCountryCode");
    private final String rusCountryName = properties.getProperty("rusCountryName");
    private final String indCountryCode = properties.getProperty("indCountryCode");
    private final String indCountryName = properties.getProperty("indCountryName");

    @Test
    public void testCountryBordersAreReciprocated() {
        Response response = client.getCountriesRequest(Arrays.asList(rusCountryCode));
        List<String> borderCountryCodes = responseExtractor.extractBordersForCountry(response, rusCountryName);

        apiVerifications.verifyCountriesContainsBorder(borderCountryCodes, rusCountryCode);
    }

    @Test
    public void testCountryBordersAreNotReciprocated() {
        Response response = client.getCountriesRequest(Arrays.asList(indCountryCode));
        List<String> borderCountryCodes = responseExtractor.extractBordersForCountry(response, indCountryName);

        Assertions.assertFalse(borderCountryCodes.contains(rusCountryCode));
    }
}