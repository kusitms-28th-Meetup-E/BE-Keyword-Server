package gwangjang.server.domain.morpheme.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import gwangjang.server.domain.morpheme.domain.entity.Morpheme;
import gwangjang.server.domain.morpheme.domain.entity.QMorpheme;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MorphemeRepositoryImpl extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public MorphemeRepositoryImpl (JPAQueryFactory jpaQueryFactory){
        super(Morpheme.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<String> findTop5KeywordsExcluding(String excludedKeyword) {
        return jpaQueryFactory
                .select(QMorpheme.morpheme.word)
                .from(QMorpheme.morpheme)
                .where(QMorpheme.morpheme.word.ne(excludedKeyword))
                .orderBy(QMorpheme.morpheme.count().desc())
                .limit(5)
                .fetch();
    }

}
