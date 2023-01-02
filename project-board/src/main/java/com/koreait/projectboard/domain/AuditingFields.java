package com.koreait.projectboard.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)  // 여기 선언하면서 ArticleComment쪽에는 빼주기
@MappedSuperclass                               // 얘가 부모가 될거야
public class AuditingFields {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate @Column(nullable=false) private LocalDateTime createdAt;          // 생성일시 // @CreatedDate: 생성일시 자동으로 넣어줌
    @CreatedBy@Column(nullable=false, length=100) private String createdBy;        // 생성자   // @CreatedBy:
    @LastModifiedDate@Column(nullable=false) private LocalDateTime modifiedAt;     // 수정일시 // @LastModifiedDate: 작성된 날짜, 수정된 날짜에 모두 자동으로 넣어줌
    @LastModifiedBy@Column(nullable=false, length=100) private String modifiedBy;  // 수정자   // @LastModifiedBy:
}
