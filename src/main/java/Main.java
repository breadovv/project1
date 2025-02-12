import modules.dao.GameDAO;
import modules.dao.GenreDAO;
import modules.dao.UsersDAO;
import modules.enteties.Game;
import modules.enteties.Genre;
import modules.enteties.Users;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameDAO gameDAO = new GameDAO();
        GenreDAO genreDAO = new GenreDAO();
        UsersDAO userDAO = new UsersDAO();
        Scanner scanner = new Scanner(System.in);
        Users loggedInUser = null;

        while (true) {
            System.out.println("\n--- Game Shop ---");
            if (loggedInUser == null) {
                System.out.println("1. Register a new user");
                System.out.println("2. Login");
                System.out.println("3. Browse games by genre");
                System.out.println("4. Search for a game by title");
                System.out.println("5. Exit");
            } else {
                System.out.println("1. Browse games by genre");
                System.out.println("2. Logout");
                System.out.println("3. Search for a game by title");
                System.out.println("4. Exit");
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

                } else if (choice == 5) {
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
        List<Genre> genres = genreDAO.readAll();
        if (genres.isEmpty()) {
            System.out.println("No genres available.");
            return;
        }

        for (Genre genre : genres) {
            System.out.println("ID: " + genre.getId() + " | Name: " + genre.getName());
        }
    }

    private static void searchGames(GameDAO gameDAO, Scanner scanner) {
        System.out.print("\nEnter a keyword to search for a game: ");
        String keyword = scanner.nextLine();

        List<Game> searchResults = gameDAO.searchGamesByTitle(keyword);
        if (searchResults.isEmpty()) {
            System.out.println("No games found.");
        } else {
            System.out.println("\nSearch results:");
            for (Game game : searchResults) {
                System.out.println("ID: " + game.getId() + " | Title: " + game.getTitle() + " | Price: " + game.getPrice() + " ТГ");
            }
        }
    }
}
