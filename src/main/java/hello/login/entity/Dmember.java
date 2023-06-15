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
        sequenceName="DMEMBER_SEQ",
        initialValue=1,
        allocationSize = 1
)
public class Dmember {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BOARD_SEQ_GENERATOR")
    @Column(name = "Dmember_id")
    private Long id;

    @NotEmpty
    private String dLoginId; //로그인 ID

    @NotEmpty
    private String dName; //사용자 이름

    @NotEmpty
    private String addr;

    @NotEmpty
    private String dPassword;

    @NotEmpty
    private String dPhone;

    @NotEmpty
    private String info;

    @NotEmpty
    private String sub;
    @Enumerated(EnumType.STRING)
    private RoleType roleType = RoleType.DOCTOR;
}

