package net.softsociety.subject1.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemList {
	int p_num;
	String p_name;
	int amount;
	int price;
}
