package pack.spring.first;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BbsServiceimp implements BbsService{

	@Autowired
	BbsDao bbsDao;
	
	//글쓰기 페이지 보이기
	@Override
	public Map<String, Object> selectId(String uId) {
		return this.bbsDao.selectId(uId);
	}

	//글쓰기 페이지 글번호 맨끝 조회하기
	@Override
	public int num() {
		return this.bbsDao.num();
	}
	
	//글쓰기
	@Override
	public int write(Map<String, Object>map) {
		return this.bbsDao.write(map);
	}
	
	//리스트
	@Override
	public List<Map<String, Object>> list() {
		return this.bbsDao.list();
	}
	
	//이름으로 검색
	@Override
	public List<Map<String, Object>> listuName(String uName) {
		return this.bbsDao.listuName(uName);
	}

	//제목으로 검색
	@Override
	public List<Map<String, Object>> listSubject(String subject) {
		return this.bbsDao.listSubject(subject);
	}

	//내용으로 검색
	@Override
	public List<Map<String, Object>> listContent(String content) {
		return this.bbsDao.listContent(content);
	}

	//게시글 읽기
	@Override
	public Map<String, Object> read(int num) {
		return this.bbsDao.read(num);
	}
	
	//조회수 증가
	@Override
	public int cnt(int num) {
		return this.bbsDao.cnt(num);
	}
	
	//게시글 수정
	@Override
	public int readProc(Map<String, Object> map) {
		return this.bbsDao.readProc(map);
	}
	
	//게시글 삭제
	@Override
	public int deleteProc(int num) {
		return this.bbsDao.deleteProc(num);
	}
	
	//답변달기
	@Override
	public int replyProc(Map<String, Object> map) {
		return this.bbsDao.replyProc(map);
	}
	
}