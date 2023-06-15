package hello.login.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@SequenceGenerator(
        name="BOARD_SEQ_GENERATOR",
        sequenceName="MEMBER_SEQ",
        initialValue=1,
        allocationSize = 1
)
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BOARD_SEQ_GENERATOR")
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String loginId; //로그인 ID
    @NotEmpty
    private String name; //사용자 이름
    @NotEmpty
    private String password;
    @NotEmpty
    private String phone;

    @Enumerated(EnumType.STRING)
    private RoleType roleType = RoleType.MEMBER;
}
