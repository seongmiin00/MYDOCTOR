package hello.login.repository;


import hello.login.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {
    private final EntityManager em; //최종적으로 이렇게 간단하게 생략가능
    public Article save(Article article){
        if (article.getId()==null){
            em.persist(article);
        }else{
            em.merge(article);
        }
        return article;
    }
    public Article findOne(Long id){ //단건 조회
        return em.find(Article.class, id);
    }

    public List<Article> findAll(){ // 모두 조회
        return em.createQuery("select a from Article a", Article.class).getResultList(); //Ctrl+Alt+N 지역변수 인라인화
    }
//    public List<Article> findByName(String name){ //특정 이름 조회
//        return em.createQuery("select a from Article a where a.name = :name", Article.class).setParameter("name",name).getResultList();
//    }

    public Optional<Article> findById(Long ArticleId) {
        return findAll().stream()
                .filter(a -> a.getId().equals(ArticleId))
                .findFirst();
    }

    public void delete(Article article) {
        em.remove(article);
    }
}