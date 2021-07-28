package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPojoFull {

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("id")
    private int id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("email")
    private String email;

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "UserPojoFull{" +
                "lastName='" + lastName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}