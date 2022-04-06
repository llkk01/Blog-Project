package com.stone.member.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.stone.member.common.Member;
import com.stone.member.service.MemberService;
import com.stone.member.service.MemberServiceImpl;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@GetMapping("/member")
	public ModelAndView testMember() {		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/TestView");
		
		return mv;
	}
	
	//회원 등록 준비
	@RequestMapping("/memberAdd")
	ModelAndView prepareMember( ) {
		ModelAndView mv = new ModelAndView();
		memberService.prepare();
		
		mv.setViewName("member/MemberAddView");
		
		return mv;
	}
	
	//회원 등록
	@PostMapping("/memberAdd")
	ModelAndView addMember(Member member) {
		ModelAndView mv = new ModelAndView();
		memberService.insters(member);
		
		mv.setViewName("member/MemberAddSuccessView");
		
		return mv;
	}
	
	//ID중복검사준비
	@GetMapping("/id")
	public String usableIDprepare() {
		
		return "member/MemberIDView";	
	}
	
	//id중복검사
	@PostMapping("/id")
	ModelAndView idUsable(String id){
		ModelAndView mv = new ModelAndView();
		boolean usable = memberService.usableIDs(id);
			
		mv.setViewName("member/MemberIDView");
		
		mv.addObject("id", id);
		mv.addObject("usable", usable);
			
		return mv;
	}
	
	
	//회원정보 목록 출력(페이징)
	@RequestMapping("/memberList")
	ModelAndView listMember(@RequestParam(value = "pageno", required = false, defaultValue = "1") int pageNo) {
		ModelAndView mv = new ModelAndView();

		int maxSize = 5;
		int maxSizeNo = maxSize * pageNo;

		int memberNum = maxSizeNo - (maxSize - 1);

		Object[] listMemberCount = memberService.selects(memberNum, maxSize);

		int listMemberNumber = (int) listMemberCount[1]; 
		int lastPageNo = (int) (listMemberNumber / maxSize) + ((listMemberNumber % maxSize > 0) ? 1 : 0);

		mv.setViewName("member/MemberListView"); 
		mv.addObject("members", listMemberCount[0]);
		mv.addObject("pageNo", pageNo);
		mv.addObject("lastPageNo", lastPageNo);

		return mv;
	}
	
	//회원정보 상세보기
	@RequestMapping("/memberDetail")
	ModelAndView detailMember(int no) {
		ModelAndView mv = new ModelAndView();
		Member member = memberService.selectNums(no);
		
		mv.setViewName("member/MemberDetailView");
		mv.addObject("member", member);
		
		return mv;
	}

}
