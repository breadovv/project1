import modules.dao.CartDAO;
import modules.dao.GameDAO;
import modules.dao.GenreDAO;
import modules.dao.UsersDAO;
import modules.enteties.Cart;
import modules.enteties.Games;
import modules.enteties.Genres;
import modules.enteties.Users;

import java.util.List;
import java.util.Scanner;


public class Main {
    public static void printMenuUnlogged() {
        System.out.println("1. Register a new user");
        System.out.println("2. Login");
        System.out.println("3. Browse games by genre");
        System.out.println("4. Search for a game by title");
        System.out.println("5. Exit");
    }
    public static void printMenuLogged() {
        System.out.println("1. Browse games by genre");
        System.out.println("2. Logout");
        System.out.println("3. Search for a game by title");
        System.out.println("4. Add game to cart");
        System.out.println("5. View cart");
        System.out.println("6. Exit");
    }
    public static void main(String[] args) {
        GameDAO gameDAO = new GameDAO();
        GenreDAO genreDAO = new GenreDAO();
        UsersDAO userDAO = new UsersDAO();
        CartDAO cartDAO = new CartDAO();
        Scanner scanner = new Scanner(System.in);
        Users loggedInUser = null;

        while (true) {
            System.out.println("\n--- Game Shop ---");
            if (loggedInUser == null) {
                printMenuUnlogged();
            } else {
                printMenuLogged();
            }
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (loggedInUser == null) {
                if (choice == 1) {
                    // Регистер
                    System.out.println("\nRegister a new user:");
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    if (userDAO.findByEmail(email) != null) {
                        System.out.println("User with this email already exists!");
                        continue;
                    }
                    Users newUser = new Users();
                    newUser.setName(name);
                    newUser.setEmail(email);
                    newUser.setPassword(password);
                    newUser.setPhone(phone);
                    userDAO.create(newUser);
                    System.out.println("User registered successfully: " + newUser.getName());

                    // логин афтер регистер
                    loggedInUser = newUser;
                    System.out.println("Welcome, " + loggedInUser.getName() + "! You are now logged in.");

                } else if (choice == 2) {
                    // логин
                    System.out.println("\nLogin:");
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();

                    Users user = userDAO.findByEmail(email);
                    if (user == null) {
                        System.out.println("User not found. Try again.");
                        continue;
                    }

                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    if (user.getPassword().equals(password)) {
                        System.out.println("Login successful! Welcome, " + user.getName() + "!");
                        loggedInUser = user;
                    } else {
                        System.out.println("Invalid password. Try again.");
                    }

                } else if (choice == 3) {
                    // жанры
                    displayGenres(genreDAO);

                } else if (choice == 4) {
                    // поиск
                    searchGames(gameDAO, scanner);

                }else if (choice == 5) {
                    System.out.println("Goodbye!");
                    break;

                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            } else {
                if (choice == 1) {
                    // жанр
                    displayGenres(genreDAO);

                } else if (choice == 2) {
                    // выход
                    System.out.println("Goodbye, " + loggedInUser.getName() + "!");
                    loggedInUser = null;

                } else if (choice == 3) {
                    // поиск
                    searchGames(gameDAO, scanner);

                } else if (choice == 4) {
                    System.out.print("Enter game ID to add to cart: ");
                    Long gameId = scanner.nextLong();
                    scanner.nextLine();

                    if (gameDAO.read(gameId) != null) {
                        Cart newCart = new Cart();
                        newCart.setUser(loggedInUser);
                        newCart.setGames(gameDAO.read(gameId));
                        newCart.setQuantity(1);

                        cartDAO.create(newCart);
                        System.out.println("Game added to cart!");
                    } else {
                        System.out.println("Game not found!");
                    }
                } else if (choice == 5) {
                    List<Cart> cartItems = cartDAO.findByUserId(loggedInUser.getId());
                    System.out.println("\nYour cart:");
                    for (Cart item : cartItems) {
                        System.out.println("- " + item.getGames().getTitle() + " | Price: " + item.getGames().getPrice() + " ТГ");
                    }
                }else if (choice == 6) {
                    System.out.println("Goodbye!");
                    break;

                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }

    private static void displayGenres(GenreDAO genreDAO) {
        System.out.println("\nAvailable genres:");
        List<Genres> genres = genreDAO.readAll();
        if (genres.isEmpty()) {
            System.out.println("No genres available.");
            return;
        }

        for (Genres genre : genres) {
            System.out.println("ID: " + genre.getId() + " | Name: " + genre.getName());
        }
    }

    private static void searchGames(GameDAO gameDAO, Scanner scanner) {
        System.out.print("\nEnter a keyword to search for a game: ");
        String keyword = scanner.nextLine();

        List<Games> searchResults = gameDAO.searchGamesByTitle(keyword);
        if (searchResults.isEmpty()) {
            System.out.println("No games found.");
        } else {
            System.out.println("\nSearch results:");
            for (Games games : searchResults) {
                System.out.println("ID: " + games.getId() + " | Title: " + games.getTitle() + " | Price: " + games.getPrice() + " ТГ");
            }
        }
    }
}
