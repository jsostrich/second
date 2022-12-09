package pack.spring.first;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;
	
	//로그인 처리
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginMember(@RequestParam Map<String, Object> map) {
		System.out.println("로그인 프로세스 시작");
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map2 = this.memberService.loginMember(map); 
			String msg ="아이디나 비밀번호가 맞지 않습니다",url=""; 
			if(map2.get("count(*)").toString().equals("1")) {
				String uId = map.get("uId").toString();
				System.out.println("uId ="+uId);
				Map<String, Object>sang = this.memberService.selectMember(uId);
				int sangtae = (int)sang.get("sangtae");
				if(sangtae !=1) {
					msg = "정지나 휴면, 탈퇴처리된 계정입니다. 관리자에게 문의하세요";
					url ="/";
				}else {
					HttpSession session = request.getSession();
					session.setAttribute("uId_Session", uId);
					msg ="로그인 성공";
					url ="/";
				}
			}
		mav.addObject("msg",msg);
		mav.addObject("url", url);
		mav.setViewName("/message/message");
		return mav;
	}
	
	
	//회원 가입처리 기능
	@RequestMapping(value = "/memberProc", method = RequestMethod.POST)
	public ModelAndView insertMember(@RequestParam Map<String, Object> map) {
		System.out.println("회원 가입 프로세스 시작");
		
		ModelAndView mav = new ModelAndView();
		String email1 = map.get("uEmail_01").toString();
		String email2 = map.get("uEmail_02").toString();
		String uEmail = email1+"@"+email2;
		
		map.put("uEmail", uEmail);
		
		String[] hobby = {"0", "0", "0", "0", "0"}; 
		if(request.getParameterValues("uHobby") != null) {
			hobby = request.getParameterValues("uHobby");
		}
		String[] hobbyName = {"인터넷", "여행", "게임", "영화", "운동"};
		char[] hobbyCode = {'0', '0', '0', '0', '0'};
		for (int i=0; i<hobby.length; i++) {
			for(int j=0; j<hobbyName.length; j++) {
				if (hobby[i].equals(hobbyName[j])) {
					hobbyCode[j] = '1';
				}
			}
		}
		map.replace("uHobby", new String(hobbyCode));
		
		int result = this.memberService.insertMember(map);
		String msg="회원 가입 실패 ", url = "#";
		if(result ==1 ) {
			System.out.println("내가 입력한 uName = "+map.get("uName").toString());
			msg = "회원 가입 성공! 메인 페이지로 돌아갑니다.";
			url = "/";
		}
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("/message/message");
		return mav; 
	}
	
	//회원정보 수정처리
	@PostMapping(value = "/memberModProc")
	public ModelAndView updateMember(@RequestParam Map<String, Object> map) {
		System.out.println("회원정보 수정 프로세스 시작");
		
		ModelAndView mav = new ModelAndView();
		String email1 = map.get("uEmail_01").toString();
		String email2 = map.get("uEmail_02").toString();
		String uEmail = email1+"@"+email2;
		map.put("uEmail", uEmail);
		
		String[] hobby = request.getParameterValues("uHobby");
		String[] hobbyName = {"인터넷", "여행", "게임", "영화", "운동"};
		char[] hobbyCode = {'0', '0', '0', '0', '0'};
		for (int i=0; i<hobby.length; i++) {
			for(int j=0; j<hobbyName.length; j++) {
				if (hobby[i].equals(hobbyName[j])) {
					hobbyCode[j] = '1';
				}
			}
		}
		map.replace("uHobby", new String(hobbyCode));
		int result = this.memberService.updateMember(map);
		String msg="정보 수정 실패 ", url = "#";
		if(result ==1 ) {
			System.out.println("내가 입력한 uName = "+map.get("uName").toString());
			msg = "정보 수정 성공! 메인 페이지로 돌아갑니다.";
			url = "/";
		}
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("/message/message");
		return mav; 
	}
	
	//중복아이디 체크
	@RequestMapping(value = "/idCheck",method = RequestMethod.GET)
	public ModelAndView checkId(@RequestParam Map<String, Object>map) {
		System.out.println("로그인아이디 중복처리 프로세스 시작");
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object>map2 = this.memberService.checkId(map);
		String count =  map2.get("count(*)").toString();
		String uId = request.getParameter("uId");
		
		if(count.equals("0")) {
			mav.addObject("uId", uId);
			mav.addObject("msg", "는 사용가능합니다.");
			mav.addObject("result","사용하기");
		}else {
			mav.addObject("uId", uId);
			mav.addObject("msg", "는 이미존재하는 아이디입니다.");
			mav.addObject("result", "재입력");
		}
		mav.setViewName("/member/idCheck");
		return mav;
	}
	
	//우편번호 검색창 이동
	@RequestMapping(value = "/zipCheck", method = RequestMethod.GET)
	public ModelAndView zipcodeRead() {
		System.out.println("우편번호 찾기 화면 오픈");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/zipCheck");
		return mav;
	}
	
	//우편번호처리
	@GetMapping(value = "/zipCheckOk")
	public ModelAndView zipcodeRead(@RequestParam Map<String, Object>map) {
		System.out.println("우편번호 찾기 프로세스 시작");
		ModelAndView mav = new ModelAndView();
		List<Map<String, Object>>list = this.memberService.zipcodeRead(map);
		String area3 = map.get("area3").toString();
		mav.addObject("zipList",list);
		mav.addObject("a3",area3);
		mav.setViewName("/member/zipCheck");
		return mav;
	}
	
	//로그아웃 처리
	@GetMapping(value = "/logout")
	public ModelAndView logout() {
		System.out.println("로그 아웃 프로세스 시작");
		ModelAndView mav = new ModelAndView();
		String msg="로그아웃 성공",url="/";
		
		session.invalidate();
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("/message/message");
		return mav;
	}
	
	
	// 회원탈퇴 처리
	@PostMapping(value = "/memberQuitProc")
	public ModelAndView memberQuit(@RequestParam Map<String, Object> map) {
		System.out.println("회원탈퇴 프로세스 시작");
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map2 = map;
		session = request.getSession();
		String uId = session.getAttribute("uId_Session").toString();
		map2.replace("uId", uId);
		int cnt=this.memberService.memberQuit(map2);
		if(cnt==1) {
			String msg="탈퇴되었습니다", url="/";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			session.invalidate();
			
		}else {
			String msg="실패!	", url="/memberQuit";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			
		}
		mav.setViewName("/message/message");
		return mav;
		
	}
	
	@GetMapping(value = "/memberQuit")
	public ModelAndView quitview() {
		System.out.println("탈퇴 페이지 이동");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/memberQuit");
		return mav;
	}
	
	
}
