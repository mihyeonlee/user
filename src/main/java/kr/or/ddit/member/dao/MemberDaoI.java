package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.common.model.PageVO;
import kr.or.ddit.member.model.MemberVO;

public interface MemberDaoI {
	
	public MemberVO getMember(String userId);
	
	public List<MemberVO> selectAllMember();
	
	public List<MemberVO> getPageMember(PageVO pageVo);
	
	public int getMemberTotalCnt();
	
	public int insertMember(MemberVO memberVo);
	
	public int deleteMember(String userid);

	public int updateMember(MemberVO memberVo);

	public int checkUserid(String userid);

	public Object memberSearch(PageVO pageVO);

	public int searchTotalCnt(PageVO pageVO);
	
	
}
