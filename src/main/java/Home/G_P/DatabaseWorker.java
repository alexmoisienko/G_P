package Home.G_P;

import java.sql.*;

public class DatabaseWorker {
    private final String url = "jdbc:postgresql://192.168.1.3:5432/admin";
    private final String username = "admin";
    private final String password = "123456";

    public User Select(String login) throws Exception {

        String query = "SELECT u.login, u.password, u.date, e.email\nFROM users u\nJOIN emails e ON\nu.login = e.login\nWHERE u.login = '" + login + "'";
//        Connection conn = null;
//        Statement statement = null;
//        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
//            while (resultSet.next()) {
            User user = new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(4)
            );
            user.setDate(resultSet.getDate(3));

            statement.close();
            conn.close();

            return user;

//            }

//        } catch (Exception ex) {
//
//            throw new Exception(ex);
//
//        }
//        finally {
//
//            if (statement != null) {
//                try { statement.close(); } catch (Exception ex) { System.out.println(ex); }
//            }
//            if (conn != null) {
//                try { conn.close(); } catch (Exception ex) { System.out.println(ex); }
//            }
//
//        }

//        return null;
    }


    public String Insert(User user) {

        String query = "INSERT INTO users (login, password, date) Values (?, ?, ?); INSERT INTO emails (login, email) Values (?, ?)";

        try (
                Connection conn = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = conn.prepareStatement(query)
        ) {

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDate(3, user.getDate());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getEmail());

            int rows = preparedStatement.executeUpdate();
            return rows + " rows affected";

        } catch (Exception ex) {

            System.out.println("Connection failed");
            System.out.println(ex);
        }
        return "0 rows affected";
    }
}
