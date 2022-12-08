package pack.spring.first;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//로그인 처리 dao
	public Map<String, Object> loginMember(Map<String, Object>map) {
		return this.sqlSessionTemplate.selectOne("member.loginmember", map);
	}
	
	//회원 가입 처리 dao
	public int insertMember(Map<String, Object> map) {
		return this.sqlSessionTemplate.insert("member.insertmember", map);
	}
	
	//아이디 중복 처리 dao
	public  Map<String, Object> checkId(Map<String, Object>map){
		return this.sqlSessionTemplate.selectOne("member.checkId",map);
	}
	
	// 회원 정보 수정 select dao
	public Map<String, Object> selectMember(String uId	){
		return this.sqlSessionTemplate.selectOne("member.selectmember", uId);
	}
	
	// 회원 정보 수정 처리 dao
	public int updateMember(Map<String, Object> map) {
		return this.sqlSessionTemplate.update("member.updatemember", map);
	}
	
	
	//우편번호 찾기 Dao
	public List<Map<String, Object>> zipcodeRead(Map<String, Object>map){
		return this.sqlSessionTemplate.selectList("member.zipcodeRead",map);
	}
	
	
	// 회원 탈퇴 Dao
	public int memberQuit(Map<String, Object> map) {
		return this.sqlSessionTemplate.delete("member.deletemember", map);
	}
	

	
}
