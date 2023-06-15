package hello.login.web.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class DloginForm {

    @NotEmpty
    private String dLoginId;

    @NotEmpty
    private String dPassword;
}
