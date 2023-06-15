package hello.login.web.controller;

import hello.login.entity.Dmember;
import hello.login.entity.Member;

import hello.login.repository.MemberRepositoryV2;
import hello.login.web.SessionConst;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepositoryV2 memberRepository;



    @GetMapping("/")
    public String homeLoginV3Spring(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) //홈에서 session을 받아도 생성할 이유가 없으므로 false값
            Member loginMember, Model model) {

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "login"; //login.html로
        }

        //세션이 유지되면 맵으로 이동
        model.addAttribute("member", loginMember);
        return "map";
    }

    @GetMapping("/d")
    public String homeDLoginV3Spring(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Dmember loginMember, Model model) {

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "login";
        }

        //세션이 유지되면 맵으로 이동
        //세션이 유지되면 맵으로 이동
        model.addAttribute("doctor", loginMember);
        return "mypage/doctor";
    }
}