package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.PageVO;
import kr.or.ddit.member.model.MemberVO;

public interface MemberServiceI {

	MemberVO getMember(String userId);

	public List<MemberVO> selectAllMember();

	public Map<String, Object> getPageMember(PageVO pageVO);

	public int insertMember(MemberVO memberVo);

	public int deleteMember(String userid);

	int updateMember(MemberVO memberVo);

	int checkUserid(String userid);

	Map<String, Object> memberSearch(PageVO pageVO);

}
