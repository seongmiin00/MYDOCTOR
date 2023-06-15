package hello.login.web.dto;


import hello.login.entity.Article;
import hello.login.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {

    private Long id;
    private String title;
    private String content;
    private RoleType roleType;

    public Article toEntity() {
        return new Article(id, title, content, roleType);
    }
}
