package hello.login.service;

import hello.login.entity.Dmember;
import hello.login.repository.DmemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DLoginService {

    private final DmemberRepositoryV2 memberRepository;

    /**
     * @return null 로그인 실패
     */
    public Dmember login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getDPassword().equals(password))
                .orElse(null);
    }
}
