import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {


    public void registerUser(User user) {
        String sql = "INSERT INTO User (Name, Phone, Email) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPhone());
            stmt.setString(3, user.getEmail());
            stmt.executeUpdate();

            System.out.println("User registered successfully!");

        } catch (SQLException e) {
            System.err.println("Error registering user: " + e.getMessage());
        }
    }


    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM User WHERE Email = ?";
        User user = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("User_ID");
                String name = rs.getString("Name");
                String phone = rs.getString("Phone");

                user = new User(userId, name, phone, email);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching user: " + e.getMessage());
        }

        return user;
    }


    public void getAllUsers() {
        String sql = "SELECT * FROM User";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String name = rs.getString("Name");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");

                System.out.println("ID: " + userId + ", Name: " + name + ", Phone: " + phone + ", Email: " + email);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching all users: " + e.getMessage());
        }
    }
}
