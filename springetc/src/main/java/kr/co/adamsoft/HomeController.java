package kr.co.adamsoft;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.adamsoft.service.EtcService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@GetMapping("/echo")
	public String echo() {
		return "echo";
	}
	
	@GetMapping("/chat")
	public String chat() {
		return "chat";
	}
	
	@Autowired
	private EtcService etcService;
	
	@GetMapping("/push")
	public void push(
			HttpServletRequest request, 
			HttpServletResponse response) {
		etcService.push(request, response);
	}
	
	@GetMapping("/proxy")
	public String proxy(
			HttpServletRequest request, 
			HttpServletResponse response,
			Model model) {
		String result = etcService.proxy(request, response);
		//데이터 저장
		//request.setAttribute("result", result);
		model.addAttribute("result", result);
		return "proxy";
		
	}
	
	@GetMapping("/mailsend")
	public String mailsend() {
		return "mailsend";
	}
	
	
	@PostMapping("/mailsending")
	public String mailsending(
			HttpServletRequest request, 
			HttpServletResponse response) {
		etcService.mailsend(request, response);
		return "redirect:/";
	}
}









