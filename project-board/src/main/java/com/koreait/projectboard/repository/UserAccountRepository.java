package com.koreait.projectboard.repository;

import com.koreait.projectboard.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource     // 이전까지는 안 넣어줘도 됐지만.
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}
