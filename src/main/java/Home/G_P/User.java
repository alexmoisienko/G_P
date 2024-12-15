package Home.G_P;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {
    @NotNull
    @NotEmpty
    private String login;
    @NotNull
    @NotEmpty
    private String password;
    private Date date;
    @NotNull
    @NotEmpty
    private String email;

    public String toString() {
            return "{login='" + login + "', password='" + password + "', datetime='" + date + "', email='" + email + "'}";
    }

    public User(String login, String password, Date date, String email) {
        this.login = login;
        this.password = password;
        this.date = date;
        this.email = login;
    }
    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.date = new Date();
        this.email = email;
    }
}