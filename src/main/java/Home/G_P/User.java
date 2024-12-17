package Home.G_P;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    @NotNull
    @NotEmpty
    private String login;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String email;
    private String date;

    public String toString() {
            return "{login='" + login + "', password='" + password + "', date='" + date + "', email='" + email + "'}";
    }

    public User() {}

    public User(String login, String password, String date, String email) {
        this.login = login;
        this.password = password;
        this.date = date;
        this.email = email;
    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
}