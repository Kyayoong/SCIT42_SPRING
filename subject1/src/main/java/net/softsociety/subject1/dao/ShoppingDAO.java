package net.softsociety.subject1.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.subject1.vo.ItemList;
import net.softsociety.subject1.vo.OrderList;

@Mapper
public interface ShoppingDAO {
	//제품 목록 가져오기
	ArrayList<ItemList> selectAllItem();
	
	//한 제품의 정보 가져오기
	ItemList selectOneItem(ItemList detailForm);
	
	//장바구니에 저장하기
	int insertOrderList(OrderList list);
	
	//장바구니 리스트 가져오기
	ArrayList<OrderList> selectAllLikes(String usrid);
	
	//장바구니 리스트에서 삭제하기
	int deleteList(int orderNum);
}

