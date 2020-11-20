package kr.or.ddit.member.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.model.PageVO;
import kr.or.ddit.member.dao.MemberDaoI;
import kr.or.ddit.member.model.MemberVO;

@Transactional
@Service("memberService")
public class MemberService implements MemberServiceI {
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
	
	@Resource(name="memberDao") // 필드안에서도 주입받아 사용해야 빈임.
	private MemberDaoI memberDao; 
	
	public MemberService() {
		//memberDao = new MemberDao(); 필드안에 객체를 생성하는 형식은 bean이 아니다 사용하면 안됨.
	}
	
	
	@Override
	public MemberVO getMember(String userId) {
		return memberDao.getMember(userId);
	}
	

	@Override
	public List<MemberVO> selectAllMember() {
		return memberDao.selectAllMember();
	}

	@Override
	public Map<String, Object> getPageMember(PageVO pageVO) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberList",memberDao.getPageMember(pageVO));
		
		//15건, 페이지 사이즈를 7로 가정했을때 3개의 페이지가 나와야한다.
		double totalCnt = memberDao.getMemberTotalCnt();
		int pages = (int)Math.ceil(totalCnt/pageVO.getPageSize());
		map.put("pages", pages);
		
		return map;
	}
	
	@Override
	public Map<String, Object> memberSearch(PageVO pageVO) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<MemberVO> memberList = (List<MemberVO>) memberDao.memberSearch(pageVO); 
		map.put("memberList", memberList);
		
		//15건, 페이지 사이즈를 7로 가정했을때 3개의 페이지가 나와야한다.
		double totalCnt = memberDao.searchTotalCnt(pageVO);
		
		int pages = (int)Math.ceil(totalCnt/pageVO.getPageSize());
		map.put("pages", pages);
		
		return map;
	}

	@Override
	public int insertMember(MemberVO memberVo) {
//		logger.debug("첫번째 insert 시작전");
//		memberDao.insertMember(memberVo);
//		logger.debug("첫번째 insert 시작후");
//		
		//첫 번째 쿼리는 정상적으로 실행되지만 
		//두 번째 쿼리에서 동일한 데이터를 입력하여 PRIMARY KEY 제약조건에 의해 
		//SQL 실행 실패
		//첫 번째 쿼리는 성공했지만 트랜잭션 설정을 service 레벨에 설정하였기 때문에
		//서비스 메소드에서 실행된 모든 쿼리를 rollback 처리한다.
		
//		logger.debug("두번째 insert 시작전");
//		memberDao.insertMember(memberVo);
//		logger.debug("두번째 insert 시작후");
		
		return memberDao.insertMember(memberVo);
	}

	@Override
	public int deleteMember(String userid) {
		return memberDao.deleteMember(userid);
	}

	@Override
	public int updateMember(MemberVO memberVo) {
		return memberDao.updateMember(memberVo);
	}


	@Override
	public int checkUserid(String userid) {
		return memberDao.checkUserid(userid);
	}

}
