package org.jinseon.article;
import java.util.List;

public interface ArticleDao {
		
		//게시글 수
		int countAll();
		//목록
		List<Article> selectAll(int offset, int count);
		//입력
		void insertArticle(Article article);
		//조회
		Article getArticle(String articleId);
		
		/**
		 * 수정
		 */
		void updateArticle(Article article);

		/**
		 * 삭제
		 */
		void deleteArticle(String articleId);
	
}
