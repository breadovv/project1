import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDAO {


    public void addGameToCart(int userId, int gameId) {
        String sql = "INSERT INTO Cart (User_id, Game_id, Purchase_date, Amount_of_games, Total_price, Status) " +
                     "VALUES (?, ?, CURRENT_DATE, 1, " +
                     "(SELECT price FROM Games WHERE Game_id = ?), 0)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, gameId);
            stmt.setInt(3, gameId);
            stmt.executeUpdate();

            System.out.println("Game added to cart successfully!");

        } catch (SQLException e) {
            System.err.println("Error adding game to cart: " + e.getMessage());
        }
    }


    public void checkoutCart(int userId) {
        String sql = "UPDATE Cart SET Status = 1 WHERE User_id = ? AND Status = 0";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Checkout successful! Order placed.");
            } else {
                System.out.println("No items in the cart to checkout.");
            }

        } catch (SQLException e) {
            System.err.println("Error during checkout: " + e.getMessage());
        }
    }
}
