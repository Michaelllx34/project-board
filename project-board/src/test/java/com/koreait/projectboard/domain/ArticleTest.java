package com.koreait.projectboard.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;  // servlet이라고 되어있는 import 사용해야 함
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Data REST - API 테스트")       // 이름을 이렇게 만듦
@AutoConfigureMockMvc                       // ??
@Transactional                              // 실제로 운영하는 서버에서, 운영되고 있는 transaction과 test transaction를 분리해서 실행해줌 -> DB의 부하 감소     // 데이터 실행되고 롤백시키는 기능이 있음(DB에 적용 안되므로 테스트에 적합)
@SpringBootTest                             // 이걸 안 적어줄거면 ProjectBoardApplicatonTests를 상속받아서 해야함
class ArticleTest {

    private final MockMvc mvc;

    public ArticleTest(@Autowired MockMvc mvc){     // import~.AutoConfigureMockMvc가 여기 매개변수 통해서  껍데기에다가 mvc를 주입킨다. mvc 빈껍데기가 가리키게 만들어 놓음(오류 안남. 오류 안나게?)
        this.mvc = mvc;
    }

    @DisplayName("[API] 게시글 리스트 조회")
    @Test
    void read() throws Exception {
        mvc.perform(get("/api/articles"))       // get("/api/articles")여기에서 바로 import하지 말고 단축키 있음.          // import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; 가 import되어야함(주의)       // isOk() 이거 함수임
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));     // .contentType("application.json"): 컨텐트 타입 비교할 때 원래는 우리가 직접 할 땐 통과가 되는데 hallex 통할 땐 이게 통과 안됨. 왜냐하면 content-type에 contenttype/hal이 붙어서 그냐은 안됨.
    }

    @DisplayName("[API] 게시글 단건 조회")
    @Test     // /api/articles/1 urL은 이런식으로
    void detail() throws Exception {
        mvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk())                     // 접근이 되는지, OK가 떨어지는지 확인
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @Disabled("Spring Data REST 테스트는 불필요하므로 제외시킴")      // 실무에서는 괜히 DB 뒤지면 별로 안 좋음
    @DisplayName("[API] 게시글 -> 댓글 리스트 조회")      // 단일 게시글의 댓글
    @Test     // /api/articles/1/articleComments urL은 이런식으로
    void detailComments() throws Exception {
        mvc.perform(get("/api/articles/1/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

//     // 404 오류 나오는건 데이터가 없기 때문(1번이라는 녀석이 없어서.)
//    @DisplayName("[API] 댓글 단건 조회")      // 단일 댓글
//    @Test     // /api/articles/1/articleComments urL은 이런식으로
//    void detailComment() throws Exception {
//        mvc.perform(get("/api/articles/1/articleComments"))       // get("/api/articles")여기에서 바로 import하지 말고 단축키 있음.          // import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; 가 import되어야함(주의)       // isOk() 이거 함수임
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
//    }
}