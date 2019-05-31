
package org.jinseon.article;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jinseon.book.chap11.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class ArticleController {
	
	@Autowired
	ArticleDao articleDao;
	
	static final Logger logger = LogManager.getLogger();
	
	/*
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	*/
	
	//글 목록
	@GetMapping("/articles")
	public String articles(
		@RequestParam(value = "page", defaultValue = "1") int page,
		Model model) {

	// 페이지 당 가져오는 행의 수
	final int COUNT = 100;
	// 시작점
	int offset = (page - 1) * COUNT;

	List<Article> articleList = articleDao.selectAll(offset, COUNT);

	int totalCount = articleDao.countAll();

	model.addAttribute("totalCount", totalCount);
	model.addAttribute("articles", articleList);
		return "articles";
	}
	//글 저장
	@PostMapping("/finish")
	public String editText(Article article,// HttpSession session,
			@SessionAttribute("MEMBER") Member member) {
		//Object memberObj = session.getAttribute("MEMBER");
		/*
		if (memberObj == null)
			return "redirect:/app/loginForm";
		
		Member member = (Member) memberObj; */
		article.setUserId(member.getMemberId());
		article.setName(member.getName());
		articleDao.insertArticle(article);
		return "finish";
	}
	//글 등록 화면
	@GetMapping("/edittext")
	public String edittext(HttpSession session) {
		/*
		Object memberObj = session.getAttribute("MEMBER");
		if (memberObj == null)
			return "redirect:/app/loginForm";
		*/
		return "edittext";
	}
	//글보기
	@GetMapping("/readtext")
	public void readtext(@RequestParam("articleId") String articleId,
			Model model
			) {
		Article article = articleDao.getArticle(articleId);
		model.addAttribute("article",article);
	}
	
	//글수정
	@PostMapping("/update")
	public String update(Article article,
			@RequestParam("articleId") String articleId,
			@SessionAttribute("MEMBER") Member member
			) {
		article.setArticleId(articleId);
		articleDao.updateArticle(article);
		return "redirect:/app/articles";
	}
	
	//글 수정 화면
	@GetMapping("/updateForm")
		public String updateForm(@RequestParam("articleId") String articleId,
				@SessionAttribute("MEMBER") Member member,
				Model model) {
			Article article = articleDao.getArticle(articleId);
			model.addAttribute("article",article);
			return "updateForm";
		}
	
	//글삭제
		@GetMapping("/deletearticle")
		public String deletearticle(
				@RequestParam("articleId") String articleId,
				@SessionAttribute("MEMBER") Member member
				) {
			Article article = articleDao.getArticle(articleId);
			
			//String memberId = "member.getMemberId()";
			//String userId = "article.getArticleId()";
			if (!member.getMemberId().equals(article.getUserId())) {
				return "redirect:/app/readtext?articleId="+articleId;
			}
			articleDao.deleteArticle(articleId);
			return "redirect:/app/articles";
		}
}