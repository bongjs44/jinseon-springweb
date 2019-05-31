package org.jinseon.letter;

import org.jinseon.article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LetterDao {
	
	//편지 보내기
	static final String SEND_LETTER = "insert letter(title,content,senderId,senderName,receiverId,receiverName)values(?,?,?,?,?,?)";
	//받은 편지 목록 확인
	static final String RECEIVED_LETTER = "select letterId,title,senderId,senderName,cdate from letter where receiverId=?";
	//보낸 편지 목록 확인
	static final String SENT_LETTER = "select letterId,title,senderId,senderName,cdate from letter where receiverId=?";
	//편지 내용 확인
	static final String READ_LETTER = "select letterId,title,content,senderId,senderName,receiverId,receiverName,cdate from letter where letterId=? and (senderId=? or receiverId=?)";
	//편지 삭제
	static final String DELETE_LETTER = "delete from letter where letterId = ? and (senderId=? or receiverId=?)";
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	RowMapper<Article> articleRowMapper = new BeanPropertyRowMapper<>(Article.class);
	
	
}
