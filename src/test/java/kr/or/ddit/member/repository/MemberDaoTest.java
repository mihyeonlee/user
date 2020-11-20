package kr.or.ddit.member.repository;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.common.model.PageVO;
import kr.or.ddit.member.dao.MemberDaoI;
import kr.or.ddit.member.model.MemberVO;

public class MemberDaoTest extends ModelTestConfig{
	
	@Resource(name="memberDao")
	private MemberDaoI memberDao;
	
	
	@Test
	public void insertMemberTest() {
		/***Given***/

		MemberVO memberVO = new MemberVO("temp", "dditpass", "대덕인재", "개발원", "", "", "", "", "");
		/***When***/
		int insertCnt = memberDao.insertMember(memberVO);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void selectAllMemberTest() {
		/***Given***/
		
		/***When***/
		List<MemberVO> memberList = memberDao.selectAllMember();
		/***Then***/
		assertTrue(memberList.size() > 15);
	}
	
	@Test
	public void getMemberTest() {
		/***Given***/
		String userid = "brown";

		/***When***/
		MemberVO memberVo = memberDao.getMember(userid);
		
		/***Then***/
		assertEquals(memberVo.getUserid(),"brown");
	}
	
	
	@Test
	public void getPageMemberTest() {
		/***Given***/
		PageVO pageVo = new PageVO(1, 7);
		/***When***/
		List<MemberVO> memberList = memberDao.getPageMember(pageVo);
		
		/***Then***/
		assertTrue(memberList.size()==7);
	}
	@Test
	public void memberSearchTest() {
		/***Given***/
		PageVO pageVo = new PageVO(1, 7,"userid","brown");
		/***When***/
		List<MemberVO> memberList = (List<MemberVO>) memberDao.memberSearch(pageVo);
		
		/***Then***/
		assertTrue(memberList.size()==1);
	}
	
	@Test
	public void getMemberTotalCntTest() {
		/***Given***/
		
		/***When***/
		int totalCnt = memberDao.getMemberTotalCnt();
		
		/***Then***/
		assertTrue(totalCnt>20);
	}
	@Test
	public void searchTotalCntTest() {
		/***Given***/
		PageVO pageVo = new PageVO(1, 7,"userid","b");
		/***When***/
		int totalCnt = memberDao.searchTotalCnt(pageVo);
		
		/***Then***/
		assertTrue(totalCnt>1);
	}
	
	@Test
	public void deleteMemberTest() {
		/***Given***/
		String userid = "brown";
		/***When***/
		int deleteCnt = memberDao.deleteMember(userid);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	
	@Test
	public void updateMemberTest() {
		/***Given***/

		MemberVO memberVO = new MemberVO("temp", "dditpass", "대덕인재", "개발원", "1", "2", "3", "", "");
		/***When***/
		int updateCnt = memberDao.insertMember(memberVO);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void checkUseridTest() {
		/***Given***/
		
		String userid = "fkdfjkd";
		/***When***/
		int cnt = memberDao.checkUserid(userid);
		
		/***Then***/
		assertEquals(0, cnt);
	}
	
	
	
	
	

}
