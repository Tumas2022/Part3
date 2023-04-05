package org.example;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
/*Автоматизируйте POST https://test-stand.gb.ru/gateway/login (минимум 3 кейса, например передать
    валидные/невалидные логин и пароль и т.д), используя rest-assured.
    */
public class PostRequestLogin {
   AuthReq authReq= new AuthReq( "testTumas" , "a77388c148");
    @Test
        public void AuthReq() {
            given()
                    .baseUri(  "https://test-stand.gb.ru/gateway").log().all()
                    .contentType( ContentType.JSON )
            .when()
                    .body(authReq  )
                    .post("/login")
                    .then()
                    .log().all()
                    .statusCode( 200 );
        }
        public static class AuthReq {
        public AuthReq(String username, String password) {
            this.username = username;
            this.password = password;
        }
        private String username; private String password;

        public String getUsername() {
            return username;
        }
        public String getPassword() {
            return password;
        }
    }

    @Test

    public void invalidPasswordPostReq() {
        given()
                .baseUri(  "https://test-stand.gb.ru/gateway").log().all()
                .contentType( ContentType.JSON )
                .when()
                .body( "{\n" +
                        "\"username\": \"test24test\",\n" +
                        "\"password\": \"a77388c148\"\n" +
                        "}\n" )
                .post("/login")
                .then()
                .log().all()
                .statusCode( 200 );

    }

    @Test

    public void invalidUsernamePostReq() {
        given()
                .baseUri(  "https://test-stand.gb.ru/gateway").log().all()
                .contentType( ContentType.JSON )
                .when()
                .body( "{\n" +
                        "\"username\": \"testTumas\",\n" +
                        "\"password\": \"665464545\"\n" +
                        "}\n" )
                .post("/login")
                .then()
                .log().all()
                .statusCode( 200 );
    }
}



