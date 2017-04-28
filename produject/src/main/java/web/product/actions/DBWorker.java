package web.product.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import web.product.DAO.AddUserException;
import web.product.DAO.NotFoundUserException;
import web.product.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static web.product.Attributes.*;

public class DBWorker {
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private final String LOGIN = "root";
    private final String PASSWORD = "root";

    private static Logger logger = LogManager.getLogger();
    private Connection connection;

    public DBWorker() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        } catch (SQLException e) {
            logger.fatal("Connection failed!");
        } catch (ClassNotFoundException e) {
            logger.fatal("Where is your MySQL JDBC Driver?!");
        }
    }

    public boolean addUser(User user) throws AddUserException {
        boolean result = false;
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users VALUES(DEFAULT,?,?,?,?)")) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDate(3, user.getAge());
            preparedStatement.setString(4, user.getEmail());
            result = preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new AddUserException();
        }
        return result;
    }

    public List<User> selectAll() throws ClassNotFoundException {
        List<User> users = new ArrayList<>();
        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(PARAM_ID));
                user.setLogin(resultSet.getString(PARAM_LOGIN));
                user.setPassword(resultSet.getString(PARAM_PASSWORD));
                user.setAge(resultSet.getDate(PARAM_AGE));
                user.setEmail(resultSet.getString(PARAM_EMAIL));
                users.add(user);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("selectAllUserException!");
        }
        return users;
    }

    public User selectUserById(int id) throws NotFoundUserException {
        User user = new User();
        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE id="+id)){
            while(resultSet.next()) {
                user.setId(id);
                user.setLogin(resultSet.getString(PARAM_LOGIN));
                user.setPassword(resultSet.getString(PARAM_PASSWORD));
                user.setAge(resultSet.getDate(PARAM_AGE));
                user.setEmail(resultSet.getString(PARAM_EMAIL));
            }
        } catch (SQLException e) {
            throw new NotFoundUserException(id);
        }
        return user;
    }

    public User selectUserByLoginAndPassword(String login, String password) throws NotFoundUserException {
        User user = new User();
        try (ResultSet resultSet= connection.createStatement().executeQuery("SELECT * FROM users WHERE login='"
                +login+"' AND password='"+password+"'")){
            while(resultSet.next()) {
            user.setId(resultSet.getInt(PARAM_ID));
            user.setLogin(resultSet.getString(PARAM_LOGIN));
            user.setPassword(resultSet.getString(PARAM_PASSWORD));
            user.setAge(resultSet.getDate(PARAM_AGE));
            user.setEmail(resultSet.getString(PARAM_EMAIL));
        }
        } catch (SQLException e) {
            throw new NotFoundUserException();
        }
        if (user.getId()>0) return user;
        throw new NotFoundUserException();
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
