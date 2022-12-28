package com.koreait.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing      //
@Configuration          // Aduiting에 대한 세팅을 여기서 하겠다
public class JpaConfig {
    @Bean                // 사용자를 세팅하는 과정     // 빈: 데이터를 넣어주는 것
    public AuditorAware<String> auditorAware() {    // 리턴형은 자유이나, 내가 데이터를 넣을 것이 string이라서. ex) 관리자     // 메소드이름은 정해져 있다. auditorAware로.
        return () -> Optional.of("관리자");    // 나중에는 ""안에 로그인 세션 객체를 넣어주면 됨. (스프링 시큐리티로 인증 기능을 붙이게 될 때 수정할 예정)
    }
}
