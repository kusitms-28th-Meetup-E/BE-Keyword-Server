package gwangjang.server.domain.Issue.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_id")
    private Long id;
    private String issueTitle;

    private String imgUrl;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;


}
