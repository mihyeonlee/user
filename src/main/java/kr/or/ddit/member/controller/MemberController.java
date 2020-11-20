package kr.or.ddit.member.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.model.PageVO;
import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.service.MemberServiceI;

@RequestMapping("/member")
@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Resource(name = "memberService")
	private MemberServiceI memberService;

	// 파라미터이름(name)과 동일한 메소드 인자를 선언하면
	// 스프링 프레임워크가 자동으로 바인딩 해준다.
	// 값을 담을 수 있는 객체를 메소드 인자로 선언한 경우에도 필드명과 파라미터 명이 동일하면 자동으로 바인딩 처리를 해준다
	// 이때 값을 담는 객체(form전송 data)를 스프링 프레임워크에서는 command객체라고 명명한다. HttpSession객체도 인자 가능

	// Model: view객체에서 응답을 생성할 때 참조할 데이터를 담는 객체
	// jsp/servlet 기반의 request 역할을 담당 HttpServletRequest객체에 담아도 되지만 특정뷰에서 처리를 못하는경우도
	// 있기때문에 그렇게 쓰지 않는다.

	@RequestMapping("/memberList")
	public String memberList(@RequestParam(name= "page", required= false,defaultValue="1") int page,
							 @RequestParam(name="pageSize", required=false,defaultValue="5") int pageSize, 
							  Model model) {
		logger.debug("memberListController!!!");
		
		model.addAttribute("page", page);
		model.addAttribute("pageSize", pageSize);

		//pageVO
		PageVO pageVO = new PageVO(page, pageSize);

		Map<String, Object> map = memberService.getPageMember(pageVO);
		model.addAllAttributes(map);

		return "member/memberList";
	}
	
	@RequestMapping("/memberSearch")
	public String memberSearch(@RequestParam(name= "page", required= false,defaultValue="1") int page,
			@RequestParam(name="pageSize", required=false,defaultValue="5") int pageSize, 
			@RequestParam(name="searchType", required=false) String searchType,
			@RequestParam(name="keyword", required=false) String keyword
			, Model model) {
		logger.debug("membersearchController!!!");
		
		model.addAttribute("page", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);
		
		//pageVO
		PageVO pageVO = new PageVO(page, pageSize,searchType,keyword);
		
		Map<String, Object> map = memberService.memberSearch(pageVO);
		model.addAllAttributes(map);
		
		return "member/memberSearch";
	}
	
	
	
	@RequestMapping("/view")
	public String view(String userid, Model model) {
		//userid 파라미터가 없을 때는 brown사용자를 보여준다.

		MemberVO memberVo = memberService.getMember(userid);
		model.addAttribute("memberVo", memberVo);

		return "member/member";
	}

	@RequestMapping("/memberRegistView")
	public String memberRegistView() {
		return "member/memberRegist";

	}

	@RequestMapping("/memberRegist")
	public String memberRegist(@Valid MemberVO memberVo, BindingResult br, @RequestPart("realFilename")MultipartFile file) {
//	public String memberRegist(@Valid JSRMemberVO memberVo, BindingResult br, @RequestPart("realFilename")MultipartFile file) {
//		new MemberVoValidator().validate(memberVo, br);
		
		// 검증을 통과하지 못했으므로 사용자 등록 화면으로 이동
		if(br.hasErrors()) {
			return "member/memberRegistView";
		}
		
		logger.debug("file : {}", file);
		
		File profile = null;
		String filename = UUID.randomUUID().toString();
		
		if (file.getSize() > 0) {
			profile = new File("D:\\profile\\"+filename+file.getOriginalFilename());
			try {
				file.transferTo(profile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			memberVo.setRealfilename(file.getOriginalFilename());
			memberVo.setFilename(profile.toString());
		}
		
		logger.debug("memberVo:{}",memberVo);
		
		//예외처리 하는이유는 실패테스트코드실행 과정에서 실패가 뜨는걸 예외처리 안해주면 테스트코드 실패가 되기 때문에
		int cnt = 0;
		try {
			
			cnt = memberService.insertMember(memberVo);
			if (cnt == 1) {
				return "redirect:/member/view?userid="+memberVo.getUserid();
			}
			 
		} catch (Exception e) {
		}
		
		return "member/memberRegist";
	}
	
	@RequestMapping("/checkUserid")
	public String checkUserid(@RequestParam(name="userid", required = false) String userid, Model model) {
		int cnt = memberService.checkUserid(userid);
		model.addAttribute("cnt",cnt);
		return "jsonView";
	}

	@RequestMapping("/memberUpdateView")
	public String memberUpdateView(@RequestParam(name="userid", required = false,defaultValue = "brown") String userid, Model model) {

		logger.debug("memberUpdate userid : {}", userid);

		MemberVO memberVo = memberService.getMember(userid);
		model.addAttribute("memberVo", memberVo); 

		return "member/memberUpdate";
	}

	@RequestMapping("/memberUpdate")
	public String memberUpdate(MemberVO memberVo, @RequestPart("realFilename")MultipartFile file,RedirectAttributes ra) {

		logger.debug("memberUpdate memberVo : {}", memberVo);
		
		File profile = null;
		String filename = UUID.randomUUID().toString();
		if (file.getSize() > 0) {
			profile = new File("D:\\profile\\"+filename+file.getOriginalFilename());
			try {
				file.transferTo(profile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			memberVo.setRealfilename(file.getOriginalFilename());
			memberVo.setFilename(profile.toString());
		}

		int cnt = memberService.updateMember(memberVo);

		if (cnt == 1) {
			ra.addAttribute("userid",memberVo.getUserid());
			return "redirect:/member/view";
		}

		return "member/memberList";
	}
	
	@RequestMapping("/memberDelete")
	public String memberDelete(@RequestParam(name="userid", required = false) String userid,Model model) {
		int cnt = memberService.deleteMember(userid);
		model.addAttribute(cnt);
		
		return "jsonView";
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
