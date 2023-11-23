package gwangjang.server.domain.Issue.domain.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gwangjang.server.domain.Issue.application.dto.res.IssueDetailTopicRes;
import gwangjang.server.domain.Issue.application.dto.res.IssueRes;
import gwangjang.server.domain.Issue.application.dto.res.KeywordRes;
import gwangjang.server.domain.Issue.application.dto.res.TopicIssue;
import gwangjang.server.domain.Issue.domain.entity.Issue;
import gwangjang.server.domain.Issue.domain.entity.Keyword;
import gwangjang.server.domain.Issue.domain.entity.QIssue;
import gwangjang.server.domain.Issue.domain.entity.QTopic;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static gwangjang.server.domain.Issue.domain.entity.QIssue.issue;

import static gwangjang.server.domain.Issue.domain.entity.QTopic.topic;
import static gwangjang.server.domain.Issue.domain.entity.QKeyword.keyword1;

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
            Tuple topicTuple = jpaQueryFactory
                    .select(topic.topicTitle,
                            topic.id
                            )
                    .from(issue)
                    .leftJoin(issue.topic, topic)
                    .where(issue.id.eq(issueId))
                    .fetchOne();

            return Optional.of(new IssueRes(issueTitle, topicTuple.get(topic.topicTitle), imgUrl, issueId , topicTuple.get(topic.id)));
        }

        return Optional.empty();
    }
    public Optional<KeywordRes> findKeywordsByIssueId(Long issueId) {
        KeywordRes keywordRes = jpaQueryFactory
                .select(
                        Projections.constructor(
                                KeywordRes.class,
                                issue.id,
                                issue.issueTitle,
                                issue.imgUrl,
                                topic.id,
                                topic.topicTitle,
                                keyword1.id,
                                keyword1.keyword
                        )
                )
                .from(issue)
                .leftJoin(issue.topic, topic)
                .leftJoin(keyword1).on(issue.eq(keyword1.issue))
                .where(issue.id.eq(issueId))
                .fetchOne();

        return Optional.ofNullable(keywordRes);
    }
    public List<IssueDetailTopicRes> getAllIssueDetailTopicRes() {
        return jpaQueryFactory
                .select(
                        issue.id,
                        issue.issueTitle,
                        issue.issueDetail,
                        issue.imgUrl,
                        topic.id,
                        topic.topicTitle
                )
                .from(issue)
                .leftJoin(issue.topic, topic)
                .fetch()
                .stream()
                .map(tuple -> new IssueDetailTopicRes(
                        tuple.get(issue.id),
                        tuple.get(issue.issueTitle),
                        tuple.get(issue.issueDetail),
                        tuple.get(issue.imgUrl),
                        tuple.get(topic.id),
                        tuple.get(topic.topicTitle)
                ))
                .collect(Collectors.toList());
    }
    public List<Issue> search(String keyword) {
        QIssue issue = QIssue.issue;

        return jpaQueryFactory
                .selectFrom(issue)
                .leftJoin(issue.keywords).fetchJoin()
                .where(
                        containsIgnoreCase(issue.issueTitle, keyword)
                                .or(containsIgnoreCase(issue.issueDetail, keyword))
                                .or(containsIgnoreCase(issue.topic.topicTitle, keyword))
                )
                .fetch();
    }

    private BooleanExpression containsIgnoreCase(StringPath path, String keyword) {
        return path.lower().contains(keyword.toLowerCase());
    }
    public List<TopicIssue> findRandomIssues() {
        NumberTemplate<Double> random = Expressions.numberTemplate(Double.class, "rand()");
        QIssue issue = QIssue.issue;
        QTopic topic = QTopic.topic;
        List<Tuple> result = jpaQueryFactory
                .select(issue.issueTitle, topic.topicTitle)
                .from(issue)
                .join(topic).on(issue.topic.id.eq(topic.id))
                .orderBy(random.asc())
                .limit(3)
                .fetch();

// Assuming you have a class to hold the result, let's call it RandomIssueResult
        List<TopicIssue> resultList = result.stream()
                .map(tuple -> new TopicIssue(tuple.get(issue.issueTitle), tuple.get(topic.topicTitle)))
                .collect(Collectors.toList());

        return resultList;
    }
    //분석된 결과 다ㅓㅅ개
}
