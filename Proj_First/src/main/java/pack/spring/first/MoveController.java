package pack.spring.first;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MoveController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	BbsService bbsService;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;
	
	
	/* -------------------------------- MEMBER -------------------------------------- */
	
	
	//메인페이지 이동
	@GetMapping(value = "/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		String uId="";
		uId = (String)session.getAttribute("uId_Session");
		Map<String, Object> user = this.bbsService.selectId(uId);
		mav.addObject("user", user);
		mav.setViewName("/index");
		return mav;
	}
	
	//로그인페이지 이동
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginMember() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/login");
		return mav;
	}

	//회원 가입페이지 이동
	@RequestMapping(value = "/joinAgreement", method = RequestMethod.POST)
	public ModelAndView member(@RequestParam(required = false) String vCode) {
		System.out.println("회원 가입 페이지 진입");
		ModelAndView mav = new ModelAndView();
		mav.addObject("vCode", vCode);
		if(vCode.isEmpty()) {
			mav.setViewName("/member/joinAgreement");
		}else {
			mav.setViewName("/member/member");
		}
		return mav;
	}
	
	//약관 동의 페이지 이동
	@GetMapping(value = "/joinAgreement")
	public ModelAndView joinAgreement() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/joinAgreement");
		return mav;
	}
	
	//마이페이지 이동
	@GetMapping(value = "/myPage")
	public ModelAndView myPage() {
		ModelAndView mav = new ModelAndView();
		String uId = (String)session.getAttribute("uId_Session");
		Map<String, Object> user = this.bbsService.selectId(uId);
		mav.addObject("user", user);
		mav.setViewName("/member/myPage");
		return mav;
	}
	
	
	//정보수정 페이지 이동
	@GetMapping(value = "/memberMod")
	public ModelAndView memberMod()  {
		ModelAndView mav = new ModelAndView();
		System.out.println("회원수정 페이지 이동");
		String uId = (String)session.getAttribute("uId_Session");
		String hobby = request.getParameter("uHobby");
		System.out.println("취미생활="+hobby);
		System.out.println("정보수정uId ="+uId);
		Map<String, Object> map = this.memberService.selectMember(uId);
		
		mav.addObject("member", map);
		mav.setViewName("/member/memberMod");
		return mav;
	}
	
	
	// 아이디찾기로 이동 
	@GetMapping(value="/findId")
	public ModelAndView findId() {
		ModelAndView mav = new ModelAndView();
		System.out.println("아이디찾기 페이지 이동");
		mav.setViewName("/member/findId");
		return mav; 
	}
	
	// 비밀번호 찾기로 이동 
	@GetMapping(value = "/findPwd")
	public ModelAndView findPwd() {
		ModelAndView mav = new ModelAndView();
		System.out.println("비밀번호 찾기로 이동");
		mav.setViewName("/member/findPwd");
		return mav;
	}
	
	// 이메일확인 페이지로 이동 
	@GetMapping(value = "/checkEmail")
	public ModelAndView checkEmail() {
		ModelAndView mav = new ModelAndView();
		System.out.println("이메일인증 페이지로 이동");
		mav.setViewName("/member/checkEmail");
		return mav;
	}	
	
	
	
	/* -------------------------------- BBS -------------------------------------- */
	
	// 쓰기 페이지 이동
	@GetMapping(value = "/write")
	public ModelAndView selectId() {
		System.out.println("게시판 글쓰기 페이지 이동");
		
		ModelAndView mav = new ModelAndView();
		session = request.getSession();
		String ip = request.getRemoteAddr();
		String uId = (String)session.getAttribute("uId_Session");
		String msg = "세션이 만료되었습니다. 메인페이지로 돌아갑니다.", url="/";
		if(uId != null) {
			Map<String, Object> map = this.bbsService.selectId(uId);
			int sangtae = (int)map.get("sangtae");
			if(sangtae ==1) {
				mav.addObject("member",map);
				mav.addObject("ip",ip);
				mav.setViewName("/bbs/write");
			}else {
				msg = "정지나 휴면, 탈퇴처리된 계정입니다. 관리자에게 문의하세요";
				url ="/list";
				mav.addObject("msg",msg);
				mav.addObject("url",url);
				mav.setViewName("/message/message");
			}
		}else {
			mav.addObject("msg",msg);
			mav.addObject("url",url);
			mav.setViewName("/message/message");
		}
		return mav;
	}
	
	//게시판 이동
	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam(required = false) Map<String, Object>map)
		{
		ModelAndView mav = new ModelAndView();
		System.out.println("게시판 리스트 페이지 이동");
		
		PagingVO vo = null;
		String keyWord = "";
		String keyField ="";
		
		System.out.println(String.valueOf(map.get("keyWord")));
		
		if(String.valueOf(map.get("keyField")) !="") {
			keyField =String.valueOf(map.get("keyField"));
		}
		List<Map<String, Object>>list2 = null;
		if(String.valueOf(map.get("keyWord"))!="null") {
			keyWord = String.valueOf(map.get("keyWord"));
			System.out.println("대입된 키워드 " +String.valueOf(map.get("keyWord")));
		}
		
		int nowPage =1; 
		if(map.get("nowPage")!=null) {
			nowPage = Integer.parseInt((String)map.get("nowPage"));
		}
		if(keyWord.isEmpty()) {
			System.out.println("키워드 없음");
			list2 = this.bbsService.list();
			vo = new PagingVO(nowPage, list2.size(), 5, 5);
			mav.addObject("vo",vo);
			mav.addObject("list", list2);
			System.out.println("키워드 없을때 listsize="+list2.size());
		}
		if(!keyWord.isEmpty()) {
			if(keyField.equals("subject")) {
				list2 = this.bbsService.listSubject(keyWord);
				vo = new PagingVO(nowPage, list2.size(), 5, 5);
				System.out.println("키워드 subject listsize = "+list2.size());
			}else if(keyField.equals("uName")) {
				list2 = this.bbsService.listuName(keyWord);
				vo = new PagingVO(nowPage, list2.size(), 5, 5);
				System.out.println("키워드 uName listsize = "+list2.size());
			}else {
				list2 = this.bbsService.listContent(keyWord);
				vo = new PagingVO(nowPage, list2.size(), 5, 5);
				System.out.println("키워드 content listsize = "+list2.size());
			}
		}
		
		mav.addObject("map", map);
		mav.addObject("vo", vo);
		mav.addObject("list", list2);
		mav.setViewName("bbs/list.jsp?nowPage="+
				Integer.toString(nowPage)+"&keyField="+keyField+"&keyWord="+keyWord);
		return mav;
	}
	
	//게시글 보기 
	@GetMapping(value = "/read")
	public ModelAndView read(@RequestParam Map<String, Object>map) {
		System.out.println("게시글 보기 이동");
		ModelAndView mav = new ModelAndView();
		int num = Integer.parseInt(map.get("num").toString());
		System.out.println(num);
		int cnt = this.bbsService.cnt(num);
		int c_num = Integer.parseInt(map.get("num").toString());
		System.out.println(c_num);
		List<Map<String, Object>>list = this.bbsService.commentList(c_num);
		if(cnt>0) {
			Map<String, Object>result = this.bbsService.read(num);
			mav.addObject("result",result);
		}
		System.out.println(list.size());
		mav.addObject("list",list);
		mav.addObject("map", map);
		mav.setViewName("/bbs/read");
		return mav;		
	}
	
	//게시글 수정페이지 보기
	@GetMapping(value = "/modify")
	public ModelAndView modify(@RequestParam Map<String, Object>map) {
		System.out.println("게시글 수정 페이지 이동");
		ModelAndView mav = new ModelAndView();
		int num = Integer.parseInt(map.get("num").toString());
		System.out.println(num);
		Map<String, Object>result = this.bbsService.read(num);
		mav.addObject("result",result);
		mav.addObject("map",map);
		mav.setViewName("/bbs/modify");
		return mav;
	}
	
	//댓글 페이지 보기
	@GetMapping(value = "/reply")
	public ModelAndView reply(@RequestParam Map<String, Object>map) {
		System.out.println("댓글 페이지 이동");
		ModelAndView mav = new ModelAndView();
		session = request.getSession();
		String ip = request.getRemoteAddr();
		String uId = (String)session.getAttribute("uId_Session");
		int num = Integer.parseInt(map.get("num").toString());
		System.out.println(num);
		Map<String, Object>read = this.bbsService.read(num);
		String msg = "세션이 만료되었습니다. 메인페이지로 돌아갑니다.", url="/";
		if(uId != null) {
			Map<String, Object> result = this.bbsService.selectId(uId);
			mav.addObject("result", result);
			mav.addObject("ip",ip);
			mav.addObject("map", map);
			mav.addObject("read", read);
			mav.setViewName("/bbs/reply");
		}else {
			mav.addObject("msg",msg);
			mav.addObject("url",url);
			mav.setViewName("/message/message");
		}
		return mav;
	}
	
	/*------------------------------------------- admin ---------------------------------------------*/
	
	//관리자 페이지 이동
	@GetMapping(value = "/adminPage")
	public ModelAndView adminPage() {
		System.out.println("관리자 페이지 이동");
		ModelAndView mav = new ModelAndView();
		String uId = (String)session.getAttribute("uId_Session");
		Map<String, Object> user = this.bbsService.selectId(uId);
		mav.addObject("user", user);
		mav.setViewName("/admin/adminPage");
		return mav;
	}
	
	
	//관리자 페이지에서 회원관리 페이지 이동
	@GetMapping(value = "/memberMgr")
	public ModelAndView memberMgr(@RequestParam(required = false) Map<String, Object>map) {
		System.out.println("회원관리 페이지 이동");
		ModelAndView mav = new ModelAndView();
		PagingVO vo = null;
		String keyWord = "";
		String keyField ="";
		
		System.out.println(String.valueOf(map.get("keyWord")));
		
		if(String.valueOf(map.get("keyField")) !="") {
			keyField =String.valueOf(map.get("keyField"));
		}
		List<Map<String, Object>>list2 = null;
		if(String.valueOf(map.get("keyWord"))!="null") {
			keyWord = String.valueOf(map.get("keyWord"));
			System.out.println("대입된 키워드 " +String.valueOf(map.get("keyWord")));
		}
		
		int nowPage =1; 
		if(map.get("nowPage")!=null) {
			nowPage = Integer.parseInt((String)map.get("nowPage"));
		}
		if(keyWord.isEmpty()) {
			System.out.println("키워드 없음");
			list2 = this.adminService.memberList();
			vo = new PagingVO(nowPage, list2.size(), 5, 5);
			mav.addObject("vo",vo);
			mav.addObject("list", list2);
			System.out.println("키워드 없을때 listsize="+list2.size());
		}
		if(!keyWord.isEmpty()) {
			if(keyField.equals("uId")) {
				list2 = this.adminService.memberListuId(keyWord);
				vo = new PagingVO(nowPage, list2.size(), 5, 5);
				System.out.println("키워드 uId listsize = "+list2.size());
			}else if(keyField.equals("uName")) {
				list2 = this.adminService.memberListuName(keyWord);
				vo = new PagingVO(nowPage, list2.size(), 5, 5);
				System.out.println("키워드 uName listsize = "+list2.size());
			}
		}
		
		mav.addObject("map", map);
		mav.addObject("vo", vo);
		mav.addObject("list", list2);
		mav.setViewName("admin/memberMgr.jsp?nowPage="+
				Integer.toString(nowPage)+"&keyField="+keyField+"&keyWord="+keyWord);
		return mav;
	}
	
	//관리자 페이지에서 게시글 관리 페이지 이동
	@GetMapping(value = "/boardMgr")
	public ModelAndView boardMgr(@RequestParam(required = false) Map<String, Object>map) {
		System.out.println("게시글 관리 페이지 이동");
		ModelAndView mav = new ModelAndView();
		PagingVO vo = null;
		String keyWord = "";
		String keyField ="";
		
System.out.println(String.valueOf(map.get("keyWord")));
		
		if(String.valueOf(map.get("keyField")) !="") {
			keyField =String.valueOf(map.get("keyField"));
		}
		List<Map<String, Object>>list2 = null;
		if(String.valueOf(map.get("keyWord"))!="null") {
			keyWord = String.valueOf(map.get("keyWord"));
			System.out.println("대입된 키워드 " +String.valueOf(map.get("keyWord")));
		}
		
		int nowPage =1; 
		if(map.get("nowPage")!=null) {
			nowPage = Integer.parseInt((String)map.get("nowPage"));
		}
		if(keyWord.isEmpty()) {
			System.out.println("키워드 없음");
			list2 = this.bbsService.list();
			vo = new PagingVO(nowPage, list2.size(), 5, 5);
			mav.addObject("vo",vo);
			mav.addObject("list", list2);
			System.out.println("키워드 없을때 listsize="+list2.size());
		}
		if(!keyWord.isEmpty()) {
			if(keyField.equals("subject")) {
				list2 = this.bbsService.listSubject(keyWord);
				vo = new PagingVO(nowPage, list2.size(), 5, 5);
				System.out.println("키워드 subject listsize = "+list2.size());
			}else if(keyField.equals("uName")) {
				list2 = this.bbsService.listuName(keyWord);
				vo = new PagingVO(nowPage, list2.size(), 5, 5);
				System.out.println("키워드 uName listsize = "+list2.size());
			}else {
				list2 = this.bbsService.listContent(keyWord);
				vo = new PagingVO(nowPage, list2.size(), 5, 5);
				System.out.println("키워드 content listsize = "+list2.size());
			}
		}
		
		mav.addObject("map", map);
		mav.addObject("vo", vo);
		mav.addObject("list", list2);
		mav.setViewName("admin/boardMgr.jsp?nowPage="+
				Integer.toString(nowPage)+"&keyField="+keyField+"&keyWord="+keyWord);
		return mav;
	}
	
	//갤러리 게시판 보기
	@RequestMapping(value = "/imageGallery")
	public ModelAndView galleryList(@RequestParam(required = false) 
		Map<String, Object>map){
		ModelAndView mav = new ModelAndView();
		System.out.println("이미지 갤러리 리스트 페이지 이동");
		
		PagingVO vo = null;
		String keyWord = "";
		String keyField ="";
		
		System.out.println(String.valueOf(map.get("keyWord")));
		
		if(String.valueOf(map.get("keyField")) !="") {
			keyField =String.valueOf(map.get("keyField"));
		}
		List<Map<String, Object>>list2 = null;
		if(String.valueOf(map.get("keyWord"))!="null") {
			keyWord = String.valueOf(map.get("keyWord"));
			System.out.println("대입된 키워드 " +String.valueOf(map.get("keyWord")));
		}
		
		int nowPage =1; 
		if(map.get("nowPage")!=null) {
			nowPage = Integer.parseInt((String)map.get("nowPage"));
		}
		if(keyWord.isEmpty()) {
			System.out.println("키워드 없음");
			list2 = this.bbsService.list();
			vo = new PagingVO(nowPage, list2.size(), 5, 5);
			mav.addObject("vo",vo);
			mav.addObject("list", list2);
			System.out.println("키워드 없을때 listsize="+list2.size());
		}
		if(!keyWord.isEmpty()) {
			if(keyField.equals("subject")) {
				list2 = this.bbsService.listSubject(keyWord);
				vo = new PagingVO(nowPage, list2.size(), 5, 5);
				System.out.println("키워드 subject listsize = "+list2.size());
			}else if(keyField.equals("uName")) {
				list2 = this.bbsService.listuName(keyWord);
				vo = new PagingVO(nowPage, list2.size(), 5, 5);
				System.out.println("키워드 uName listsize = "+list2.size());
			}else {
				list2 = this.bbsService.listContent(keyWord);
				vo = new PagingVO(nowPage, list2.size(), 5, 5);
				System.out.println("키워드 content listsize = "+list2.size());
			}
		}
		
		mav.addObject("map", map);
		mav.addObject("vo", vo);
		mav.addObject("list", list2);
		mav.setViewName("bbs/imageGallery.jsp?nowPage="+
				Integer.toString(nowPage)+"&keyField="+keyField+"&keyWord="+keyWord);
		return mav;
	}
	
	
	
	
	
}
