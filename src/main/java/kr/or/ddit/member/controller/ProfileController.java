package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.service.MemberServiceI;

@Controller
public class ProfileController {

	@Resource(name = "memberService")
	private MemberServiceI memberService;

	@RequestMapping("/profileImgView")
	public String profileImgView(String userid, Model model) throws IOException {
		// 응답으로 생성하려고 하는 것 : 이미지 파일을 읽어서 output stream 객체에 쓰는것

		MemberVO memverVo = memberService.getMember(userid);
		model.addAttribute("filepath", memverVo.getFilename());

		return "profileImgView";
	}

	@RequestMapping("/profileImgDownloadView")
	public String profileImgDownloadView(String userid, Model model) throws IOException {
		// 응답으로 생성하려고 하는 것 : 이미지 파일을 읽어서 output stream 객체에 쓰는것

		MemberVO memberVo = memberService.getMember(userid);
		model.addAttribute("filepath", memberVo.getFilename());
		model.addAttribute("realFilename",memberVo.getRealfilename());

		return "profileImgDownloadView";
	}

	

	

}
