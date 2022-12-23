package pack.spring.first;

import java.util.List;
import java.util.Map;

public interface BbsService {

	// 게시판 글쓸때 이름 보이기
	Map<String, Object> selectId(String uId);

	// 글 쓸때 MAXNUM(맨끝 글번호 몇번인지) 확인
	int num();
	
	// 글 쓸때 MAXNUM(맨끝 글번호 몇번인지) 확인
	int commentnum();

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
	
	// 게시글 읽기(끼워넣기용)
	Map<String, Object> read2(int num);
	
	// 게시글 카운트 증가
	int cnt(int num);
	
	//게시글 수정
	int readProc(Map<String, Object>map);
	
	//게시글 삭제
	int deleteProc(int num);
	
	//답변 달기
	int replyProc(Map<String, Object>map);
	
	//답변 달고나서 글번호 끼워맞추기
	int replyProc2(Map<String, Object>map);
	
	//답변 달기위한 포스 구하기
	Map<String, Object> maxpos(Map<String, Object>map);
	
	//댓글달기
	int comment(Map<String, Object>map);
	
	//댓글 맥스포스 찾기
	Map<String, Object>maxcpos(Map<String, Object>map);
	
	//댓글 리스트보기
	List<Map<String, Object>> commentList(int c_num);
	
	//댓글 찾기
	Map<String, Object> searchComment(Map<String, Object>map);
	
	//댓글 수정하기
	int commentEdit(Map<String, Object>map);
	
	//댓글 삭제하기
	int commentDel(Map<String, Object>map); 
	
	//대댓글 작성하기
	int recomment(Map<String, Object>map);

	
}