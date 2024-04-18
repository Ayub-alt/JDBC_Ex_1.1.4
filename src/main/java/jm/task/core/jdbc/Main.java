package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Getting connection
       // Util util = new Util();
        //util.getConnection();

        //Creating an object of DAO class
        UserDao userDB = new UserDaoJDBCImpl();

        //Creating table of users
        userDB.createUsersTable();

        //Creating 4 users to add to DB
        User user1 = new User("UserName1", "UserLastName1", (byte) 1);
        User user2 = new User("UserName2", "UserLastName2", (byte) 2);
        User user3 = new User("UserName3", "UserLastName3", (byte) 4);
        User user4 = new User("UserName4", "UserLastName4", (byte) 14);

        //Adding users to DB
        userDB.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.printf("User с именем — %s добавлен в базу данных\n", user1.getName());

        userDB.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.printf("User с именем — %s добавлен в базу данных\n", user2.getName());

        userDB.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.printf("User с именем — %s добавлен в базу данных\n", user3.getName());

        userDB.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.printf("User с именем — %s добавлен в базу данных\n", user4.getName());

        //Printing all users from DB
        System.out.println("\nUsers from Database: ");
        List<User> allUsers = userDB.getAllUsers();
        for (User user: allUsers){
            System.out.println(user.toString());
        }

        //Clearing the 'user' table
        userDB.cleanUsersTable();

        //Deleting the 'user' table
        userDB.dropUsersTable();

    }
}
