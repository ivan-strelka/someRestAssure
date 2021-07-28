import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import pojos.*;


public class RestTest {

    @Test
    void getUsers() {
        given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .log().all()
                .when().get()
                .then().statusCode(200)
                .log().all();
    }

    @Test
    void getUsers1() {
        given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .log().all()
                .when().get()
                .then().statusCode(200)
                .body("data[0].email", equalTo("george.bluth@reqres.in"))
                .statusCode(200)
                .log().all();
    }

    @Test
    void getUsers2() {
        given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .log().all()
                .when().get()
                .then().statusCode(200)
                .body("data.find{it.email=='george.bluth@reqres.in'}.first_name",
                        equalTo("George"))
                .statusCode(200)
                .log().all();
    }

    @Test
    void getUsers3() {
        List<String> allEmail = given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .log().all()
                .when().get()
                .then().statusCode(200)
                .log().all()
                .extract().jsonPath().getList("data.email");

        allEmail.stream().forEach(i -> System.out.println(i));

    }


    @Test
    void getUsers4() {
        List<UserPojo> allEmail = given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .log().all()
                .when().get()
                .then().statusCode(200)
                .log().all()
                .extract().jsonPath().getList("data", UserPojo.class);

        allEmail.forEach(System.out::println);

    }

    @Test
    void getUsers5() {
        List<UserPojoFull> users = given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .log().all()
                .when().get()
                .then().statusCode(200)
                .log().all()
                .extract().jsonPath().getList("data", UserPojoFull.class);

        users.forEach(System.out::println);
    }

    @Test
    void getUsers6() {
        List<UserPojoFull> users = given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .log().all()
                .when().get()
                .then().statusCode(200)
                .log().all()
                .extract().jsonPath().getList("data", UserPojoFull.class);

        assertThat(users).extracting(UserPojoFull::getEmail).contains("charles.morris@reqres.in");
        assertThat(users).extracting(UserPojoFull::getFirstName).contains("Charles");
    }


}
