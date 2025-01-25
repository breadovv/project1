@Entity
@Table(name = "\"Games\"")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "genre_id")
    private Genre genre;

    private String title;

    private double price;

    @Column(name = "release_date")
    private LocalDate releaseDate;

}
