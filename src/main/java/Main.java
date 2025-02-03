import modules.dao.UsersDAO;
import modules.enteties.Users;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UsersDAO userDAO = new UsersDAO();
        Scanner scanner = new Scanner(System.in);

        // User Registration
        System.out.println("Register a new user:");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Users newUser = new Users();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setPhone(phone);
        userDAO.create(newUser);
        System.out.println("User registered successfully: " + newUser.getName());


        Users fetchedUser = userDAO.findByname(name);
        if (fetchedUser != null) {
            System.out.println("Fetched User: " + fetchedUser.getName() + " | Email: " + fetchedUser.getEmail());
        }


        System.out.println("All Users:");
        userDAO.findAllUsers().forEach(user ->
                System.out.println(user.getName() + " | " + user.getEmail()));


    }
}
