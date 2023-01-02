package com.koreait.projectboard.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("Spring Data REST 테스트는 불필요하므로 제외시킴")
@DisplayName("Data REST - API 테스트")       // 이름을 이렇게 만듦
@AutoConfigureMockMvc                       // ??
@Transactional
// 실제로 운영하는 서버가 있다면 운영되고 있는 tr이랑 test tr이랑 분리해서 실행해줌 -> DB의 부하가 적어짐     // 데이터 실행되고 롤백시킬 수 있는 기능이 있음(DB에 적용 안되므로 테스트에 적합)
@SpringBootTest                             // 이걸 안 적어줄거면 ProjectBoardApplicatonTests를 상속받아서 해야함
class UserAccountTest {

}