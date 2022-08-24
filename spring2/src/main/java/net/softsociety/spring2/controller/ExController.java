package net.softsociety.spring2.controller;

import java.util.Calendar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExController {
	//계산 폼으로 이동
	@GetMapping("/ex/calc")
	public String calc() {
		return "exview/calc";
	}
	//계산 폼에서 입력한 값을 전달받아 계산하여 결과를 calcOutput.html에서 출력
	@PostMapping("/ex/calc") //위랑 같은 이름인데 되네...?? 위에는 get방식 아래는 post 방식이라 괜찮음.
	public String calc(String op, String num1, String num2, Model model) {
		System.out.println("전달된 값: " + op + ", " + num1 + ", " + num2);

		int res = 0, n1, n2;
		try {
			n1 = Integer.parseInt(num1);
			n2 = Integer.parseInt(num2);
			
			switch (op) {
				case "+" : res = n1 + n2; break;
				case "-" : res = n1 - n2; break;
				case "*" : res = n1 * n2; break;
				case "/" : res = n1 / n2; break;
				default: throw new Exception("연산자 오류");
			}
			model.addAttribute("num1", num1);
			model.addAttribute("num2", num2);
			model.addAttribute("op", op);
			model.addAttribute("res", res);
		}
		catch (Exception e) {
			e.printStackTrace();
			return "redirect:/ex/calc";	//예외 발생시 계산폼으로 다시 이동
		}
		
		return "/exview/calcOutput";
	}

	//개인정보 입력폼으로 이동
	@GetMapping("/ex/info")
	public String info() {
		return "exview/info";
	}
	//입력한 이름과 주민등록번호를 전달받아 처리 결과를 infoOutput.html에서 출력
	@PostMapping("/ex/info")
	public String info(String name, String num, Model model) {
		System.out.println(name + ", " + num);
		
		String str = null;
		char ch;
		int y, m, d, age;
		
		//현재 연도
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		
		//글자수
		if (num.length() != 14) {
			return "redirect:/ex/info";
		}
		//'-' 문자 확인
		if (num.indexOf('-') != 6) {
			return "redirect:/ex/info";
		}
		//성별
		ch = num.charAt(7);
		if ( ch < '1' || ch > '4') {
			return "redirect:/ex/info";
		}
		
		try {
			//생년월일
			y = Integer.parseInt(num.substring(0, 2));
			m = Integer.parseInt(num.substring(2, 4));
			d = Integer.parseInt(num.substring(4, 6));
			//성별
			str = ch == '1' || ch == '3' ? "남자" : "여자";
			//나이
			if (ch == '1' || ch == '2') {
				age = year - y - 1900;
			}
			else {
				age = year - y - 2000;
			}
		}
		catch (Exception e) {
			return "redirect:/ex/info";
		}
		
		model.addAttribute("name", name);
//		model.addAttribute("y", y);						//정수로 전달
		model.addAttribute("y", num.substring(0, 2));	//문자열로 전달
		model.addAttribute("m", m);
		model.addAttribute("d", d);
		model.addAttribute("age", age);
		model.addAttribute("str", str);
		
		return "exview/infoOutput";
	}

	

}
