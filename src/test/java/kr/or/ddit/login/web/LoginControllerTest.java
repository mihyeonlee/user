package kr.or.ddit.login.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.WebTestConfig;


public class LoginControllerTest extends WebTestConfig{
	
	
	
	@Test
	public void getViewTest() throws Exception {
		mockMvc.perform(get("/login/view"))
			.andExpect(status().isOk())  // 상태 :200
			.andExpect(view().name("login/login")); // url 
	}
	
	//로그인 요청 테스트(정상적인 경우)
	//contentType(MediaType.APPLICATION_FORM_URLENCODED) => 없으면 header정보가 빠져 처리하기가 힘들다.
	@Test
	public void processSuccessTest() throws Exception {
		mockMvc.perform(post("/login/process").contentType(MediaType.APPLICATION_FORM_URLENCODED)
							.param("userid", "brown")
							.param("pass", "brownPass"))
				.andDo(print())
				.andExpect(status().is(302))
				.andExpect(view().name("redirect:/member/memberList"))
				.andExpect(model().attributeExists("to_day"));
	}
	
	//로그인 요청 테스트(실패테스트)
	@Test
	public void processFailTest() throws Exception {
		MvcResult result = mockMvc.perform(post("/login/process").contentType(MediaType.APPLICATION_FORM_URLENCODED)
							.param("userid", "brown")
							.param("pass", "brownPassFail")).andReturn();
		ModelAndView mav = result.getModelAndView(); // 결과적으로 modelandview객체로 생성된다.
		assertEquals("login/login",mav.getViewName());
		assertEquals("fail", mav.getModel().get("msg"));

	}
	

}
















