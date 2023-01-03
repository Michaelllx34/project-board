package com.koreait.projectboard.controller;

import lombok.Getter;
import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("articles")
@Controller     // 얘는 controller 역할을 할거야
public class ArticleController {    // thymeleaf를 쓰기 위해 HTML을 실행하기 위해? controller 만들자

    @GetMapping
    public String articles(ModelMap map) {        // 서블릿 형식으로 했을땐 모델앤뷰 썼음 // 이건 스프링 전용 클래스    // 페이지 호출하게 되면 map으로 들어오게 되고 해당 모델이 map으로 작동하게 됨
        map. addAttribute("articles", List.of());       // 해당 페이지에 ar 전달해주려 한다. 그 값은 List로 전달.
        return "articles/index";        // articles에 index를 전달하겠다. 그러면 index.html을 이제 만들면 되겠찌?
    }

    @GetMapping("/{articleId}")     // {}
    public String article(@PathVariable Long articleId, ModelMap map) {
        map.addAttribute("article", "article");
        map.addAttribute("articleComments", List.of());
        return "articles/detail";
    }
}
