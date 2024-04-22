package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userHB = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userHB.createUsersTable();
    }

    public void dropUsersTable() {
        userHB.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userHB.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userHB.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userHB.getAllUsers();
    }

    public void cleanUsersTable() {
        userHB.cleanUsersTable();
    }
}
