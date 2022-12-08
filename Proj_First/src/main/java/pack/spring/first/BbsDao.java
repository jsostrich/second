package pack.spring.first;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BbsDao {
	
	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	//글쓰기 할때 회원 이름 보이게 만들기
	public Map<String, Object> selectId(String uId){
		return this.sessionTemplate.selectOne("bbs.selectId", uId);
	}
	
	//글 쓸때 MAXNUM(맨끝 글번호 몇번인지) 확인
	public int num(){
		int cnt = 0;
		if(this.sessionTemplate.selectOne("bbs.num")!=null) {
			cnt = this.sessionTemplate.selectOne("bbs.num");
		}
		return  cnt;
	}
	
	//글쓰기
	public int write(Map<String, Object>map){
		return this.sessionTemplate.insert("bbs.write",map);
	}
	
	//리스트
	public List<Map<String, Object>> list(){
		return this.sessionTemplate.selectList("bbs.list");
	}
	
	//이름으로 검색
	public List<Map<String, Object>> listuName(String uName){
		return this.sessionTemplate.selectList("bbs.listuName",uName);
	}
	
	//제목으로 검색
	public List<Map<String, Object>> listSubject(String subject){
		return this.sessionTemplate.selectList("bbs.listSubject",subject);
	}
	
	//내용으로 검색
	public List<Map<String, Object>> listContent(String content){
		return this.sessionTemplate.selectList("bbs.listContent",content);
	}
	
	//게시글 읽기
	public Map<String, Object> read(int num){
		return this.sessionTemplate.selectOne("bbs.read", num);
	}
	
	//게시글 조회수 증가
	public int cnt(int num) {
		return this.sessionTemplate.update("bbs.cnt", num);
	}
	
	//게시글 수정
	public int readProc(Map<String, Object>map) {
		return this.sessionTemplate.update("bbs.readProc", map);
	}
	
	//게시글 삭제
	public int deleteProc(int num) {
		return this.sessionTemplate.update("bbs.deleteProc", num);
	}
	
	//답변 달기
	public int replyProc(Map<String, Object>map) {
		return this.sessionTemplate.insert("bbs.replyProc",map);
	}
	
}