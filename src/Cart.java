public class Cart {
    private int cartId;
    private int userId;
    private int gameId;
    private String purchaseDate;
    private int amountOfGames;
    private double totalPrice;
    private int status;


    public Cart(int cartId, int userId, int gameId, String purchaseDate, int amountOfGames, double totalPrice, int status) {
        this.cartId = cartId;
        this.userId = userId;
        this.gameId = gameId;
        this.purchaseDate = purchaseDate;
        this.amountOfGames = amountOfGames;
        this.totalPrice = totalPrice;
        this.status = status;
    }


    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getAmountOfGames() {
        return amountOfGames;
    }

    public void setAmountOfGames(int amountOfGames) {
        this.amountOfGames = amountOfGames;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
