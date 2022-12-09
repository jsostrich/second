package pack.spring.first;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	
	@Autowired
	BbsService bbsService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	AdminService adminService;
	
	@GetMapping(value = "/memberDo")
	public ModelAndView memberDo(@RequestParam Map<String, Object>map) {
		ModelAndView mav = new ModelAndView();
		System.out.println("회원 정지기능 시작");
		
		String chkBox = (String)map.get("chkBox");
		String numArr [] = null;
		numArr = chkBox.split(",");
		
		String msg="처리 완료",url="/admin/memberMgr";
		int [] num = new int[numArr.length];
		for(int i =0;i<numArr.length;i++) {
			num[i]= Integer.parseInt(numArr[i]);
			
			int result = this.adminService.memberStop(num[i]);
			
			if(result==0) {
				msg="처리 실패 / 처리실패한 회원 번호 기준 :"+num[i];
				break;
			}
		}
		mav.addObject("msg",msg);
		mav.addObject("url", url);
		mav.setViewName("/message/message");
		return mav;
	}
	
	
}