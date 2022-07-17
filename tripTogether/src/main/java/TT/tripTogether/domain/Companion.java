package TT.tripTogether.domain;

@Entity
@Getter
@NoArgsConstructor
@Table(name ="Companion")
public class Companion extends BaseEntity{

    @Id
    @GernatedValue(strategy = GernerationType.IDENTITY)
    @Column(name = "Companion_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Application(Long id, User user, Posting posting) {
        this.id = id;
        this.user = user;
        this.posting = posting;
    }
}