package com.koreait.projectboard.repository;

import com.koreait.projectboard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {     // <entity의 클래스명, id의 자료형>
}
