package pack.spring.first;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceimp implements MemberService{
	
	@Autowired
	MemberDao memberDao;
	
	//메인 페이지 이동
	@Override
	public String index() {
		return null;
	}
	
	//로그인 기능
	@Override
	public Map<String, Object> loginMember(Map<String, Object> map) {
		return this.memberDao.loginMember(map);
	}
	
	//로그아웃 페이지 이동
	@Override
	public String logOut() {
		return null;
	}
	
	//회원 약관 동의 
	@Override
	public String joinAgreement() {
		return null;
	}
	
	//회원가입 페이지 이동
	@Override
	public String member() {
		return null;
	}
	
	//중복 아이디 체크
	@Override
	public Map<String, Object> checkId(Map<String, Object>map) {
		return this.memberDao.checkId(map);
	}
	
	//주소 찾기
	@Override
	public List<Map<String, Object>> zipcodeRead(Map<String, Object>map) {
		return this.memberDao.zipcodeRead(map);
	}
	
	//회원 가입 기능
	@Override
	public int insertMember(Map<String, Object> map) {
		return this.memberDao.insertMember(map);
	}
	
	// 회원정보 수정페이지 이동
	@Override
	public String memberMod() {
		return null;
	}
	
	// 회원정보 수정 select
	@Override
	public Map<String, Object> selectMember(String uId) {
		return this.memberDao.selectMember(uId);
	}
	
	// 회원정보 수정 처리  
	@Override
	public int updateMember(Map<String, Object> map) {
		return this.memberDao.updateMember(map);
	}
	
	//마이페이지 이동
	@Override
	public String myPage() {
		return null;
	}

	// 회원탈퇴 페이지 이동 
	@Override
	public String quitview() {
		return null;
	}

	// 회원 탈퇴 처리
	@Override
	public int memberQuit(Map<String, Object> map) {
		return this.memberDao.memberQuit(map); 
	}

	

	
}
