package org.jinseon.letter;

import java.util.List;

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
public class LetterController {
	
	@Autowired
	LetterDao letterDao;
	
	static final Logger logger = LogManager.getLogger();
	
	 //편지 보내기 화면
    @GetMapping("/letter/sendLetterForm")
    public String sendLetterForm() {
        return "letter/sendLetterForm";
    }
	
	//편지 보내기
	@PostMapping("/letter/sendLetter")
	public String sendLetter(Letter letter,
			@SessionAttribute("MEMBER") Member member
			) {
		letter.setSenderId(member.getMemberId());
		letter.setSenderName(member.getName());
		letterDao.sendLetter(letter);
		return "redirect:/app/letter/sentLetter";
	}

	//받은 편지 목록 확인
	@GetMapping("/letter/receivedLetter")
	public void receivedLetter(@RequestParam(value = "page", defaultValue = "1") int page,
			@SessionAttribute("MEMBER") Member member,
			Model model) {
		// 페이지 당 가져오는 행의 수
		final int COUNT = 100;
		// 시작점
		int offset = (page - 1) * COUNT;
				
		List<Letter> letters = letterDao.receivedLetter(member.getMemberId(), offset, COUNT);
		
		int totalCount = letterDao.countSent(member.getMemberId());
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("letters",letters);
	}
	
	
	//보낸 편지 목록 확인
	@GetMapping("/letter/sentLetter")
	public void sentLetter(@RequestParam(value = "page", defaultValue = "1") int page,
			@SessionAttribute("MEMBER") Member member,
			Model model) {
		// 페이지 당 가져오는 행의 수
		final int COUNT = 100;
		// 시작점
		int offset = (page - 1) * COUNT;
		
		List<Letter> letters = letterDao.sentLetter(member.getMemberId(), offset, COUNT);
		
		int totalCount = letterDao.countSent(member.getMemberId());
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("letters",letters);
	}
	
	
	//편지 내용 확인
	@GetMapping("/letter/readLetter")
	public void readLetter(@SessionAttribute("MEMBER") Member member,
			@RequestParam("letterId") String letterId,
			Model model) {
		Letter letters = letterDao.readLetter(letterId, member.getMemberId());
		model.addAttribute("letter",letters);
	}
	
	
	//편지 삭제
	@GetMapping("/letter/deleteLetter")
	public String deleteLetter(@RequestParam(value = "mode", required = false) String mode,
			@RequestParam("letterId") String letterId,
			@SessionAttribute("MEMBER") Member member) {
		int updatedRows = letterDao.deleteLetter(letterId, member.getMemberId());
		if (updatedRows == 0)
			// 자신의 편지가 아닐 경우 삭제되지 않음
			throw new RuntimeException("No Authority!");
		if ("SENT".equals(mode))
			return "redirect:/app/letter/sentLetter";
		else
			return "redirect:/app/letter/receivedLetter";
	}
}
