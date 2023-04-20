package org.example;

import io.restassured.response.Response;

import java.util.List;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.example.utils.ReadPropertiesFile.readPropertiesFile;

public class ApiClient {

    private Properties properties = readPropertiesFile("src/test/resources/config.properties");
    public Response getCountriesRequest(List<String> codes){
        return given()
                .param("codes", codes)
                .when()
                .get(properties.getProperty("countries.url"))
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();
    }
}
