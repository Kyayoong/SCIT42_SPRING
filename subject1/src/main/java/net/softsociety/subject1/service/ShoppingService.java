package net.softsociety.subject1.service;

import java.util.ArrayList;

import net.softsociety.subject1.vo.ItemList;
import net.softsociety.subject1.vo.OrderList;

public interface ShoppingService {
	//제품 목록 가져오기
	public ArrayList<ItemList> selectAllItem();
	
	//한 제품의 정보 가져오기
	public ItemList selectOneItem(ItemList detailForm);
	
	//장바구니에 저장하기
	public int insertOrderList(OrderList list);
	
	//장바구니 리스트 가져오기
	public ArrayList<OrderList> selectAllLikes(String id);
	
	//장바구니 리스트에서 삭제하기
	public int deleteList(int orderNum);
	
}
