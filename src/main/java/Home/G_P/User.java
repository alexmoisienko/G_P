package Home.G_P;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

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

    private String email;

    public String toString() {
            return "{login='" + login + "', password='" + password + "', date='" + date + "'}";
    }

//    public User(String login, String password, Date date, String email) {
//        this.login = login;
//        this.password = password;
//        this.date = date;
//        this.email = email;
//    }
    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.date = new Date(124, 11, 11);
        this.email = email;
    }
}