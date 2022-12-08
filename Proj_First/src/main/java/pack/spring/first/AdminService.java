package pack.spring.first;

import java.util.List;
import java.util.Map;

public interface AdminService {

	//유저리스트
	List<Map<String, Object>> memberList();
	
	//유저리스트 아이디 필터링
	List<Map<String, Object>> memberListuId(String uId);
	
	//유저리스트 이름 필터링
	List<Map<String, Object>> memberListuName(String uName);
	
	//유저리스트 넘버 필터링
	Map<String, Object> memberListNum(int num);
	
	
}
