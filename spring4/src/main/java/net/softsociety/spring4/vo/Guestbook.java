package net.softsociety.spring4.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guestbook {
	int num;
	String name;
	String password;
	String message;
	Date inputDate;
}
