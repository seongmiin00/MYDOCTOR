package hello.login.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
@Slf4j
public class SignUpController {
    @RequestMapping(value = "/select")
    public String selSignup() {
        return "members/selSignup";
    }
}
