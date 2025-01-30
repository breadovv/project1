import modules.User;
import modules.UserDAO;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDAO userDao = new UserDAO();


        User newUser = new User("Иван Иванов", "+79123456789", "ivan@example.com");
        userDao.addUser(newUser);


        User user = userDao.getUserById(1);
        System.out.println(user);


        List<User> users = userDao.getAllUsers();
        users.forEach(System.out::println);


        if (user != null) {
            user.setName("Петр Петров");
            userDao.updateUser(user);
        }



        userDao.close();
    }
}