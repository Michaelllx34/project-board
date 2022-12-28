package com.koreait.projectboard.repository;

import com.koreait.projectboard.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {     // <entity의 클래스명, id의 자료형>
}
