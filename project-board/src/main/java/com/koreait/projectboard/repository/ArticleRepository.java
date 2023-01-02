package com.koreait.projectboard.repository;

import com.koreait.projectboard.domain.Article;
import com.koreait.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.time.DateTimeException;


@RepositoryRestResource     // 우리가 설정한 Rest 기능을 기본으로 부여하게 됨  //이 Entity에 대해 기본적인 api를 만들어주게 됨    // .yaml의 halexplorer(curd 자동으로 만들어주는)
public interface ArticleRepository extends
    JpaRepository<Article, Long>,
    QuerydslPredicateExecutor<Article>,
    QuerydslBinderCustomizer<QArticle> {     // Article: dsl 쿼리 dsl 적용하고자 하는 entity이름
                // 기본적으로 적용되지 않고, qu dsl도 기본 기능있고 수정기능이 있는데 수정할 때 qclass 이름을 써주게 되어있다.

    // like검색이 되도록 만들어 주기(createdAt 제외)
    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {              // default: 인터페이스에서 추상메소드 구현 가능(1.8이상부터 적용됨)
        bindings.excludeUnlistedProperties(true);                                   // 모든 where절 검색이 되는걸 일단 막아줌.
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);     // 얘네만 검색되도록 하겠다.
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);      // 지금 여러개로 검색을 시도할 수는 있는데, 한번만 검색되도록 만듦. StringExpression::containsIgnoreCase 라고 해주면 like 검색이 됨. 참고로 ::equal은 완벽하게 일치할 경우 검색되도록 하는것.
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);                // 날짜는 like검색이 안된다. 나중에 범위값으로 검색할 것임. DateTimeExpression로 바꿔줘라
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);

        // customize() 부분 추가되었을 때 -> ?content=tiny로도 검색이 가능하다.(원래는 full name이 아니라 안되었음)
    }

}

//public interface ArticleRepository extends
//        JpaRepository<Article, Long>,
//        QuerydslPredicateExecutor<Article> {
//         // 이렇게 했을 때 knolux로 검색이 된다.
//         // but Zaam을 검색하면 안나오는데, Zaam-Dox 로 검색하면 나온다. 완벽하게 같을 때만 검색됨(like검색 아님)
//         // createdAt=2021-12-06T00:0 -> 날짜나 다른 content 등으로 검색해도 나온다.
//}

