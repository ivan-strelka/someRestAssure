package utils;

import pojos.UserRequest;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class userGenerator {

    public static UserRequest getSimpleUser() {
        return UserRequest.builder()
                .name("New One " + LocalDateTime.now().toString())
                .job("QA " + LocalDateTime.now().toString())
                .build();
    }

    public static UserRequest getSimpleUser1() {
        return UserRequest.builder()
                .name("New One " + ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT))
                .job("QA " + ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT))
                .build();
    }

    public static UserRequest getSimpleUser3() {
        PodamFactory factory = new PodamFactoryImpl();
        return factory.manufacturePojo(UserRequest.class);
    }
}
