package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

/*Автоматизируйте GET https://test-stand.gb.ru/api/posts?owner=notMe&sort=createdAt&order=ASC&page=1 - запрос
с чужими постами (минимум 5 кейсов, например, передав различные данные в query параметры, учтите, что
 неавторизованный пользователь не может запрашивать ленту с постами), используя rest-assured.
 */

public class GetRequestNotMyPosts {
    String token = "0766dcfa9d2b0026d683ed3ed99258d8";
    String baseURL = "https://test-stand.gb.ru/api/posts";

    @BeforeAll
    static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void validPageGetReq() {
        given()
                .log().all()
                .header( "X-Auth-Token", token )
                .queryParam( "owner", "notMe" ).queryParam( "sort", "createdAt" )
                .queryParam( "order", "ASC" )
                .queryParam( "page", "24" )
                .when()
                .contentType( ContentType.JSON)
                .get(baseURL)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
    @Test
    public void pageNotExistsGetReq() {
        given()
                .log().all()
                .header( "X-Auth-Token", token )
                .queryParam( "owner", "notMe" ) .queryParam( "sort", "createdAt" )
                .queryParam( "order", "ASC" )
                .queryParam( "page", "0" )
                .when()
                .contentType( ContentType.JSON)
                .get(baseURL)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
    @Test
    public void formParamGetReq() {
        given()
                .log().all()
                .header( "X-Auth-Token", token )
                .formParam("title", "Тест2Тест2Тест2Тест2Тест2Тест2Тест2Тест24" )
                .when()
                .contentType( ContentType.JSON)
                .get(baseURL)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
    @Test
    public void noParamsGetReq() {
        given()
                .log().all()
                .header( "X-Auth-Token", token )
                .when()
                .contentType( ContentType.JSON)
                .get(baseURL)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    public void chaneOrderDESCGetReq() {
        given()
                .log().all()
                .header( "X-Auth-Token", token )
                .queryParam( "owner", "notMe" ) .queryParam( "sort", "createdAt" )
                .queryParam( "order", "DESC" )
                .when()
                .contentType( ContentType.JSON)
                .get(baseURL)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
    @Test
    public void chaneOrderALLGetReq() {
        given()
                .log().all()
                .header( "X-Auth-Token", token )
                .queryParam( "owner", "notMe" ) .queryParam( "sort", "createdAt" )
                .queryParam( "order", "ALL" )
                .when()
                .contentType( ContentType.JSON)
                .get(baseURL)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}



