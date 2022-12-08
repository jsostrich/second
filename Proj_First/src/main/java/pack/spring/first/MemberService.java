package pack.spring.first;

import java.util.List;
import java.util.Map;

public interface MemberService {
	
	//메인 페이지
	String index();
	
	//로그인 처리
	Map<String, Object> loginMember(Map<String, Object>map);
	
	//로그 아웃 처리 페이지
	String logOut();
	
	//약관 동의 페이지
	String joinAgreement();
	
	//회원 가입 페이지
	String member();
	
	//회원 가입처리
	int insertMember(Map<String, Object>map);
	
	// 회원정보 수정 페이지
	String memberMod();
	
	// 회원정보 불러오기
	Map<String, Object> selectMember(String uId);
	
	// 회원정보 수정처리
	int updateMember(Map<String, Object> map);
	
	//아이디 중복체크 처리
	Map<String, Object> checkId(Map<String, Object>map);
	
	//주소 찾기
	List<Map<String, Object>> zipcodeRead(Map<String, Object>map);
	
	//마이페이지 페이지 이동
	String myPage();
	
	// 회원탈퇴 페이지 이동 
	String quitview();
	
	// 회원 탈퇴처리
	int memberQuit(Map<String, Object> map);

	
}
