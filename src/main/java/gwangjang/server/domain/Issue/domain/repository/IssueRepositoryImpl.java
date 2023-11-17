package gwangjang.server.domain.Issue.domain.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;


import gwangjang.server.domain.Issue.application.dto.res.IssueRes;
import gwangjang.server.domain.Issue.domain.entity.Issue;
import gwangjang.server.domain.Issue.domain.entity.QIssue;

import gwangjang.server.domain.Issue.domain.entity.QTopic;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static gwangjang.server.domain.Issue.domain.entity.QIssue.issue;
import static gwangjang.server.domain.Issue.domain.entity.QTopic.topic;

@Repository
public class IssueRepositoryImpl extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public IssueRepositoryImpl (JPAQueryFactory jpaQueryFactory){
        super(Issue.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
    public Optional<IssueRes> findIssueAndTopicById(Long issueId) {
        // First query to get issueTitle and imgUrl
        Tuple issueTuple = jpaQueryFactory
                .select(
                        issue.issueTitle,
                        issue.imgUrl
                )
                .from(issue)
                .where(issue.id.eq(issueId))
                .fetchOne();

        if (issueTuple != null) {
            String issueTitle = issueTuple.get(issue.issueTitle);
            String imgUrl = issueTuple.get(issue.imgUrl);

            // Second query to get topicTitle based on topicId associated with the issue
            String topicTitle = jpaQueryFactory
                    .select(topic.topicTitle)
                    .from(issue)
                    .leftJoin(issue.topic, topic)
                    .where(issue.id.eq(issueId))
                    .fetchOne();

            return Optional.of(new IssueRes(issueTitle, topicTitle, imgUrl));
        }

        return Optional.empty();
    }
}
