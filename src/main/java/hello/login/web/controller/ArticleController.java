package hello.login.web.controller;


import hello.login.entity.Article;
import hello.login.entity.RoleType;
import hello.login.repository.ArticleRepository;
import hello.login.service.ArticleService;
import hello.login.service.CommentService;
import hello.login.service.MemberService;
import hello.login.web.dto.ArticleForm;
import hello.login.web.dto.CommentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private MemberService memberService;

    @PostMapping("/articles/new")
    public String newArticleForm(RoleType roleType,Model model){
        model.addAttribute("roleType",roleType);
        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(RoleType roleType,ArticleForm form
                                ,RedirectAttributes rttr) {
        //System.out.println(form.toString());
        log.info(form.toString());

        Article saved = articleService.save(form);
        log.info(saved.toString());
      //  System.out.println("saved.toString() = " + saved.toString());
        rttr.addAttribute("roleType",roleType);
        log.info("roleType-create = "+rttr.getAttribute("roleType"));
        return "redirect:/articles/"+saved.getId();

    }


    @RequestMapping("/articles/{id}") // 해당 URL요청을 처리 선언
    public String show(@PathVariable Long id, RoleType roleType,
                        Model model) { // URL에서 id를 변수로 가져옴
        log.info("id = " + id);
        List<CommentDto> commentDtos = commentService.comments(id);
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article",articleEntity);
        model.addAttribute("commentDtos",commentDtos);
        model.addAttribute("roleType",roleType);

        return "/articles/view";
    }

    @PostMapping("/articles")
    public String index(RoleType roleType,Model model){
        RoleType rt = RoleType.MEMBER;
        log.info("roleType = " + roleType);
        List<Article> articleEntityList = articleRepository.findAll();
        model.addAttribute("articleList",articleEntityList);
        model.addAttribute("roleType",roleType);
        model.addAttribute("rt",rt);
        return "/articles/index";
    }

    @PostMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, RoleType roleType,Model model){
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        model.addAttribute("roleType",roleType);
        return"articles/edit";
    }
    @PostMapping("/articles/update")
    public String update(RoleType roleType, ArticleForm form,RedirectAttributes rttr){
        log.info(form.toString());
        Article article =articleService.update(form);
        rttr.addAttribute("roleType",roleType);
        return "redirect:/articles/"+ article.getId();
    }
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제요청이 들어왔습니다");
        articleService.delete(id);
        rttr.addFlashAttribute("msg","삭제가 완료되었습니다!");

        return "redirect:/articles";
    }

}
