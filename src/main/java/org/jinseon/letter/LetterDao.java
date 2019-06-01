package org.jinseon.letter;

import java.util.List;

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
	static final String RECEIVED_LETTER = "select letterId,title,senderId,senderName,left(cdate,19) cdate from letter where receiverId=? order by letterId desc limit ?,?";
	//보낸 편지 목록 확인
	static final String SENT_LETTER = "select letterId,title,receiverId,receiverName,left(cdate,19) cdate from letter where senderId=? order by letterId desc limit ?,?";
	
	//편지 내용 확인
	static final String READ_LETTER = "select letterId,title,content,senderId,senderName,receiverId,receiverName,cdate from letter where letterId=? and (senderId=? or receiverId=?)";
	//편지 삭제
	static final String DELETE_LETTER = "delete from letter where letterId = ? and (senderId=? or receiverId=?)";

	//보낸 편지 갯수
	static final String COUNT_SENT = "select count(letterId) from letter where senderId=?";
	//받은 편지 갯수
	static final String COUNT_RECEIVED = "select count(letterId) from letter where receiverId=?";
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	RowMapper<Letter> letterRowmapper = new BeanPropertyRowMapper<>(Letter.class);
	
	//받은 편지 갯수
	public int countReceived(String receiverId) {
		return jdbcTemplate.queryForObject(COUNT_RECEIVED, Integer.class, receiverId);
	}
	//보낸 편지 갯수
	public int countSent(String senderId) {
		return jdbcTemplate.queryForObject(COUNT_SENT, Integer.class, senderId);
	}
	
	//편지 보내기
	public void sendLetter(Letter letter) {
		jdbcTemplate.update(SEND_LETTER, letter.getTitle(), letter.getContent(), letter.getSenderId(), letter.getSenderName(), letter.getReceiverId(), letter.getReceiverName());
	}
	
	//받은 편지 목록 확인
	public List<Letter> receivedLetter(String receiverId, int offset, int count) {
		return jdbcTemplate.query(RECEIVED_LETTER, letterRowmapper, receiverId, offset, count);
	}
	
	//보낸 편지 목록 확인
	public List<Letter> sentLetter(String senderId, int offset, int count) {
		return jdbcTemplate.query(SENT_LETTER, letterRowmapper, senderId, offset, count);
	}
	
	//편지 내용 확인
	public Letter readLetter(String letterId, String memberId) {
		return jdbcTemplate.queryForObject(READ_LETTER, letterRowmapper, letterId, memberId, memberId);
	}
	
	//편지 삭제
	public int deleteLetter(String letterId, String memberId) {
		return jdbcTemplate.update(DELETE_LETTER, letterId, memberId, memberId);
	}
	
	
}
