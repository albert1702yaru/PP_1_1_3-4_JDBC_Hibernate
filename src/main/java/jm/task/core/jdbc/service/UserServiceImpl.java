package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao user= new UserDaoHibernateImpl();
    @Override
    public void createUsersTable() {
        user.createUsersTable();
    }
    @Override
    public void dropUsersTable() {
        user.dropUsersTable();
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        user.saveUser(name, lastName, age);
    }
    @Override
    public void removeUserById(long id) {
        user.removeUserById(id);
    }
    @Override
    public List<User> getAllUsers() {
        return  user.getAllUsers();
    }
    @Override
    public void cleanUsersTable() {
        user.cleanUsersTable();
    }
}