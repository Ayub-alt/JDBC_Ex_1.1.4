package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    private Connection connection = new Util().getConnection();

    public void createUsersTable() {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS `user`.`user` (`id` INT NOT NULL AUTO_INCREMENT, `name` VARCHAR(45) NOT NULL," +
                " `lastName` VARCHAR(45) NOT NULL, `age` INT NULL, PRIMARY KEY (`id`))";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void dropUsersTable() {

        String sqlQuery = "DROP TABLE IF EXISTS `user`.`user`";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlQuery = "insert into user(name, lastname, age) value('" + name + "', '" + lastName + "', " + (int)age + ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {

        String sqlQuery = "delete from user where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sqlQuery = "SELECT * FROM user.user";
        try (Statement statement = connection.createStatement()
        ) {
            ResultSet res = statement.executeQuery(sqlQuery);
            while (res.next()){
                users.add(new User(res.getString(2), res.getString(3), (byte) res.getInt(4)));
                users.get(users.size()-1).setId((long) res.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {

        String sqlQuery = "TRUNCATE `user`.`user`";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
