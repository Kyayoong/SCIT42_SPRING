package net.softsociety.spring3.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
	String name; //변수명, 칼럼명 동일하게
	int age; 
}
