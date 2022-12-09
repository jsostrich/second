package pack.spring.first;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceimp implements AdminService{

	@Autowired
	AdminDao adminDao;
	
	//유저 리스트
	@Override
	public List<Map<String, Object>> memberList() {
		return this.adminDao.memberList();
	}
	
	//유저 리스트 아이디 필터링
	@Override
	public List<Map<String, Object>> memberListuId(String uId) {
		return this.adminDao.memberListuId(uId);
	}
	
	//유저 리스트 이름 필터링
	@Override
	public List<Map<String, Object>> memberListuName(String uName) {
		return this.adminDao.memberListuName(uName);
	}
	
	//유저 리스트 넘버 필터링
	@Override
	public Map<String, Object> memberListNum(int num) {
		return this.adminDao.memberListNum(num);
	}

	//유저 관리
	@Override
	public int memberUpdate(Map<String, Object> map) {
		return this.adminDao.memberUpdate(map);
	}
	
	
	
	

	

}
