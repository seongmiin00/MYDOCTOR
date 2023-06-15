package hello.login.web.controller;

import hello.login.entity.Dmember;
import hello.login.repository.DmemberRepositoryV2;
import hello.login.service.DmemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dmembers")
public class DmemberController {

    private final DmemberService  memberService;

    private final DmemberRepositoryV2 dmemberRepositoryV2;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("Dmember") Dmember Dmember) {

        return "members/doctorSignup";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute Dmember Dmember, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/doctorSignup";
        }

        memberService.join(Dmember);
        return "redirect:/";
    }

    @GetMapping("/info")
    public String info(Model model){
        List<Dmember> dmembers = dmemberRepositoryV2.findAll();
        model.addAttribute("dmembers",dmembers);
        return "info";
    }
}
