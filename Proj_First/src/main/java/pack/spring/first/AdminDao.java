package pack.spring.first;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {

	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	//유저 리스트보기
	public List<Map<String, Object>> memberList(){
		return this.sessionTemplate.selectList("admin.memberList");
	}
	
	//유저 리스트보기 아이디 필터링
	public List<Map<String, Object>> memberListuId(String uId){
		return this.sessionTemplate.selectList("admin.memberListuId",uId);
	}
	
	//유저 리스트보기 이름 필터링
	public List<Map<String, Object>> memberListuName(String uName){
		return this.sessionTemplate.selectList("admin.memberListuName",uName);
	}
	
	//유저 리스트보기 넘버 필터링
	public Map<String, Object> memberListNum(int num){
		return this.sessionTemplate.selectOne("admin.memberListNum",num);
	}
	
	//유저 과
	public int memberUpdate(Map<String, Object>map) {
		return this.sessionTemplate.update("admin.memberUpdate",map);
	}
	
}
