package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `users` " +
                "(`id` INT NOT NULL AUTO_INCREMENT, " +
                "`name` VARCHAR(45) NULL, " +
                "`lastName` VARCHAR(45) NULL, " +
                "`age` INT(3) NULL, " +
                "PRIMARY KEY (`id`)) ENGINE = MyISAM;";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS `users`";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = String.format("INSERT INTO `users` (name, lastName, age) VALUE ('%s', '%s', '%d');", name, lastName, age);
        try (Statement statement = Util.getConnection().createStatement()){
            statement.executeUpdate(sql);
            System.out.printf("User с именем - %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void removeUserById(long id) {
        String sql = String.format("DELETE FROM `users` WHERE id = '%s';", id);
        try (Statement statement = Util.getConnection().createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT id, name, lastName, age FROM `users`;";
        try (Statement statement = Util.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(sql)){
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User newUser = new User(rs.getString(2), rs.getString(3), (byte) rs.getInt(4));
                newUser.setId((long) rs.getInt(1));
                users.add(newUser);
                System.out.println(newUser);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE `users`;";
        try (Statement statement = Util.getConnection().createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}