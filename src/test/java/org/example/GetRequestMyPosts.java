package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

/*Автоматизируйте GET https://test-stand.gb.ru/api/posts?sort=createdAt&order=ASC&page=1 - запрос со своими
 постами (минимум 5 кейсов, например, передав различные данные в query параметры, учтите, что неавторизованный
  пользователь не может запрашивать ленту с постами), используя rest-assured.
 */
public class GetRequestMyPosts {
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
                .queryParam( "sort", "createdAt" ).queryParam( "order", "ASC" )
                .queryParam( "page", "2" )
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
                .queryParam( "sort", "createdAt" ).queryParam( "order", "ASC" )
                .queryParam( "page", "7" )
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
                .formParam("title", "Торт наполеон 2" )
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
    public void chaneOrderGetReq() {
        given()
                .log().all()
                .header( "X-Auth-Token", token )
                .queryParam( "sort", "createdAt" ).queryParam( "order", "DESC" )
                .when()
                .contentType( ContentType.JSON)
                .get(baseURL)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
