package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Getting connection
        Util util = new Util();
        util.getConnection();

        //Creating an object of DAO class
        UserDao userDB = new UserDaoJDBCImpl();
        userDB.createUsersTable();
        userDB.createUsersTable();

        //Creating 4 users to add to DB
        User user1 = new User("UserName1", "UserLastName1", (byte) 1);
        User user2 = new User("UserName2", "UserLastName2", (byte) 2);
        User user3 = new User("UserName3", "UserLastName3", (byte) 4);
        User user4 = new User("UserName4", "UserLastName4", (byte) 14);

        //Adding users to DB
        userDB.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println(user1.getName() + " was successfully added!");
        userDB.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println(user2.getName() + " was successfully added!");
        userDB.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println(user3.getName() + " was successfully added!");
        userDB.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println(user4.getName() + " was successfully added!");

        //Printing all users from DB
        System.out.println("\nUsers from Database: ");
        List<User> allUsers = userDB.getAllUsers();
        for (User user: allUsers){
            System.out.println(user.toString());
        }

        //Clearing the 'user' table
        userDB.cleanUsersTable();
        System.out.println("\nThe table has been cleared!");

        //Deleting the 'user' table
        userDB.dropUsersTable();
        userDB.dropUsersTable();
        System.out.println("\nThe table has been dropped!");



    }
}
