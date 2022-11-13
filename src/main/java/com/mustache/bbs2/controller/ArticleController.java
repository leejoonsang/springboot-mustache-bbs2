package com.mustache.bbs2.controller;

import com.mustache.bbs2.domain.dto.ArticleDto;
import com.mustache.bbs2.domain.entity.Article;
import com.mustache.bbs2.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {
    // Spring이 ArticleRepository 구현체를 DI(인터페이스를 DI X)
    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping(value = "/new")
    public String newArticleForm() {
        return "new";
    }

//    @GetMapping(value = "/list")
//    public String getAll(Model model) {
//        List<Article> articleEntityList = articleRepository.findAll();
//        model.addAttribute("articleList", articleEntityList);
//
//        return "list";
//    }

    @GetMapping("")
    public String getAll(Model model) {
        List<Article> articleEntityList = articleRepository.findAll();

        model.addAttribute("articleList", articleEntityList);

        return "list";
    }

    @GetMapping(value = "/{id}")
    public String selectSingle(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);
        if(!optArticle.isEmpty()){
            // Optional.get --> Article
            model.addAttribute("article", optArticle.get());
            return "show";
        }else{
            return "error";
        }
    }

    @GetMapping(value = "/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if(!optionalArticle.isEmpty()){
            // Optional.get --> Article
            model.addAttribute("article", optionalArticle.get());
            return "edit";
        }else{
            model.addAttribute("message", String.format("Id %d이(가) 없습니다.", id));
            return "error";
        }
    }

    @GetMapping(value = "/{id}/delete")
    public String delete(@PathVariable Long id) {
        articleRepository.deleteById(id);
        return "redirect:/articles";
    }

    @PostMapping(value = "/posts")
    public String createArticle(ArticleDto form) {
        log.info(form.toString());
        Article article = form.toEntity();
        articleRepository.save(article); // 생성한 entity를 repository에 저장
        return String.format("redirect:/articles/%d", article.getId());
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, ArticleDto form, Model model) {
        log.info("title:{} content:{}", form.getTitle(), form.getContent());
        Article article = articleRepository.save(form.toEntity());
        model.addAttribute("article", article);
        return String.format("redirect:/articles/%d", form.getId());
    }

}
