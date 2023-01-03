package com.koreait.projectboard.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;// import static 스태틱으로 ㅁ나들어져있는 클래스인데 가져다 쓰고 싶다. 근데 이름이 겹치는게 많음. 근데 원래는 import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get() 이라고 적어야 되는데 간단하게 위에 올리고 밑에는 get만 적은 것.
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest(ArticleController.class)           // 웹에 관련 테스트이므로     // ArticleController 클래스와 연관지어서 해볼 것이다.
class ArticleControllerTest {
    private final MockMvc mvc;      // 여기까지만 하면 생성자든 뭐든 사용할 `~~~ 가 없기 때문에 에러뜸

//    public ArticleControllerTest(@Autowired MockMvc mvc){ // 요런 형태도 가능
    @Autowired      // 요즘 스프링 부트는 이게 자동으로 실행되기 때문에 실전에서는 잘 안씀. 안써줘도 됨. 근데 테스트 할 때는 써줘야 한다.      // 이런 형태는 밑에 적을 내용이 많을 때
    public ArticleControllerTest(MockMvc mvc){
        this.mvc = mvc;
    }

//    @Disabled("구현중")      // 사용하기 위해서는 이거 없애줘야함
    @Disabled("완료")         // 테스트 완료 후
//    @DisplayName("[view][GET] 게시글 리스트 게시판 페이지")
    @DisplayName("[view][GET] 게시글 리스트 게시판 페이지 - 정상")         // 테스트 완료 후
    @Test
    public void read() throws Exception {     // 예외처리를 해야 한다.
        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.TEXT_HTML))      // 오류나서 바꿔줄거야(Content type expected:<text/html> but was:<text/html;charset=UTF-8> Expected :text/html// Actual   :text/html;charset=UTF-8)
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("articles"));     // 속성이 존재하냐는 것 articles라는   // 이 html문서가 화면에 보여질 것인데 ㅁㄱ샤칟ㄴfksms epdlxjfmf qkedktj 할거냐       // ~ 하면 에러남
    }

    @Disabled("구현중")
    @DisplayName("[view][GET] 게시글 상세 페이지")
    @Test
    public void detail() throws Exception {
        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("article")); // 단건이니까 article
    }

    @Disabled("구현중")
    @DisplayName("[view][GET] 게시글 검색 전용 페이지")
    @Test
    public void search() throws Exception {
        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML));
//                .andExpect(model().attributeExists("articles"));      // 이유?
    }

    @Disabled("구현중")
    @DisplayName("[view][GET] 게시글 해시태그 검색 페이지")
    @Test
    public void hashtag() throws Exception {     // 예외처리를 해야 한다.
        mvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML));
//                .andExpect(model().attributeExists("articles"));
    }


}