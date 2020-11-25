package kr.or.ddit.login.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.login.service.KakaoAPI;
import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.service.MemberServiceI;

//@WebServlet 혹은 web.xml url-mappring을 통해 url 획득, class로 서블릿을 만들어야 하는 이유
//참조하는게 없기 때문에 Controller만 적어도 된다.
@SessionAttributes("rangers")
@RequestMapping("/login") 
@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name="memberService")
	private MemberServiceI memberService;
	
	@Autowired
	private KakaoAPI kakao;
	
	@RequestMapping("/json")
	public String json() {
		return "jsonView"; 	// <bean id="jsonView" class="MappringJackson2JsonView"
				
	}
	
	
	// 로그인화면 처리
	@RequestMapping(path="/view",method = RequestMethod.GET)
	@GetMapping()
	public String getView(Locale locale) {
		logger.debug("LoginController.getView()");
		logger.debug("locale : {}", locale);
		
		//응답은 jsp그대로 사용 => spring에서는 관습적으로 WEB-INF에 저장 -> 사용자의 jsp접근을 막겠다.
		return "login/login";
	}
	 
	// 로그인 요청 처리
	@RequestMapping(path="/process", params = {"userid"})
	public String process(String userid, String pass, MemberVO memberVO, HttpSession session, Model model, 
						@RequestBody String body,
						@RequestParam(name="email", 
									required=false, 
									defaultValue = "brown@line.kr") String user_id) {
		
		logger.debug("LoginController.process() {}/{}/{}",userid,pass,memberVO);
		logger.debug("user_id:{}",user_id);
		MemberVO memberVo = memberService.getMember(userid);
		logger.debug("memberVo:{}",memberVo);
		logger.debug("requestBody:{}",body);
		
	
		if(memberVo != null && memberVO.getPass().equals(memberVo.getPass())) {
			session.setAttribute("S_MEMBER", memberVo);
			model.addAttribute("to_day", new Date());
			return "redirect:/member/memberList";
		}else {
			model.addAttribute("msg","fail");
			return "login/login";
		}
		
	}
	
	//localhost/login/unt/dd
	@RequestMapping("/unt/{unt_cd}")
	public String untMain(@PathVariable("unt_cd") String unt_cd) {
		logger.debug("unt_cd : {}", unt_cd );
		return "main";
	}
	
	// 로그인화면 처리
		@RequestMapping("/kakao")
		@GetMapping()
		public String kakao(@RequestParam(value="code",required=false) String code, HttpSession session ) throws Exception {
			logger.debug("#########kakao code : {}",code);
			//토큰가져오기
			String access_Token = kakao.getAccessToken(code);
			logger.debug("#########access_token : {}",access_Token);
			
			//토큰이용해 회원정보 가져오기
			HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
			logger.debug("########userInfo:{}",userInfo);
			
//		    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
		    if (userInfo.get("email") != null) {
		        session.setAttribute("S_MEMBER", userInfo.get("email"));
		        session.setAttribute("access_Token", access_Token);
		    }
			
			return "login/login";
		}
	
	
	

}
