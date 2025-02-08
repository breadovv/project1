import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDAO {


    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM Games";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int gameId = rs.getInt("Game_id");
                int genreId = rs.getInt("Genre_id");
                String title = rs.getString("Title");
                double price = rs.getDouble("price");
                String releaseDate = rs.getString("release_date");

                Game game = new Game(gameId, genreId, title, price, releaseDate);
                games.add(game);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching games: " + e.getMessage());
        }

        return games;
    }

    public Game getGameById(int gameId) {
        String sql = "SELECT * FROM Games WHERE Game_id = ?";
        Game game = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, gameId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int genreId = rs.getInt("Genre_id");
                String title = rs.getString("Title");
                double price = rs.getDouble("price");
                String releaseDate = rs.getString("release_date");

                game = new Game(gameId, genreId, title, price, releaseDate);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching game: " + e.getMessage());
        }

        return game;
    }
}
