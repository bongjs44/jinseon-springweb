package org.jinseon.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository("articleDao")
public class ArticleDaoImplUsingSpringJdbc implements ArticleDao {
	/**
	 * 목록 가져오는 sql
	 */
	static final String LIST_ARTICLES = "SELECT articleId, title, name, cdate FROM article ORDER BY articleId desc LIMIT ?,?";

	/**
	 * 글 1개 가져오는 sql
	 */
	static final String GET_ARTICLE = "SELECT articleId, title, content, name, cdate FROM article WHERE articleId=?";

	/**
	 * 글 등록하는 sql
	 */
	static final String ADD_ARTICLE = "INSERT INTO article(title, content, userId, name) VALUES (?,?,?,?)";

	//글 갯수
	static final String COUNT_ALL = "SELECT count(articleId) count FROM article";

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	final RowMapper<Article> articleRowMapper = new BeanPropertyRowMapper<>(
			Article.class);

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(COUNT_ALL, Integer.class);
	}

	@Override
	public List<Article> selectAll(int offset, int count) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(LIST_ARTICLES, articleRowMapper, offset, count);
	}

	@Override
	public void insertArticle(Article article) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(ADD_ARTICLE, article.getTitle(), article.getContent(), article.getUserId(), article.getName());
		
	}

	@Override
	public Article getArticle(String articleId) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(GET_ARTICLE,new BeanPropertyRowMapper<>(Article.class), articleId);
	}

	

}