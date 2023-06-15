package hello.login.service;


import hello.login.entity.Dmember;
import hello.login.repository.DmemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //기본값은 false jpa는 하나의 트랙잭션에서 구동함 조회만하는 기능이라면 트루로해주는게 최적화에 좋음
@RequiredArgsConstructor //lombok이 제공 final이있는 필드만 생성자를 만들어줌
public class DmemberService {

    private final DmemberRepositoryV2 dmemberRepository; //한번만 생성하고난뒤 값을 바꿀이유가 없으므로 setter대신 생성자로만들고 final로 지정
    @Transactional //따로 @Transactional 설정해두는게 클래스로 @Transactional 설정하는것보다 우선순위가 높고 읽기전용이 아니기에 따로 설정함
    public Long join(Dmember dmember){
        validateDuplicateMember(dmember); //중복회원 검증 메서드
        dmemberRepository.save(dmember);
        return dmember.getId();
    }

    private void validateDuplicateMember(Dmember dmember) {
        List<Dmember> findMembers = dmemberRepository.findByName(dmember.getDName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

}
