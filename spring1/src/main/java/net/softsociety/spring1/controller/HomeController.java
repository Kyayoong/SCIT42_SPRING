//메인화면으로 이동하는 컨트롤러
package net.softsociety.spring1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller가 없으면 연결이 안됨... 해당 클래스가 컨트롤러라는 것을 알리고 미리 자동으로 생성할 것을 명령하는 것이다.
@Controller
public class HomeController {
	//메인 화면
	@GetMapping({"", "/"})
	public String home() {
		return "home";
	}
	//다른 루트에 대한 링크의 GetMapping은 이곳에 따로 만들어도되고 다른 클래스로 연결해도 된다.
}
