import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import podamEx.Category;
import podamEx.Response;
import pojos.CreateUserResponse;
import pojos.UserPojo;
import pojos.UserPojoFull;
import pojos.UserRequest;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import utils.RestWrapper;
import utils.userGenerator;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class RestTest {

    static RestWrapper api;

    @BeforeAll
    public static void prepareClient() {
        api = RestWrapper.loginAs("eve.holt@reqres.in", "cityslicka");
    }


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
        assertThat(api.userServices.getListUsers()).extracting(UserPojoFull::getEmail).contains("charles.morris@reqres.in");
        assertThat(api.userServices.getListUsers()).extracting(UserPojoFull::getFirstName).contains("Charles");
    }


    @Test
    void CreateUserRq() {
        UserRequest rq =
                UserRequest.builder()
                        .name("New One" + LocalDateTime.now().toString())
                        .job("QA" + LocalDateTime.now().toString())
                        .build();


        CreateUserResponse rs = given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .log().all()
                .body(rq)
                .when().post()
                .then().statusCode(201)
                .log().all()
                .extract().as(CreateUserResponse.class);

        assertThat(rs)
                .isNotNull()
                .extracting(CreateUserResponse::getName).isEqualTo(rq.getName());
        assertThat(rs)
                .isNotNull()
                .extracting(CreateUserResponse::getJob).isEqualTo(rq.getJob());

    }

    @Test
    void CreateUserRq1() {
        UserRequest rq = userGenerator.getSimpleUser();
        CreateUserResponse rs = api.userServices.createUser(rq);

        assertThat(rs)
                .isNotNull()
                .extracting(CreateUserResponse::getName).isEqualTo(rq.getName());
        assertThat(rs)
                .isNotNull()
                .extracting(CreateUserResponse::getJob).isEqualTo(rq.getJob());

    }

    @Test
    void jsonObjectNodeEx() throws IOException {
        ObjectNode json = new ObjectMapper().readValue(new File("src/test/java/ex/ex.json"),
                ObjectNode.class);
        ((ObjectNode) json.get("tags").get(0)).put("id", 12);
        ((ObjectNode) json.get("category")).put("id", 99);
        ((ObjectNode) json.get("category")).put("name", "NAME_TEST");
        json.put("status", "12");
        ((ObjectNode) json.get("tags").get(0)).put("from", ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));

        CreateUserResponse as = given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .log().all()
                .body(json)
                .when().post()
                .then().statusCode(201)
                .log().all()
                .extract().as(CreateUserResponse.class);

        System.out.println(as.toString());
        System.out.println(json.asText());

    }

    @Test
    void podamEx() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        Response response = podamFactory.manufacturePojo(Response.class);
        Category category = podamFactory.manufacturePojo(Category.class);

        System.out.println("RES ------>>>>>>  " + response.toString());
        System.out.println("CAT  ------>>>>   " + category.toString());


        given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .log().all()
                .body(response)
                .when().post()
                .then().statusCode(201);
    }
}