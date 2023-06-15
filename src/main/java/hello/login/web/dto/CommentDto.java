package hello.login.web.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import hello.login.entity.Comment;
import hello.login.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;
    @JsonProperty("article_id") //
    private Long articleId;
    private String name;
    private String body;
    private RoleType roleType;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getName(),
                comment.getBody(),
                comment.getRoleType()
        );
    }
}