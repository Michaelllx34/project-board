package com.koreait.projectboard.repository;

import com.koreait.projectboard.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource     // 우리가설정한 Rest 기능을 기본으로 부여하게 됨  //이 Entity에 대해 기본적인 api를 만들어주게 됨    //   .yaml의 data.rest:base-path: /apidetection-strategy: annotated     // halexplorer(curd 자동으로 만들어주는)
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {     // <entity의 클래스명, id의 자료형>
}
