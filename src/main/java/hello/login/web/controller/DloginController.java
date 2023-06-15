package hello.login.web.controller;

import hello.login.entity.Dmember;
import hello.login.service.DLoginService;
import hello.login.web.SessionConst;
import hello.login.web.dto.DloginForm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DloginController {

    private final DLoginService loginService;



    @GetMapping("/dlogin")
    public String loginForm(@ModelAttribute("dloginForm") DloginForm form) {
        return "login/login";
    }
    @PostMapping("/dlogin")
    public String loginV4(@Valid @ModelAttribute DloginForm dloginForm, BindingResult bindingResult,
                          @RequestParam(defaultValue = "/d") String redirectURL,
                          HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "login/login";
        }

        Dmember loginMember = loginService.login(dloginForm.getDLoginId(), dloginForm.getDPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/message";
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:" + redirectURL;

    }


//    @PostMapping("/logout")
//    public String logoutV3(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//        return "redirect:/";
//    }
//
//    private void expireCookie(HttpServletResponse response, String cookieName) {
//        Cookie cookie = new Cookie(cookieName, null);
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);
//    }
}
