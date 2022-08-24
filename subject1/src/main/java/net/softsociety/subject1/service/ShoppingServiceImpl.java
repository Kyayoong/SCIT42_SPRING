package net.softsociety.subject1.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.subject1.dao.ShoppingDAO;
import net.softsociety.subject1.vo.ItemList;
import net.softsociety.subject1.vo.OrderList;


@Service
@Slf4j
@Transactional
public class ShoppingServiceImpl implements ShoppingService {
	
	@Autowired
	private ShoppingDAO shoppingDAO;
	//제품 목록 가져오기
	@Override
	public ArrayList<ItemList> selectAllItem() {
		ArrayList<ItemList> list = shoppingDAO.selectAllItem();
		log.debug(list.toString());
	
		return list;
	}
	

	//한 제품의 정보 가져오기
	@Override
	public ItemList selectOneItem(ItemList detailForm) {
		ItemList item = shoppingDAO.selectOneItem(detailForm);
		
		log.debug("DAO에서 받아온 객체 : {}", item);
		
		return item;
	}
	
	
	//장바구니에 저장하기
	@Override
	public int insertOrderList(OrderList list) {
		int result = shoppingDAO.insertOrderList(list);
		return result;
	}

	
	//장바구니 리스트 가져오기
	@Override
	public ArrayList<OrderList> selectAllLikes(String id) {
		ArrayList<OrderList> list = shoppingDAO.selectAllLikes(id);
		return list;
	}
	
	//장바구니 리스트에서 삭제하기
	@Override
	public int deleteList(int orderNum) {
		int result = shoppingDAO.deleteList(orderNum);
		return result;
	}

}
