package hello.login.entity;


import hello.login.web.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
        name="BOARD_SEQ_GENERATOR",
        sequenceName="COMMENT_SEQ",
        initialValue=1,
        allocationSize = 1
)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BOARD_SEQ_GENERATOR")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
    @Column
    private String name;
    @Column
    private String body;
    @Enumerated
    private RoleType roleType;

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외 발생
        if (dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        if (dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");
        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getName(),
                dto.getBody(),
                dto.getRoleType()
        );
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if (this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");
        // 객체를 갱신
        if (dto.getName() != null)
            this.name = dto.getName();
        if (dto.getBody() != null)
            this.body = dto.getBody();
    }
}