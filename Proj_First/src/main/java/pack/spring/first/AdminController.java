package pack.spring.first;

import java.util.List;
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
	
	@GetMapping(value = "/do")
	public ModelAndView memberListuName(@RequestParam Map<String, Object>map) {
		System.out.println("회원 정지 기능 시작");
		ModelAndView mav = new ModelAndView();
		
		String numArr [] = null;
		String num  = (String)map.get("chkBox");
		numArr = num.split(",");
		
		for(int i =0;i<numArr.length;i++) {
			mav.addObject("num"+i,numArr[i]);
		}
		mav.setViewName("admin/test");
		return mav;
	}
	
	
	
}