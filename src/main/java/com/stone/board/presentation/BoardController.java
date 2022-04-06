package com.stone.board.presentation;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stone.board.common.Board;
import com.stone.board.service.BoardService;
import com.stone.member.common.Member;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	//게시물 작성 준비
	@GetMapping("/boardAdd")
	public String preparePrepare() {
		if(boardService.boardPrepare()) {
			return "board/BoardAddView";
		}
		return "board/BoardErrorView";
	}
	
	//게시물 등록
	@PostMapping("/boardAdd")
	public String addBoard(Board board, HttpSession session) {

		//로그인한 회원의 번호 구하기
		int userNum = (int)session.getAttribute("userNo");
		
		//로그인한 회원(=작성자 회원) 새게시물에 작성자 set해주기
		Member memberW = new Member();
		memberW.setNo(userNum);
		board.setWriters(memberW);
		
		boardService.inserts(board);
		
		return "board/BoardAddSuccessView";
	}
	
	//게시물 수정
	@PostMapping("/boardUpdate")
	ModelAndView UpdateBoard(Board board) {
		ModelAndView mv = new ModelAndView();

		boardService.updates(board);
		mv.addObject("board", board);
			
		mv.setViewName("board/BoardUpdateView");
			
		return mv;
	}
		
	//게시물 삭제
	@GetMapping("/boardDelete")
	ModelAndView DeleteBoard(Board board) {
		ModelAndView mv = new ModelAndView();
		
		boardService.deletes(board);
			
		mv.setViewName("board/BoardDeleteView");

		return mv;
	}
	
	//게시물 목록 출력
	@GetMapping("/boardList")
	public ModelAndView listBoard(HttpSession session) {
		//로그인중인지 확인
		boolean loginIn = (session.getAttribute("id")!=null)?true:false;
		
		ModelAndView mv = new ModelAndView();
		List<Board> list = boardService.selects();
		
		mv.setViewName("board/BoardListView");
		mv.addObject("boards", list);
		mv.addObject("isLogin",loginIn);
		
		return mv;
	}
	
	//게시물상세출력
	@GetMapping("/boardAdd/{num}")
	public ModelAndView detailBoard(@PathVariable int num, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Board board = boardService.viewCounts(num);
		
		mv.setViewName("board/BoardDetailView");
		mv.addObject("board", board);
		
		//로그인  안 되어있을 경우 > 동일인물인지를 null로 지정
		Boolean userSame = null;
		if(session!=null) {
			Integer userNo = (Integer)session.getAttribute("userNo");
			//로그인이 된 경우
			if(userNo!=null) {
				//로그인 회원과 게시물 작성자가 동일 인물인가 확인
				if(userNo==board.getWriters().getNo()) { 
					userSame = true; //동일인물인경우 true
				} else {
					userSame = false;
				}
			}
		}
		mv.addObject("isWriters", userSame);
		
		return mv;
	}
	
}