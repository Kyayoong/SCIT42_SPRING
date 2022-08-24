package net.softsociety.subject1.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.subject1.service.ShoppingService;
import net.softsociety.subject1.vo.ItemList;
import net.softsociety.subject1.vo.OrderList;

@Slf4j
@Controller
public class ShoppingController {
	//서비스 연결
	@Autowired
	ShoppingService service;
	
	//쇼핑목록 컨트롤러
	@GetMapping("shop")
	public String shop(Model model) {
		//전체 목록 담기
		ArrayList<ItemList> list = service.selectAllItem();
		
		//모델에 리스트 저장
		model.addAttribute("list", list);
		
		return "shoplist";
	}
	
	//상세정보 컨트롤러
	@PostMapping("detail")
	public String detail(ItemList detailForm, Model model) {
		log.debug("전달된 객체 : {}", detailForm);
		
		//폼 형태로 받아온 itemList vo (제품 번호만 들어있음)을 통해 해당 제품 찾기
		ItemList item = service.selectOneItem(detailForm);
		
		//찾아온 제품 정보를 모델에 저장
		model.addAttribute("item", item);
		
		return "details";
	}
	
	//장바구니 컨트롤러(홈->장바구니)
	@GetMapping("favorite")
	public String favorite(
			@CookieValue(name="id", defaultValue="없음") String id,
			Model model
			) {
		
		//해당 아이디의 장바구니 목록 가져오기
		ArrayList<OrderList> list = service.selectAllLikes(id);
		
		log.debug("dao에서 받아온 장바구니 리스트 : {}", list);
		
		//받아온 리스트 모델에 저장
		model.addAttribute("list", list);
		
		return "myList";
	}
	
	//상세페이지에서 담기 눌렀을 때의 컨트롤러
	@PostMapping("insertFav")
	public String insertFav(
			@CookieValue(name="id", defaultValue="없음") String id,
			@CookieValue(name="name", defaultValue="없음") String name,
			@CookieValue(name="address", defaultValue="없음") String address,
			ItemList insertForm
			) {
		//제품의 번호로 제품 vo 찾기
		ItemList product = service.selectOneItem(insertForm);
		
		//찾은 해당 제품의 vo와 쿠키의 내용을 합쳐 OrderList vo로 생성
		OrderList list = new OrderList(0, id, name, address, product.getP_name(), 
				product.getAmount(), product.getPrice(), product.getP_num());
		log.debug(list.toString());
		
		//새로 만든 OrderList vo를 테이블에 저장하기.
		int result = service.insertOrderList(list);
		
		log.debug("전달 여부 : {}", result);
		
		return "redirect:/favorite";
	}
	
	//장바구니 페이지에서 삭제버튼 눌렀을 때의 컨트롤러
	@PostMapping("deleteList")
	public String deleteList(int orderNum) {
		//버튼이 눌린 제품의 제품 번호와 누른 사람의 id가 담긴 OrderList vo로 삭제
		int result = service.deleteList(orderNum);
		if(result != 0) {
			log.debug("삭제됨");
		}
		return "redirect:/favorite";
	}
}
