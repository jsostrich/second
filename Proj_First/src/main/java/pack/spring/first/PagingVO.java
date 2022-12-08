package pack.spring.first;

public class PagingVO { 
	private int nowPage; //현재페이지 수정 currentpage
	private int totalRecord; //총 레코드수 17
	private int numPerPage; //페이지당 보여질 레코드수 5 수정 pageSize
	private int totalPage; //총 페이지수 4
	private int pagePerBlock; //블럭당 보여질 페이지 수 10 수정
	private int pageStart; //블럭당 시작페이지 1,11,21,31... 수정 firstPage
	private int pageEnd; //블럭당 마지막 페이지 10,20,30,40.... 수정 lastPage
	private int curPos; //페이지당 시작 인덱스(list 내에서)
		//=>0, 5, 10, 15...
	private int num; // 페이지당 시작 글 번호
		//=>17, 12, 7...
	private int nowBlock;
	private int totalBlock;

	public PagingVO(int nowPage, int totalRecord, int numPerPage, int pagePerBlock){
		super();
		this.nowPage=nowPage;	  
		this.totalRecord=totalRecord;

		this.numPerPage=numPerPage;
		this.pagePerBlock = pagePerBlock;

		totalPage=(int)Math.ceil((float)totalRecord/numPerPage);
		nowBlock=(int)Math.ceil((double)nowPage/pagePerBlock);
		totalBlock = (int)Math.ceil((double)totalPage/pagePerBlock);
		pageStart= nowPage-((nowPage-1)%pagePerBlock);
		pageEnd = pageStart+(pagePerBlock-1);
		curPos=(nowPage-1)*numPerPage;
		num=totalRecord-curPos;
	}

	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	} 

	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPagePerBlock() {
		return pagePerBlock;
	}
	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}
	public int getPageStart() {
		return pageStart;
	}
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
	public int getPageEnd() {
		return pageEnd;
	}
	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}
	public int getCurPos() {
		return curPos;
	}
	public void setCurPos(int curPos) {
		this.curPos = curPos;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public int getNowBlock() {
		return nowBlock;
	}

	public void setNowBlock(int nowblock) {
		this.nowBlock = nowblock;
	}

	public int getTotalBlock() {
		return totalBlock;
	}

	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	} 
	
	public static String con(String path) {
		String str = null;
		
		try {
			str = new String(path.getBytes("8859_1"), "UTF-8");
			             // String 생성자가 매개변수를 2개 가질 때
			             // 1번째 매개변수는 입력받는 데이터(=외부파일)의 인코딩
			             // 2번째 매개변수는 해당 데이터(=외부파일)에 적용할 인코딩
			             // 결론! 관련 파일과 경로명이 깨지지 않도록 만듬
		} catch(Exception e) {
			System.out.println("파일 입출력 이슈 : " + e.getMessage());
		}
		
		
		return str;
	}
	
}
