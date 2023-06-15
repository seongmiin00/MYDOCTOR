package hello.login.repository;


import hello.login.entity.Member;
import hello.login.entity.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryV2 {
//    @PersistenceContext //엔티티매니저 팩토리도 만들고 엔티티매니저 만드는 과정을 생략하고 엔티티매니저를 알아서 주입해줌
//    private EntityManager em;
//    @Autowired 스프링부트에있는 스프링jpa 라이브러리를 사용하면 @PersistenceContext를 @Autowired로 바꿀수있고 생성자 주입이 가능함 단 Auto
//    @Autowired로 바꿨다면 스프링에서는 생략이 가능하며 @RequiredArgsConstructor 롬북 라이브러리를 필드에 final넣어주고 이용해 생성자 생략가능
    private final EntityManager em; //최종적으로 이렇게 간단하게 생략가능

    public void save(Member member){
        em.persist(member);
    }
    public Member findOneRole(RoleType r){ //단건 조회
        return em.find(Member.class, r);
    }

    public List<Member> findAll(){ // 모두 조회
        return em.createQuery("select m from Member m", Member.class).getResultList(); //Ctrl+Alt+N 지역변수 인라인화
    }
    public List<Member> findByName(String name){ //특정 이름 조회
        return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name",name).getResultList();
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }
}