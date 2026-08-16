package utils;

import dto.UserData;
import net.datafaker.Faker;

public class UserFactory {
    static Faker faker = new Faker();

    public static UserData positiveUser() {
        UserData user = UserData.builder()
                .username(faker.internet().emailAddress())
                .password(PropertiesReader.getProperty(
                        "base.properties","sign_up_pass"))
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();
        return user;
    }
}
