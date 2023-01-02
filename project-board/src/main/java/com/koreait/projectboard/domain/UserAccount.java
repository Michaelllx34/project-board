package com.koreait.projectboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {      // 검색할 때의 속도 향상을 위해
        @Index(columnList = "userid"),
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class UserAccount extends AuditingFields{  // 회원관련 entity      // table을 user라고 하는 순간 에러가 엄청 나옴. 왜냐면, mysql자체 내에서 user 라는 system table이 있어서 꼬인다. 마찬가지로 account.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false, length = 50) private String userId;
    @Setter @Column(nullable = false) private String userPw;
    @Setter @Column(length = 100) private String email;
    @Setter @Column(length = 100) private String nickname;
    @Setter private String memo;


    protected UserAccount(){}

    public UserAccount(String userId, String userPw, String email, String nickname, String memo) {
        this.id = id;
        this.userId = userId;
        this.userPw = userPw;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;
    }

    public static UserAccount of(String userId, String userPw, String email, String nickname, String memo) {    // 외부에서 쓸 때는 이 함수를 통해 Article을 호출해서 사용
        return new UserAccount(userId, userPw, email, nickname, memo);
    }

    @Override
    public int hashCode() {     // id를 넣었을 때의 메모리 주소 얻기 위한 메소드     // 누구(id)의 해시코드? -> 메모리 주소를 뽑아 오는 것.
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {      // 완벽하게 같은 객체인지 확인하는 메소드
        if(this == obj) return true;                        // 값 비교
        if(!(obj instanceof UserAccount userAccount)) return false; // 객체 비교
        return id != null && id.equals(userAccount.id);         // 완벽히 같으면 true
    }
}
