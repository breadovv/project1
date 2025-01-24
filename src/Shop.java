import java.util.Scanner;

public class Shop {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Online Game Store!");
        int choice;

        do {
            showMainMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> registerUser();
                case 2 -> loginUser();
                case 3 -> listGames();
                case 4 -> addToCart();
                case 5 -> checkoutCart();
                case 6 -> System.out.println("Thank you for visiting the Online Game Store. Goodbye!");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void showMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. List Games");
        System.out.println("4. Add to Cart");
        System.out.println("5. Checkout");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private static void registerUser() {
        System.out.println("\nRegister New User:");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        // Save user to the database using UserDAO
        User user = new User(0, name, phone, email);
        UserDAO userDAO = new UserDAO();
        userDAO.registerUser(user);

        System.out.println("Registration successful!");
    }

    private static void loginUser() {
        System.out.println("\nLogin:");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();


        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByEmail(email);

        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getName() + ".");
        } else {
            System.out.println("User not found. Please register first.");
        }
    }

    private static void listGames() {
        System.out.println("\nGames Available in the Store:");
        GameDAO gameDAO = new GameDAO();
        for (Game game : gameDAO.getAllGames()) {
            System.out.println("ID: " + game.getGameId() + " | Title: " + game.getTitle() + " | Price: $" + game.getPrice());
        }
    }

    private static void addToCart() {
        System.out.println("\nAdd Game to Cart:");
        System.out.print("Enter your User ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter Game ID: ");
        int gameId = scanner.nextInt();

        // Add the game to the user's cart using CartDAO
        CartDAO cartDAO = new CartDAO();
        cartDAO.addGameToCart(userId, gameId);

        System.out.println("Game added to cart!");
    }

    private static void checkoutCart() {
        System.out.println("\nCheckout Cart:");
        System.out.print("Enter your User ID: ");
        int userId = scanner.nextInt();


        CartDAO cartDAO = new CartDAO();
        cartDAO.checkoutCart(userId);

        System.out.println("Checkout successful! Thank you for your purchase.");
    }
}
