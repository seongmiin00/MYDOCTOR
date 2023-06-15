package hello.login.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@SequenceGenerator(
        name="BOARD_SEQ_GENERATOR",
        sequenceName="ARTICLE_SEQ",
        initialValue=1,
        allocationSize = 1
)
public class Article {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BOARD_SEQ_GENERATOR")
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

//    public Article(Long id, String title, String content, Member member) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//        this.member = member.getRoleType();
//    }


    public void patch(Article article) {
        if(article.title != null)
            this.title= article.title;
        if(article.content != null)
            this.content= article.content;
    }
}
