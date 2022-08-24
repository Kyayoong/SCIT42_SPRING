package net.softsociety.subject1.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderList {
	int order_num;
	String id;
	String name;
	String address;
	String p_name;
	int p_amount;
	int p_price;
	int num;
	
}
