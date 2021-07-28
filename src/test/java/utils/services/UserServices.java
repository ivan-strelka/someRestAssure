package utils.services;

import io.restassured.http.Cookies;
import pojos.CreateUserResponse;
import pojos.UserPojoFull;
import pojos.UserRequest;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserServices extends RestServices {

    public UserServices(Cookies cookies) {
        super(cookies);
    }

    public List<UserPojoFull> getListUsers() {
        return given().spec(REQ_SPEC)
                .get()
                .jsonPath().getList("data", UserPojoFull.class);
    }

    public CreateUserResponse createUser(UserRequest rq) {
        return given().spec(REQ_SPEC).body(rq).post().as(CreateUserResponse.class);
    }


    @Override
    protected String getBasePath() {
        return "/users";
    }
}
