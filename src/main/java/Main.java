

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
                System.out.println("3. Exit");
            } else {
                System.out.println("1. Browse games by genre");
                System.out.println("2. Cart");
                System.out.println("3. Logout");
                System.out.println("4. Exit");
            }
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (loggedInUser == null) {
                // не вошел в систему
                if (choice == 1) {
                    // Регистрация пользователя
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

                    // Автоматический вход
                    loggedInUser = newUser;
                    System.out.println("Welcome, " + loggedInUser.getName() + "! You are now logged in.");

                } else if (choice == 2) {
                    // Вход в аккаунт
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
                    System.out.println("Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid choice. Try again.");
                }

            } else {
                // уже вошел в систему
                if (choice == 1) {
                    // Вывод всех жанров
                    List<Genre> genres = genreDAO.readAll();
                    if (genres.isEmpty()) {
                        System.out.println("No genres available.");
                        continue;
                    }

                    System.out.println("\nAvailable genres:");
                    for (Genre genre : genres) {
                        System.out.println("ID: " + genre.getId() + " | Name: " + genre.getName());
                    }

                    // Выбор жанра
                    System.out.print("\nEnter the ID of the genre you want to explore: ");
                    Long genreId = scanner.nextLong();
                    scanner.nextLine();

                    Genre selectedGenre = genreDAO.read(genreId);
                    if (selectedGenre == null) {
                        System.out.println("Invalid genre ID. Try again.");
                        continue;
                    }

                    // Выбор игр внутри жанра
                    List<Game> games = gameDAO.findGamesByGenre(genreId);
                    if (games.isEmpty()) {
                        System.out.println("No games available in this genre.");
                        continue;
                    }

                    System.out.println("\nAvailable games in " + selectedGenre.getName() + ":");
                    for (Game game : games) {
                        System.out.println("ID: " + game.getId() + " | Title: " + game.getTitle() + " | Price: $" + game.getPrice());
                    }

                    // Выбор игры
                    System.out.print("\nEnter the ID of the game you want to choose: ");
                    Long gameId = scanner.nextLong();
                    scanner.nextLine();

                    Game selectedGame = gameDAO.read(gameId);
                    if (selectedGame != null) {
                        System.out.println("You have selected: " + selectedGame.getTitle() + " for $" + selectedGame.getPrice());
                    } else {
                        System.out.println("Invalid game ID. Try again.");
                    }

                } else if (choice == 2) {
                    // Выход из аккаунта
                    System.out.println("Goodbye, " + loggedInUser.getName() + "!");
                    loggedInUser = null;

                } else if (choice == 3) {
                    System.out.println("Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }
}
