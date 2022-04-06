package com.stone.visitor.presentation;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stone.board.common.Board;
import com.stone.member.common.Member;
import com.stone.member.service.MemberService;
import com.stone.visitor.common.Visitor;
import com.stone.visitor.service.VisitorService;

@Controller
public class VisitorController {
	
	@Autowired
	VisitorService visitorService;
	MemberService memberService;
	
	//방명록 작성 준비
	@GetMapping("/visitorAdd")
	public String visitorPrepare() {
		if(visitorService.visitorPrepare()) {
			return "visitor/VisitorAddView";
		}
		return "visitor/VisitorErrorView";
	}
	
	//방명록 등록
	@PostMapping("/visitorAdd")
	public String addVisitor(Visitor visitor, HttpSession session) {
		//로그인한 회원의 번호 구하기
		int userNum = (int)session.getAttribute("userNo");
		
		//로그인한 회원(=작성자 회원) 새게시물에 작성자 set해주기
		Member memberW = new Member();
		memberW.setNo(userNum);
		visitor.setMember_no(memberW);
		
		visitorService.inserts(visitor);
		
		return "visitor/VisitorAddSuccessView";
	}
	
	//방명록 수정
	@PostMapping("/visitorUpdate")
	ModelAndView UpdateVisitor(Visitor visitor) {
		ModelAndView mv = new ModelAndView();

		visitorService.updates(visitor);
		mv.addObject("visitor", visitor);
			
		mv.setViewName("visitor/VisitorUpdateView");
			
		return mv;
	}
		
	//방명록 삭제
	@GetMapping("/visitorDelete")
	ModelAndView DeleteVisitor(Visitor visitor) {
		ModelAndView mv = new ModelAndView();
		
		visitorService.deletes(visitor);
			
		mv.setViewName("visitor/VisitorDeleteView");

		return mv;
	}
	
	//방명록 목록 출력
	@GetMapping("/visitorList")
	public ModelAndView listVisitor(HttpSession session) {
		//로그인중인지 확인
		boolean loginIn = (session.getAttribute("id")!=null)?true:false;
		
		ModelAndView mv = new ModelAndView();
		List<Visitor> list = visitorService.selects();
		
		mv.setViewName("visitor/VisitorListView");
		mv.addObject("visitors", list);
		mv.addObject("isLogin",loginIn);
		
		return mv;
	}
	
	//게시물상세출력
	@GetMapping("/visitorAdd/{num}")
	public ModelAndView detailVisitor(@PathVariable int num, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		Visitor visitor = visitorService.selectNums(num);
		
		mv.setViewName("visitor/VisitorDetailView");
		mv.addObject("visitor", visitor);
		
		//로그인  안 되어있을 경우 > 동일인물인지를 null로 지정
		Boolean userSame = null;
		if(session!=null) {
			Integer userNo = (Integer)session.getAttribute("userNo");
			//로그인이 된 경우
			if(userNo!=null) {
				//로그인 회원과 게시물 작성자가 동일 인물인가 확인
				if(userNo==visitor.getMember_no().getNo()) { 
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
