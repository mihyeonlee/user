package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.common.model.PageVO;
import kr.or.ddit.member.model.MemberVO;

public class MemberServiceTest extends ModelTestConfig{
	
	@Resource(name="memberService")
	private MemberServiceI memberService;
	
	@Test
	public void insertMember_SUCCESS_Test() {
		/***Given***/

		MemberVO memberVO = new MemberVO("temp", "dditpass", "대덕인재", "개발원", "", "", "", "", "");
		/***When***/
		int insertCnt = memberService.insertMember(memberVO);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}

	@Test
	public void insertMember_FAIL_Test() {
		/***Given***/
		
		MemberVO memberVO = new MemberVO("ddit", "dditpass", "대덕인재", "개발원", "", "", "", "", "");
		/***When***/
		int insertCnt = 0; 
		try {
			insertCnt = memberService.insertMember(memberVO);
			
		} catch (Exception e) {
		}
		
		/***Then***/
		assertEquals(0, insertCnt);
	}
	
	@Test
	public void getMember_SUCCESS_Test() {
		/***Given***/
		String userid = "brown";

		/***When***/
		MemberVO memberVo = memberService.getMember(userid);
		
		/***Then***/
		assertEquals(memberVo.getUserid(),"brown");
	}
	
	@Test
	public void selectAllMember_SUCCESS_Test() {
		/***Given***/
		
		/***When***/
		List<MemberVO> memberList = memberService.selectAllMember();
		
		/***Then***/
		assertTrue(memberList.size()>20);
	}
	
	@Test
	public void getPageMember_SUCCESS_Test() {
		/***Given***/
		PageVO pageVo = new PageVO(1, 7);
		/***When***/
		Map<String, Object> map = memberService.getPageMember(pageVo);
		
		/***Then***/
		assertTrue(map.size()==2);
		assertTrue((int)map.get("pages")>3);
	}
	
	@Test
	public void deleteMember_SUCCESS_Test() {
		/***Given***/
		String userid = "brown";
		/***When***/
		int deleteCnt = memberService.deleteMember(userid);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	@Test
	public void updateMember_SUCCESS_Test() {
		/***Given***/

		MemberVO memberVO = new MemberVO("temp", "dditpass", "대덕인재", "개발원", "1", "2", "3", "", "");
		/***When***/
		int updateCnt = memberService.insertMember(memberVO);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void checkUseridTest() {
		/***Given***/
		
		String userid = "fkdfjkd";
		/***When***/
		int cnt = memberService.checkUserid(userid);
		
		/***Then***/
		assertEquals(0, cnt);
	}
	
	
	
	@Test
	public void memberSearchTest() {
		/***Given***/
		PageVO pageVo = new PageVO(1, 7,"userid","brown");
		/***When***/
		Map<String, Object> map = memberService.memberSearch(pageVo);
		
		/***Then***/
		assertTrue(map.size()==2);
	}
	
}
