package utils;

import pojos.UserRequest;

import java.time.LocalDateTime;

public class userGenerator {

    public static UserRequest getSimpleUser() {
        return UserRequest.builder()
                .name("New One" + LocalDateTime.now().toString())
                .job("QA" + LocalDateTime.now().toString())
                .build();
    }
}
