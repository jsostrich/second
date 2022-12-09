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
	
	@GetMapping(value = "/memberDo")
	public ModelAndView memberDo(@RequestParam Map<String, Object>map) {
		ModelAndView mav = new ModelAndView();
		System.out.println("회원 정지기능 시작");
		
		String chkBox = (String)map.get("chkBox");
		String numArr [] = null;
		numArr = chkBox.split(",");
		
		int [] num = new int[numArr.length];
		for(int i =0;i<numArr.length;i++) {
			num[i]= Integer.parseInt(numArr[i]);
			
			int result = 
		}
		
		
		
		
		
		return mav;
	}
	
	
}