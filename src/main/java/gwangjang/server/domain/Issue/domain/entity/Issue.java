package gwangjang.server.domain.Issue.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private String issueDetail;
    private String imgUrl;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @OneToMany(fetch = FetchType.LAZY)
    List<Keyword> keywords = new ArrayList<>();

}
