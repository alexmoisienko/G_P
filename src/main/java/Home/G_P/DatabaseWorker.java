package Home.G_P;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DatabaseWorker {
    private final String url = "jdbc:postgresql://192.168.1.3:5432/admin";
    private final String username = "admin";
    private final String password = "123456";

    public User Select(String login) throws Exception {
        String query = "SELECT u.login, u.password, u.date, e.email\nFROM users u\nJOIN emails e ON\nu.login = e.login\nWHERE u.login = '" + login + "'";
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
            } else {
                throw new Exception("No user found");
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public String Insert(User user) {
        String query = "INSERT INTO users (login, password, date) Values (?, ?, ?); INSERT INTO emails (login, email) Values (?, ?)";
        LocalDateTime localdatetime = LocalDateTime.now();
        try (
                Connection conn = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = conn.prepareStatement(query)
        ) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getEmail());
            return preparedStatement.executeUpdate() + " rows affected";
        } catch (Exception ex) {
            System.out.println("Connection failed");
            System.out.println(ex);
        }
        return "0 rows affected";
    }
}
