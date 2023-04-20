package org.example.utils;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.example.dto.Country;

import java.util.List;

public class ResponseParsers {
    public List<String> extractBordersForCountry(Response response, String countryCode) {
        List<Country> countries = response.getBody().as(new TypeRef<>() {
        });
        return countries.stream().filter(country ->
                country.getName().equals(countryCode)).findAny().get().getBorders();
    }
}
