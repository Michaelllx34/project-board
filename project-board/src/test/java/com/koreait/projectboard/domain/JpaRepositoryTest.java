package com.koreait.projectboard.domain;

import com.koreait.projectboard.config.JpaConfig;
import com.koreait.projectboard.repository.ArticleCommentRepository;
import com.koreait.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;  // assertThat import 후 hasSize 사용하려면 이걸로 import
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;

@Import(JpaConfig.class)    // main에 있는 내용을 여기에 적용시키려면 import해서 써야하는데 어노테이션을 선언해야함?
@DisplayName("JPA 연결 테스트")      // 왼쪽 아래 TEst란?에[ 이름 표시 가능
@DataJpaTest        // Jpa를 활용할 수 있는 Test 가능
class JpaRepositoryTest {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ){
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void select(){      // NotNull이고 1000개의 데이터면 정상적으로 출력된다하고 오류 안남(결과는 안나옴)    // 만약 999로 적어놓고 데이터 1000개 넣으면 오류
        List<Article> articles = articleRepository.findAll();
        assertThat(articles).isNotNull().hasSize(1000);        // 단위테스트 할 때 쓰는 것       //  0이 아니길.     // .hasSize(0): 데이터가 0개일 때 실행
    }

    @DisplayName("insert 테스트")
    @Test
    void insert(){
        long prevCount = articleRepository.count(); // 현재 데이터가 몇개인지 알 수 있
        Article saveArticle = articleRepository.save(Article.of("새로운 글", "새로운 글을 등록합니다!", "#new")); // static으로 만들어놨던 of함수
        assertThat(articleRepository.count()).isEqualTo(prevCount + 1); // 이러면 정상적인것 이전 데이터 개수보다 하나가 더 늘었다면 정상이니까 통과
    }

    @DisplayName("update 테스트")
    @Test
    void update(){
        Article article = articleRepository.findById(1L).orElseThrow();     // 존재하지 않으면 Throw
        String updateHashTag = "#Spring";
        article.setHashtag(updateHashTag);

        Article saveArticle = articleRepository.saveAndFlush(article);     // 롤백되지 않도록 Flush를 하면
        assertThat(saveArticle).hasFieldOrPropertyWithValue("hashtag", updateHashTag);  // 필드에 프로퍼티에 이런값을 가지고 있느냐하는걸 물어보는 것    // 1L에서 hashtag가 updatehashtag로 적용되었는지
    }

    @DisplayName("delete 테스트")
    @Test
    void delete(){
        Article article = articleRepository.findById(1L).orElseThrow();     // 존재하지 않으면 Throw
        long preArticleCount = articleRepository.count();                   // 이전 카운트를 센다. 지워졌는ㄴ지 안지워졌는지 확인
        long preArticleCommentCount = articleCommentRepository.count();     // 댓글 개수 카운트
        int deletedCommentsSize = article.getArticleComments().size();  // 사이즈가 몇이니? 몇개 들어가 있어?     // 해당 리스트의 사이즈를 댓글이 몇개 달렸는지를 확인

        articleRepository.delete(article);      // 원본 글 아티클 제거

        assertThat(articleRepository.count()).isEqualTo(preArticleCount - 1);   // -1한거랑 같은지 비교     // 원본 글이 지워졌으머ㅡ로 1 빼고
        assertThat(articleCommentRepository.count()).isEqualTo(preArticleCommentCount - deletedCommentsSize);     // 그에 따른 댓글의 개수도 줄었을 거니까 뺀 개수만큼 같은지 확인
    }
}