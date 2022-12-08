package pack.spring.first;

import java.util.List;
import java.util.Map;

public interface BbsService {

	// 게시판 글쓸때 이름 보이기
	Map<String, Object> selectId(String uId);

	// 글 쓸때 MAXNUM(맨끝 글번호 몇번인지) 확인
	int num();

	// 글쓰기
	int write(Map<String, Object> map);

	// 리스트
	List<Map<String, Object>> list();

	// 이름으로 검색
	List<Map<String, Object>> listuName(String uName);

	// 제목으로 검색
	List<Map<String, Object>> listSubject(String subject);

	// 내용으로 검색
	List<Map<String, Object>> listContent(String content);
	
	// 게시글 읽기
	Map<String, Object> read(int num);
	
	// 게시글 카운트 증가
	int cnt(int num);
	
	//게시글 수정
	int readProc(Map<String, Object>map);
	
	//게시글 삭제
	int deleteProc(int num);
	
	//답변 달기
	int replyProc(Map<String, Object>map);
}