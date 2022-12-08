package pack.spring.first;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class BbsController {
	
	@Autowired
	BbsService bbsService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ServletContext context;
	
	private static String saveFolder =
			"D:\\openAPI\\git\\community\\Proj_First\\src\\main\\webapp\\resources\\images";
	private static int maxsize = 20 *1024 *1024;
		
	//글쓰기
	@PostMapping(value = "/writeProc")
	public ModelAndView write(HttpServletRequest request)  {
		System.out.println("글쓰기 처리 프로세스 시작");
		ModelAndView mav= new ModelAndView();
		String fileName = null;
		int fileSize = 0;
		String msg="글쓰기 실패/메인으로 돌아갑니다.",url="/";
		try {
			int max = this.bbsService.num();
			int ref = 1;
			ref = max + 1;
			File file = new File(saveFolder);
			if (!file.exists()) {
				file.mkdirs();
			}
			MultipartRequest multi = new MultipartRequest
					(request, saveFolder, maxsize, "UTF-8", new DefaultFileRenamePolicy());
			if(multi.getFilesystemName("fileName")!=null) {
				fileName = multi.getFilesystemName("fileName");
				fileSize = (int)multi.getFile("fileName").length();
			}
			String content = multi.getParameter("content");
			if(multi.getParameter("contentType").equalsIgnoreCase("TEXT")) {
				// ignoreCase, 대소문자 무시,    tExt == TEXT  => true
				content = UtilMgr.replace(content, "<", "&lt;");
                // a1,      a2,    a3
				// 입력값 가정 =>    ABC<p>가나다</p>
				// UtilMgr.replace( ) 실행 후 content에 저장되는 값  ABC&lt;p>가나다&lt;p>
			}
			
			Map<String, Object> map = new HashMap<>();
			map.put("uId", multi.getParameter("uId"));
			map.put("uName", multi.getParameter("uName"));
			map.put("subject", multi.getParameter("subject"));
			map.put("content", content);
			map.put("ref", ref);
			map.put("ip", multi.getParameter("ip"));
			map.put("fileName", fileName);
			map.put("fileSize", fileSize);
			int result = this.bbsService.write(map);
			if(result ==1) {
				msg="게시글 작성 완료!";
				url="/list";
			}
			mav.addObject("msg", msg);
			mav.addObject("url",url);
			mav.setViewName("/message/message");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mav;
	}	
	
	//게시글 수정
	@GetMapping(value = "/modifyProc")
	public ModelAndView modifyProc(@RequestParam Map<String, Object>map) {
		System.out.println("게시글 수정 시작");
		ModelAndView mav = new ModelAndView();
		int num = Integer.parseInt(map.get("num").toString());
		String msg = "수정 실패", url="#";
		int cnt = this.bbsService.readProc(map);
		if(cnt>0) {
			msg="수정 완료";
			url="/read?num="+num;
		}
		mav.addObject("msg",msg);
		mav.addObject("url",url);
		mav.setViewName("/message/message");
		return mav;
	}
	
	//게시글 삭제
	@GetMapping(value = "/deleteProc")
	public ModelAndView deleteProd(@RequestParam Map<String, Object>map) {
		System.out.println("게시글 수정 시작");
		ModelAndView mav = new ModelAndView();
		int num = Integer.parseInt(map.get("num").toString());
		String msg = "삭제 실패", url="#";
		int cnt = this.bbsService.deleteProc(num);
		if(cnt>0) {
			msg="삭제 완료";
			url="/list";
		}
		mav.addObject("msg",msg);
		mav.addObject("url",url);
		mav.setViewName("/message/message");
		return mav;
	}
	
	//다운로드받기
	@GetMapping(value = "/download")
	protected void renderMergedOutputModel(HttpServletResponse response,
			@RequestParam Map<String, Object>map) throws Exception {
		String fn =String.valueOf(map.get("fileName").toString());
		File file = new File(saveFolder+ File.separator +fn);
		if(file==null || !file.exists() || !file.canRead()) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(
	"<script>alert('파일이 존재하지 않거나 손상되었습니다.');history.back();</script>");
			return;
		}
		
		String fileName=new String(file.getName().getBytes("utf-8"),
				"8859_1");
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;filename="
				+fileName);
		
		OutputStream os = response.getOutputStream();
		FileInputStream fis=null;
		try {
			fis=new FileInputStream(file);
			FileCopyUtils.copy(fis, os);
		}finally {
			if(fis!=null) fis.close();
		}
	}
	
	@GetMapping(value = "/replyProc")
	public ModelAndView replyProc(@RequestParam Map<String, Object>map) {
		System.out.println("게시글 답변 작성 시작");
		ModelAndView mav = new ModelAndView();
		int cnt = this.bbsService.replyProc(map);
		
		String msg ="답글 작성 실패",url="#";
		if(cnt>0) {
			msg="답글 작성 완료";
			url="/list";
		}
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("/message/message");
		return mav;
	}
	
}