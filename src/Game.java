public class Game {
    private int gameId;
    private int genreId;
    private String title;
    private double price;
    private String releaseDate;


    public Game(int gameId, int genreId, String title, double price, String releaseDate) {
        this.gameId = gameId;
        this.genreId = genreId;
        this.title = title;
        this.price = price;
        this.releaseDate = releaseDate;
    }


    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
