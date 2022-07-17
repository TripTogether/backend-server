package TT.tripTogether.domain;

@Entity
@Getter
@NoArgsConstructor
public class Scrap extends BaseEntity{

    @Id
    @GernatedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Scrap_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Scrap(Long id, User user, Posting posting){
        this.id = id;
        this.user = user,
        this.posting = posting;
    }

}