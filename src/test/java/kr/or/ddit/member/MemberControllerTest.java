package kr.or.ddit.member;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.InputStream;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.WebTestConfig;


public class MemberControllerTest extends WebTestConfig{
	
	@Test
	public void memberListTest() throws Exception {
		mockMvc.perform(get("/member/memberList"))
			.andExpect(status().isOk())  // 상태 :200
			.andExpect(view().name("member/memberList")); // jsp
	}
	@Test
	public void memberSearchTest() throws Exception {
		mockMvc.perform(get("/member/memberSearch"))
		.andExpect(status().isOk())  // 상태 :200
		.andExpect(view().name("member/memberSearch")); // jsp
	}
	
	@Test
	public void memberUpdateViewTest() throws Exception {
		mockMvc.perform(get("/member/memberUpdateView")
							.param("userid", "나는코니"))
		.andExpect(status().isOk())  // 상태 :200
		.andExpect(view().name("member/memberUpdate"))
		.andExpect(model().attributeExists("memberVo")); // jsp
	}
	
	//유저한명 test
	@Test
	public void viewTest() throws Exception {
		mockMvc.perform(post("/member/view")
							.param("userid", "나는코니"))
				.andExpect(status().is(200))
				.andExpect(view().name("member/member"))
				.andExpect(model().attributeExists("memberVo"));
	}
	
	//유저insert test
	@Test
	public void memberRegistTest() throws Exception {
		InputStream is = getClass().getResourceAsStream("/kr/or/ddit/upload/sally.png");
		MockMultipartFile realFilename = new MockMultipartFile("realFilename","sally.png","image/png",is);
		mockMvc.perform(fileUpload("/member/memberRegist")
				.file(realFilename)
				.param("userid", "나는코니56")
				.param("usernm", "어피치2")
				.param("pass", "wwewe")
				.param("addr1", "서울 관악구 장군봉길 6")
				.param("addr2", "we")
				.param("zipcode", "08782")
				.param("alias", "pc-03"))
		.andExpect(view().name("redirect:/member/view?userid=나는코니56"));
	}
	
	@Test
	public void memberUpdateTest() throws Exception {
		InputStream is = getClass().getResourceAsStream("/kr/or/ddit/upload/sally.png");
		MockMultipartFile realFilename = new MockMultipartFile("realFilename","sally.png","image/png",is);
		mockMvc.perform(fileUpload("/member/memberUpdate")
				.file(realFilename)
				.param("userid", "나는코니3")
				.param("usernm", "코니3")
				.param("pass", "wwewe2")
				.param("addr1", "너도? 관악구 장군봉길 6")
				.param("addr2", "we")
				.param("zipcode", "12121")
				.param("alias", "pc-03"))
		.andExpect(view().name("redirect:/member/view"))
		.andExpect(status().is(302));
	}
	
	
	
	
	
	

}
