package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = new Util().getSessionFactory();
    Transaction transaction;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            String sqlStr = "CREATE TABLE IF NOT EXISTS users (`id` INT NOT NULL AUTO_INCREMENT, `name` VARCHAR(45) NOT NULL," +
                    " `lastName` VARCHAR(45) NOT NULL, `age` INT NULL, PRIMARY KEY (`id`))";
            transaction = null;
            transaction = session.beginTransaction();
            session.createSQLQuery(sqlStr).executeUpdate();
            session.getTransaction().commit();
        } catch (SessionException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

        try (Session session = sessionFactory.openSession()) {
            String sqlStr = "drop table if exists users";
            transaction = null;
            transaction = session.beginTransaction();
            session.createSQLQuery(sqlStr).executeUpdate();
            session.getTransaction().commit();
        } catch (SessionException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User newUser = new User(name, lastName, age);

        try (Session session = sessionFactory.openSession()) {
            String sqlStr = "insert into users(name, lastName, age) values(?, ?, ?)";
            transaction = null;
            transaction = session.beginTransaction();
            session.createSQLQuery(sqlStr).setParameter(1, newUser.getName()).setParameter(2, newUser.getLastName())
                    .setParameter(3, newUser.getAge()).executeUpdate();
            session.getTransaction().commit();

        } catch (SessionException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {

        try (Session session = sessionFactory.openSession()) {

            String sqlStr = "delete  from users where id = ?";
            transaction = null;
            transaction = session.beginTransaction();
            session.createSQLQuery(sqlStr).setParameter(1, id).executeUpdate();
            session.getTransaction().commit();
        } catch (SessionException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = null;
        try (Session session = sessionFactory.openSession()) {
            String sqlStr = "SELECT * FROM users";
            transaction = null;
            transaction = session.beginTransaction();
            allUsers = session.createSQLQuery(sqlStr).addEntity(User.class).list();
            session.getTransaction().commit();

        } catch (SessionException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            String sqlStr = "truncate users";
            transaction = null;
            transaction = session.beginTransaction();
            session.createSQLQuery(sqlStr).executeUpdate();
            session.getTransaction().commit();
        } catch (SessionException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

    }
}
