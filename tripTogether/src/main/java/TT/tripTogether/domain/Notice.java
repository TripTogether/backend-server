package TT.tripTogether.domain;

@Entity
@Getter
@NoArgsConstructor
public class Notice extends BaseEntity{

    @Id
    @GernatedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Notice_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Post post;
    private Content content;

    public Application(Long id, User receiver, Content content) {
        this.id = id;
        this.user = user;
        this.content = content;
    }
}