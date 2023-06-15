package hello.login.service;


import hello.login.entity.Article;
import hello.login.repository.ArticleRepository;
import hello.login.web.dto.ArticleForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;


    @Transactional
    public Article save(ArticleForm form) {
        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println("article.toString() = " + article.toString());
        return articleRepository.save(article);
    }
    @Transactional
    public Article update(ArticleForm form) {
        Article articleEntity = form.toEntity();
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if (target!=null) {
            articleRepository.save(articleEntity);
        }
        return articleEntity;
    }
    @Transactional
    public void delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        if (target !=null){
            articleRepository.delete(target);
        }
    }
}
